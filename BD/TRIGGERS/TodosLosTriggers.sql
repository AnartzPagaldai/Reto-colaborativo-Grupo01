drop trigger TRG_NO_DUPLICATE_DORSAL;
drop trigger control_presupuesto_personal;
drop trigger control_presupuesto;
drop trigger trg_comprobar_min_personal_wc;
drop trigger trg_comprobar_min_jugadores;
drop trigger MAX_NUM_PRESIDENTE;
drop trigger MAX_NUM_PERSONAL;
drop trigger MAX_NUM_JUGADORES;
drop trigger MAX_NUM_ENTRENADOR;
drop trigger JUGADORES_JORNADA_TRG;
drop trigger EQUIPOS_JORNADA_TRG;
drop trigger trg_comprobar_min_jugadores_wc;


CREATE OR REPLACE TRIGGER TRG_NO_DUPLICATE_DORSAL
FOR INSERT OR UPDATE OF DORSAL ON CONTRATOS_JUGADORES
COMPOUND TRIGGER
  NEWIDEQUIPO CONTRATOS_JUGADORES.ID_EQUIPO%TYPE;
  NEWDORSAL CONTRATOS_JUGADORES.DORSAL%TYPE;
  BEFORE EACH ROW IS 
  BEGIN
        NEWIDEQUIPO:=:NEW.ID_EQUIPO;
        NEWDORSAL:=:NEW.DORSAL;
  END BEFORE EACH ROW;
  
  AFTER STATEMENT IS
  V_COUNT NUMBER:=0;
  BEGIN
    SELECT COUNT(*) INTO V_COUNT
    FROM CONTRATOS_JUGADORES
    WHERE ID_EQUIPO = NEWIDEQUIPO AND DORSAL =NEWDORSAL;

    IF V_COUNT > 0 THEN
      RAISE_APPLICATION_ERROR(-20001, 'No se puede agregar o actualizar el jugador porque ya existe otro jugador con el mismo dorsal en el mismo equipo.');
    END IF;
  END AFTER STATEMENT;
END;
/
/*
select * from contratos_jugadores where id_equipo = 3;


        ID  ID_EQUIPO ID_JUGADOR FECHA_IN FECHA_FIN                     CLAUSULA DO     SUELDO
---------- ---------- ---------- -------- --------------------------- ---------- -- ----------
        19          3         23 01/01/23                                1000000 1    22500000
        20          3         24 01/01/23                                1000000 10   10500000
        21          3         25 01/01/23                                1000000 6    15000000
        22          3         26 01/01/23                               72000000 9    15000000
        24          3         27 01/01/23                               48000000 14   10500000
        25          3         29 01/01/23                               40000000 19   10500000
        26          3         30 01/01/23                                1000000 23   10000000
        27          3         31 01/01/23                               25000000 11   10000

update contratos_jugadores set dorsal = 10 where id_jugador = 27;

Error que empieza en la línea: 1 del comando :
update contratos_jugadores set dorsal = 10 where id_jugador = 27
Informe de error -
ORA-20001: No se puede agregar o actualizar el jugador porque ya existe otro jugador con el mismo dorsal en el mismo equipo.
ORA-06512: en "EQDAW01.TRG_NO_DUPLICATE_DORSAL", línea 18
ORA-04088: error durante la ejecución del disparador 'EQDAW01.TRG_NO_DUPLICATE_DORSAL'

*/


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
/
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
        -- Si el presupuesto se supera, lanzar una excepci�n y cancelar la operaci�n
                RAISE_APPLICATION_ERROR(-20001, 'En el equipo ' || NEWIDEQUIPO || ' el presupuesto total ('|| presupuesto_jugadores || '�) del personal no puede superar el presupuesto anual del equipo: ' || presupuesto_equipo || '�');

    END IF;
    END AFTER STATEMENT;
END control_presupuesto;
/

/*
INSERT INTO EQUIPOS (NOMBRE, PRESUPUESTO_ANUAL)
VALUES('MANOLO FC', 1)

UPDATE CONTRATOS_JUGADORES
SET ID_EQUIPO=18
WHERE ID_EQUIPO=3;

Error en la l�nea de comandos : 32 Columna : 8
Informe de error -
Error SQL: ORA-20001: En el equipo 18 el presupuesto total (420500000�) del personal no puede superar el presupuesto anual del equipo: 1�
ORA-06512: en "SYSTEM.CONTROL_PRESUPUESTO", l�nea 20
ORA-04088: error durante la ejecuci�n del disparador 'SYSTEM.CONTROL_PRESUPUESTO'
*/


CREATE OR REPLACE TRIGGER trg_comprobar_min_personal_wc
before INSERT OR UPDATE ON SPLITS
FOR EACH ROW
DECLARE
    v_num_equipos NUMBER(2);
    v_min_personal NUMBER(2) :=1;
    v_num_personal NUMBER(2);
BEGIN
    -- Obtener el n�mero de equipos
    SELECT COUNT(*) INTO v_num_equipos FROM EQUIPOS;
    
    -- Comprobar el n�mero m�nimo de jugadores en cada equipo
    FOR i IN 1..v_num_equipos LOOP
        SELECT COUNT(*) INTO v_num_personal FROM CONTRATOS_PERSONAL CP, PERSONALES P WHERE (CP.ID_EQUIPO = i AND P.OFICIO='PRESIDENTE') AND cp.id_personal = P.ID;
        IF v_num_personal < v_min_personal THEN
            RAISE_APPLICATION_ERROR(-20001, 'El equipo ' || i || ' no tiene presidente (' || v_min_personal || ')');
        END IF;
    END LOOP;
END trg_comprobar_min_personal_wc;
/



/*

DELETE FROM CONTRATOS_PERSONAL WHERE ID_EQUIPO = 1;

2 filas eliminado


INSERT INTO SPLITS (ANIO, ESTADO, TIPO) VALUES(TO_DATE('03/10/2023', 'DD/MM/YYYY'), 'ABIERTO', 'VERANO')


Error que empieza en la l�nea: 1 del comando -
INSERT INTO SPLITS (ANIO, ESTADO, TIPO) VALUES(TO_DATE('03/10/2023', 'DD/MM/YYYY'), 'ABIERTO', 'VERANO')
Error en la l�nea de comandos : 1 Columna : 13
Informe de error -
Error SQL: ORA-20001: El equipo 1 no tiene presidente (1)
ORA-06512: en "SYSTEM.TRG_COMPROBAR_MIN_PERSONAL_WC", l�nea 13
ORA-04088: error durante la ejecuci�n del disparador 'SYSTEM.TRG_COMPROBAR_MIN_PERSONAL_WC'


*/



CREATE OR REPLACE TRIGGER trg_comprobar_min_jugadores
before INSERT OR UPDATE ON SPLITS
FOR EACH ROW
DECLARE
    v_num_equipos NUMBER(2);
    v_min_jugadores NUMBER(2) :=8;
    v_num_jugadores NUMBER(2);
BEGIN
    -- Obtener el n�mero de equipos
    SELECT COUNT(*) INTO v_num_equipos FROM EQUIPOS;
    
    -- Comprobar el n�mero m�nimo de jugadores en cada equipo
    FOR i IN 1..v_num_equipos LOOP
        SELECT COUNT(CJ.ID_EQUIPO) INTO v_num_jugadores FROM CONTRATOS_JUGADORES CJ, JUGADORES J 
        WHERE ( (CJ.ID_EQUIPO = i AND J.TIPO='DRAFT') AND CJ.ID_JUGADOR = J.ID);
        IF v_num_jugadores < v_min_jugadores THEN
            RAISE_APPLICATION_ERROR(-20001, 'El equipo ' || i || ' no tiene el n�mero m�nimo de jugadores DRAFT (' || v_min_jugadores || ')');
        END IF;
    END LOOP;
END trg_comprobar_min_jugadores;
/
/*
DELETE CONTRATOS_JUGADORES
WHERE DORSAL='1' AND ID_EQUIPO=1;

INSERT INTO SPLITS (ANIO, TIPO, ESTADO) VALUES (TO_DATE('04/10/2023',' DD/MM/YYYY'), 'VERANO', 'ABIERTO');

Error que empieza en la l�nea: 33 del comando -
INSERT INTO SPLITS (ANIO, TIPO, ESTADO) VALUES (TO_DATE('04/10/2023',' DD/MM/YYYY'), 'VERANO', 'ABIERTO')
Error en la l�nea de comandos : 33 Columna : 69
Informe de error -
Error SQL: ORA-20001: El equipo 1 no tiene el n�mero m�nimo de jugadores DRAFT (8)
ORA-06512: en "SYSTEM.TRG_COMPROBAR_MIN_JUGADORES", l�nea 14
ORA-04088: error durante la ejecuci�n del disparador 'SYSTEM.TRG_COMPROBAR_MIN_JUGADORES'
*/



CREATE OR REPLACE TRIGGER MAX_NUM_PRESIDENTE
FOR INSERT OR UPDATE OF ID_EQUIPO ON CONTRATOS_PERSONAL
COMPOUND TRIGGER
NEWIDEQUIPO CONTRATOS_JUGADORES.ID_EQUIPO%TYPE:=NULL;
BEFORE EACH ROW IS
BEGIN
NEWIDEQUIPO:=:NEW.ID_EQUIPO;
END BEFORE EACH ROW;
AFTER STATEMENT IS
    V_NUM NUMBER:=0;
    MENSAJE VARCHAR2(100):=CONCAT ('SOLO PUEDE HABER UN PRESIDENTE EN EL EQUIPO', NEWIDEQUIPO);
BEGIN
SELECT COUNT(CJ.ID_PERSONAL) INTO V_NUM
    FROM CONTRATOS_PERSONAL CJ, PERSONALES P
    WHERE CJ.ID_EQUIPO = NEWIDEQUIPO AND P.OFICIO='PRESIDENTE';
    IF V_NUM > 1
        THEN 
        RAISE_APPLICATION_ERROR(-20001, MENSAJE);
    END IF;
    END AFTER STATEMENT;
END MAX_NUM_PRESIDENTE;
/
/*

select * from contratos_personal;

UPDATE PERSONALES SET OFICIO = 'PRESIDENTE' WHERE ID = 2;

Error que empieza en la l�nea: 1 del comando -
UPDATE CONTRATOS_PERSONAL SET ID_EQUIPO = 1 WHERE ID_PERSONAL = 13
Error en la l�nea de comandos : 1 Columna : 8
Informe de error -
Error SQL: ORA-20001: SOLO PUEDE HABER UN PRESIDENTE EN EL EQUIPO1
ORA-06512: en "SYSTEM.MAX_NUM_PRESIDENTE", l�nea 16
ORA-04088: error durante la ejecuci�n del disparador 'SYSTEM.MAX_NUM_PRESIDENTE'
*/




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
/

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
        
      Error que empieza en la l�nea: 59 del comando -
update contratos_personal set id_equipo = 4 where id_equipo = 9
Error en la l�nea de comandos : 59 Columna : 8
Informe de error -
Error SQL: ORA-20001: NUMERO MAXIMO DE PERSONAL (2) EN EL EQUIPO 4
ORA-06512: en "SYSTEM.MAX_NUM_PERSONAL", l�nea 16
ORA-04088: error durante la ejecuci�n del disparador 'SYSTEM.MAX_NUM_PERSONAL'  
        
*/


CREATE OR REPLACE TRIGGER MAX_NUM_JUGADORES
FOR INSERT OR UPDATE OF ID_EQUIPO ON CONTRATOS_JUGADORES
COMPOUND TRIGGER
NEWIDEQUIPO CONTRATOS_JUGADORES.ID_EQUIPO%TYPE:=NULL;
BEFORE EACH ROW IS
BEGIN
NEWIDEQUIPO:=:NEW.ID_EQUIPO;
END BEFORE EACH ROW;
AFTER STATEMENT IS
    V_NUM NUMBER:=0;
    MENSAJE VARCHAR2(100):=CONCAT ('DEMASIADOS JUGADORES EN EL EQUIPO ', NEWIDEQUIPO);
BEGIN
SELECT COUNT(*) INTO V_NUM
    FROM CONTRATOS_JUGADORES
    WHERE ID_EQUIPO = NEWIDEQUIPO;
    IF V_NUM > 10
        THEN 
        RAISE_APPLICATION_ERROR(-20001, MENSAJE);
    END IF;
    END AFTER STATEMENT;
END MAX_NUM_JUGADORES;
/
/*
UPDATE CONTRATOS_JUGADORES
SET ID_EQUIPO=1
WHERE ID_EQUIPO=2;
Error en la l�nea de comandos : 25 Columna : 8
Informe de error -
Error SQL: ORA-20001: DEMASIADOS JUGADORES EN EL EQUIPO 1
ORA-06512: en "SYSTEM.MAX_NUM_JUGADORES", l�nea 16
ORA-04088: error durante la ejecuci�n del disparador 'SYSTEM.MAX_NUM_JUGADORES'
*/




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
/
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

Error que empieza en la l�nea: 43 del comando -
UPDATE CONTRATOS_PERSONAL set id_equipo = 5 where id_equipo = 7
Error en la l�nea de comandos : 43 Columna : 8
Informe de error -
Error SQL: ORA-20001: NUMERO MAXIMO DE PERSONAL (2) EN EL EQUIPO 5
ORA-06512: en "SYSTEM.MAX_NUM_PERSONAL", l�nea 16
ORA-04088: error durante la ejecuci�n del disparador 'SYSTEM.MAX_NUM_PERSONAL'
*/



CREATE OR REPLACE TRIGGER JUGADORES_JORNADA_TRG
BEFORE INSERT OR UPDATE OR DELETE ON JUGADORES
FOR EACH ROW
DECLARE
V_SPLIT_ESTADO VARCHAR2(10);
V_ID_JORNADA NUMBER;
JORNADA_EXISTENTE EXCEPTION;
BEGIN
  SELECT ESTADO INTO V_SPLIT_ESTADO FROM SPLITS  WHERE ID IN (SELECT MAX(ID_SPLIT) FROM JORNADAS);
  IF V_SPLIT_ESTADO = 'CERRADO' THEN
    RAISE JORNADA_EXISTENTE;
    END IF;
EXCEPTION
  WHEN JORNADA_EXISTENTE THEN
    RAISE_APPLICATION_ERROR(-20001, 'No se puede realizar esta operaci�n mientras hay una jornada en curso.');
    WHEN NO_DATA_FOUND THEN 
    NULL;
END;
/
/*
UPDATE JUGADORES
SET nombre='MANOLO' WHERE DNI='11111265A';

Error que empieza en la l�nea: 18 del comando -
UPDATE JUGADORES
SET nombre='MANOLO' WHERE DNI='11111265A';
Error en la l�nea de comandos : 18 Columna : 8
Informe de error -
Error SQL: ORA-20001: No se puede realizar esta operaci�n mientras hay una jornada en curso.
ORA-06512: en "SYSTEM.JUGADORES_JORNADA_TRG", l�nea 12
ORA-04088: error durante la ejecuci�n del disparador 'SYSTEM.JUGADORES_JORNADA_TRG'
*/



CREATE OR REPLACE TRIGGER EQUIPOS_JORNADA_TRG
BEFORE INSERT OR UPDATE OR DELETE ON EQUIPOS
FOR EACH ROW
DECLARE
V_SPLIT_ESTADO VARCHAR2(10);
V_ID_JORNADA NUMBER;
JORNADA_EXISTENTE EXCEPTION;
BEGIN
  SELECT ESTADO INTO V_SPLIT_ESTADO FROM SPLITS WHERE ID IN (SELECT MAX(ID_SPLIT) FROM JORNADAS);
  IF V_SPLIT_ESTADO = 'CERRADO' THEN
    RAISE JORNADA_EXISTENTE;
    END IF;
EXCEPTION
  WHEN JORNADA_EXISTENTE THEN
    RAISE_APPLICATION_ERROR(-20001, 'No se puede realizar esta operaci�n mientras hay una jornada en curso.');
    WHEN NO_DATA_FOUND THEN 
    NULL;
END;
/
/*
UPDATE EQUIPOS
SET nombre='MANOLO' WHERE nombre='Porcinos FC';

Error que empieza en la l�nea: 18 del comando -
UPDATE EQUIPOS
SET nombre='MANOLO' WHERE nombre='Porcinos FC'
Error en la l�nea de comandos : 18 Columna : 8
Informe de error -
Error SQL: ORA-20001: No se puede realizar esta operaci�n mientras hay una jornada en curso.
ORA-06512: en "SYSTEM.EQUIPOS_JORNADA_TRG", l�nea 12
ORA-04088: error durante la ejecuci�n del disparador 'SYSTEM.EQUIPOS_JORNADA_TRG'
*/


CREATE OR REPLACE TRIGGER trg_comprobar_min_jugadores_wc
before INSERT OR UPDATE ON SPLITS
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
/

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
                  
                  
INSERT INTO SPLITS (ANIO, TIPO, ESTADO) VALUES (TO_DATE('04/10/2023',' DD/MM/YYYY'), 'VERANO', 'ABIERTO');

Error que empieza en la línea: 1 del comando :
INSERT INTO SPLITS (ANIO, TIPO, ESTADO) VALUES (TO_DATE('04/10/2023',' DD/MM/YYYY'), 'VERANO', 'ABIERTO')
Informe de error -
ORA-20001: El equipo 1 no tiene el número mínimo de jugadores WILD-CARD (2)
ORA-06512: en "EQDAW01.TRG_COMPROBAR_MIN_JUGADORES_WC", línea 13
ORA-04088: error durante la ejecución del disparador 'EQDAW01.TRG_COMPROBAR_MIN_JUGADORES_WC'

*/

