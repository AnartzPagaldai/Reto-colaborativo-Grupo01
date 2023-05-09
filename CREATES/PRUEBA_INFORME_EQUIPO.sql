SET SERVEROUTPUT ON;

DECLARE
C_INFORME INFORMES.TCURSOR;
V_TIPOFILA V_INFO_EQUIPOS%ROWTYPE;
BEGIN
    
    INFORMES.INFORME_EQUIPO(C_INFORME, 'Porcinos FC');
    
    LOOP
        FETCH C_INFORME INTO V_TIPOFILA;
        EXIT WHEN C_INFORME%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE('Presupuesto_anual: ' || V_TIPOFILA.PRESUPUESTO_ANUAL ||
            ', entrenador: ' || V_TIPOFILA.ENTRENADOR ||
            ', presidente: ' || V_TIPOFILA.PRESIDENTE ||
            ', cantidad_jugadores: ' || V_TIPOFILA.CANTIDADJUGADORES ||
            ', nombre_jugador: ' || V_TIPOFILA.NOMBRE_JUGADOR ||
            ', apellido_jugador: ' || V_TIPOFILA.APELLIDO_JUGADOR ||
            ', posicion_jugador: ' || V_TIPOFILA.POSICION ||
            ', tipo_jugador: ' || V_TIPOFILA.TIPO ||
            ', velocidad_jugador: ' || V_TIPOFILA.VELOCIDAD ||
            ', fisico_jugador: ' || V_TIPOFILA.FISICO ||
            ', tiro_jugador: ' || V_TIPOFILA.TIRO ||
            ', pase_jugador: ' || V_TIPOFILA.PASE ||
            ', talento_jugador: ' || V_TIPOFILA.TALENTO ||
            ', defensa_jugador: ' || V_TIPOFILA.DEFENSA ||
            ', sueldo_jugador: ' || V_TIPOFILA.SUELDO);
    END LOOP;
    CLOSE C_INFORME;
END;

/* RESULTADOS

Presupuesto_anual: 2000000, entrenador: Juvenal, presidente: Ibai, cantidad_jugadores: 10, nombre_jugador: Martin, apellido_jugador: Espinosa, posicion_jugador: MEDIO, tipo_jugador: WILD-CARD, velocidad_jugador: 99, fisico_jugador: 99, tiro_jugador: 99, pase_jugador: 99, talento_jugador: 99, defensa_jugador: 99, sueldo_jugador: 22500000
Presupuesto_anual: 2000000, entrenador: Juvenal, presidente: Ibai, cantidad_jugadores: 10, nombre_jugador: Jorge, apellido_jugador: Segovia, posicion_jugador: PORTERO, tipo_jugador: DRAFT, velocidad_jugador: 0, fisico_jugador: 0, tiro_jugador: 0, pase_jugador: 0, talento_jugador: 99, defensa_jugador: 99, sueldo_jugador: 15000000
Presupuesto_anual: 2000000, entrenador: Juvenal, presidente: Ibai, cantidad_jugadores: 10, nombre_jugador: Guillermo, apellido_jugador: Cichero, posicion_jugador: DEFENSA, tipo_jugador: DRAFT, velocidad_jugador: 73, fisico_jugador: 49, tiro_jugador: 66, pase_jugador: 75, talento_jugador: 76, defensa_jugador: 74, sueldo_jugador: 22500000
Presupuesto_anual: 2000000, entrenador: Juvenal, presidente: Ibai, cantidad_jugadores: 10, nombre_jugador: David, apellido_jugador: López, posicion_jugador: DEFENSA, tipo_jugador: DRAFT, velocidad_jugador: 70, fisico_jugador: 48, tiro_jugador: 80, pase_jugador: 62, talento_jugador: 81, defensa_jugador: 75, sueldo_jugador: 10000000
Presupuesto_anual: 2000000, entrenador: Juvenal, presidente: Ibai, cantidad_jugadores: 10, nombre_jugador: Toti, apellido_jugador: Sàlvia, posicion_jugador: MEDIO, tipo_jugador: DRAFT, velocidad_jugador: 72, fisico_jugador: 58, tiro_jugador: 80, pase_jugador: 69, talento_jugador: 78, defensa_jugador: 73, sueldo_jugador: 10000000
Presupuesto_anual: 2000000, entrenador: Juvenal, presidente: Ibai, cantidad_jugadores: 10, nombre_jugador: Carlitos, apellido_jugador: González, posicion_jugador: MEDIO, tipo_jugador: WILD-CARD, velocidad_jugador: 99, fisico_jugador: 99, tiro_jugador: 99, pase_jugador: 99, talento_jugador: 99, defensa_jugador: 99, sueldo_jugador: 15000000
Presupuesto_anual: 2000000, entrenador: Juvenal, presidente: Ibai, cantidad_jugadores: 10, nombre_jugador: Killian, apellido_jugador: Honorato, posicion_jugador: DELANTERO, tipo_jugador: DRAFT, velocidad_jugador: 72, fisico_jugador: 70, tiro_jugador: 87, pase_jugador: 69, talento_jugador: 78, defensa_jugador: 73, sueldo_jugador: 15000000
Presupuesto_anual: 2000000, entrenador: Juvenal, presidente: Ibai, cantidad_jugadores: 10, nombre_jugador: Juan, apellido_jugador: Blanco, posicion_jugador: DELANTERO, tipo_jugador: DRAFT, velocidad_jugador: 73, fisico_jugador: 54, tiro_jugador: 65, pase_jugador: 66, talento_jugador: 71, defensa_jugador: 65, sueldo_jugador: 15000000
Presupuesto_anual: 2000000, entrenador: Juvenal, presidente: Ibai, cantidad_jugadores: 10, nombre_jugador: Hernesto, apellido_jugador: Lao, posicion_jugador: MEDIO, tipo_jugador: DRAFT, velocidad_jugador: 73, fisico_jugador: 69, tiro_jugador: 89, pase_jugador: 65, talento_jugador: 74, defensa_jugador: 77, sueldo_jugador: 22500000
Presupuesto_anual: 2000000, entrenador: Juvenal, presidente: Ibai, cantidad_jugadores: 10, nombre_jugador: Iker, apellido_jugador: López, posicion_jugador: DEFENSA, tipo_jugador: DRAFT, velocidad_jugador: 73, fisico_jugador: 67, tiro_jugador: 79, pase_jugador: 84, talento_jugador: 69, defensa_jugador: 71, sueldo_jugador: 15000000

Procedimiento PL/SQL terminado correctamente.

*/