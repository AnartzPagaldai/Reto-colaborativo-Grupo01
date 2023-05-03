CREATE OR REPLACE TRIGGER trg_comprobar_min_jugadores_wc
before INSERT OR UPDATE ON SPLIT
FOR EACH ROW
DECLARE
    v_num_equipos NUMBER(2);
    v_min_jugadores NUMBER(2) :=2;
    v_num_jugadores NUMBER(2);
BEGIN
    -- Obtener el n�mero de equipos
    SELECT COUNT(*) INTO v_num_equipos FROM EQUIPOS;
    
    -- Comprobar el n�mero m�nimo de jugadores en cada equipo
    FOR i IN 1..v_num_equipos LOOP
        SELECT COUNT(*) INTO v_num_jugadores FROM CONTRATOS_JUGADORES WHERE ID_EQUIPO = i AND TIPO='WILD-CARD';
        IF v_num_jugadores < v_min_jugadores THEN
            RAISE_APPLICATION_ERROR(-20001, 'El equipo ' || i || ' no tiene el n�mero m�nimo de jugadores WILD-CARD (' || v_min_jugadores || ')');
        END IF;
    END LOOP;
END trg_comprobar_min_jugadores_wc;


SELECT * FROM CONTRATOS_PERSONAL;