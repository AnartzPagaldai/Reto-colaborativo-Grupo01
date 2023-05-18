package Modelo.Jugador;

import Modelo.BaseDeDatos.BaseDeDatos;
import Modelo.Equipo.Equipo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
          ps.setString(7, jugador.getTipoJugador().toString());
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
    public static boolean eliminar(Jugador jugador){
        boolean borrar = false;
        try {
            BaseDeDatos.abrirConexion();
            PreparedStatement ps= BaseDeDatos.getCon().prepareStatement("delete jugadores where dni=?");
            ps.setString(1, jugador.getDni());
            int resul= ps.executeUpdate();
            System.out.println(resul +" Fila eliminada en Jugadores");
            ResultSet resulatdo=ps.executeQuery();
            if (resulatdo.next()){
                borrar=true;
            }
            System.out.println();
            return borrar;
        }catch (Exception e){
            System.out.println(e.getClass()+ e.getMessage());
            return false;
        }
    }

    public static boolean consultarPorDni(String dni) {
        Jugador jugador = new Jugador();
        BaseDeDatos.cosultaObjeto(jugador, "select * from jugador where dni = ?", new Object[]{dni});
        return jugador != null;
    }
}
