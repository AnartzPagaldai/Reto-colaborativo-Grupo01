package Modelo.Equipo;

import Modelo.BaseDeDatos.BaseDeDatos;
import Modelo.Enumeraciones.TipoPersonal;
import Modelo.Jugador.Jugador;
import Modelo.Personal.Personal;
import Modelo.Personal.TPersonal;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TEquipo {

    public static ArrayList<Jugador> getInfomeEquipos(Equipo equipo, Personal[] personales) {
        try {
            BaseDeDatos.abrirConexion();
            CallableStatement statement = BaseDeDatos.getCon().prepareCall("{call INFORMES.INFORME_EQUIPO(?,?)}");
            statement.registerOutParameter(1, OracleTypes.CURSOR);
            statement.setString(2, equipo.getNombre());
            statement.execute();
            ResultSet resultSet = (ResultSet) statement.getObject(1);
            ArrayList<Jugador> jugadores = new ArrayList<>();
            if (resultSet.next()) {
                equipo.resultSetObjeto(resultSet);
                personales[0] = new Personal(resultSet.getInt("id_presidente"), TipoPersonal.PRESIDENTE);
                personales[1] = new Personal(resultSet.getInt("id_entrenador"), TipoPersonal.ENTRENADOR);

                TPersonal.ConsultarPersonal(personales[0]);
                TPersonal.ConsultarPersonal(personales[1]);
                do {
                    jugadores.add(new Jugador());
                    jugadores.get(jugadores.size() - 1).resultSetObjeto(resultSet);
                } while (resultSet.next());
            }
            BaseDeDatos.cerrarConexion();
            return jugadores;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public static Equipo getEquipoPorNombre(String nombre) {
        Equipo equipo = new Equipo();
        BaseDeDatos.cosultaObjeto(equipo, "select * from equipos where upper(nombre) = upper(?)", new Object[]{nombre});
        return equipo;
    }


    public static ArrayList<Equipo> selectAllEquipos (ArrayList<Equipo> equipos) throws SQLException {
        BaseDeDatos.abrirConexion();

        PreparedStatement ps = BaseDeDatos.getCon().prepareStatement("select nombre, logo_img, color from equipos");
        ResultSet resul = ps.executeQuery();
        while (resul.next())
        {
            Equipo equipo = new Equipo();
            equipo.setNombre(resul.getString("nombre"));
            equipo.setLogoImg(resul.getString("logo_img"));
            equipo.setColor(resul.getString("color"));
            equipos.add(equipo);
        }
        BaseDeDatos.cerrarConexion();
    return equipos;}




}
