package Modelo.Jornada;

import Modelo.BaseDeDatos.BaseDeDatos;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import Modelo.Equipo.TEquipo;
import Modelo.Partido.Partido;
import Modelo.Split.Split;
import Modelo.Split.TSplit;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.File;
import java.util.ArrayList;


public class TJornada {

    public static void getJornadas() {
        try {
            Jornada[] jornadas = new Jornada[11];
            ArrayList<Partido> partidos = new ArrayList<>();
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document documento = dBuilder.parse(new File("src/Modelo/XML/jornadas.xml"));
            Element root = documento.getDocumentElement();

            NodeList listaJornada = root.getElementsByTagName("jornada");

            for (int i = 0; i < listaJornada.getLength(); i++) {
                NodeList listaJornda = (NodeList) listaJornada.item(i);
                Element jornada = (Element) listaJornada.item(i);
                jornadas[i].setId(Integer.parseInt(jornada.getAttribute("num_jornada")));
                jornadas[i].setNumJornada(i + 1);
                jornadas[i].setTipoJornada(Jornada.TipoJornada.valueOf(jornada.getElementsByTagName("tipo_jornada").item(0).getTextContent()));
                jornadas[i].setSplit(TSplit.ConsultarSplitDeJorada(jornadas[i]));
                for (int e = 0; e < listaJornada.getLength(); e++) {
                    Element partido = (Element) listaJornada.item(e);
                    partidos.add(new Partido());
                    int ultimo = partidos.size() - 1;
                    // todo rellenar partidos
                    //partidos.get(ultimo).;
                    partidos.get(ultimo).setEquipo1(TEquipo.getEquipoPorNombre(partido.getElementsByTagName("equipo1").item(0).getTextContent()));
                    partidos.get(ultimo).setEquipo2(TEquipo.getEquipoPorNombre(partido.getElementsByTagName("equipo2").item(0).getTextContent()));
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
