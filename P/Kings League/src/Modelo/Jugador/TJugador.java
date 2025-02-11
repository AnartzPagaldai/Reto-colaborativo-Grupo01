package Modelo.Jugador;

import Modelo.BaseDeDatos.BaseDeDatos;
import Modelo.Equipo.Equipo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Generar la clase TJugador.
 * Esta clase contiene los métodos necesarios para hacer operaciones con los jugadores (mostrar un jugador, insertarlos, etc.).
 */
public class TJugador {
    public static boolean insertar(Jugador jugador){
      boolean insertar=false;
      try {
          BaseDeDatos.abrirConexion();
          PreparedStatement ps= BaseDeDatos.getCon().prepareStatement("INSERT INTO JUGADORES (NOMBRE, APELLIDOS, DNI, TELEFONO, POSICION, IMG, TIPO, VELOCIDAD, FISICO, TIRO, PASE, TALENTO, DEFENSA) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
          ps.setString(1, jugador.getNombre());
          ps.setString(2, jugador.getApellidos());
          ps.setString(3, jugador.getDni());
          ps.setString(4, jugador.getTelefono());
          ps.setString(5, jugador.getTipoPosicion().toString());
          ps.setString(6, jugador.getImg());
          ps.setString(7, jugador.getTipoJugador().getValor());
          ps.setInt(8, jugador.getVelocidad());
          ps.setInt(9, jugador.getFisico());
          ps.setInt(10, jugador.getTiro());
          ps.setInt(11, jugador.getPase());
          ps.setInt(12, jugador.getTalento());
          ps.setInt(13, jugador.getDefensa());
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
    public static boolean update(Jugador jugador){
        boolean update=false;
        try {
            BaseDeDatos.abrirConexion();
            PreparedStatement ps= BaseDeDatos.getCon().prepareStatement("UPDATE JUGADORES SET NOMBRE=?, APELLIDOS=?, DNI=?, TELEFONO=?, POSICION=?, IMG=?, TIPO=?, VELOCIDAD=?,FISICO=?, TIRO=?, PASE=?, TALENTO=?, DEFENSA=? WHERE id=?");
            ps.setString(1, jugador.getNombre());
            ps.setString(2, jugador.getApellidos());
            ps.setString(3, jugador.getDni());
            ps.setString(4, jugador.getTelefono());
            ps.setString(5, jugador.getTipoPosicion().toString());
            ps.setString(6, jugador.getImg());
            ps.setString(7, jugador.getTipoJugador().getValor());
            ps.setInt(8, jugador.getVelocidad());
            ps.setInt(9, jugador.getFisico());
            ps.setInt(10, jugador.getTiro());
            ps.setInt(11, jugador.getPase());
            ps.setInt(12, jugador.getTalento());
            ps.setInt(13, jugador.getDefensa());
            ps.setInt(14, jugador.getId());
            int resultado= ps.executeUpdate();
            if (resultado==1){
                update=true;
            }
            BaseDeDatos.cerrarConexion();
            return update;
        }catch (Exception e){
            System.out.println(e.getClass()+ e.getMessage());
            return false;
        }

    }
    public static boolean eliminar(Jugador jugador){
        try {
            BaseDeDatos.abrirConexion();
            PreparedStatement ps= BaseDeDatos.getCon().prepareStatement("delete jugadores where dni=?");
            ps.setString(1, jugador.getDni());
            int resul= ps.executeUpdate();
            System.out.println(resul +" Fila eliminada en Jugadores");
            return resul != 0;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getClass()+ e.getMessage());
            return false;
        }
    }

    public static boolean consultarPorDni(String dni) {
        Jugador jugador = new Jugador();
        BaseDeDatos.consultaObjeto(jugador, "select * from jugador where dni = ?", new Object[]{dni});
        return jugador != null;
    }

    public static ArrayList<String> selectDNI(ArrayList<String> dni){
        try {
            BaseDeDatos.abrirConexion();
            PreparedStatement ps= BaseDeDatos.getCon().prepareStatement("select distinct dni from jugadores");
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
    public static Jugador getJugadorPorDNI(Jugador jugador){
        try {
            BaseDeDatos.abrirConexion();
            PreparedStatement ps= BaseDeDatos.getCon().prepareStatement("select * from jugadores where dni=?");
            ps.setString(1, jugador.getDni());
            ResultSet resulatdo=ps.executeQuery();
            if (resulatdo.next()){
                jugador.setId(resulatdo.getInt("id"));
                jugador.setNombre(resulatdo.getString("nombre"));
                jugador.setApellidos(resulatdo.getString("apellidos"));
                jugador.setDni(resulatdo.getString("dni"));
                jugador.setTelefono(resulatdo.getString("telefono"));
                jugador.setTipoPosicion(Jugador.TipoPosicion.valueOf(resulatdo.getString("posicion")));
                jugador.setImg(resulatdo.getString("img"));
                String tipo =resulatdo.getString("tipo");
                Jugador.TipoJugador tipoJugador;
                if (tipo.equalsIgnoreCase("DRAFT")){
                    tipoJugador= Jugador.TipoJugador.DRAFT;
                }else tipoJugador= Jugador.TipoJugador.WILDCARD;
                jugador.setTipoJugador(tipoJugador);
                jugador.setVelocidad(resulatdo.getInt("velocidad"));
                jugador.setFisico(resulatdo.getInt("fisico"));
                jugador.setTiro(resulatdo.getInt("tiro"));
                jugador.setPase(resulatdo.getInt("pase"));
                jugador.setTalento(resulatdo.getInt("talento"));
                jugador.setDefensa(resulatdo.getInt("defensa"));
            }
            return jugador;
        }catch (Exception e){
            System.out.println(e.getClass()+ e.getMessage());
            return null;
        }
    }
    public static Jugador getJugadorPorID(Jugador jugador){
        try {
            BaseDeDatos.abrirConexion();
            PreparedStatement ps= BaseDeDatos.getCon().prepareStatement("select * from jugadores where id=?");
            ps.setInt(1, jugador.getId());
            ResultSet resulatdo=ps.executeQuery();
            if (resulatdo.next()){
                jugador.setId(resulatdo.getInt("id"));
                jugador.setNombre(resulatdo.getString("nombre"));
                jugador.setApellidos(resulatdo.getString("apellidos"));
                jugador.setDni(resulatdo.getString("dni"));
                jugador.setTelefono(resulatdo.getString("telefono"));
                jugador.setTipoPosicion(Jugador.TipoPosicion.valueOf(resulatdo.getString("posicion")));
                jugador.setImg(resulatdo.getString("img"));
                jugador.setTipoJugador(Jugador.TipoJugador.valueOf(resulatdo.getString("tipo")));
                jugador.setVelocidad(resulatdo.getInt("velocidad"));
                jugador.setFisico(resulatdo.getInt("fisico"));
                jugador.setTiro(resulatdo.getInt("tiro"));
                jugador.setPase(resulatdo.getInt("pase"));
                jugador.setTalento(resulatdo.getInt("talento"));
                jugador.setDefensa(resulatdo.getInt("defensa"));
            }
            return jugador;
        }catch (Exception e){
            System.out.println(e.getClass()+ e.getMessage());
            return null;
        }
    }
}
