package Modelo.Personal;

import Modelo.Enumeraciones.TipoPersonal;
import Modelo.BaseDeDatos.BaseDeDatos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TPersonal {
    public static boolean insertar(Personal p) {
        return BaseDeDatos.executeUpdate("INSERT INTO PERSONALES (NOMBRE, APELLIDOS, DNI, TELEFONO, OFICIO, IMG) VALUES (?,?,?,?,?,?);",
                new Object[]{p.getNombre(), p.getApellidos(), p.getDni(), p.getTelefono(), p.getOficio().toString(), p.getImg()});
    }

    public static void ConsultarPersonal(Personal personal) {
        BaseDeDatos.consultaObjeto(personal,"select * from personales where id = ?", new Object[]{personal.getId()});
    }
    public static boolean consutlarPorDni(String dni) {
        Personal personal = new Personal();
        BaseDeDatos.consultaObjeto(personal, "select * from personales wehre dni = ?", new Object[]{dni});
        return personal != null;
    }

    public static boolean actualizarPersonal(Personal personal) {
        return BaseDeDatos.executeUpdate( "update personales set nombre = ?, apellidos = ?, telefono = ?, oficio = ?, img = ? where upper(dni) = upper(?)",
                new Object[]{personal.getNombre(), personal.getApellidos(), personal.getTelefono(), String.valueOf(personal.getOficio()), personal.getDni()});
    }

    public static boolean borrarPersonal(String dni) {
        return BaseDeDatos.executeUpdate("delete from personales where dni = ?", new Object[]{dni});
    }
  
}
