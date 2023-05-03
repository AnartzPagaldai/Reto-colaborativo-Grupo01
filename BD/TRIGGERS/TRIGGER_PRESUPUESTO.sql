CREATE OR REPLACE TRIGGER trg_comprobar_presupuesto
BEFORE INSERT OR UPDATE ON CONTRATOS_JUGADORES
FOR EACH ROW
DECLARE
    v_presupuesto_equipo EQUIPOS.PRESUPUESTO_ANUAL%TYPE;
    v_COD EQUIPOS.ID%TYPE;
    v_sueldo_jugadores CONTRATOS_JUGADORES.SUELDO%TYPE;
    v_sueldo_personal CONTRATOS_PERSONAL.SUELDO%TYPE;
BEGIN
    -- OBTENER EL CODIGO DEL EQUIPO
    SELECT ID INTO V_COD 
    FROM EQUIPOS 
    WHERE ID= :NEW.ID_EQUIPO;

    -- Obtener el presupuesto del equipo
    SELECT PRESUPUESTO_ANUAL INTO v_presupuesto_equipo
    FROM EQUIPOS
    WHERE ID = :new.ID_EQUIPO;
    
    -- Obtener el sueldo total de los jugadores del equipo
    SELECT SUM(SUELDO) INTO v_sueldo_jugadores
    FROM CONTRATOS_JUGADORES
    WHERE ID_EQUIPO = :new.ID_EQUIPO;
    
    -- Obtener el sueldo total del personal del equipo
    SELECT SUM(SUELDO) INTO v_sueldo_personal
    FROM CONTRATOS_PERSONAL
    WHERE ID_EQUIPO = :new.ID_EQUIPO;
    
    -- Comprobar que el presupuesto es mayor que el sueldo total
    IF v_presupuesto_equipo < (v_sueldo_jugadores + v_sueldo_personal) THEN
        RAISE_APPLICATION_ERROR(-29875, 'El presupuesto del equipo ' || V_COD || ' no es suficiente para pagar a todos los jugadores y personal.');
    END IF;
END;