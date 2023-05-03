begin 
    gestion_calendario.generar_enfrentamientos;
end;

SELECT * FROM JORNADAS;
DROP TABLE EMPAREJAMIENTOS;
CREATE GLOBAL TEMPORARY TABLE EMPAREJAMIENTOS (
                      ID1 VARCHAR2(50),
                      ID2 VARCHAR2(50)
                    );

