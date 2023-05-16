package Modelo.Usuario;

import Modelo.BaseDeDatos.BaseDeDatos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TUsuario {
    public static boolean insertar(Usuario usuario){
        boolean insertar=false;
        try {
            BaseDeDatos.abrirConexion();
            PreparedStatement ps= BaseDeDatos.getCon().prepareStatement("INSERT INTO USUARIOS (NOMBRE, CONTRASENA, CORREO, TIPO) VALUES (?,?,?,?)");
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getContrasena());
            ps.setString(3, usuario.getCorreo());
            ps.setString(4, usuario.getTipoUsuario().toString());
            ResultSet resultado= ps.executeQuery();
            if (resultado.next()){
                insertar=true;
            }
            BaseDeDatos.cerrarConexion();
            return insertar;
        }catch (Exception e){
            System.out.println(e.getClass()+ e.getMessage());
            return false;
        }
    }
    public static boolean selectUsuario(Usuario usuario){
        boolean existe = false;
        try {
            BaseDeDatos.abrirConexion();
            PreparedStatement ps= BaseDeDatos.getCon().prepareStatement("select * from usuarios where nombre=? and contrasena=?");
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getContrasena());
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

    public static boolean selectUsuarioInsertar(Usuario usuario){
        boolean existe = false;
        try {
            BaseDeDatos.abrirConexion();
            PreparedStatement ps= BaseDeDatos.getCon().prepareStatement("select * from usuarios where nombre=? or correo=?");
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getCorreo());
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
