package Controlador;

import Modelo.BaseDeDatos.BaseDeDatos;
import Modelo.Enumeraciones.TipoPersonal;
import Modelo.Enumeraciones.TipoSueldo;
import Modelo.Equipo.Equipo;
import Modelo.Equipo.TEquipo;
import Modelo.Jornada.TJornada;
import Modelo.Jugador.ContratoJugador;
import Modelo.Jugador.Jugador;
import Modelo.Jugador.TContratosJugador;
import Modelo.Jugador.TJugador;
import Modelo.Partido.Partido;
import Modelo.Partido.TPartido;
import Modelo.Personal.Personal;
import Modelo.Personal.TPersonal;
import Modelo.Split.TSplit;
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
    public static JFrame vPersonalActualizar;
    public static JFrame vUsuario;
    public static JFrame vInsertarJugadores;
    public static JDialog vBorrarJugadores;
    public static JFrame vClasificacion;
    public static JFrame vContratosJ;
    public static JFrame vUpdateJugadores;
    public static JFrame vInsertEquipos;
    public static JDialog vBorrarEquipos;
    public static JDialog vBorrarPersonales;
    public static JFrame vBorrarSplits;
    public static JFrame vBorrarUsuarios;
    public static JFrame vInsertarPersonal;
    private static JFrame vUpdatecontratosjugadores;
    public static JFrame vInsertarResultados;
    public static JFrame vInsertarEquipos;

    public static JFrame vPartido;
    public static Usuario u;

    public static Equipo equipo=new Equipo();
    private static ArrayList<Jugador> jugadoresInfome;

    private static Usuario usuarioInicio = new Usuario();

    private static Personal[] personalesInfome = new Personal[2];

    private static ArrayList<Partido> partidos;

    public static void main(String[] args) throws MalformedURLException {
        generarVentanaInicio();
        //TJornada.generarJornadas();
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
        //crearSplit("verano");
        //generarJornada();
        //generarXml();
    }
    public static void cerrarSesion() {
        actual.dispose();
        vInicio.setVisible(true);
    }

    public static void PrincipalUsuario() {
        actual.dispose();
        vPrinicpalUsuario.setVisible(true);
    }

    public static void Principal() {
        actual.dispose();
        if (vPrinicpalUsuario != null)
           vPrinicpalUsuario.setVisible(true);
        else
            vPrinicpalAdmin.setVisible(true);
    }
    public static void vEquipos() {
        actual.dispose();
        vEquipos.setVisible(true);
    }
    public static void PrincipalAdmin() {
        actual.dispose();
        vPrinicpalAdmin.setVisible(true);
        vPrinicpalAdmin.setEnabled(true);
    }

    // Crear ventanas
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
        if (vPrinicpalUsuario != null)
            vPrinicpalUsuario.setVisible(false);
        else
            vPrinicpalAdmin.setVisible(false);
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
    public static void generarActualizarEquipo() throws MalformedURLException {
        vEquipoActualizar = new JFrame("vActualizarEquipo");
        vEquipoActualizar.setContentPane(new vActualizarEquipo().getpPrincipal());
        vEquipoActualizar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vEquipoActualizar.pack();
        vEquipoActualizar.setVisible(true);
        vEquipoActualizar.setExtendedState(Frame.MAXIMIZED_BOTH);
        actual=vEquipoActualizar;
        vPrinicpalAdmin.setVisible(false);
    }
    public static void generarInsertarJugadores() throws MalformedURLException {
        vInsertarJugadores = new JFrame("vInsertarJugadores");
        vInsertarJugadores.setContentPane(new vInsertarJugadores().getpPrincipal());
        vInsertarJugadores.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vInsertarJugadores.pack();
        vInsertarJugadores.setExtendedState(Frame.MAXIMIZED_BOTH);
        vInsertarJugadores.setVisible(true);
        vPrinicpalAdmin.setVisible(false);
        actual=vInsertarJugadores;
    }
    public static void generarActualizarJugadores() throws MalformedURLException {
        vUpdateJugadores= new JFrame("vUpdateJugadores");
        vUpdateJugadores.setContentPane(new vUpdateJugadores().getpPrincipal());
        vUpdateJugadores.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vUpdateJugadores.pack();
        vUpdateJugadores.setExtendedState(Frame.MAXIMIZED_BOTH);
        vUpdateJugadores.setVisible(true);
        vPrinicpalAdmin.setVisible(false);
        actual=vUpdateJugadores;
    }
    public static void generarBorrarJugadores() throws MalformedURLException {
        vBorrarJugadores = new vBorrarJugador();
        vBorrarJugadores.pack();
        vBorrarJugadores.setVisible(true);
        vPrinicpalAdmin.setEnabled(false);
    }
    public static void generarVentanaClasificacion() throws MalformedURLException {
        vClasificacion = new JFrame("vClasificacionEquipos");
        vClasificacion.setContentPane(new vClasificacionEquipos().getpPrincipal());
        vClasificacion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vClasificacion.pack();
        vClasificacion.setVisible(true);
        vClasificacion.setExtendedState(Frame.MAXIMIZED_BOTH);
        vPrinicpalUsuario.setVisible(false);
        actual=vClasificacion;
    }
    public static void generarVentanaInsertarEquipos() throws MalformedURLException {
        vInsertEquipos= new JFrame("vInsertEquipos");
        vInsertEquipos.setContentPane(new vInsertEquipos().getpPrincipal());
        vInsertEquipos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vInsertEquipos.pack();
        vInsertEquipos.setVisible(true);
        vInsertEquipos.setExtendedState(Frame.MAXIMIZED_BOTH);
        vPrinicpalUsuario.setVisible(false);
        actual=vInsertEquipos;
    }
    public static void generarBorrarEquipos() throws MalformedURLException {
        vBorrarEquipos = new vBorrarEquipo();
        vBorrarEquipos.pack();
        vBorrarEquipos.setVisible(true);
        vPrinicpalAdmin.setEnabled(false);
    }
    public static void generarBorrarPersonales() throws MalformedURLException {
        vBorrarPersonales = new vBorrarPersonal();
        vBorrarPersonales.pack();
        vBorrarPersonales.setVisible(true);
        vPrinicpalAdmin.setEnabled(false);
    }
    public static void generarInsertarContratosJugador() throws MalformedURLException, SQLException {
        vContratosJ = new JFrame("vDeleteSplit");
        vContratosJ.setContentPane(new vInsertarContratosJugadores().getpPrincipal());
        vContratosJ.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vContratosJ.pack();
        vContratosJ.setVisible(true);
        vContratosJ.setExtendedState(Frame.MAXIMIZED_BOTH);
        vPrinicpalAdmin.setVisible(false);
        actual=vContratosJ;
    }
    public static void generarBorrarSplits() throws MalformedURLException {
        vBorrarSplits = new JFrame("vDeleteSplit");
        vBorrarSplits.setContentPane(new vDeleteSplit().getpPrincipal());
        vBorrarSplits.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vBorrarSplits.pack();
        vBorrarSplits.setVisible(true);
        vBorrarSplits.setExtendedState(Frame.MAXIMIZED_BOTH);
        vPrinicpalAdmin.setVisible(false);
        actual=vBorrarSplits;
    }
    public static void generarBorrarUsuarios() throws MalformedURLException {
        vBorrarUsuarios = new JFrame("vDeleteusuarios");
        vBorrarUsuarios.setContentPane(new vDeleteusuarios().getpPrincipal());
        vBorrarUsuarios.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vBorrarUsuarios.pack();
        vBorrarUsuarios.setVisible(true);
        vBorrarUsuarios.setExtendedState(Frame.MAXIMIZED_BOTH);
        vPrinicpalAdmin.setVisible(false);
        actual=vBorrarUsuarios;
    }
    public static void generarInsertarPersonal() throws MalformedURLException {
        vInsertarPersonal = new JFrame("vInsertarPersonal");
        vInsertarPersonal.setContentPane(new vInsertarPersonal().getpPrincipal());
        vInsertarPersonal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vInsertarPersonal.pack();
        vInsertarPersonal.setVisible(true);
        vInsertarPersonal.setExtendedState(Frame.MAXIMIZED_BOTH);
        vPrinicpalAdmin.setVisible(false);
        actual=vInsertarPersonal;
    }
    public static void generarInsertarResultados() throws Exception {
        vInsertarResultados = new JFrame("vInsertarResultados");
        vInsertarResultados.setContentPane(new vInsertarResultados().getpPrincipal());
        vInsertarResultados.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vInsertarResultados.pack();
        vInsertarResultados.setVisible(true);
        vInsertarResultados.setExtendedState(Frame.MAXIMIZED_BOTH);
        vPrinicpalAdmin.setVisible(false);
        actual=vInsertarResultados;
    }
    public static void generarInsertarEquipos() throws Exception {
        vInsertarEquipos = new JFrame("vInsertEquipos");
        vInsertarEquipos.setContentPane(new vInsertEquipos().getpPrincipal());
        vInsertarEquipos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vInsertarEquipos.pack();
        vInsertarEquipos.setVisible(true);
        vInsertarEquipos.setExtendedState(Frame.MAXIMIZED_BOTH);
        vPrinicpalAdmin.setVisible(false);
        actual=vInsertarEquipos;
    }
    public static void generarUpdateContratosJugadores() throws Exception {
        vUpdatecontratosjugadores= new JFrame("vUpdatecontratosjugadores");
        vUpdatecontratosjugadores.setContentPane(new vUpdatecontratosjugadores().getpPrincipal());
        vUpdatecontratosjugadores.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vUpdatecontratosjugadores.pack();
        vUpdatecontratosjugadores.setVisible(true);
        vUpdatecontratosjugadores.setExtendedState(Frame.MAXIMIZED_BOTH);
        vPrinicpalAdmin.setVisible(false);
        actual=vUpdatecontratosjugadores;
    }

    public static void generarVerPartido() throws Exception {
        vPartido = new JFrame("vParido");
        vPartido.setContentPane(new vPartidos().getpPrincipal());
        vPartido.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vPartido.pack();
        vPartido.setVisible(true);
        vPartido.setExtendedState(Frame.MAXIMIZED_BOTH);
        if (vPrinicpalUsuario != null )
            vPrinicpalUsuario.setVisible(false);
        else
            vPrinicpalAdmin.setVisible(false);
        actual=vPartido;
    }

    // Métodos para los CRUD
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


    public static ArrayList<Integer> getJornadas() throws Exception{
        partidos = TJornada.getJornadas();
        ArrayList<Integer> NumJornadas = new ArrayList<>();
        if (partidos == null) {
            throw new Exception("error al leer desde jornadas");
        }
        for (int x = 0; x< partidos.size(); x += 6) {
            NumJornadas.add(partidos.get(x).getJornada().getNumJornada());
        }
        return NumJornadas;}
    public static HashMap<String, String>[] getUltimaJornada() {
        return dePartidosAhashmap(TJornada.getUltimaJornada());
    }

    public static HashMap<String, String>[] getJornada(int numJornada) {
            ArrayList<Partido> partidoDeJornada = partidos.stream().filter(_partido -> _partido.getJornada().getNumJornada() == numJornada).collect(Collectors.toCollection(ArrayList::new));;
        return dePartidosAhashmap(partidoDeJornada);
    }
    private static HashMap<String, String>[] dePartidosAhashmap(ArrayList<Partido> partidos) {
        ArrayList<HashMap<String, String>> partidosMap = new ArrayList<>();
        for (int i = 0; i < partidos.size(); i++) {
            HashMap<String, String> partidoMap = new HashMap<>();
            partidoMap.put("numJornada", String.valueOf(partidos.get(i).getJornada().getNumJornada()));
            partidoMap.put("fecha", partidos.get(i).getFecha().toString());
            partidoMap.put("nombre_equiop1", partidos.get(i).getEquipo1().getNombre());
            partidoMap.put("nombre_equiop2", partidos.get(i).getEquipo2().getNombre());
            partidoMap.put("logoEquipo1", partidos.get(i).getEquipo1().getLogoImg());
            partidoMap.put("logoEquipo2", partidos.get(i).getEquipo2().getLogoImg());
            if (partidos.get(i).getFecha().before(new Date()) || partidos.get(i).getGolesEquipo1() != 0 || partidos.get(i).getGolesEquipo2() != 0) {
                partidoMap.put("golesEquipo1", String.valueOf(partidos.get(i).getGolesEquipo1()));
                partidoMap.put("golesEquipo2", String.valueOf(partidos.get(i).getGolesEquipo2()));
            } else {
                partidoMap.put("golesEquipo1", "sin");
                partidoMap.put("golesEquipo2", "jugar");
            }
            partidosMap.add(partidoMap);
        }
    return partidosMap.toArray(new HashMap[partidosMap.size()]);}
    public static int getCantidadPersonas(){
        return jugadoresInfome.size()+2;
    }

    public static ArrayList<Equipo> rellenarBotones() throws SQLException {
        ArrayList equipos = new ArrayList<>();
        TEquipo.selectAllEquipos(equipos);
        return equipos;
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
    public static boolean insertarJugador(String nombre, String apellido, String dni, String telefono, Jugador.TipoPosicion posicion, Jugador.TipoJugador tipo, String img, int velocidad, int fisico, int defensa, int pase, int tiro, int talento){
        boolean insertar;
        Jugador jugador=new Jugador();
        jugador.setNombre(nombre);
        jugador.setApellidos(apellido);
        jugador.setDni(dni);
        jugador.setTipoPosicion(Jugador.TipoPosicion.valueOf(posicion.toString()));
        jugador.setTipoJugador(Jugador.TipoJugador.valueOf(tipo.toString()));
        jugador.setTelefono(telefono);
        jugador.setImg(img);
        jugador.setVelocidad(velocidad);
        jugador.setFisico(fisico);
        jugador.setDefensa(defensa);
        jugador.setPase(pase);
        jugador.setTiro(tiro);
        jugador.setTalento(talento);
        insertar=TJugador.insertar(jugador);
        return insertar;
    }
    public static boolean borrarJugador(String dni){
        boolean borrar;
        Jugador jugador=new Jugador();
        jugador.setDni(dni);
        borrar=TJugador.eliminar(jugador);
        return borrar;
    }
    public static boolean ActualizarPartido (String equipo1, String equipo2, String golesEq1, String golesEq2) {
        Equipo equ1 = TEquipo.getEquipoPorNombre(equipo1);
        Equipo equ2 = TEquipo.getEquipoPorNombre(equipo2);
        Partido elPartido = new Partido();
        elPartido.setEquipo1(equ1);
        elPartido.setEquipo2(equ2);
        elPartido.setGolesEquipo1(Integer.parseInt(golesEq1));
        elPartido.setGolesEquipo2(Integer.parseInt(golesEq2));
        TPartido.actualizarPartido(elPartido);
        boolean valido = true;
        return valido;
    }

    public static void crearSplit(String tipoSlit) {
        TSplit.crearSplit(tipoSlit);
    }

    public static void generarXml() {
        XML.generarXMLjornadas();
        XML.generarXMlultimaJornada();
        XML.generarXMLclasificacion();
    }

    public static void crearPlayOff() throws Exception {
        TJornada.crearPlayOff();
    }

    public static void crearJornadaPlayOff(boolean semifinal) throws Exception {
        TJornada.crearJornadaPlayOff(semifinal);
    }

    public static boolean insertarPersonal(String nombre, String apellido, String dni, String telefono, TipoPersonal oficio, String img) {
        boolean insertar;
        Personal personal=new Personal();
        personal.setNombre(nombre);
        personal.setApellidos(apellido);
        personal.setDni(dni);
        personal.setTelefono(telefono);
        personal.setOficio(oficio);
        personal.setImg(img);
        insertar=TPersonal.insertar(personal);
        return insertar;
    }


    public static boolean buscarDni(String dni) {
        if (TPersonal.consutlarPorDni(dni)) return true;
        return TJugador.consultarPorDni(dni);
    }
    public static boolean actualizarPersonal(String nombre, String apellido, String dni, String telefono, String oficio, String img) {
        return TPersonal.actualizarPersonal(new Personal(nombre, apellido, dni, telefono, TipoPersonal.valueOf(oficio), img));
    }

    public static boolean buscarDniPersoal(String dni) {
        return TPersonal.consutlarPorDni(dni);
    }

    public static boolean borrarPersonal(String dni) {
        return TPersonal.borrarPersonal(dni);
    }
    public static ArrayList<String> selectDNI(){
        ArrayList<String> dnis=new ArrayList<>();
        dnis=TJugador.selectDNI(dnis);
        return dnis;
    }
    public static ArrayList<String> selectDNIPersonal(){
        ArrayList<String> dnis=new ArrayList<>();
        dnis=TPersonal.selectDNI(dnis);
        return dnis;
    }
    public static Jugador jugadorPorDNI(String dni){
        Jugador jugador =new Jugador();
        jugador.setDni(dni);
        jugador=TJugador.getJugadorPorDNI(jugador);
        return jugador;
    }
    public static boolean updateJugador(int id,String nombre, String apellido, String dni, String telefono, Jugador.TipoPosicion posicion, Jugador.TipoJugador tipo, String img, int velocidad, int fisico, int defensa, int pase, int tiro, int talento){
        boolean update;
        Jugador jugador=new Jugador();
        jugador.setId(id);
        jugador.setNombre(nombre);
        jugador.setApellidos(apellido);
        jugador.setDni(dni);
        jugador.setTipoPosicion(Jugador.TipoPosicion.valueOf(posicion.toString()));
        jugador.setTipoJugador(Jugador.TipoJugador.valueOf(tipo.toString()));
        jugador.setTelefono(telefono);
        jugador.setImg(img);
        jugador.setVelocidad(velocidad);
        jugador.setFisico(fisico);
        jugador.setDefensa(defensa);
        jugador.setPase(pase);
        jugador.setTiro(tiro);
        jugador.setTalento(talento);
        update=TJugador.update(jugador);
        return update;
    }

    public static boolean insertarEquipo(String nombre, double presupuesto, String imagen, String color) {
        boolean insertar;
        boolean existe;
        Equipo equipo=new Equipo();
        equipo.setNombre(nombre);
        equipo.setPresupuestoAnual(presupuesto);
        equipo.setLogoImg(imagen);
        equipo.setColor(color);
        existe=TEquipo.selectEquipoInsertar(equipo);
        if (existe){
            insertar=false;
        }else{
            insertar=TEquipo.insertar(equipo);
        }
        return insertar;
    }

    public static ArrayList<String> selectNombresEquipos(){
        ArrayList<String> nombres=new ArrayList<>();
        nombres=TEquipo.selectNombre(nombres);
        return nombres;
    }

    public static Equipo equipoPorNombre(String nombre) {
        Equipo equipo =new Equipo();
        equipo.setNombre(nombre);
        equipo=TEquipo.getEquipoPorNombreDavid(equipo);
        return equipo;
    }

    public static boolean updateEquipos(String nombre, double presupuesto, String imagen, String color) {
        boolean update;
        boolean existe;
        Equipo equipo=new Equipo();
        equipo.setNombre(nombre);
        equipo.setPresupuestoAnual(presupuesto);
        equipo.setLogoImg(imagen);
        equipo.setColor(color);
        existe=TEquipo.selectEquipoInsertar(equipo);
        if (existe){
            update=false;
        }else{
            update=TEquipo.update(equipo);
        }
        return update;
    }

    public static boolean deleteEquipo(String nombre) {
        boolean delete;
        Equipo equipo =new Equipo();
        equipo.setNombre(nombre);
        equipo=equipoPorNombre(equipo.getNombre());
        delete=TEquipo.delete(equipo);
        return delete;
    }
    public static ArrayList<String> getDNISinContrato(){
        ArrayList<String> dnis=new ArrayList<>();
        dnis= TContratosJugador.getDNIJugadoresSinContratos(dnis);
        return dnis;
    }
    public static java.sql.Date fechaActual(){
        Date fechaActual = new Date();
        java.sql.Date fechaSQL = new java.sql.Date(fechaActual.getTime());
        return fechaSQL;
    }
    public static boolean insertarContratoJugadores(String nombreEquipo, String dniJugador, String fecha_fin, String clausula, String dorsal, TipoSueldo sueldo) {
        boolean insertar;
        ContratoJugador contratoJugador=new ContratoJugador();
        Equipo equipo=equipoPorNombre(nombreEquipo);
        Jugador jugador=jugadorPorDNI(dniJugador);
        contratoJugador.setJugador(jugador);
        contratoJugador.setEquipo(equipo);
        contratoJugador.setFechaInicio(fechaActual());
        contratoJugador.setFechaFin(java.sql.Date.valueOf(fecha_fin));
        contratoJugador.setClausula(Double.parseDouble(clausula));
        contratoJugador.setDorsal(dorsal);
        contratoJugador.setTipoSueldo(sueldo);
        insertar=TContratosJugador.insertar(contratoJugador);
        return insertar;
    }
    public static ArrayList<String> getIDContratosJugadores(){
        ArrayList<String> id=new ArrayList<>();
        id=TContratosJugador.getID(id);
        return id;
    }
    public static boolean deleteContratosJugadores(String id){
        boolean delete;
        delete=TContratosJugador.delete(id);
        return delete;
    }

    public static ContratoJugador contratosJugadorPorID(String id){
        ContratoJugador contratoJugador=new ContratoJugador();
        contratoJugador.setId(Integer.parseInt(id));
        contratoJugador=TContratosJugador.datosContratoPorId(contratoJugador);
        return contratoJugador;
    }

    public static boolean updateContratosJugadores(String nombre, String fechaFin, String clausula, String dorsal, TipoSueldo sueldo) {
        boolean update;
        ContratoJugador contratoJugador=new ContratoJugador();
        Equipo equipo=new Equipo();
        equipo.setNombre(nombre);
        equipo=TEquipo.getEquipoPorNombre(equipo.getNombre());
        contratoJugador.setEquipo(equipo);
        contratoJugador.setFechaFin(java.sql.Date.valueOf(fechaFin));
        contratoJugador.setClausula(Double.parseDouble(clausula));
        contratoJugador.setDorsal(dorsal);
        contratoJugador.setTipoSueldo(sueldo);
        update= TContratosJugador.update(contratoJugador);
        return update;
    }

    public static Personal personalPorDNI(String dni) {
        Personal personal = new Personal();
        personal.setDni(dni);
        personal=TPersonal.getPersonalPorDNI(personal);
        return personal;
    }

    public static boolean updatePersonal(int id, String nombre, String apellido, String dni, String telefono, TipoPersonal oficio, String img) {
        boolean update;
        Personal personal=new Personal();
        personal.setId(id);
        personal.setNombre(nombre);
        personal.setApellidos(apellido);
        personal.setDni(dni);
        personal.setTelefono(telefono);
        personal.setOficio(oficio);
        personal.setImg(img);
        update=TPersonal.update(personal);
        return update;
    }
}