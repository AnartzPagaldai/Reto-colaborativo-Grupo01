package Modelo.Partido;

import Modelo.BaseDeDatos.BaseDeDatos;

import Modelo.BaseDeDatos.BaseDeDatos;
import Modelo.Equipo.TEquipo;

public class TPartido {

    public static boolean actualizarPartido (Partido partido)
    {
        boolean valido = true;
        BaseDeDatos.executeUpdate("update partidos set GOLES_EQUIPO1 = , GOLES_EQUIPO2 = where ID_EQUIPO1 = , ID_EQUIPO2 =   ",new Object[]{partido.getGolesEquipo1(), partido.getGolesEquipo2(), partido.getEquipo1().getId(), partido.getEquipo2().getId()});

    return valido;}
    public static boolean insertarPartido(Partido partido) {
        return BaseDeDatos.executeUpdate("inset into partidos (id_jornada, fecha, lugar, id_equipo1, id_equipo2) values ((select max(id) from jornadas),?,?,?,?) ",
                new Object[]{partido.getFecha(), partido.getLugar(), partido.getEquipo1(), partido.getEquipo2()});
    }
}
