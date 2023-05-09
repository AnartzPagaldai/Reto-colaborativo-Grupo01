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
