CREATE OR REPLACE TRIGGER control_presupuesto
FOR INSERT OR UPDATE ON CONTRATOS_JUGADORES
COMPOUND TRIGGER
NEWIDEQUIPO CONTRATOS_JUGADORES.ID_EQUIPO%TYPE:=NULL;
BEFORE EACH ROW IS
BEGIN
NEWIDEQUIPO:=:NEW.ID_EQUIPO;
END BEFORE EACH ROW;
AFTER STATEMENT IS
    presupuesto_equipo NUMBER;
    presupuesto_jugadores NUMBER;
BEGIN
    -- Obtener el presupuesto anual del equipo
    SELECT presupuesto_anual INTO presupuesto_equipo FROM equipos WHERE id = NEWIDEQUIPO;
    
    -- Obtener el presupuesto total de los jugadores del equipo
    SELECT SUM(SUELDO) INTO presupuesto_jugadores FROM CONTRATOS_JUGADORES WHERE id_equipo = NEWIDEQUIPO;
    
    -- Comprobar si el presupuesto total de los jugadores supera el presupuesto anual del equipo
    IF presupuesto_jugadores > presupuesto_equipo THEN
        -- Si el presupuesto se supera, lanzar una excepción y cancelar la operación
                RAISE_APPLICATION_ERROR(-20001, 'En el equipo ' || NEWIDEQUIPO || ' el presupuesto total ('|| presupuesto_jugadores || '€) del personal no puede superar el presupuesto anual del equipo: ' || presupuesto_equipo || '€');

    END IF;
    END AFTER STATEMENT;
END control_presupuesto;

/*
INSERT INTO EQUIPOS (NOMBRE, PRESUPUESTO_ANUAL)
VALUES('MANOLO FC', 1)

UPDATE CONTRATOS_JUGADORES
SET ID_EQUIPO=18
WHERE ID_EQUIPO=3;

Error en la línea de comandos : 32 Columna : 8
Informe de error -
Error SQL: ORA-20001: En el equipo 18 el presupuesto total (420500000€) del personal no puede superar el presupuesto anual del equipo: 1€
ORA-06512: en "SYSTEM.CONTROL_PRESUPUESTO", línea 20
ORA-04088: error durante la ejecución del disparador 'SYSTEM.CONTROL_PRESUPUESTO'
*/
