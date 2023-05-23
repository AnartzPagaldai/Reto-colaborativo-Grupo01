package Modelo.Usuario;

import Modelo.BaseDeDatos.BaseDeDatos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Generar la clase TUsuario.
 * Esta clase contiene los métodos necesarios para hacer operaciones con los usuarios (mostrar usuario, insertarlos, etc.).
 */
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

    public static Usuario selectDatosUsuario (Usuario usuario) {
        Usuario user = null;
        try {
            BaseDeDatos.abrirConexion();
            PreparedStatement ps = BaseDeDatos.getCon().prepareStatement("select * from usuarios where nombre=? and contrasena=?");
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getContrasena());
            ResultSet resultado = ps.executeQuery();
            user = new Usuario();
            if (resultado.next()) {
                user.setNombre(resultado.getString("nombre"));
                user.setContrasena(resultado.getString("contrasena"));
                user.setCorreo(resultado.getString("correo"));
            }
            BaseDeDatos.cerrarConexion();

        } catch (Exception e) {

        }
        return user;
    }


    public static void updateUsuario (Usuario usuarioViejo, Usuario userNuevo) throws SQLException {
        BaseDeDatos.abrirConexion();
        PreparedStatement ps = BaseDeDatos.getCon().prepareStatement("update usuarios set nombre = ?, correo = ?, contrasena = ? where id=? ");
        ps.setString(1, userNuevo.getNombre().toUpperCase());
        ps.setString(2, userNuevo.getCorreo().toUpperCase());
        ps.setString(3, userNuevo.getContrasena().toUpperCase());
        ps.setInt(4, usuarioViejo.getId());
        int result = ps.executeUpdate();
        System.out.println(result+ " Fila actualizada");
        BaseDeDatos.cerrarConexion();

    }
    public static Usuario selectUsuarioDatos(Usuario usuario){
        try {
            BaseDeDatos.abrirConexion();
            PreparedStatement ps= BaseDeDatos.getCon().prepareStatement("select * from usuarios where nombre=?");
            ps.setString(1, usuario.getNombre().toUpperCase());
            ResultSet resultado= ps.executeQuery();
            if (resultado.next()){
                usuario=new Usuario();
                usuario.setId(resultado.getInt("ID"));
                usuario.setNombre(resultado.getString("nombre"));
                usuario.setContrasena(resultado.getString("contrasena"));
                usuario.setCorreo(resultado.getString("correo"));
                usuario.setTipoUsuario(Usuario.TipoUsuario.valueOf(resultado.getString("tipo")));
            }
            BaseDeDatos.cerrarConexion();
            return usuario;
        }catch (Exception e){
            return null;
        }
    }
}

