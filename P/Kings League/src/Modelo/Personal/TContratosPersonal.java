package Modelo.Personal;

/**
 * Generar la clase TContratosPersonal.
 * Esta clase contiene los métodos necesarios para hacer operaciones con los contratos del personal (mostrar un contrato, insertarlos, etc.).
 */
import Controlador.Main;
import Modelo.BaseDeDatos.BaseDeDatos;
import Modelo.Enumeraciones.TipoSueldo;
import Modelo.Equipo.Equipo;
import Modelo.Jugador.ContratoJugador;
import Modelo.Jugador.Jugador;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TContratosPersonal {
    public static ArrayList<String> getDNIJugadoresSinContratos(ArrayList<String> dni){
        ArrayList<String> dniSinContrato=new ArrayList<>();
        try {
            BaseDeDatos.abrirConexion();
            PreparedStatement ps= BaseDeDatos.getCon().prepareStatement("select dni from personales j where not exists(select * from contratos_personal cj where cj.id_personal=j.id)");
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

    public static boolean insertar(ContratoPersonal contratoPersonal) {
        boolean insertar=false;
        try {
            BaseDeDatos.abrirConexion();
            PreparedStatement ps= BaseDeDatos.getCon().prepareStatement("INSERT INTO CONTRATOS_PERSONAL (ID_PERSONAL, ID_EQUIPO, FECHA_INICIO, FECHA_FIN, SUELDO) VALUES (?,?,?,?,?)");
            ps.setInt(1, contratoPersonal.getPersonal().getId());
            ps.setInt(2, contratoPersonal.getEquipo().getId());
            ps.setDate(3, contratoPersonal.getFechaInicio());
            ps.setDate(4, contratoPersonal.getFechaFin());
            ps.setInt(5, contratoPersonal.getSueldo().getValor());
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
    public static ArrayList<String> getID(ArrayList <String> id){
        try {
            BaseDeDatos.abrirConexion();
            PreparedStatement ps= BaseDeDatos.getCon().prepareStatement("select id from contratos_personal");
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
    public static ContratoPersonal datosContratoPorId(ContratoPersonal contratoPersonal){
        try {
            BaseDeDatos.abrirConexion();
            PreparedStatement ps= BaseDeDatos.getCon().prepareStatement("select * from contratos_personal where id=?");
            ps.setInt(1, contratoPersonal.getId());
            ResultSet resultado=ps.executeQuery();
            if (resultado.next()){
                contratoPersonal.setId(resultado.getInt("id"));
                Equipo equipo = Main.equipoPorID(resultado.getInt("id_equipo"));
                contratoPersonal.setEquipo(equipo);
                Personal personal =Main.personalPorID(resultado.getInt("id_personal"));
                contratoPersonal.setPersonal(personal);
                contratoPersonal.setFechaInicio(resultado.getDate("fecha_inicio"));
                contratoPersonal.setFechaFin(resultado.getDate("fecha_fin"));
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
                contratoPersonal.setSueldo(tipoSueldo);
            }
            return contratoPersonal;
        }catch (Exception e){
            System.out.println(e.getClass()+ e.getMessage());
            return null;
        }
    }

    public static boolean update(ContratoPersonal contratoPersonal) {
        boolean update=false;
        try {
            BaseDeDatos.abrirConexion();
            PreparedStatement ps= BaseDeDatos.getCon().prepareStatement("UPDATE contratos_personal SET id_equipo=?, fecha_fin=? , sueldo=? WHERE id=?");
            ps.setInt(1, contratoPersonal.getEquipo().getId());
            ps.setDate(2, contratoPersonal.getFechaFin());
            ps.setInt(3, contratoPersonal.getSueldo().getValor());
            ps.setInt(4, contratoPersonal.getId());
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

    public static boolean delete(String id) {
        boolean borrar = false;
        try {
            BaseDeDatos.abrirConexion();
            PreparedStatement ps= BaseDeDatos.getCon().prepareStatement("delete contratos_personal where id=?");
            ps.setInt(1, Integer.parseInt(id));
            int resul= ps.executeUpdate();
            System.out.println(resul +" Fila eliminada");
            if (resul==1){
                borrar=true;
            }
            System.out.println();
            return borrar;
        }catch (Exception e){
            System.out.println(e.getClass()+ e.getMessage());
            return false;
        }
    }
}
