package Modelo.Jornada;

import Modelo.BaseDeDatos.BaseDeDatos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.*;

public class TJornada {

    public static void generarXMLjornadas() {
        try {
            BaseDeDatos.abrirConexion();
            CallableStatement statement = BaseDeDatos.getCon().prepareCall("{call PAQUETE_XML.GENERAR_XML_JORNADAS()}");
            statement.execute();
            BaseDeDatos.cerrarConexion();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void getXMLjornadas() {
        try {
            BaseDeDatos.abrirConexion();
            PreparedStatement statement = BaseDeDatos.getCon().prepareStatement("select resul from xml_jornada where id = (select max(id) from xml_jornada)");
            ResultSet resultado = statement.executeQuery();
            if (resultado.next()) {
                Clob xml = resultado.getClob("resul");
                BufferedReader reader = new BufferedReader(xml.getCharacterStream());
                String line;
                StringBuilder content = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    content.append(line);
                }
                BufferedWriter fichero = new BufferedWriter(new FileWriter("src/Modelo/Jornada/jornadas.xml"));
                fichero.write(String.valueOf(content));
                fichero.close();
            }
            BaseDeDatos.cerrarConexion();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
