CREATE OR REPLACE TRIGGER control_presupuesto_personal
FOR INSERT OR UPDATE ON CONTRATOS_PERSONAL
COMPOUND TRIGGER
NEWIDEQUIPO CONTRATOS_PERSONAL.ID_EQUIPO%TYPE:=NULL;
BEFORE EACH ROW IS
BEGIN
NEWIDEQUIPO:=:NEW.ID_EQUIPO;
END BEFORE EACH ROW;
AFTER STATEMENT IS
    presupuesto_equipo NUMBER;
    presupuesto_personal NUMBER;
BEGIN
    -- Obtener el presupuesto anual del equipo
    SELECT presupuesto_anual INTO presupuesto_equipo FROM equipos WHERE id = NEWIDEQUIPO;
    
    -- Obtener el presupuesto total de los jugadores del equipo
    SELECT SUM(SUELDO) INTO presupuesto_personal FROM CONTRATOS_PERSONAL WHERE id_equipo = NEWIDEQUIPO;
    
    -- Comprobar si el presupuesto total de los jugadores supera el presupuesto anual del equipo
    IF presupuesto_personal > presupuesto_equipo THEN
        -- Si el presupuesto se supera, lanzar una excepci�n y cancelar la operaci�n
        RAISE_APPLICATION_ERROR(-20001, 'En el equipo ' || NEWIDEQUIPO || ' el presupuesto total ('|| presupuesto_personal || '�) del personal no puede superar el presupuesto anual del equipo: ' || presupuesto_equipo || '�');
    END IF;
    END AFTER STATEMENT;
END control_presupuesto_personal;
/*
INSERT INTO EQUIPOS (NOMBRE, PRESUPUESTO_ANUAL)
VALUES('MANOLO FC', 1);

UPDATE CONTRATOS_PERSONAL
SET ID_EQUIPO=18
WHERE ID_EQUIPO=3;

Error en la l�nea de comandos : 30 Columna : 8
Informe de error -
Error SQL: ORA-20001: En el equipo 18 el presupuesto total (30000000�) del personal no puede superar el presupuesto anual del equipo: 1�
ORA-06512: en "SYSTEM.CONTROL_PRESUPUESTO_PERSONAL", l�nea 20
ORA-04088: error durante la ejecuci�n del disparador 'SYSTEM.CONTROL_PRESUPUESTO_PERSONAL'
*/

