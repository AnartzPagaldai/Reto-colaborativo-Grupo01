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

SELECT COUNT(CJ.ID_EQUIPO) FROM CONTRATOS_JUGADORES CJ, JUGADORES J WHERE ( ( J.TIPO='WILD-CARD') AND CJ.ID_JUGADOR = J.ID) GROUP BY ID_EQUIPO;

SELECT COUNT(J.TIPO), CJ.ID_EQUIPO FROM CONTRATOS_JUGADORES CJ, JUGADORES J WHERE ( ( J.TIPO='DRAFT') AND CJ.ID_JUGADOR = J.ID) GROUP BY ID_EQUIPO ORDER BY ID_EQUIPO ASC;

