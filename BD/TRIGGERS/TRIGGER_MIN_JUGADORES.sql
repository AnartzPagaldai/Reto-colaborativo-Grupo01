CREATE OR REPLACE TRIGGER trg_comprobar_min_jugadores
before INSERT OR UPDATE ON SPLIT
FOR EACH ROW
DECLARE
    v_num_equipos NUMBER(2);
    v_min_jugadores NUMBER(2) :=8;
    v_num_jugadores NUMBER(2);
BEGIN
    -- Obtener el número de equipos
    SELECT COUNT(*) INTO v_num_equipos FROM EQUIPOS;
    
    -- Comprobar el número mínimo de jugadores en cada equipo
    FOR i IN 1..v_num_equipos LOOP
        SELECT COUNT(CJ.ID_EQUIPO) INTO v_num_jugadores FROM CONTRATOS_JUGADORES CJ, JUGADORES J 
        WHERE ( (CJ.ID_EQUIPO = i AND J.TIPO='DRAFT') AND CJ.ID_JUGADOR = J.ID);
        IF v_num_jugadores < v_min_jugadores THEN
            RAISE_APPLICATION_ERROR(-20001, 'El equipo ' || i || ' no tiene el número mínimo de jugadores DRAFT (' || v_min_jugadores || ')');
        END IF;
    END LOOP;
END trg_comprobar_min_jugadores;

/*
DELETE CONTRATOS_JUGADORES
WHERE DORSAL='1' AND ID_EQUIPO=1;

INSERT INTO SPLIT (ANIO, TIPO, ESTADO) VALUES (TO_DATE('04/10/2023',' DD/MM/YYYY'), 'VERANO', 'ABIERTO');

Error que empieza en la línea: 33 del comando -
INSERT INTO SPLIT (ANIO, TIPO, ESTADO) VALUES (TO_DATE('04/10/2023',' DD/MM/YYYY'), 'VERANO', 'ABIERTO')
Error en la línea de comandos : 33 Columna : 69
Informe de error -
Error SQL: ORA-20001: El equipo 1 no tiene el número mínimo de jugadores DRAFT (8)
ORA-06512: en "SYSTEM.TRG_COMPROBAR_MIN_JUGADORES", línea 14
ORA-04088: error durante la ejecución del disparador 'SYSTEM.TRG_COMPROBAR_MIN_JUGADORES'
*/