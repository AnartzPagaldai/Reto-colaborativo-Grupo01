package Modelo.Jugador;

import Controlador.Main;
import Modelo.BaseDeDatos.BaseDeDatos;
import Modelo.Enumeraciones.TipoSueldo;
import Modelo.Equipo.Equipo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Generar la clase TContratosJugador.
 * Esta clase contiene los métodos necesarios para hacer operaciones con los contratos de los jugadores (mostrar un contrato, insertarlos, etc.).
 */
public class TContratosJugador {
    public static boolean insertar(ContratoJugador contratoJugador){
        boolean insertar=false;
        try {
            BaseDeDatos.abrirConexion();
            PreparedStatement ps= BaseDeDatos.getCon().prepareStatement("INSERT INTO CONTRATOS_JUGADORES (ID_EQUIPO, ID_JUGADOR, FECHA_INICIO, FECHA_FIN, CLAUSULA, DORSAL, SUELDO) VALUES (?,?,?,?,?,?,?)");
            ps.setInt(1, contratoJugador.getEquipo().getId());
            ps.setInt(2, contratoJugador.getJugador().getId());
            ps.setDate(3, contratoJugador.getFechaInicio());
            ps.setDate(4, contratoJugador.getFechaFin());
            ps.setDouble(5, contratoJugador.getClausula());
            ps.setString(6, contratoJugador.getDorsal());
            ps.setInt(7, contratoJugador.getTipoSueldo().getValor());
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
    public static ArrayList<String> getDNIJugadoresSinContratos(ArrayList<String> dni){
        ArrayList<String> dniSinContrato=new ArrayList<>();
        try {
            BaseDeDatos.abrirConexion();
            PreparedStatement ps= BaseDeDatos.getCon().prepareStatement("select dni from jugadores j where not exists(select * from contratos_jugadores cj where cj.id_jugador=j.id)");
            ResultSet resulatdo=ps.executeQuery();
            while (resulatdo.next()){
                dniSinContrato.add(resulatdo.getString("dni"));
            }
            return dniSinContrato;
        }catch (Exception e){
            System.out.println(e.getClass()+ e.getMessage());
            return null;
        }
    }
    public static ArrayList<String> getID(ArrayList <String> id){
        try {
            BaseDeDatos.abrirConexion();
            PreparedStatement ps= BaseDeDatos.getCon().prepareStatement("select id from contratos_jugadores");
            ResultSet resulatdo=ps.executeQuery();
            while (resulatdo.next()){
                id.add(resulatdo.getString("id"));
            }
            return id;
        }catch (Exception e){
            System.out.println(e.getClass()+ e.getMessage());
            return null;
        }
    }
    public static boolean delete(String id) {
        boolean borrar = false;
        try {
            BaseDeDatos.abrirConexion();
            PreparedStatement ps= BaseDeDatos.getCon().prepareStatement("delete contratos_jugadores where id=?");
            ps.setInt(1, Integer.parseInt(id));
            int resul= ps.executeUpdate();
            System.out.println(resul +" Fila eliminada en Contratos_Jugadores");
            borrar = resul != 0;
            System.out.println();
            return borrar;
        }catch (Exception e){
            System.out.println(e.getClass()+ e.getMessage());
            return false;
        }

    }
    public static ContratoJugador datosContratoPorId(ContratoJugador contratoJugador){
        try {
            BaseDeDatos.abrirConexion();
            PreparedStatement ps= BaseDeDatos.getCon().prepareStatement("select * from contratos_jugadores where id=?");
            ps.setInt(1, contratoJugador.getId());
            ResultSet resultado=ps.executeQuery();
            if (resultado.next()){
                contratoJugador.setId(resultado.getInt("id"));
                Equipo equipo = Main.equipoPorID(resultado.getInt("id_equipo"));
                contratoJugador.setEquipo(equipo);
                Jugador jugador =Main.jugadorPorID(resultado.getInt("id_jugador"));
                contratoJugador.setJugador(jugador);
                contratoJugador.setFechaInicio(resultado.getDate("fecha_inicio"));
                contratoJugador.setFechaFin(resultado.getDate("fecha_fin"));
                contratoJugador.setClausula(resultado.getInt("clausula"));
                contratoJugador.setDorsal(resultado.getString("dorsal"));
                int sueldo= resultado.getInt("sueldo");
                TipoSueldo tipoSueldo = null;
                if (sueldo==10000000){
                    tipoSueldo=TipoSueldo.DIEZ_MILLONES;
                }
                if (sueldo==10500000){
                    tipoSueldo=TipoSueldo.DIEZ_MILLONES_MEDIO;
                }
                if (sueldo==(15000000)){
                    tipoSueldo=TipoSueldo.QUINCE_MILLONES;
                }
                if (sueldo==(22500000)){
                    tipoSueldo=TipoSueldo.VEINTIDOS_MILLONES_MEDIO;
                }
                contratoJugador.setTipoSueldo(tipoSueldo);
            }
            return contratoJugador;
        }catch (Exception e){
            System.out.println(e.getClass()+ e.getMessage());
            return null;
        }
    }

    public static boolean update(ContratoJugador contratoJugador) {
        boolean update=false;
        try {
            BaseDeDatos.abrirConexion();
            PreparedStatement ps= BaseDeDatos.getCon().prepareStatement("UPDATE contratos_jugadores SET id_equipo=?, fecha_fin=? , clausula=? , dorsal=? , sueldo=? WHERE id=?");
            ps.setInt(1, contratoJugador.getEquipo().getId());
            ps.setDate(2, contratoJugador.getFechaFin());
            ps.setDouble(3, contratoJugador.getClausula());
            ps.setString(4, contratoJugador.getDorsal());
            ps.setInt(5, contratoJugador.getTipoSueldo().getValor());
            ps.setInt(6, contratoJugador.getId());
            int resultado= ps.executeUpdate();
            System.out.println(resultado);
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
}
