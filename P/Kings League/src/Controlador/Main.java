package Controlador;

import Modelo.Equipo.Equipo;
import Modelo.Equipo.TEquipo;
import Modelo.Jugador.Jugador;
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
import java.util.HashMap;

public class Main {
    public static JFrame actual;
    public static JFrame vInicio;
    public static JFrame vPrinicpal;

    public static JFrame vRegistro;
    public static Usuario u;

    private static ArrayList<Jugador> jugadoresInfome;

    private static Personal[] personalesInfome = new Personal[2];

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
            persona.put("nombre", personalesInfome[posicion].getNombre());
            persona.put("img", personalesInfome[posicion].getImg());// todo rellenar personas y personal
        } else {
            persona.put("nombre", jugadoresInfome.get(posicion - 2).getNombre());
            persona.put("img", jugadoresInfome.get(posicion - 2).getImg());

        }
        return persona;
    }


    public static HashMap[] getClasificacion() {
        return XML.getClasificacion();
    }
}