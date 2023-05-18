package Modelo.Jornada;

import Modelo.BaseDeDatos.BaseDeDatos;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import Modelo.Equipo.Equipo;
import Modelo.Equipo.TEquipo;
import Modelo.Partido.Partido;

import Modelo.Partido.TPartido;
import Modelo.Split.TSplit;
import Modelo.XML.XML;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.File;
import java.sql.CallableStatement;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;


public class TJornada {

    public static ArrayList<Partido> getJornadas() {
        return getJornadas("jornadas.xml");
    }

    public static ArrayList<Partido> getUltimaJornada() {
        return getJornadas("ultimaJornada.xml");
    }

    public static boolean generarJornadas() {
        try {
            BaseDeDatos.abrirConexion();
            CallableStatement statement = BaseDeDatos.getCon().prepareCall("{call GESTION_CALENDARIO.GENERAR_ENFRENTAMIENTOS");
            statement.execute();
            BaseDeDatos.cerrarConexion();
            return true;
        } catch (Exception e) {
            generarJornadas(); // todo especificar el error. Por si el error es que no se a creado split
        }
        return false;
    }


    private static ArrayList<Partido> getJornadas(String archivo) {
        try {
            Jornada[] jornadas = new Jornada[14];
            ArrayList<Partido> partidos = new ArrayList<>();
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document documento = dBuilder.parse(new File("src/Modelo/XML/" + archivo));
            Element root = documento.getDocumentElement();

            NodeList listaJornada = root.getElementsByTagName("jornada");

            for (int i = 0; i < listaJornada.getLength(); i++) {

                Element jornada = (Element) listaJornada.item(i);
                jornadas[i] = new Jornada();
                jornadas[i].setId(Integer.parseInt(jornada.getAttribute("id_jornada")));
                jornadas[i].setNumJornada(Integer.parseInt(jornada.getAttribute("num_jornada")));
                jornadas[i].setTipoJornada(Jornada.TipoJornada.valueOf(jornada.getElementsByTagName("tipo_jornada").item(0).getTextContent()));
                jornadas[i].setSplit(TSplit.ConsultarSplitDeJorada(jornadas[i]));
                NodeList listaPartidos = jornada.getElementsByTagName("partido");
                for (int e = 0; e < listaPartidos.getLength(); e++) {
                    partidos.add(new Partido());
                    int ultimo = partidos.size() - 1;
                    Element partido = (Element) listaPartidos.item(e);
                    partidos.get(ultimo).setId(Integer.parseInt(partido.getAttribute("id_partido")));
                    partidos.get(ultimo).setJornada(jornadas[i]);
                    partidos.get(ultimo).setEquipo1(TEquipo.getEquipoPorNombre(partido.getElementsByTagName("equipo1").item(0).getTextContent()));
                    partidos.get(ultimo).setEquipo2(TEquipo.getEquipoPorNombre(partido.getElementsByTagName("equipo2").item(0).getTextContent()));
                    String golesEquipo1 = String.valueOf(partido.getElementsByTagName("goles_equipo1").item(0));
                    if (golesEquipo1.equals("")) {
                        partidos.get(ultimo).setGolesEquipo1(Integer.parseInt(golesEquipo1));
                        partidos.get(ultimo).setGolesEquipo2(Integer.parseInt(partido.getElementsByTagName("goles_equipo2").item(0).getTextContent()));
                    }
                    Date date = new SimpleDateFormat("dd/MM/yy").parse(jornada.getElementsByTagName("fecha_partido").item(0).getTextContent());
                    partidos.get(ultimo).setFecha(new java.sql.Date(date.getTime()));
                    partidos.get(ultimo).setLugar(partido.getElementsByTagName("lugar_partido").item(0).getTextContent());
                }
            }
            return partidos;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean insertarJornada(Jornada jornada) {
        return BaseDeDatos.executeUpdate("insert into jornadas (num_jornada, tipo, id_spit) values (?,?,?)",
                new Object[]{jornada.getNumJornada(), jornada.getTipoJornada(), jornada.getSplit().getId()});
    }

    public static void crearPlayOff() throws Exception {
        Partido partido = getUltimaJornada().get(0);
        comprbarEinsertarJornadaPlayoff(partido, 11);
        HashMap<String, String>[] equipos = XML.getClasificacion();
        for (int i = 0, f = 7; i < f; i++, f--) {
            if (TPartido.insertarPartido(new Partido(java.sql.Date.valueOf(partido.getFecha().toLocalDate().plusDays(7)),
                    "Cupra Arena",
                    TEquipo.getEquipoPorNombre(equipos[i].get("nombre_equipo")),
                    TEquipo.getEquipoPorNombre(equipos[f].get("nombre_equipo")))))
                throw new Exception("no se an podido insertar todo los partido");
        }
    }

    public static void crearJornadaPlayOff(boolean semifinal) throws Exception {
        ArrayList<Partido> partidos = getUltimaJornada();
        if (semifinal) {
            comprbarEinsertarJornadaPlayoff(partidos.get(0), 12);
        } else {
           comprobarJornada(partidos.get(0), 13);
        }
        for (int i = 0; i < partidos.size() - 1; i++) {
            Equipo ganador1 = setGanador(partidos.get(i));
            Equipo ganador2 = setGanador(partidos.get(i + 1));
            if (TPartido.insertarPartido(new Partido(java.sql.Date.valueOf(partidos.get(i).getFecha().toLocalDate().plusDays(7)),
                    "Cupra Arena", ganador1, ganador2)))
                throw new Exception("no se an podido insertar todo los partido");
        }
    }

    private static void comprobarJornada(Partido partido, int numJornadaAnterior) throws Exception {
        if (partido.getJornada().getNumJornada() < numJornadaAnterior)
            throw new Exception("no se puede crear playoff sin abaer jugado la jornada anterior");
    }
    private static void comprbarEinsertarJornadaPlayoff(Partido partido, int numJornadaAnterior) throws Exception {
        comprobarJornada(partido, numJornadaAnterior);
        if (insertarJornada(new Jornada(partido.getJornada().getNumJornada() + 1, Jornada.TipoJornada.valueOf("PLAYOFF"), partido.getJornada().getSplit())))
            throw new Exception("no se a insertado la jornada");
    }

    private static Equipo setGanador(Partido partido) {
        if (partido.getGolesEquipo1() > partido.getGolesEquipo2()) {
            return partido.getEquipo1();
        } else {
            return partido.getEquipo2();
        }
    }

}
