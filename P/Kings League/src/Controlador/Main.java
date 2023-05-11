package Controlador;

import Vista.vInicioSesion;
import Vista.vPrincipal;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;

public class Main {
    public static void main(String[] args) throws MalformedURLException {
        generarVentanaInicio();
    }


    public static void generarVentanaInicio () throws MalformedURLException {
        JFrame frame = new JFrame("vInicioSesion");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vInicioSesion inicioSesion = new vInicioSesion();
        frame.setContentPane(inicioSesion.getpPrincipal());
        frame.pack();
        frame.setVisible(true);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
    }
    public static void generarVentanaPrincipal () throws MalformedURLException {
        JFrame frame = new JFrame("vPrincipal");
        frame.setContentPane(new vPrincipal().getpPrincipal());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
    }
}