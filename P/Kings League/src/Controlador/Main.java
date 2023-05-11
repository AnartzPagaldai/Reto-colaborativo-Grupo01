package Controlador;

import Modelo.Usuario.TUsuario;
import Modelo.Usuario.Usuario;
import Vista.vInicioSesion;
import Vista.vPrincipal;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;

public class Main {
    public static JFrame actual;
    public static JFrame vInicio;
    public static JFrame vPrinicpal;
    public static Usuario u;
    public static void main(String[] args) throws MalformedURLException {
        generarVentanaInicio();
    }


    public static void generarVentanaInicio () throws MalformedURLException {
        vInicio = new JFrame("vInicioSesion");
        vInicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vInicioSesion inicioSesion = new vInicioSesion();
        vInicio.setContentPane(inicioSesion.getpPrincipal());
        vInicio.pack();
        vInicio.setVisible(true);
        vInicio.setExtendedState(Frame.MAXIMIZED_BOTH);
        actual=vInicio;
    }
    public static void generarVentanaPrincipal () throws MalformedURLException {
        vPrinicpal= new JFrame("vPrincipal");
        vPrinicpal.setContentPane(new vPrincipal().getpPrincipal());
        vPrinicpal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vPrinicpal.pack();
        vPrinicpal.setVisible(true);
        vPrinicpal.setExtendedState(Frame.MAXIMIZED_BOTH);
        actual=vPrinicpal;
        vInicio.dispose();
    }


    public static boolean selectUsuario(String nombre, String contrasena){
        boolean existe;
        u=new Usuario();
        u.setNombre(nombre);
        u.setContrasena(contrasena);
        existe=TUsuario.selectUsuarioAdmin(u);
        return existe;
    }
}