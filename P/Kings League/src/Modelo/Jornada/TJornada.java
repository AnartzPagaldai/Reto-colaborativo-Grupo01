package Modelo.Jornada;

import Modelo.BaseDeDatos.BaseDeDatos;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TJornada {

    private static Connection con;

    public static void generarXMLjornadas() {
        try {
            BaseDeDatos.abrirConexion();
            con = BaseDeDatos.getCon();
            CallableStatement statement = con.prepareCall("call {paquete_xml.generar_xml_jornadas}");
            statement.execute();
        } catch (Exception e) {
            System.out.println("getXML\n" + e.getMessage());
        }
    }

    public static void getXMLjornadas() {
        try {
            BaseDeDatos.abrirConexion();
            con = BaseDeDatos.getCon();
            PreparedStatement statement = con.prepareStatement("select resul from xml_jornada where id = (select max(id) from xml_jornada)");
            ResultSet resultado = statement.executeQuery();
            String xml;
            if (resultado.next()) {
                xml = String.valueOf(resultado.getClob("resul"));
                BufferedWriter fichero = new BufferedWriter(new FileWriter("src/Modelo/Jornada"));
                fichero.write(xml);
                fichero.close();
            }
        } catch (Exception e) {

        }
    }
}
