package Modelo.Personal;

import Modelo.Enumeraciones.TipoPersonal;
import Modelo.BaseDeDatos.BaseDeDatos;
import Modelo.Equipo.Equipo;
import Modelo.Jugador.Jugador;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Generar la clase TPersonal.
 * Esta clase contiene los métodos necesarios para hacer operaciones con los miembros del personal (mostrar un miembro, insertarlos, etc.).
 */
public class TPersonal {
    public static boolean insertar(Personal personal) {
        boolean insertar=false;
        try {
            BaseDeDatos.abrirConexion();
            PreparedStatement ps =BaseDeDatos.getCon().prepareStatement("INSERT INTO PERSONALES (NOMBRE, APELLIDOS, DNI, TELEFONO, OFICIO, IMG) VALUES (?,?,?,?,?,?)");
            ps.setString(1, personal.getNombre());
            ps.setString(2, personal.getApellidos());
            ps.setString(3, personal.getDni());
            ps.setString(4, personal.getTelefono());
            ps.setString(5, personal.getOficio().toString());
            ps.setString(6, personal.getImg());
            ResultSet resultado=ps.executeQuery();
            if (resultado.next()){
                insertar=true;
            }
            return insertar;
        }
        catch (Exception e){
            System.out.println(e.getClass()+ e.getMessage());
            return false;
        }
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
    public static ArrayList<String> selectDNI(ArrayList<String> dni){
        try {
            BaseDeDatos.abrirConexion();
            PreparedStatement ps= BaseDeDatos.getCon().prepareStatement("select distinct dni from personales");
            ResultSet resulatdo=ps.executeQuery();
            dni.add("Seleccione un DNI");
            while (resulatdo.next()){
                dni.add(resulatdo.getString("dni"));
            }
            return dni;
        }catch (Exception e){
            System.out.println(e.getClass()+ e.getMessage());
            return null;
        }
    }
    public static Personal getPersonalPorDNI(Personal personal){
        try {
            BaseDeDatos.abrirConexion();
            PreparedStatement ps= BaseDeDatos.getCon().prepareStatement("select * from personales where dni=?");
            ps.setString(1, personal.getDni());
            ResultSet resulatdo=ps.executeQuery();
            if (resulatdo.next()){
                personal.setId(resulatdo.getInt("id"));
                personal.setNombre(resulatdo.getString("nombre"));
                personal.setApellidos(resulatdo.getString("apellidos"));
                personal.setDni(resulatdo.getString("dni"));
                personal.setTelefono(resulatdo.getString("telefono"));
                personal.setOficio(TipoPersonal.valueOf(resulatdo.getString("oficio")));
                personal.setImg(resulatdo.getString("img"));
            }
            return personal;
        }catch (Exception e){
            System.out.println(e.getClass()+ e.getMessage());
            return null;
        }
    }

    public static boolean update(Personal personal) {
        boolean update = false;
        try {
            BaseDeDatos.abrirConexion();
            PreparedStatement ps= BaseDeDatos.getCon().prepareStatement("UPDATE PERSONALES SET NOMBRE=?, APELLIDOS=?,DNI=? , TELEFONO=? , OFICIO=? , IMG=? WHERE ID=?");
            ps.setString(1, personal.getNombre());
            ps.setString(2, personal.getApellidos());
            ps.setString(3, personal.getDni());
            ps.setString(4, personal.getTelefono());
            ps.setString(5, personal.getOficio().toString());
            ps.setString(6, personal.getImg());
            ps.setInt(7, personal.getId());
            int resultado= ps.executeUpdate();
            if (resultado==1){
                update = true;
            }
            return update;
        }catch (Exception e){
            System.out.println(e.getClass()+ e.getMessage());
            return false;
        }
    }
    public static Personal getPersonalPorID(Personal personal){
        try {
            BaseDeDatos.abrirConexion();
            PreparedStatement ps= BaseDeDatos.getCon().prepareStatement("select * from personales where id=?");
            ps.setInt(1, personal.getId());
            ResultSet resulatdo=ps.executeQuery();
            if (resulatdo.next()){
                personal.setId(resulatdo.getInt("id"));
                personal.setNombre(resulatdo.getString("nombre"));
                personal.setApellidos(resulatdo.getString("apellidos"));
                personal.setDni(resulatdo.getString("dni"));
                personal.setTelefono(resulatdo.getString("telefono"));
                personal.setOficio(TipoPersonal.valueOf(resulatdo.getString("oficio")));
                personal.setImg(resulatdo.getString("img"));
            }
            return personal;
        }catch (Exception e){
            System.out.println(e.getClass()+ e.getMessage());
            return null;
        }
    }
}
