package Modelo.Split;

import Modelo.BaseDeDatos.BaseDeDatos;
import Modelo.Jornada.Jornada;
import Modelo.Jornada.TJornada;
import Modelo.Partido.Partido;

import javax.print.attribute.standard.ReferenceUriSchemesSupported;
import java.sql.*;
import java.util.ArrayList;

/**
 * Generar la clase TSplit.
 * Esta clase contiene los métodos necesarios para hacer operaciones con los splits (mostrar splits, insertarlos, etc.).
 */
public class TSplit {

    public static Split ConsultarSplitDeJorada(Jornada jornada) {
        Split split = new Split();
        BaseDeDatos.consultaObjeto(split, "select s.* from splits s, jornadas j where s.id = j.id_split and j.id = ?", new Object[]{jornada.getId()});
        return split;
    }

    public static boolean crearSplit(String tipo) throws Exception {
        try {
            BaseDeDatos.abrirConexion();
            PreparedStatement st = BaseDeDatos.getCon().prepareStatement("select max(id) id from splits");
            ResultSet resultSet = st.executeQuery();
            resultSet.next();
            int id = resultSet.getInt("id");
            if (id != 0) {
                PreparedStatement st1 = BaseDeDatos.getCon().prepareStatement("select id_split from jornadas where id = (select max(id) from jornadas)");
                ResultSet resultSet1 = st1.executeQuery();
                if (resultSet1.next()) {
                    if (resultSet1.getInt("id_split") != id) {
                        throw new Exception("No se puede crear el siguiente split si no se ha terminado al anterior.");
                    }
                }
                ArrayList<Partido> partidos = TJornada.getUltimaJornada();
                if (partidos == null) {
                    throw new Exception("No se puede crear el siguiente split si no se ha terminado al anterior.");
                }
                if (partidos.get(0).getJornada().getNumJornada() != 13 ||
                        (partidos.get(0).getJornada().getNumJornada() == 13 &&
                                partidos.get(2).getGolesEquipo1() == 0 && partidos.get(2).getGolesEquipo2() == 0)) {
                    throw new Exception("No se puede crear el siguiente split si no se ha terminado el anterior.");
                }
            }

            CallableStatement statement = BaseDeDatos.getCon().prepareCall("{call GESTION_CALENDARIO.CREAR_SPLIT(?)}");
            statement.setString(1, tipo);
            statement.execute();
            BaseDeDatos.cerrarConexion();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean comprobarSplit() {
        try {
            BaseDeDatos.abrirConexion();
            PreparedStatement statement = BaseDeDatos.getCon().prepareStatement("select * from splits where id >all (select max(id_split) from jornadas) or not exists (select 'x' from jornadas)");
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
