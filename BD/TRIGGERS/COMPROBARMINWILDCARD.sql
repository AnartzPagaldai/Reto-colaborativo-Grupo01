CREATE OR REPLACE TRIGGER trg_comprobar_min_jugadores_wc
before INSERT OR UPDATE ON SPLIT
FOR EACH ROW
DECLARE
    v_num_equipos NUMBER(2);
    v_min_jugadores NUMBER(2) :=2;
    v_num_jugadores NUMBER(2);
BEGIN
    -- Obtener el número de equipos
    SELECT COUNT(*) INTO v_num_equipos FROM EQUIPOS;
    
    -- Comprobar el número mínimo de jugadores en cada equipo
    FOR i IN 1..v_num_equipos LOOP
        SELECT COUNT(CJ.ID_EQUIPO) INTO v_num_jugadores FROM CONTRATOS_JUGADORES CJ, JUGADORES J WHERE ( (CJ.ID_EQUIPO = i AND J.TIPO='WILD-CARD') AND CJ.ID_JUGADOR = J.ID);
        IF v_num_jugadores < v_min_jugadores THEN
            RAISE_APPLICATION_ERROR(-20001, 'El equipo ' || i || ' no tiene el número mínimo de jugadores WILD-CARD (' || v_min_jugadores || ')');
        END IF;
    END LOOP;
END trg_comprobar_min_jugadores_wc;

/*
SELECT COUNT(CJ.ID_EQUIPO), cj.id_equipo FROM CONTRATOS_JUGADORES CJ, JUGADORES J WHERE (J.TIPO='WILD-CARD' and CJ.ID_JUGADOR = J.ID) group by cj.id_equipo order by id_equipo asc;

COUNT(CJ.ID_EQUIPO)  ID_EQUIPO
------------------- ----------
                  1          1
                  1          2
                  1          4
                  2          5
                  2          6
                  1          7
                  1          9
                  1         10
                  2         11
                  2         1
                  
                  
INSERT INTO SPLIT (ANIO, TIPO, ESTADO) VALUES (TO_DATE('04/10/2023',' DD/MM/YYYY'), 'VERANO', 'ABIERTO');

Error que empieza en la línea: 1 del comando :
INSERT INTO SPLIT (ANIO, TIPO, ESTADO) VALUES (TO_DATE('04/10/2023',' DD/MM/YYYY'), 'VERANO', 'ABIERTO')
Informe de error -
ORA-20001: El equipo 1 no tiene el número mínimo de jugadores WILD-CARD (2)
ORA-06512: en "EQDAW01.TRG_COMPROBAR_MIN_JUGADORES_WC", línea 13
ORA-04088: error durante la ejecución del disparador 'EQDAW01.TRG_COMPROBAR_MIN_JUGADORES_WC'

*/
DROP TRIGGER trg_comprobar_min_jugadores_wc