CREATE OR REPLACE PACKAGE BODY PAQUETE_XML AS
    PROCEDURE GENERAR_XML_JORNADAS AS
        RESULT CLOB;
        C_PARTIDOS SYS_REFCURSOR;
        NUMERO_JORNADA_ANTERIOR NUMBER := 0;
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
        INFORME_PARTIDOS_POR_JORNADA(C_PARTIDOS);
        RESULT := '<?xml version="1.0" encoding="UTF-8"?>
            <jornadas xmlns:xs = "http://www.w3.org/2001/XMLSchema-instance" xs:noNamespaceSchemaLocation = "jornadas.xsd">';

        LOOP
            FETCH C_PARTIDOS INTO TIPO_SPLIT, NUMERO_JORNADA, TIPO_JORNADA,
                ID, GOLES_EQUIPO1, GOLES_EQUIPO2, FECHA, LUGAR,
                ID_JORNADA, ID_EQUIPO1, ID_EQUIPO2, EQUIPO1, EQUIPO2;
            EXIT WHEN C_PARTIDOS%NOTFOUND;

            IF NUMERO_JORNADA_ANTERIOR != NUMERO_JORNADA THEN
                IF NUMERO_JORNADA_ANTERIOR != 0 THEN
                    RESULT := RESULT || '</jornada>';
                END IF;
                RESULT := RESULT || '<jornada num_jornada="' || NUMERO_JORNADA || '" id_jornada="' || ID_JORNADA || '">
                        <tipo_split>' || TIPO_SPLIT || '</tipo_split>
                        <tipo_jornada>' || TIPO_JORNADA || '</tipo_jornada>';
                NUMERO_JORNADA_ANTERIOR := NUMERO_JORNADA;
            END IF;
            RESULT := RESULT ||
                      '<partido id_partido="'|| ID ||'">
                <equipo1>'|| EQUIPO1 || '</equipo1>
                <goles_equipo1>' || GOLES_EQUIPO1 || '</goles_equipo1>
                <equipo2>'|| EQUIPO2 || '</equipo2>
                <goles_equipo2>'|| GOLES_EQUIPO2 || '</goles_equipo2>
                <fecha_partido>' || FECHA || '</fecha_partido>
                <lugar_partido>' || LUGAR || '</lugar_partido>
            </partido>';
        END LOOP;
        RESULT := RESULT || '</jornada></jornadas>';
        INSERT INTO XML_JORNADA (RESUL) VALUES (RESULT);


    END GENERAR_XML_JORNADAS;

    PROCEDURE GENERAR_XML_CLASIFICACION AS
        RESULT CLOB;
    BEGIN
        RESULT := '<?xml version="1.0" encoding="UTF-8"?>
        <!DOCTYPE clasificacion SYSTEM "clasificacion.dtd">
        <clasificacion split="1" xmlns:xs="http://www.w3.org/2001/XMLSchema-instance" xs:noNamespaceSchemaLocation="clasificacion.xsd">
        <fecha>' || TO_DATE(SYSDATE, 'DD/MM/YYYY') || '</fecha>';
        FOR FILA IN (SELECT * FROM CLASIFICACION) LOOP
                RESULT :=  RESULT ||
                           '<equipo posicion="' || FILA.POSICION || '">
                <nombre>' || FILA.EQUIPO || '</nombre>
                <victorias>' || FILA.VICTORIAS || '</victorias>
                <goles_a_favor>' || FILA.GOLES_A_FAVOR || '</goles_a_favor>
                <goles_en_contra>' || FILA.GOLES_EN_CONTRA ||'</goles_en_contra>
                <diferencia_de_goles>' || FILA.BALANCE_GOLES || '</diferencia_de_goles>
            </equipo>';
            END LOOP;
        RESULT := RESULT || '</clasificacion>';
        INSERT INTO XML_CLASIFICACION (RESUL) VALUES (RESULT);
    END GENERAR_XML_CLASIFICACION;


    PROCEDURE GENERAR_XML_ULTIMA_JORNADA (XML OUT CLOB) AS
        CURSOR C_XML IS
            SELECT S.TIPO TIPO_SPLIT,
                   J.NUM_JORNADA NUMERO_JORNADA,
                   J.TIPO TIPO_JORNADA,
                   P.*,
                   (SELECT NOMBRE FROM EQUIPOS WHERE ID = ID_EQUIPO1) EQUIPO1,
                   (SELECT NOMBRE FROM EQUIPOS WHERE ID = ID_EQUIPO2) EQUIPO2
            FROM SPLITS S, JORNADAS J, PARTIDOS P
            WHERE S.ID=J.ID_SPLIT AND J.ID=P.ID_JORNADA
              AND S.ID=(SELECT MAX(ID) FROM SPLITS) AND J.ID = (SELECT MAX(JORNADAS.ID) FROM JORNADAS, PARTIDOS WHERE FECHA < SYSDATE AND PARTIDOS.ID_JORNADA = JORNADAS.ID);
        JORNADA NUMBER(1) := 0;
    BEGIN
        XML := XML || '<?xml version="1.0" encoding="UTF-8"?>
            <jornadas xmlns:xs = "http://www.w3.org/2001/XMLSchema-instance" xs:noNamespaceSchemaLocation = "jornadas.xsd">';
        FOR FILA IN  C_XML LOOP
                IF JORNADA = 0 THEN
                    XML := XML || '<jornada num_jornada="' || FILA.NUMERO_JORNADA || '" id_jornada="' || FILA.ID_JORNADA || '">
                    <tipo_split>' || FILA.TIPO_SPLIT || '</tipo_split>
                    <tipo_jornada>' || FILA.TIPO_JORNADA || '</tipo_jornada>';
                    JORNADA := 1;
                END IF;
                XML := XML ||
                       '<partido id_partido="'|| FILA.ID ||'">
            <equipo1>'|| FILA.EQUIPO1 || '</equipo1>
            <goles_equipo1>' || FILA.GOLES_EQUIPO1 || '</goles_equipo1>
            <equipo2>'|| FILA.EQUIPO2 || '</equipo2>
            <goles_equipo2>'|| FILA.GOLES_EQUIPO2 || '</goles_equipo2>
            <fecha_partido>' || FILA.FECHA || '</fecha_partido>
            <lugar_partido>' || FILA.LUGAR || '</lugar_partido>
            </partido>';
            END LOOP;
        XML := XML || '</jornada></jornadas>';
    END GENERAR_XML_ULTIMA_JORNADA;
END PAQUETE_XML;
