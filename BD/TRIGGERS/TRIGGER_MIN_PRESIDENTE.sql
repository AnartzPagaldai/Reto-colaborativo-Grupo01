CREATE OR REPLACE TRIGGER trg_comprobar_min_personal_wc
before INSERT OR UPDATE ON SPLIT
FOR EACH ROW
DECLARE
    v_num_equipos NUMBER(2);
    v_min_personal NUMBER(2) :=1;
    v_num_personal NUMBER(2);
BEGIN
    -- Obtener el número de equipos
    SELECT COUNT(*) INTO v_num_equipos FROM EQUIPOS;
    
    -- Comprobar el número mínimo de jugadores en cada equipo
    FOR i IN 1..v_num_equipos LOOP
        SELECT COUNT(*) INTO v_num_personal FROM CONTRATOS_PERSONAL CP, PERSONALES P WHERE (CP.ID_EQUIPO = i AND P.OFICIO='PRESIDENTE') AND cp.id_personal = P.ID;
        IF v_num_personal < v_min_personal THEN
            RAISE_APPLICATION_ERROR(-20001, 'El equipo ' || i || ' no tiene presidente (' || v_min_personal || ')');
        END IF;
    END LOOP;
END trg_comprobar_min_personal_wc;



/*

DELETE FROM CONTRATOS_PERSONAL WHERE ID_EQUIPO = 1;

2 filas eliminado


INSERT INTO SPLIT (ANIO, ESTADO, TIPO) VALUES(TO_DATE('03/10/2023', 'DD/MM/YYYY'), 'ABIERTO', 'VERANO')


Error que empieza en la línea: 1 del comando -
INSERT INTO SPLIT (ANIO, ESTADO, TIPO) VALUES(TO_DATE('03/10/2023', 'DD/MM/YYYY'), 'ABIERTO', 'VERANO')
Error en la línea de comandos : 1 Columna : 13
Informe de error -
Error SQL: ORA-20001: El equipo 1 no tiene presidente (1)
ORA-06512: en "SYSTEM.TRG_COMPROBAR_MIN_PERSONAL_WC", línea 13
ORA-04088: error durante la ejecución del disparador 'SYSTEM.TRG_COMPROBAR_MIN_PERSONAL_WC'


*/