package Modelo.Partido;

import Modelo.BaseDeDatos.BaseDeDatos;

import Modelo.BaseDeDatos.BaseDeDatos;
import Modelo.Equipo.TEquipo;

public class TPartido {

    public static boolean actualizarPartido (Partido partido)
    {
        boolean valido = true;
        BaseDeDatos.executeUpdate("update partidos set ", );

    return valido}
    public static boolean insertarPartido(Partido partido) {
        return BaseDeDatos.executeUpdate("inset into partidos (id_jornada, fecha, lugar, id_equipo1, id_equipo2) values ((select max(id) from jornadas),?,?,?,?) ",
                new Object[]{partido.getFecha(), partido.getLugar(), partido.getEquipo1(), partido.getEquipo2()});
    }
}
