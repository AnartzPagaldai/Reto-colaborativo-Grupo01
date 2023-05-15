package Controlador;

import Modelo.Equipo.Equipo;
import Modelo.Equipo.TEquipo;
import Modelo.Personal.Personal;
import Modelo.Usuario.TUsuario;
import Modelo.Usuario.Usuario;
import Modelo.XML.XML;
import Vista.vConsultarEquipos;
import Vista.vInicioSesion;
import Vista.vPrincipalUsuario;
import Vista.vRegistro;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;

public class Main {
    public static JFrame actual= null;
    public static JFrame vInicio;
    public static JFrame vPrinicpal;
    public static JFrame vRegistro;
    public static JFrame vEquipos;
    public static Usuario u;
    public static void main(String[] args) throws MalformedURLException {
        generarVentanaInicio();
        /*Equipo equipo = new Equipo();
        equipo.setNombre("1K FC");
        TEquipo.getInfomeEquipos(equipo, new Personal[2]);
         */
    }
    public static void cerrarSesion(){
        actual.dispose();
        vInicio.setVisible(true);
    }
    public static void generarVentanaInicio () throws MalformedURLException {
        vInicio = new JFrame("vInicioSesion");
        vInicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vInicioSesion inicioSesion = new vInicioSesion();
        vInicio.setContentPane(inicioSesion.getpPrincipal());
        vInicio.pack();
        vInicio.setVisible(true);
        vInicio.setExtendedState(Frame.MAXIMIZED_BOTH);
    }
    public static void generarVentanaRegistro () throws MalformedURLException {
        vRegistro= new JFrame("vRegistro");
        vRegistro.setContentPane(new vRegistro().getpPrincipal());
        vRegistro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vRegistro.pack();
        vRegistro.setVisible(true);
        vRegistro.setExtendedState(Frame.MAXIMIZED_BOTH);
        actual=vRegistro;
        vInicio.dispose();
    }
    public static void generarVentanaPrincipal () throws MalformedURLException {
        vPrinicpal= new JFrame("vPrincipal");
        vPrinicpal.setContentPane(new vPrincipalUsuario().getpPrincipal());
        vPrinicpal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vPrinicpal.pack();
        vPrinicpal.setVisible(true);
        vPrinicpal.setExtendedState(Frame.MAXIMIZED_BOTH);
        vInicio.setVisible(false);
        actual=vPrinicpal;
    }
    public static void generarVentanaEquipos () throws MalformedURLException {
        vEquipos= new JFrame("vConsultarEquipos");
        vEquipos.setContentPane(new vConsultarEquipos().getpPrincipal());
        vEquipos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vEquipos.pack();
        vEquipos.setVisible(true);
        vEquipos.setExtendedState(Frame.MAXIMIZED_BOTH);
        actual.dispose();
        actual=vEquipos;
    }
    public static boolean selectUsuario(String nombre, String contrasena){
        boolean existe;
        u=new Usuario();
        u.setNombre(nombre);
        u.setContrasena(contrasena);
        existe=TUsuario.selectUsuario(u);
        return existe;
    }

    public static boolean crearUsuario(String nombre, String correo, String contrasena, Usuario.TipoUsuario tipo){
        boolean existe;
        boolean insertar;
        u=new Usuario();
        u.setNombre(nombre);
        u.setCorreo(correo);
        u.setContrasena(contrasena);
        u.setTipoUsuario(Usuario.TipoUsuario.valueOf(tipo.toString()));
        existe=TUsuario.selectUsuarioInsertar(u);
        if (existe){
            insertar=false;
        }else insertar=TUsuario.insertar(u);
        return insertar;
    }
}