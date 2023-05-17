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
EQUIPO1 VARCHAR2(50);
EQUIPO2 VARCHAR2(50);
BEGIN

    INFORMES.INFORME_PARTIDOS_POR_JORNADA(C_PARTIDOS);

    LOOP
        FETCH C_PARTIDOS INTO TIPO_SPLIT, NUMERO_JORNADA, TIPO_JORNADA,
                                ID, GOLES_EQUIPO1, GOLES_EQUIPO2, FECHA, LUGAR,
                                ID_JORNADA, ID_EQUIPO1, ID_EQUIPO2, EQUIPO1, EQUIPO2;
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
            ', id_equipo 1: ' || ID_EQUIPO2 || 
            ', nombre_equipo 1: ' || EQUIPO1 ||
            ', nombre_equipo 2: ' || EQUIPO2);
    END LOOP;
END;

/* RESULTADOS
Tipo_split: VERANO, número_jornada: 1, tipo_jornada: NORMAL, id_partido: 1, goles_equipo 1: , goles_equipo 2: , fecha_partido: 07/05/23, lugar_partido: CUPRA ARENA, id_jornada: 85, id_equipo 1: 2, id_equipo 1: 12, nombre_equipo 1: Ultimate Mostoles, nombre_equipo 2: Jijantes FC
Tipo_split: VERANO, número_jornada: 1, tipo_jornada: NORMAL, id_partido: 2, goles_equipo 1: , goles_equipo 2: , fecha_partido: 07/05/23, lugar_partido: CUPRA ARENA, id_jornada: 85, id_equipo 1: 4, id_equipo 1: 11, nombre_equipo 1: Kunisports, nombre_equipo 2: Porcinos FC
Tipo_split: VERANO, número_jornada: 1, tipo_jornada: NORMAL, id_partido: 3, goles_equipo 1: , goles_equipo 2: , fecha_partido: 07/05/23, lugar_partido: CUPRA ARENA, id_jornada: 85, id_equipo 1: 1, id_equipo 1: 10, nombre_equipo 1: Saiyans FC, nombre_equipo 2: xBuyer Team
Tipo_split: VERANO, número_jornada: 1, tipo_jornada: NORMAL, id_partido: 4, goles_equipo 1: , goles_equipo 2: , fecha_partido: 07/05/23, lugar_partido: CUPRA ARENA, id_jornada: 85, id_equipo 1: 6, id_equipo 1: 7, nombre_equipo 1: Aniquiladores FC, nombre_equipo 2: Los Troncos FC
Tipo_split: VERANO, número_jornada: 1, tipo_jornada: NORMAL, id_partido: 5, goles_equipo 1: , goles_equipo 2: , fecha_partido: 07/05/23, lugar_partido: CUPRA ARENA, id_jornada: 85, id_equipo 1: 8, id_equipo 1: 9, nombre_equipo 1: 1K FC, nombre_equipo 2: Rayo de Barcelona
Tipo_split: VERANO, número_jornada: 1, tipo_jornada: NORMAL, id_partido: 6, goles_equipo 1: , goles_equipo 2: , fecha_partido: 07/05/23, lugar_partido: CUPRA ARENA, id_jornada: 85, id_equipo 1: 3, id_equipo 1: 5, nombre_equipo 1: El Barrio, nombre_equipo 2: PIO FC
Tipo_split: VERANO, número_jornada: 2, tipo_jornada: NORMAL, id_partido: 7, goles_equipo 1: , goles_equipo 2: , fecha_partido: 14/05/23, lugar_partido: CUPRA ARENA, id_jornada: 86, id_equipo 1: 9, id_equipo 1: 11, nombre_equipo 1: Rayo de Barcelona, nombre_equipo 2: Porcinos FC
Tipo_split: VERANO, número_jornada: 2, tipo_jornada: NORMAL, id_partido: 8, goles_equipo 1: , goles_equipo 2: , fecha_partido: 14/05/23, lugar_partido: CUPRA ARENA, id_jornada: 86, id_equipo 1: 5, id_equipo 1: 12, nombre_equipo 1: PIO FC, nombre_equipo 2: Jijantes FC
Tipo_split: VERANO, número_jornada: 2, tipo_jornada: NORMAL, id_partido: 9, goles_equipo 1: , goles_equipo 2: , fecha_partido: 14/05/23, lugar_partido: CUPRA ARENA, id_jornada: 86, id_equipo 1: 3, id_equipo 1: 10, nombre_equipo 1: El Barrio, nombre_equipo 2: xBuyer Team
Tipo_split: VERANO, número_jornada: 2, tipo_jornada: NORMAL, id_partido: 10, goles_equipo 1: , goles_equipo 2: , fecha_partido: 14/05/23, lugar_partido: CUPRA ARENA, id_jornada: 86, id_equipo 1: 4, id_equipo 1: 6, nombre_equipo 1: Kunisports, nombre_equipo 2: Aniquiladores FC
Tipo_split: VERANO, número_jornada: 2, tipo_jornada: NORMAL, id_partido: 11, goles_equipo 1: , goles_equipo 2: , fecha_partido: 14/05/23, lugar_partido: CUPRA ARENA, id_jornada: 86, id_equipo 1: 1, id_equipo 1: 7, nombre_equipo 1: Saiyans FC, nombre_equipo 2: Los Troncos FC
Tipo_split: VERANO, número_jornada: 2, tipo_jornada: NORMAL, id_partido: 12, goles_equipo 1: , goles_equipo 2: , fecha_partido: 14/05/23, lugar_partido: CUPRA ARENA, id_jornada: 86, id_equipo 1: 2, id_equipo 1: 8, nombre_equipo 1: Ultimate Mostoles, nombre_equipo 2: 1K FC
Tipo_split: VERANO, número_jornada: 3, tipo_jornada: NORMAL, id_partido: 13, goles_equipo 1: , goles_equipo 2: , fecha_partido: 21/05/23, lugar_partido: CUPRA ARENA, id_jornada: 87, id_equipo 1: 1, id_equipo 1: 12, nombre_equipo 1: Saiyans FC, nombre_equipo 2: Jijantes FC
Tipo_split: VERANO, número_jornada: 3, tipo_jornada: NORMAL, id_partido: 14, goles_equipo 1: , goles_equipo 2: , fecha_partido: 21/05/23, lugar_partido: CUPRA ARENA, id_jornada: 87, id_equipo 1: 5, id_equipo 1: 6, nombre_equipo 1: PIO FC, nombre_equipo 2: Aniquiladores FC
Tipo_split: VERANO, número_jornada: 3, tipo_jornada: NORMAL, id_partido: 15, goles_equipo 1: , goles_equipo 2: , fecha_partido: 21/05/23, lugar_partido: CUPRA ARENA, id_jornada: 87, id_equipo 1: 7, id_equipo 1: 8, nombre_equipo 1: Los Troncos FC, nombre_equipo 2: 1K FC
Tipo_split: VERANO, número_jornada: 3, tipo_jornada: NORMAL, id_partido: 16, goles_equipo 1: , goles_equipo 2: , fecha_partido: 21/05/23, lugar_partido: CUPRA ARENA, id_jornada: 87, id_equipo 1: 3, id_equipo 1: 9, nombre_equipo 1: El Barrio, nombre_equipo 2: Rayo de Barcelona
Tipo_split: VERANO, número_jornada: 3, tipo_jornada: NORMAL, id_partido: 17, goles_equipo 1: , goles_equipo 2: , fecha_partido: 21/05/23, lugar_partido: CUPRA ARENA, id_jornada: 87, id_equipo 1: 2, id_equipo 1: 4, nombre_equipo 1: Ultimate Mostoles, nombre_equipo 2: Kunisports
Tipo_split: VERANO, número_jornada: 3, tipo_jornada: NORMAL, id_partido: 18, goles_equipo 1: , goles_equipo 2: , fecha_partido: 21/05/23, lugar_partido: CUPRA ARENA, id_jornada: 87, id_equipo 1: 10, id_equipo 1: 11, nombre_equipo 1: xBuyer Team, nombre_equipo 2: Porcinos FC
Tipo_split: VERANO, número_jornada: 4, tipo_jornada: NORMAL, id_partido: 19, goles_equipo 1: , goles_equipo 2: , fecha_partido: 28/05/23, lugar_partido: CUPRA ARENA, id_jornada: 88, id_equipo 1: 6, id_equipo 1: 9, nombre_equipo 1: Aniquiladores FC, nombre_equipo 2: Rayo de Barcelona
Tipo_split: VERANO, número_jornada: 4, tipo_jornada: NORMAL, id_partido: 20, goles_equipo 1: , goles_equipo 2: , fecha_partido: 28/05/23, lugar_partido: CUPRA ARENA, id_jornada: 88, id_equipo 1: 5, id_equipo 1: 10, nombre_equipo 1: PIO FC, nombre_equipo 2: xBuyer Team
Tipo_split: VERANO, número_jornada: 4, tipo_jornada: NORMAL, id_partido: 21, goles_equipo 1: , goles_equipo 2: , fecha_partido: 28/05/23, lugar_partido: CUPRA ARENA, id_jornada: 88, id_equipo 1: 8, id_equipo 1: 11, nombre_equipo 1: 1K FC, nombre_equipo 2: Porcinos FC
Tipo_split: VERANO, número_jornada: 4, tipo_jornada: NORMAL, id_partido: 22, goles_equipo 1: , goles_equipo 2: , fecha_partido: 28/05/23, lugar_partido: CUPRA ARENA, id_jornada: 88, id_equipo 1: 3, id_equipo 1: 12, nombre_equipo 1: El Barrio, nombre_equipo 2: Jijantes FC
Tipo_split: VERANO, número_jornada: 4, tipo_jornada: NORMAL, id_partido: 23, goles_equipo 1: , goles_equipo 2: , fecha_partido: 28/05/23, lugar_partido: CUPRA ARENA, id_jornada: 88, id_equipo 1: 4, id_equipo 1: 7, nombre_equipo 1: Kunisports, nombre_equipo 2: Los Troncos FC
Tipo_split: VERANO, número_jornada: 4, tipo_jornada: NORMAL, id_partido: 24, goles_equipo 1: , goles_equipo 2: , fecha_partido: 28/05/23, lugar_partido: CUPRA ARENA, id_jornada: 88, id_equipo 1: 1, id_equipo 1: 2, nombre_equipo 1: Saiyans FC, nombre_equipo 2: Ultimate Mostoles
Tipo_split: VERANO, número_jornada: 5, tipo_jornada: NORMAL, id_partido: 30, goles_equipo 1: , goles_equipo 2: , fecha_partido: 04/06/23, lugar_partido: CUPRA ARENA, id_jornada: 89, id_equipo 1: 5, id_equipo 1: 8, nombre_equipo 1: PIO FC, nombre_equipo 2: 1K FC
Tipo_split: VERANO, número_jornada: 5, tipo_jornada: NORMAL, id_partido: 31, goles_equipo 1: , goles_equipo 2: , fecha_partido: 04/06/23, lugar_partido: CUPRA ARENA, id_jornada: 89, id_equipo 1: 6, id_equipo 1: 10, nombre_equipo 1: Aniquiladores FC, nombre_equipo 2: xBuyer Team
Tipo_split: VERANO, número_jornada: 5, tipo_jornada: NORMAL, id_partido: 32, goles_equipo 1: , goles_equipo 2: , fecha_partido: 04/06/23, lugar_partido: CUPRA ARENA, id_jornada: 89, id_equipo 1: 4, id_equipo 1: 12, nombre_equipo 1: Kunisports, nombre_equipo 2: Jijantes FC
Tipo_split: VERANO, número_jornada: 5, tipo_jornada: NORMAL, id_partido: 33, goles_equipo 1: , goles_equipo 2: , fecha_partido: 04/06/23, lugar_partido: CUPRA ARENA, id_jornada: 89, id_equipo 1: 2, id_equipo 1: 9, nombre_equipo 1: Ultimate Mostoles, nombre_equipo 2: Rayo de Barcelona
Tipo_split: VERANO, número_jornada: 5, tipo_jornada: NORMAL, id_partido: 34, goles_equipo 1: , goles_equipo 2: , fecha_partido: 04/06/23, lugar_partido: CUPRA ARENA, id_jornada: 89, id_equipo 1: 1, id_equipo 1: 3, nombre_equipo 1: Saiyans FC, nombre_equipo 2: El Barrio
Tipo_split: VERANO, número_jornada: 5, tipo_jornada: NORMAL, id_partido: 35, goles_equipo 1: , goles_equipo 2: , fecha_partido: 04/06/23, lugar_partido: CUPRA ARENA, id_jornada: 89, id_equipo 1: 7, id_equipo 1: 11, nombre_equipo 1: Los Troncos FC, nombre_equipo 2: Porcinos FC
Tipo_split: VERANO, número_jornada: 6, tipo_jornada: NORMAL, id_partido: 36, goles_equipo 1: , goles_equipo 2: , fecha_partido: 11/06/23, lugar_partido: CUPRA ARENA, id_jornada: 90, id_equipo 1: 1, id_equipo 1: 5, nombre_equipo 1: Saiyans FC, nombre_equipo 2: PIO FC
Tipo_split: VERANO, número_jornada: 6, tipo_jornada: NORMAL, id_partido: 37, goles_equipo 1: , goles_equipo 2: , fecha_partido: 11/06/23, lugar_partido: CUPRA ARENA, id_jornada: 90, id_equipo 1: 4, id_equipo 1: 9, nombre_equipo 1: Kunisports, nombre_equipo 2: Rayo de Barcelona
Tipo_split: VERANO, número_jornada: 6, tipo_jornada: NORMAL, id_partido: 38, goles_equipo 1: , goles_equipo 2: , fecha_partido: 11/06/23, lugar_partido: CUPRA ARENA, id_jornada: 90, id_equipo 1: 8, id_equipo 1: 10, nombre_equipo 1: 1K FC, nombre_equipo 2: xBuyer Team
Tipo_split: VERANO, número_jornada: 6, tipo_jornada: NORMAL, id_partido: 39, goles_equipo 1: , goles_equipo 2: , fecha_partido: 11/06/23, lugar_partido: CUPRA ARENA, id_jornada: 90, id_equipo 1: 3, id_equipo 1: 7, nombre_equipo 1: El Barrio, nombre_equipo 2: Los Troncos FC
Tipo_split: VERANO, número_jornada: 6, tipo_jornada: NORMAL, id_partido: 40, goles_equipo 1: , goles_equipo 2: , fecha_partido: 11/06/23, lugar_partido: CUPRA ARENA, id_jornada: 90, id_equipo 1: 6, id_equipo 1: 12, nombre_equipo 1: Aniquiladores FC, nombre_equipo 2: Jijantes FC
Tipo_split: VERANO, número_jornada: 6, tipo_jornada: NORMAL, id_partido: 41, goles_equipo 1: , goles_equipo 2: , fecha_partido: 11/06/23, lugar_partido: CUPRA ARENA, id_jornada: 90, id_equipo 1: 2, id_equipo 1: 11, nombre_equipo 1: Ultimate Mostoles, nombre_equipo 2: Porcinos FC
Tipo_split: VERANO, número_jornada: 7, tipo_jornada: NORMAL, id_partido: 47, goles_equipo 1: , goles_equipo 2: , fecha_partido: 18/06/23, lugar_partido: CUPRA ARENA, id_jornada: 91, id_equipo 1: 7, id_equipo 1: 10, nombre_equipo 1: Los Troncos FC, nombre_equipo 2: xBuyer Team
Tipo_split: VERANO, número_jornada: 7, tipo_jornada: NORMAL, id_partido: 48, goles_equipo 1: , goles_equipo 2: , fecha_partido: 18/06/23, lugar_partido: CUPRA ARENA, id_jornada: 91, id_equipo 1: 2, id_equipo 1: 5, nombre_equipo 1: Ultimate Mostoles, nombre_equipo 2: PIO FC
Tipo_split: VERANO, número_jornada: 7, tipo_jornada: NORMAL, id_partido: 49, goles_equipo 1: , goles_equipo 2: , fecha_partido: 18/06/23, lugar_partido: CUPRA ARENA, id_jornada: 91, id_equipo 1: 1, id_equipo 1: 9, nombre_equipo 1: Saiyans FC, nombre_equipo 2: Rayo de Barcelona
Tipo_split: VERANO, número_jornada: 7, tipo_jornada: NORMAL, id_partido: 50, goles_equipo 1: , goles_equipo 2: , fecha_partido: 18/06/23, lugar_partido: CUPRA ARENA, id_jornada: 91, id_equipo 1: 11, id_equipo 1: 12, nombre_equipo 1: Porcinos FC, nombre_equipo 2: Jijantes FC
Tipo_split: VERANO, número_jornada: 7, tipo_jornada: NORMAL, id_partido: 51, goles_equipo 1: , goles_equipo 2: , fecha_partido: 18/06/23, lugar_partido: CUPRA ARENA, id_jornada: 91, id_equipo 1: 3, id_equipo 1: 6, nombre_equipo 1: El Barrio, nombre_equipo 2: Aniquiladores FC
Tipo_split: VERANO, número_jornada: 7, tipo_jornada: NORMAL, id_partido: 52, goles_equipo 1: , goles_equipo 2: , fecha_partido: 18/06/23, lugar_partido: CUPRA ARENA, id_jornada: 91, id_equipo 1: 4, id_equipo 1: 8, nombre_equipo 1: Kunisports, nombre_equipo 2: 1K FC
Tipo_split: VERANO, número_jornada: 8, tipo_jornada: NORMAL, id_partido: 93, goles_equipo 1: , goles_equipo 2: , fecha_partido: 25/06/23, lugar_partido: CUPRA ARENA, id_jornada: 92, id_equipo 1: 2, id_equipo 1: 6, nombre_equipo 1: Ultimate Mostoles, nombre_equipo 2: Aniquiladores FC
Tipo_split: VERANO, número_jornada: 8, tipo_jornada: NORMAL, id_partido: 94, goles_equipo 1: , goles_equipo 2: , fecha_partido: 25/06/23, lugar_partido: CUPRA ARENA, id_jornada: 92, id_equipo 1: 3, id_equipo 1: 4, nombre_equipo 1: El Barrio, nombre_equipo 2: Kunisports
Tipo_split: VERANO, número_jornada: 8, tipo_jornada: NORMAL, id_partido: 95, goles_equipo 1: , goles_equipo 2: , fecha_partido: 25/06/23, lugar_partido: CUPRA ARENA, id_jornada: 92, id_equipo 1: 7, id_equipo 1: 12, nombre_equipo 1: Los Troncos FC, nombre_equipo 2: Jijantes FC
Tipo_split: VERANO, número_jornada: 8, tipo_jornada: NORMAL, id_partido: 96, goles_equipo 1: , goles_equipo 2: , fecha_partido: 25/06/23, lugar_partido: CUPRA ARENA, id_jornada: 92, id_equipo 1: 5, id_equipo 1: 11, nombre_equipo 1: PIO FC, nombre_equipo 2: Porcinos FC
Tipo_split: VERANO, número_jornada: 8, tipo_jornada: NORMAL, id_partido: 97, goles_equipo 1: , goles_equipo 2: , fecha_partido: 25/06/23, lugar_partido: CUPRA ARENA, id_jornada: 92, id_equipo 1: 1, id_equipo 1: 8, nombre_equipo 1: Saiyans FC, nombre_equipo 2: 1K FC
Tipo_split: VERANO, número_jornada: 8, tipo_jornada: NORMAL, id_partido: 98, goles_equipo 1: , goles_equipo 2: , fecha_partido: 25/06/23, lugar_partido: CUPRA ARENA, id_jornada: 92, id_equipo 1: 9, id_equipo 1: 10, nombre_equipo 1: Rayo de Barcelona, nombre_equipo 2: xBuyer Team
Tipo_split: VERANO, número_jornada: 9, tipo_jornada: NORMAL, id_partido: 99, goles_equipo 1: , goles_equipo 2: , fecha_partido: 02/07/23, lugar_partido: CUPRA ARENA, id_jornada: 93, id_equipo 1: 7, id_equipo 1: 9, nombre_equipo 1: Los Troncos FC, nombre_equipo 2: Rayo de Barcelona
Tipo_split: VERANO, número_jornada: 9, tipo_jornada: NORMAL, id_partido: 100, goles_equipo 1: , goles_equipo 2: , fecha_partido: 02/07/23, lugar_partido: CUPRA ARENA, id_jornada: 93, id_equipo 1: 4, id_equipo 1: 5, nombre_equipo 1: Kunisports, nombre_equipo 2: PIO FC
Tipo_split: VERANO, número_jornada: 9, tipo_jornada: NORMAL, id_partido: 101, goles_equipo 1: , goles_equipo 2: , fecha_partido: 02/07/23, lugar_partido: CUPRA ARENA, id_jornada: 93, id_equipo 1: 2, id_equipo 1: 10, nombre_equipo 1: Ultimate Mostoles, nombre_equipo 2: xBuyer Team
Tipo_split: VERANO, número_jornada: 9, tipo_jornada: NORMAL, id_partido: 102, goles_equipo 1: , goles_equipo 2: , fecha_partido: 02/07/23, lugar_partido: CUPRA ARENA, id_jornada: 93, id_equipo 1: 1, id_equipo 1: 6, nombre_equipo 1: Saiyans FC, nombre_equipo 2: Aniquiladores FC
Tipo_split: VERANO, número_jornada: 9, tipo_jornada: NORMAL, id_partido: 103, goles_equipo 1: , goles_equipo 2: , fecha_partido: 02/07/23, lugar_partido: CUPRA ARENA, id_jornada: 93, id_equipo 1: 3, id_equipo 1: 11, nombre_equipo 1: El Barrio, nombre_equipo 2: Porcinos FC
Tipo_split: VERANO, número_jornada: 9, tipo_jornada: NORMAL, id_partido: 104, goles_equipo 1: , goles_equipo 2: , fecha_partido: 02/07/23, lugar_partido: CUPRA ARENA, id_jornada: 93, id_equipo 1: 8, id_equipo 1: 12, nombre_equipo 1: 1K FC, nombre_equipo 2: Jijantes FC
Tipo_split: VERANO, número_jornada: 10, tipo_jornada: NORMAL, id_partido: 115, goles_equipo 1: , goles_equipo 2: , fecha_partido: 09/07/23, lugar_partido: CUPRA ARENA, id_jornada: 94, id_equipo 1: 1, id_equipo 1: 11, nombre_equipo 1: Saiyans FC, nombre_equipo 2: Porcinos FC
Tipo_split: VERANO, número_jornada: 10, tipo_jornada: NORMAL, id_partido: 116, goles_equipo 1: , goles_equipo 2: , fecha_partido: 09/07/23, lugar_partido: CUPRA ARENA, id_jornada: 94, id_equipo 1: 6, id_equipo 1: 8, nombre_equipo 1: Aniquiladores FC, nombre_equipo 2: 1K FC
Tipo_split: VERANO, número_jornada: 10, tipo_jornada: NORMAL, id_partido: 117, goles_equipo 1: , goles_equipo 2: , fecha_partido: 09/07/23, lugar_partido: CUPRA ARENA, id_jornada: 94, id_equipo 1: 4, id_equipo 1: 10, nombre_equipo 1: Kunisports, nombre_equipo 2: xBuyer Team
Tipo_split: VERANO, número_jornada: 10, tipo_jornada: NORMAL, id_partido: 118, goles_equipo 1: , goles_equipo 2: , fecha_partido: 09/07/23, lugar_partido: CUPRA ARENA, id_jornada: 94, id_equipo 1: 5, id_equipo 1: 7, nombre_equipo 1: PIO FC, nombre_equipo 2: Los Troncos FC
Tipo_split: VERANO, número_jornada: 10, tipo_jornada: NORMAL, id_partido: 119, goles_equipo 1: , goles_equipo 2: , fecha_partido: 09/07/23, lugar_partido: CUPRA ARENA, id_jornada: 94, id_equipo 1: 9, id_equipo 1: 12, nombre_equipo 1: Rayo de Barcelona, nombre_equipo 2: Jijantes FC
Tipo_split: VERANO, número_jornada: 10, tipo_jornada: NORMAL, id_partido: 120, goles_equipo 1: , goles_equipo 2: , fecha_partido: 09/07/23, lugar_partido: CUPRA ARENA, id_jornada: 94, id_equipo 1: 2, id_equipo 1: 3, nombre_equipo 1: Ultimate Mostoles, nombre_equipo 2: El Barrio
Tipo_split: VERANO, número_jornada: 11, tipo_jornada: NORMAL, id_partido: 121, goles_equipo 1: , goles_equipo 2: , fecha_partido: 16/07/23, lugar_partido: CUPRA ARENA, id_jornada: 95, id_equipo 1: 5, id_equipo 1: 9, nombre_equipo 1: PIO FC, nombre_equipo 2: Rayo de Barcelona
Tipo_split: VERANO, número_jornada: 11, tipo_jornada: NORMAL, id_partido: 122, goles_equipo 1: , goles_equipo 2: , fecha_partido: 16/07/23, lugar_partido: CUPRA ARENA, id_jornada: 95, id_equipo 1: 3, id_equipo 1: 8, nombre_equipo 1: El Barrio, nombre_equipo 2: 1K FC
Tipo_split: VERANO, número_jornada: 11, tipo_jornada: NORMAL, id_partido: 123, goles_equipo 1: , goles_equipo 2: , fecha_partido: 16/07/23, lugar_partido: CUPRA ARENA, id_jornada: 95, id_equipo 1: 1, id_equipo 1: 4, nombre_equipo 1: Saiyans FC, nombre_equipo 2: Kunisports
Tipo_split: VERANO, número_jornada: 11, tipo_jornada: NORMAL, id_partido: 124, goles_equipo 1: , goles_equipo 2: , fecha_partido: 16/07/23, lugar_partido: CUPRA ARENA, id_jornada: 95, id_equipo 1: 2, id_equipo 1: 7, nombre_equipo 1: Ultimate Mostoles, nombre_equipo 2: Los Troncos FC
Tipo_split: VERANO, número_jornada: 11, tipo_jornada: NORMAL, id_partido: 125, goles_equipo 1: , goles_equipo 2: , fecha_partido: 16/07/23, lugar_partido: CUPRA ARENA, id_jornada: 95, id_equipo 1: 6, id_equipo 1: 11, nombre_equipo 1: Aniquiladores FC, nombre_equipo 2: Porcinos FC
Tipo_split: VERANO, número_jornada: 11, tipo_jornada: NORMAL, id_partido: 126, goles_equipo 1: , goles_equipo 2: , fecha_partido: 16/07/23, lugar_partido: CUPRA ARENA, id_jornada: 95, id_equipo 1: 10, id_equipo 1: 12, nombre_equipo 1: xBuyer Team, nombre_equipo 2: Jijantes FC
Procedimiento PL/SQL terminado correctamente.
*/