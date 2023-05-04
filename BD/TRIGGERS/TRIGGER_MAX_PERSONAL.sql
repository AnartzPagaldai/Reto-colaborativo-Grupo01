CREATE OR REPLACE TRIGGER MAX_NUM_PERSONAL
FOR INSERT OR UPDATE OF ID_EQUIPO ON CONTRATOS_PERSONAL
COMPOUND TRIGGER
NEWIDEQUIPO CONTRATOS_JUGADORES.ID_EQUIPO%TYPE:=NULL;
BEFORE EACH ROW IS
BEGIN
NEWIDEQUIPO:=:NEW.ID_EQUIPO;
END BEFORE EACH ROW;
AFTER STATEMENT IS
    V_NUM NUMBER:=0;
    MENSAJE VARCHAR2(100):=CONCAT ('NUMERO MAXIMO DE PERSONAL (2) EN EL EQUIPO ', NEWIDEQUIPO);
BEGIN
SELECT COUNT(*) INTO V_NUM
    FROM CONTRATOS_PERSONAL
    WHERE ID_EQUIPO = NEWIDEQUIPO;
    IF V_NUM > 2
        THEN 
        RAISE_APPLICATION_ERROR(-20001, MENSAJE);
    END IF;
    END AFTER STATEMENT;
END MAX_NUM_PERSONAL;

/*
select * from contratos_personal 


        ID ID_PERSONAL  ID_EQUIPO FECHA_IN FECHA_FI     SUELDO
---------- ----------- ---------- -------- -------- ----------
         1           1          8 01/01/23            15000000
         2           2          8 01/01/23            10000000
         3           3          6 01/01/23            15000000
         4           4          6 01/01/23            10500000
         5           5          3 01/01/23            15000000
         6           6          3 01/01/23            15000000
         7           7         12 01/01/23            10500000
         8           8         12 01/01/23            22500000
         9           9          4 01/01/23            10000000
        10          10          4 01/01/23            22500000
        11          11          7 01/01/23            15000000

        ID ID_PERSONAL  ID_EQUIPO FECHA_IN FECHA_FI     SUELDO
---------- ----------- ---------- -------- -------- ----------
        12          12          7 01/01/23            10500000
        13          13          5 01/01/23            10000000
        14          14          5 01/01/23            22500000
        15          15         11 01/01/23            10500000
        16          16         11 01/01/23            10000000
        17          17          9 01/01/23            10000000
        18          18          9 01/01/23            22500000
        21          21          2 01/01/23            10500000
        22          22          2 01/01/23            10500000
        23          23         10 01/01/23            10500000
        24          24         10 01/01/23            10500000
        
      Error que empieza en la línea: 59 del comando -
update contratos_personal set id_equipo = 4 where id_equipo = 9
Error en la línea de comandos : 59 Columna : 8
Informe de error -
Error SQL: ORA-20001: NUMERO MAXIMO DE PERSONAL (2) EN EL EQUIPO 4
ORA-06512: en "SYSTEM.MAX_NUM_PERSONAL", línea 16
ORA-04088: error durante la ejecución del disparador 'SYSTEM.MAX_NUM_PERSONAL'  
        
*/