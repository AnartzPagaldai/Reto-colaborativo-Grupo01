package Modelo.Split;

import Modelo.BaseDeDatos.BaseDeDatos;
import Modelo.Jornada.Jornada;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TSplit {

    public static Split ConsultarSplitDeJorada(Jornada jornada) {
        Split split = new Split();
        BaseDeDatos.consultaObjeto(split, "select s.* from splits s, jornadas j where s.id = j.id_split and j.id = ?", new Object[]{jornada.getId()});
        return split;
    }

    public static boolean crearSplit(String tipo) {
        try {
            // VERIFICAR QUE SE AN INTRODUCIDO LOS PLAYOFS ANTES DE QUE SE INTORDUCCAN EN NUEVAS JORNADAS EN EL SIGUIENTE SPLIT
            BaseDeDatos.abrirConexion();
            CallableStatement statement = BaseDeDatos.getCon().prepareCall("{call GESTION_CALENDARIO.CREAR_SPLIT(?)}");
            statement.setString(1, tipo);
            statement.execute();
            BaseDeDatos.cerrarConexion();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean comprobarSplit() {
        Split split = new Split();
        BaseDeDatos.consultaObjeto(split,
                "select * from splits where id >all (select max(id_split) from jornadas) or  \n" +
                        "not exists (select 'x' from jornadas);", new Object[]{});
        return split != null;
    }
}
