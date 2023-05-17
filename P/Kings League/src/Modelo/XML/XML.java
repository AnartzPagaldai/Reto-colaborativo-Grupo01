package Modelo.XML;

import Modelo.BaseDeDatos.BaseDeDatos;
import Modelo.Equipo.TEquipo;
import Modelo.Jornada.TJornada;
import Modelo.Partido.Partido;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

public class XML {
    public static void generarXMLjornadas() {
        generarXML("GENERAR_XML_JORNADAS()");
        getXML("xml_jornada", "jornadas.xml");
    }

    public static void generarXMLclasificacion() {
        generarXML("GENERAR_XML_CLASIFICACION()");
        getXML("xml_clasificacion", "clasificacion.xml");
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

    public static HashMap[] getClasificacion() {
        try {
            HashMap[] clasificacion = new HashMap[12];
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document documento = dBuilder.parse(new File("src/Modelo/XML/clasificacion.xml"));
            Element root = documento.getDocumentElement();

            NodeList listaEquipos = root.getElementsByTagName("equipo");
            for (int i = 0; i < listaEquipos.getLength(); i++) {
                HashMap<String, String> equipo = new HashMap<>();
                Element tagEquipo = (Element) listaEquipos.item(i);
                equipo.put("posicion", tagEquipo.getAttribute("posiscion"));
                equipo.put("nombre_equipo", tagEquipo.getElementsByTagName("nombre").item(0).getTextContent());
                //equipo.put("logoImg", TEquipo.getEquipoPorNombre(equipo.get("nombre")).getLogoImg());
                equipo.put("victorias", tagEquipo.getElementsByTagName("victorias").item(0).getTextContent());
                equipo.put("golesAfavor", tagEquipo.getElementsByTagName("goles_a_favor").item(0).getTextContent());
                equipo.put("golesEnContra", tagEquipo.getElementsByTagName("goles_en_contra").item(0).getTextContent());
                equipo.put("diferenciaDeGoles", tagEquipo.getElementsByTagName("diferencia_de_goles").item(0).getTextContent());

                clasificacion[i] = equipo;
            }
            return clasificacion;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void generarXMlultimaJornada() {
        try {
            BaseDeDatos.abrirConexion();
            CallableStatement statement = BaseDeDatos.getCon().prepareCall("{call PAQUETE_XML.GENERAR_XML_ULTIMA_JORNADA(?)}");
            Clob clob = BaseDeDatos.getCon().createClob();
            statement.setClob(1, clob);
            statement.execute();
            BaseDeDatos.cerrarConexion();
            System.out.println(clob);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
