package Modelo.XML;

import Modelo.BaseDeDatos.BaseDeDatos;
import Modelo.Equipo.Equipo;
import Modelo.Equipo.TEquipo;
import oracle.jdbc.OracleTypes;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.nio.CharBuffer;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.HashMap;
import java.util.StringJoiner;

public class XML {
    public static void generarXMLjornadas() {
        generarXML("GENERAR_XML_JORNADAS()");
        getXML("xml_jornada", "jornadas.xml");
    }

    public static void generarXMLclasificacion() {
        generarXML("GENERAR_XML_CLASIFICACION()");
        getXML("xml_clasificacion", "clasificacion.xml");
    }

    public static void generarXMlultimaJornada() {
        try {
            BaseDeDatos.abrirConexion();
            CallableStatement statement = BaseDeDatos.getCon().prepareCall("{ call PAQUETE_XML.GENERAR_XML_ULTIMA_JORNADA(?)}");
            statement.registerOutParameter(1, OracleTypes.CLOB);
            statement.execute();
            Clob xml = statement.getClob(1);
            escribirXML(xml, "ultimaJornada.xml");
            BaseDeDatos.cerrarConexion();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                escribirXML(xml, archivo);
            }
            BaseDeDatos.cerrarConexion();
        } catch (Exception e) {
           throw new RuntimeException(e.getMessage());
        }
    }

    private static void escribirXML(Clob xml, String archivo) throws Exception {
        try (BufferedReader reader = new BufferedReader(xml.getCharacterStream());
             Writer writer = new FileWriter("src/Modelo/XML/" + archivo)) {
            char[] buffer = new char[4096];
            int bytesRead;
            while ((bytesRead = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, bytesRead);
            }
        }
    }

    public static HashMap<String, String>[] getClasificacion() {
        try {
            HashMap<String, String>[] clasificacion = new HashMap[13];
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document documento = dBuilder.parse(new File("src/Modelo/XML/clasificacion.xml"));
            Element root = documento.getDocumentElement();
            HashMap<String, String>split = new HashMap<>();
            split.put("split", root.getAttribute("split"));
            clasificacion[0]=split;
            NodeList listaEquipos = root.getElementsByTagName("equipo");
            for (int i = 0; i < listaEquipos.getLength(); i++) {
                HashMap<String, String> equipo = new HashMap<>();
                Element tagEquipo = (Element) listaEquipos.item(i);
                equipo.put("posicion", tagEquipo.getAttribute("posicion"));
                equipo.put("nombre_equipo", tagEquipo.getElementsByTagName("nombre").item(0).getTextContent());
                equipo.put("logoImg", TEquipo.getEquipoPorNombre(equipo.get("nombre_equipo")).getLogoImg());
                equipo.put("victorias", tagEquipo.getElementsByTagName("victorias").item(0).getTextContent());
                equipo.put("golesAfavor", tagEquipo.getElementsByTagName("goles_a_favor").item(0).getTextContent());
                equipo.put("golesEnContra", tagEquipo.getElementsByTagName("goles_en_contra").item(0).getTextContent());
                equipo.put("diferenciaDeGoles", tagEquipo.getElementsByTagName("diferencia_de_goles").item(0).getTextContent());

                clasificacion[i+1] = equipo;
            }

            return clasificacion;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
