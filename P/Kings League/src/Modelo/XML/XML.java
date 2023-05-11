package Modelo.XML;

import Modelo.BaseDeDatos.BaseDeDatos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.*;

public class XML {
    public static void generarXMLjornadas() {
        generarXML("GENERAR_XML_JORNADAS();");
    }

    public static void generarXMLclasificacion() {
        generarXML("GENERAR_XML_CLASIFICACION()");
    }

    private static void generarXML(String procedure) {
        try {
            BaseDeDatos.abrirConexion();
            CallableStatement statement = BaseDeDatos.getCon().prepareCall("{call PAQUETE_XML." + procedure + "}");
            statement.execute();
            BaseDeDatos.cerrarConexion();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void getXMLjornadas() {
        getXML("xml_jornada", "jornadas.xml");
    }

    public static void getXMLclasificacion() {
        getXML("xml_clasificacion", "clasificacio.xml");
    }

    private static void getXML(String tabla, String archivo) {
        try {
            BaseDeDatos.abrirConexion();
            PreparedStatement statement = BaseDeDatos.getCon().prepareStatement("select resul from " + tabla + " where id = (select max(id) from " + tabla + ")");
            ResultSet resultado = statement.executeQuery();
            if (resultado.next()) {
                Clob xml = resultado.getClob("resul");
                BufferedReader reader = new BufferedReader(xml.getCharacterStream());
                String line;
                StringBuilder content = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    content.append(line);
                }
                BufferedWriter fichero = new BufferedWriter(new FileWriter("src/Modelo/XML/" + archivo));
                fichero.write(String.valueOf(content));
                fichero.close();
            }
            BaseDeDatos.cerrarConexion();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
