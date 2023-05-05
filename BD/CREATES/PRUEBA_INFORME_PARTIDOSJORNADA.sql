SET SERVEROUTPUT ON;

DECLARE
C_PARTIDOS INFORMES.TCURSOR;
NUMEROJORNADA NUMBER := 1;
TIPO_SPLIT VARCHAR2(10);
NUMERO_JORNADA NUMBER(3);
TIPO_JORNADA VARCHAR2(10);
ID NUMBER(4);
GOLES_EQUIPO1 NUMBER(2);
GOLES_EQUIPO2 NUMBER(2);
FECHA DATE;
LUGAR VARCHAR2(50);
ID_JORNADA NUMBER(2);
ID_EQUIPO1 NUMBER(2);
ID_EQUIPO2 NUMBER(2);
BEGIN
    
    INFORMES.INFORME_PARTIDOS_POR_JORNADA(C_PARTIDOS, NUMEROJORNADA);
    
    LOOP
        FETCH C_PARTIDOS INTO TIPO_SPLIT, NUMERO_JORNADA, TIPO_JORNADA,
                                ID, GOLES_EQUIPO1, GOLES_EQUIPO2, FECHA, LUGAR,
                                ID_JORNADA, ID_EQUIPO1, ID_EQUIPO2;
        EXIT WHEN C_PARTIDOS%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE('Tipo_split: ' || TIPO_SPLIT ||
            ', número_jornada: ' || NUMERO_JORNADA ||
            ', tipo_jornada: ' || TIPO_JORNADA ||
            ', id_partido: ' || ID ||
            ', goles_equipo 1: ' || GOLES_EQUIPO1 ||
            ', goles_equipo 2: ' || GOLES_EQUIPO2 ||
            ', fecha_partido: ' || FECHA ||
            ', lugar_partido: ' || LUGAR ||
            ', id_jornada: ' || ID_JORNADA ||
            ', id_equipo 1: ' || ID_EQUIPO1 ||
            ', id_equipo 1: ' || ID_EQUIPO2);
    END LOOP;
END;

/* RESULTADOS

Tipo_split: VERANO, número_jornada: 1, tipo_jornada: NORMAL, id_partido: 1, goles_equipo 1: , goles_equipo 2: , fecha_partido: 07/05/23, lugar_partido: CUPRA ARENA, id_jornada: 42, id_equipo 1: 4, id_equipo 1: 12
Tipo_split: VERANO, número_jornada: 1, tipo_jornada: NORMAL, id_partido: 2, goles_equipo 1: , goles_equipo 2: , fecha_partido: 07/05/23, lugar_partido: CUPRA ARENA, id_jornada: 42, id_equipo 1: 1, id_equipo 1: 10
Tipo_split: VERANO, número_jornada: 1, tipo_jornada: NORMAL, id_partido: 3, goles_equipo 1: , goles_equipo 2: , fecha_partido: 07/05/23, lugar_partido: CUPRA ARENA, id_jornada: 42, id_equipo 1: 5, id_equipo 1: 6
Tipo_split: VERANO, número_jornada: 1, tipo_jornada: NORMAL, id_partido: 4, goles_equipo 1: , goles_equipo 2: , fecha_partido: 07/05/23, lugar_partido: CUPRA ARENA, id_jornada: 42, id_equipo 1: 2, id_equipo 1: 8
Tipo_split: VERANO, número_jornada: 1, tipo_jornada: NORMAL, id_partido: 5, goles_equipo 1: , goles_equipo 2: , fecha_partido: 07/05/23, lugar_partido: CUPRA ARENA, id_jornada: 42, id_equipo 1: 7, id_equipo 1: 11
Tipo_split: VERANO, número_jornada: 1, tipo_jornada: NORMAL, id_partido: 6, goles_equipo 1: , goles_equipo 2: , fecha_partido: 07/05/23, lugar_partido: CUPRA ARENA, id_jornada: 42, id_equipo 1: 3, id_equipo 1: 9

Procedimiento PL/SQL terminado correctamente.

*/