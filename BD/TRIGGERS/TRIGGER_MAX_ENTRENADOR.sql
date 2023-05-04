CREATE OR REPLACE TRIGGER MAX_NUM_ENTRENADOR
FOR INSERT OR UPDATE OF ID_EQUIPO ON CONTRATOS_PERSONAL
COMPOUND TRIGGER
NEWIDEQUIPO CONTRATOS_JUGADORES.ID_EQUIPO%TYPE:=NULL;
BEFORE EACH ROW IS
BEGIN
NEWIDEQUIPO:=:NEW.ID_EQUIPO;
END BEFORE EACH ROW;
AFTER STATEMENT IS
    V_NUM NUMBER:=0;
    MENSAJE VARCHAR2(100):=CONCAT ('SOLO PUEDE HABER UN ENTRENADOR EN EL EQUIPO', NEWIDEQUIPO);
BEGIN
SELECT COUNT(CJ.ID_PERSONAL) INTO V_NUM
    FROM CONTRATOS_PERSONAL CJ, PERSONALES P
    WHERE CJ.ID_EQUIPO = NEWIDEQUIPO AND P.ID=cj.id_personal AND P.OFICIO='ENTRENADOR';
    IF V_NUM > 1
        THEN 
        RAISE_APPLICATION_ERROR(-20001, MENSAJE);
    END IF;
    END AFTER STATEMENT;
END MAX_NUM_ENTRENADOR;

/*
SELECT * FROM CONTRATOS_PERSONAL ORDER BY ID_EQUIPO;

        ID ID_PERSONAL  ID_EQUIPO FECHA_IN FECHA_FI     SUELDO
---------- ----------- ---------- -------- -------- ----------
        12          12          7 01/01/23            10500000
         1           1          8 01/01/23            15000000
         2           2          8 01/01/23            10000000
        17          17          9 01/01/23            10000000
        18          18          9 01/01/23            22500000
        24          24         10 01/01/23            10500000
        23          23         10 01/01/23            10500000
        15          15         11 01/01/23            10500000
        16          16         11 01/01/23            10000000
         7           7         12 01/01/23            10500000
         8           8         12 01/01/23            22500000
         
UPDATE CONTRATOS_PERSONAL set id_equipo = 5 where id_equipo = 7

Error que empieza en la línea: 43 del comando -
UPDATE CONTRATOS_PERSONAL set id_equipo = 5 where id_equipo = 7
Error en la línea de comandos : 43 Columna : 8
Informe de error -
Error SQL: ORA-20001: NUMERO MAXIMO DE PERSONAL (2) EN EL EQUIPO 5
ORA-06512: en "SYSTEM.MAX_NUM_PERSONAL", línea 16
ORA-04088: error durante la ejecución del disparador 'SYSTEM.MAX_NUM_PERSONAL'
*/