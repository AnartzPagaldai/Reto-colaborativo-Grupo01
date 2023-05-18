package Controlador;

import Modelo.Equipo.Equipo;
import Modelo.Equipo.TEquipo;
import Modelo.Jornada.TJornada;
import Modelo.Jugador.Jugador;
import Modelo.Partido.Partido;
import Modelo.Personal.Personal;
import Modelo.Usuario.TUsuario;
import Modelo.Usuario.Usuario;
import Vista.*;
import Modelo.XML.*;
import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


public class Main {
    public static JFrame actual;
    public static JFrame vInicio;
    public static JFrame vPrinicpalUsuario;
    public static JFrame vPrinicpalAdmin;
    public static JFrame vEquipos;
    public static JFrame vRegistro;
    public static JFrame vJugadores;
    public static JFrame vEquipoActualizar;
    public static JFrame vUsuario;
    public static Usuario u;
    public static Equipo equipo=new Equipo();
    private static ArrayList<Jugador> jugadoresInfome;

    private static Usuario usuarioInicio = new Usuario();

    private static Personal[] personalesInfome = new Personal[2];

    private static ArrayList<Partido> partidos;

    public static void main(String[] args) throws MalformedURLException {
        generarVentanaInicio();
        /*try {
            HashMap[] mp = getJornadas();
            for (HashMap hashMap : mp) {
                System.out.println(hashMap);
            }
            HashMap[] map = getJornada(1);
            for (HashMap hashMap : map) {
                System.out.println(hashMap);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }*/
    }
    public static void cerrarSesion() {
        actual.dispose();
        vInicio.setVisible(true);
    }

    public static void PrincipalUsuario() {
        actual.dispose();
        vPrinicpalUsuario.setVisible(true);
    }
    public static void vEquipos() {
        actual.dispose();
        vEquipos.setVisible(true);
    }
    public static void PrincipalAdmin() {
        actual.dispose();
        vPrinicpalUsuario.setVisible(true);
    }
    public static void generarVentanaInicio() throws MalformedURLException {
        vInicio = new JFrame("vInicioSesion");
        vInicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vInicioSesion inicioSesion = new vInicioSesion();
        vInicio.setContentPane(inicioSesion.getpPrincipal());
        vInicio.pack();
        vInicio.setVisible(true);
        vInicio.setExtendedState(Frame.MAXIMIZED_BOTH);
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

    public static void generarVentanaPrincipalUsuario() throws MalformedURLException {
        vPrinicpalUsuario = new JFrame("vPrincipalUsuario");
        vPrinicpalUsuario.setContentPane(new vPrincipalUsuario().getpPrincipal());
        vPrinicpalUsuario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vPrinicpalUsuario.pack();
        vPrinicpalUsuario.setVisible(true);
        vPrinicpalUsuario.setExtendedState(Frame.MAXIMIZED_BOTH);
        actual = vPrinicpalUsuario;
        vInicio.dispose();
    }

    public static void generarVentanaPrincipalAdmin() throws MalformedURLException {
        vPrinicpalAdmin = new JFrame("vPrincipalAdmin");
        vPrinicpalAdmin.setContentPane(new vPrincipalAdmin().getpPrincipal());
        vPrinicpalAdmin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vPrinicpalAdmin.pack();
        vPrinicpalAdmin.setVisible(true);
        vPrinicpalAdmin.setExtendedState(Frame.MAXIMIZED_BOTH);
        actual = vPrinicpalAdmin;
        vInicio.dispose();

    }

    public static void generarVentanaEquipos() throws MalformedURLException {
        vEquipos= new JFrame("vConsultarEquipos");
        vEquipos.setContentPane(new vConsultarEquipos().getpPrincipal());
        vEquipos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vEquipos.pack();
        vEquipos.setVisible(true);
        vEquipos.setExtendedState(Frame.MAXIMIZED_BOTH);
        vPrinicpalUsuario.setVisible(false);
        actual = vEquipos;
    }
    
    public static void generarVentanaJugadores() throws MalformedURLException {
        vJugadores= new JFrame("vConsultarJugadores");
        vJugadores.setContentPane(new vConsultarJugadores().getpPrincipal());
        vJugadores.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vJugadores.pack();
        vJugadores.setVisible(true);
        vJugadores.setExtendedState(Frame.MAXIMIZED_BOTH);
        vEquipos.setVisible(false);
        actual=vJugadores;
    }

    public static void generarVentanaAjustesUsuario() throws MalformedURLException {
        vUsuario = new JFrame("vPerfilUsuario");
        vUsuario.setContentPane(new vPerfilUsuario().getpPrincipal());
        vUsuario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vUsuario.pack();
        vUsuario.setVisible(true);
        vUsuario.setExtendedState(Frame.MAXIMIZED_BOTH);
        vPrinicpalUsuario.setVisible(false);
        actual=vUsuario;
    }
    public static void generarActualzarEquipos() throws MalformedURLException {
        vEquipoActualizar= new JFrame("vActualizarEquipo");
        vEquipoActualizar.setContentPane(new vActualizarEquipo().getpPrincipal());
        vEquipoActualizar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vEquipoActualizar.pack();
        vEquipoActualizar.setVisible(true);
        vEquipoActualizar.setExtendedState(Frame.MAXIMIZED_BOTH);
        actual=vEquipoActualizar;
        vPrinicpalAdmin.setVisible(false);
    }

    public static boolean selectUsuario(String nombre, String contrasena) {
        boolean existe;
        u = new Usuario();
        u.setNombre(nombre);
        u.setContrasena(contrasena);
        existe = TUsuario.selectUsuario(u);
        if (existe) {
            usuarioInicio = TUsuario.selectDatosUsuario(u);
        }
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
        } else {
            insertar = TUsuario.insertar(u);
            usuarioInicio = u;
        }
        return insertar;
    }

    public static HashMap setObjetosInformeEquipo(String nombre) {
        equipo = new Equipo();
        HashMap<String, String> equipos=new HashMap<>();
        equipo.setNombre(nombre);
        jugadoresInfome = TEquipo.getInfomeEquipos(equipo, personalesInfome);
        equipos.put("nombre", equipo.getNombre());
        equipos.put("logo", equipo.getLogoImg());
        equipos.put("color", equipo.getColor());
        return equipos;
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
        // todo verivicar que se a creado split
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

    public static HashMap[] getJornada(int numJornada) {
        List<Partido> partidoDeJornada = partidos.stream().filter(_partido -> _partido.getJornada().getNumJornada() == numJornada).collect(Collectors.toList());
        HashMap[] partidosMap = new HashMap[partidoDeJornada.size()];
        for (int i = 0; i < partidoDeJornada.size(); i++) {
            partidosMap[i] = dePartidosAhashmap(partidoDeJornada.get(i));
        }
        return partidosMap;
    }
    private static HashMap<String, String> dePartidosAhashmap(Partido partido) {
        HashMap<String, String> partidoMap = new HashMap<>();
        partidoMap.put("numJornada", String.valueOf(partido.getJornada().getNumJornada()));
        partidoMap.put("fecha", partido.getFecha().toString());
        partidoMap.put("nombre_equiop1", partido.getEquipo1().getNombre());
        partidoMap.put("nombre_equiop2", partido.getEquipo2().getNombre());
        partidoMap.put("logoEquipo1", partido.getEquipo1().getLogoImg());
        partidoMap.put("logoEquipo2", partido.getEquipo2().getLogoImg());
        if (partido.getFecha().before(new Date())) {
            partidoMap.put("golesEquipo1", String.valueOf(partido.getGolesEquipo1()));
            partidoMap.put("golesEquipo2", String.valueOf(partido.getGolesEquipo2()));
        } else {
            partidoMap.put("golesEquipo1", "sin jugar");
            partidoMap.put("golesEquipo2", "sin jugar");
        }
        return partidoMap;
    }
    public static int getCantidadPersonas(){
        return jugadoresInfome.size()+2;
    }

    public static ArrayList<Equipo> rellenarBotones() throws SQLException {
        // no se pueden pasar objetos a la vista
        return TEquipo.selectAllEquipos();
   }

   public static void crearPlayOff() throws Exception {
        TJornada.crearPlayOff();
   }
    public static Equipo getEquipo(){
        return equipo;
    }

    public static void setNombreEquipo(String nombre){
        equipo.setNombre(nombre);
    }
    public static String getNombreEquipo(){
        return equipo.getNombre();
    }
    public static String buscarNombre()
    {
        return usuarioInicio.getNombre();
    }

    public static String buscarCorreo()
    {
        return usuarioInicio.getCorreo();
    }

    public static String buscarContrasena()
    {
        return usuarioInicio.getContrasena();
    }

    public static void actalizarUsuario(Usuario usuarioAntes, Usuario usuarioActual) throws SQLException {
        TUsuario.updateUsuario(usuarioAntes, usuarioActual);
    }

    public static String getUsuarioTipo(String nombre, String contrasena){
        Usuario usuarioAntes=new Usuario();
        usuarioAntes.setNombre(nombre);
        usuarioAntes.setContrasena(contrasena);
        usuarioAntes=TUsuario.selectUsuarioDatos(usuarioAntes);
        return usuarioAntes.getTipoUsuario().toString();
    }
    public static Usuario getUsuario(String nombre, String contrasena){
        Usuario usuario=new Usuario();
        usuario.setNombre(nombre);
        usuario.setContrasena(contrasena);
        usuario=TUsuario.selectUsuarioDatos(usuario);
        return usuario;
    }
}
