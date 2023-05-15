package Controlador;

import Modelo.Equipo.Equipo;
import Modelo.Equipo.TEquipo;
import Modelo.Jornada.TJornada;
import Modelo.Jugador.Jugador;
import Modelo.Partido.Partido;
import Modelo.Personal.Personal;
import Modelo.Usuario.TUsuario;
import Modelo.Usuario.Usuario;
import Modelo.XML.XML;
import Vista.vInicioSesion;
import Vista.vPrincipalUsuario;
import Vista.vRegistro;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Main {
    public static JFrame actual;
    public static JFrame vInicio;
    public static JFrame vPrinicpal;

    public static JFrame vRegistro;
    public static Usuario u;

    private static ArrayList<Jugador> jugadoresInfome;

    private static Personal[] personalesInfome = new Personal[2];

    private static ArrayList<Partido> partidos;

    public static void main(String[] args) throws MalformedURLException {
        generarVentanaInicio();
    }

    public static void generarVentanaInicio() throws MalformedURLException {
        vInicio = new JFrame("vInicioSesion");
        vInicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vInicioSesion inicioSesion = new vInicioSesion();
        vInicio.setContentPane(inicioSesion.getpPrincipal());
        vInicio.pack();
        vInicio.setVisible(true);
        vInicio.setExtendedState(Frame.MAXIMIZED_BOTH);
        actual.dispose();
    }

    public static void generarVentanaRegistro() throws MalformedURLException {
        vRegistro = new JFrame("vRegistro");
        vRegistro.setContentPane(new vRegistro().getpPrincipal());
        vRegistro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vRegistro.pack();
        vRegistro.setVisible(true);
        vRegistro.setExtendedState(Frame.MAXIMIZED_BOTH);
        actual = vRegistro;
        vInicio.dispose();
    }

    public static void generarVentanaPrincipal() throws MalformedURLException {
        vPrinicpal = new JFrame("vPrincipal");
        vPrinicpal.setContentPane(new vPrincipalUsuario().getpPrincipal());
        vPrinicpal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vPrinicpal.pack();
        vPrinicpal.setVisible(true);
        vPrinicpal.setExtendedState(Frame.MAXIMIZED_BOTH);
        actual = vPrinicpal;
        vInicio.dispose();
    }

    public static boolean selectUsuario(String nombre, String contrasena) {
        boolean existe;
        u = new Usuario();
        u.setNombre(nombre);
        u.setContrasena(contrasena);
        existe = TUsuario.selectUsuario(u);
        return existe;
    }

    public static boolean crearUsuario(String nombre, String correo, String contrasena, Usuario.TipoUsuario tipo) {
        boolean existe;
        boolean insertar;
        u = new Usuario();
        u.setNombre(nombre);
        u.setCorreo(correo);
        u.setContrasena(contrasena);
        u.setTipoUsuario(Usuario.TipoUsuario.valueOf(tipo.toString()));
        existe = TUsuario.selectUsuarioInsertar(u);
        if (existe) {
            insertar = false;
        } else insertar = TUsuario.insertar(u);
        return insertar;
    }

    public static void setObjetosInformeEquipo(String nombre) {
        Equipo equipo = new Equipo();
        equipo.setNombre("Porcinos FC");
        jugadoresInfome = TEquipo.getInfomeEquipos(equipo, personalesInfome);
    }

    public static HashMap<String, String> getPersonaPorPosicion(int posicion) {
        HashMap<String, String> persona = new HashMap<>();
        if (posicion == 0 || posicion == 1) {
            persona.put("nombre", personalesInfome[posicion].getNombre()); // ¿se necesita apellido?
            persona.put("img", personalesInfome[posicion].getImg());
            persona.put("oficio", String.valueOf(personalesInfome[posicion].getOficio()));
        } else {
            persona.put("nombre", jugadoresInfome.get(posicion - 2).getNombre());
            persona.put("img", jugadoresInfome.get(posicion - 2).getImg());
            persona.put("oficio", String.valueOf(jugadoresInfome.get(posicion - 2).getTipoJugador()));
            persona.put("posicion", String.valueOf(jugadoresInfome.get(posicion - 2).getTipoPosicion()));
            persona.put("velocidad", String.valueOf(jugadoresInfome.get(posicion - 2).getVelocidad()));
            persona.put("fisico", String.valueOf(jugadoresInfome.get(posicion - 2).getFisico()));
            persona.put("tiro", String.valueOf(jugadoresInfome.get(posicion - 2).getTiro()));
            persona.put("pase", String.valueOf(jugadoresInfome.get(posicion - 2).getPase()));
            persona.put("talento", String.valueOf(jugadoresInfome.get(posicion - 2).getTalento()));
            persona.put("defensa", String.valueOf(jugadoresInfome.get(posicion - 2).getDefensa()));
        }
        return persona;
    }


    public static HashMap[] getClasificacion() {
        return XML.getClasificacion();
    }

    public static boolean generarJornada() {
       return TJornada.generarJornadas();
    }
    public static HashMap[] getJornadas() throws Exception{
        partidos = TJornada.getJornadas();
        if (partidos == null) {
            throw new Exception("error al leer desde jornadas");
        }
        HashMap[] partidosMap = new HashMap[partidos.size()];
        for (int i = 0; i < partidos.size(); i++) {
            partidosMap[i] = dePartidosAhashmap(partidos.get(i));
        }
        return partidosMap;
    }

    public static HashMap<String, String> getJornada(int numJornada) {
        Partido partido = partidos.stream().filter(_partido -> _partido.getJornada().getNumJornada() == numJornada).findAny().orElse(null);
        return dePartidosAhashmap(partido);
    }
    private static HashMap<String, String> dePartidosAhashmap(Partido partido) {
        HashMap<String, String> partidoMap = new HashMap<>();
        partidoMap.put("numJornada", String.valueOf(partido.getJornada().getNumJornada()));
        partidoMap.put("fecha", partido.getFecha().toString());
        partidoMap.put("nombre_equiop1", partido.getEquipo1().getNombre());
        partidoMap.put("nombre_equiop2", partido.getEquipo2().getNombre());
        //partidoMap.put("logoEquipo1", partido.getEquipo1().getLogoImg());
        //partidoMap.put("logoEquipo2", partido.getEquipo2().getLogoImg());
        if (partido.getFecha().before(new Date())) {
            partidoMap.put("golesEquipo1", String.valueOf(partido.getGolesEquipo1()));
            partidoMap.put("golesEquipo2", String.valueOf(partido.getGolesEquipo2()));
        } else {
            partidoMap.put("golesEquipo1", "sin jugar");
            partidoMap.put("golesEquipo2", "sin jugar");
        }
        return partidoMap;
    }
}