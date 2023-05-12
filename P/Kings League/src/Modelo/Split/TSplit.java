package Modelo.Split;

import Modelo.BaseDeDatos.BaseDeDatos;
import Modelo.Jornada.Jornada;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TSplit {

    public static Split ConsultarSplitDeJorada(Jornada jornada) {
        try {
            Split split = new Split();
            PreparedStatement statement = BaseDeDatos.rellenarStatemet("select s.* from split s, jornadas j where s.id = j.id_split and j.id = ?", new Object[]{jornada.getId()});
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                split.resultSetObjeto(resultSet);
            }
            BaseDeDatos.cerrarConexion();
            return split;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
}
