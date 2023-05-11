package Modelo.Personal;
import Modelo.Enumeraciones.TipoPersonal;
import Modelo.BaseDeDatos.BaseDeDatos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TPersonal {
    public static boolean insertar(Personal p){
        boolean insertado=false;
        try {
            BaseDeDatos.abrirConexion();
            PreparedStatement ps=BaseDeDatos.getCon().prepareStatement("INSERT INTO PERSONALES (NOMBRE, APELLIDOS, DNI, TELEFONO, OFICIO, IMG) VALUES (?,?,?,?,?,?);");
            ps.setString(1,p.getNombre());
            ps.setString(2, p.getApellidos());
            ps.setString(3, p.getDni());
            ps.setInt(4, p.getTelefono());
            ps.setString(5, p.getOficio().toString());
            ps.setString(6, p.getImg());
            ResultSet resultado = ps.executeQuery();
            if (resultado.next()){
                insertado=true;
            }
            return insertado;
        }catch (Exception e){
            return insertado=false;
        }
    }
}
