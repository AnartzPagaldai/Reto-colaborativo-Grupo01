package Modelo.Equipo;

import Modelo.BaseDeDatos.BaseDeDatos;
import Modelo.Enumeraciones.TipoPersonal;
import Modelo.Jugador.Jugador;
import Modelo.Personal.Personal;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TEquipo {

    public static void getInfomeEquipos(Equipo equipo, Personal[] personales) {
        try {
            BaseDeDatos.abrirConexion();
            CallableStatement statement = BaseDeDatos.getCon().prepareCall("{call INFORMES.INFORME_EQUIPO(?,?)}");
            statement.registerOutParameter(1, OracleTypes.CURSOR);
            statement.setString(2, equipo.getNombre());
            statement.execute();
            ResultSet resultSet = (ResultSet) statement.getObject(1);
            ArrayList<Jugador> jugadores = new ArrayList<>();
            if (resultSet.next()) {
                equipo.resultSetObjeto(resultSet);
                personales[0] = new Personal(resultSet.getInt("entrenador"), TipoPersonal.Entrenador);
                personales[1] = new Personal(resultSet.getInt("presidente"), TipoPersonal.Presidente);
                do {
                    jugadores.add(new Jugador());
                    jugadores.get(jugadores.size() - 1).resultSetObjeto(resultSet);
                } while (resultSet.next());
            }
            BaseDeDatos.cerrarConexion();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }


}
