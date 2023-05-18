package Modelo.Jugador;

import Modelo.BaseDeDatos.BaseDeDatos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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

}
