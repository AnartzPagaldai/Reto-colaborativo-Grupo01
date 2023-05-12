package Modelo.Equipo;

import Modelo.BaseDeDatos.BaseDeDatos;
import Modelo.Enumeraciones.TipoPersonal;
import Modelo.Jugador.Jugador;
import Modelo.Personal.Personal;
import Modelo.Personal.TPersonal;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
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
                personales[0] = new Personal(resultSet.getInt("id_entrenador"), TipoPersonal.ENTRENADOR);
                personales[1] = new Personal(resultSet.getInt("id_presidente"), TipoPersonal.PRESIDENTE);
                TPersonal.ConsultarPersonal(personales[0]);
                TPersonal.ConsultarPersonal(personales[1]);
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

    public static Equipo getEquipoPorNombre(String nombre) {
        try {
            PreparedStatement statement = BaseDeDatos.rellenarStatemet("select * from equipo where upper(nombre) = upper(?)", new Object[]{nombre});
            ResultSet resultSet = statement.executeQuery();
            Equipo equipo = new Equipo();
            if (resultSet.next()) {
               equipo.resultSetObjeto(resultSet);
            }
            BaseDeDatos.cerrarConexion();
            return equipo;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
