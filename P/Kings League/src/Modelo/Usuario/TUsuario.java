package Modelo.Usuario;

import Modelo.BaseDeDatos.BaseDeDatos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TUsuario {
    public static boolean selectUsuarioAdmin(Usuario usuario){
        boolean existe = false;
        try {
            BaseDeDatos.abrirConexion();
            PreparedStatement ps= BaseDeDatos.getCon().prepareStatement("select * from usuarios where nombre=?");
            ps.setString(1, usuario.getNombre());
            ResultSet resultado= ps.executeQuery();
            if (resultado.next()){
                existe=true;
            }
            BaseDeDatos.cerrarConexion();
            return existe;
        }catch (Exception e){
            return false;
        }


    }
}
