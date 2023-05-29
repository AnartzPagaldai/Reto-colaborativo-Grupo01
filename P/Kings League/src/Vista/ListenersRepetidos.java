package Vista;

import Controlador.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;

public class ListenersRepetidos {
    public static void actionListenerBarraDeNavegacion(JMenuItem principal, JMenuItem consultarEquipo, JMenuItem verPartido, JMenuItem clasificacion, JMenuItem cerrarSesion, JMenuItem verPerfil) {
        principal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.Principal();
            }
        });
        actionListenerBarraDeNavegacion(consultarEquipo,verPartido, clasificacion, cerrarSesion, verPerfil);
    }

    public static void actionListenerBarraDeNavegacion(JMenuItem consultarEquipo, JMenuItem verPartido, JMenuItem clasificacion, JMenuItem cerrarSesion, JMenuItem verPerfil) {
        consultarEquipo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Main.generarVentanaEquipos();
                } catch (MalformedURLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        verPartido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Main.generarVerPartido();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        clasificacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Main.generarVentanaClasificacion();
                } catch (MalformedURLException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });

        cerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.cerrarSesion();
            }
        });

        verPerfil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Main.generarVentanaAjustesUsuario();
                } catch (MalformedURLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public static void mouseListenerBarraDeNavegacion(JMenu principal, JMenu consultarEquipo, JMenu verPartido, JMenu clasificacion) {
       JMenu[] menus = {principal, consultarEquipo, verPartido, clasificacion};
        mouseListenerBarraDeNavegacion(menus);
    }

    public static void mouseListenerBarraDeNavegacion(JMenu consultarEquipo, JMenu verPartido, JMenu clasificacion)  {
        JMenu[] menus = {consultarEquipo, verPartido, clasificacion};
        mouseListenerBarraDeNavegacion(menus);
    }

    public static void mouseListenerBarraDeNavegacion(JMenu[] menus) {
        for (JMenuItem menu : menus) {
            menu.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    menu.setBackground(Color.orange);
                    menu.setOpaque(true);
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    menu.setBackground(UIManager.getColor("Menu.background"));
                    menu.setOpaque(false);
                }
            });
        }
    }
}
