package Modelo.Personal;

import Modelo.Enumeraciones.TipoPersonal;
import Modelo.BaseDeDatos.BaseDeDatos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TPersonal {
    public static boolean insertar(Personal p) {
        return BaseDeDatos.insert("INSERT INTO PERSONALES (NOMBRE, APELLIDOS, DNI, TELEFONO, OFICIO, IMG) VALUES (?,?,?,?,?,?);",
                new Object[]{p.getNombre(), p.getApellidos(), p.getDni(), p.getTelefono(), p.getOficio().toString(), p.getImg()});
    }

    public static void ConsultarPersonal(Personal personal) {
        BaseDeDatos.cosultaObjeto(personal,"select * from personales where id = ?", new Object[]{personal.getId()});
    }
}
