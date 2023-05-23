package Vista;

import Controlador.Main;
import Modelo.Equipo.Equipo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Generar la clase vConsultarEquipos.
 * Esta clase tiene el contenido y los métodos necesarios para ejecutar la ventana destinada a mostrar los equipos.
 */
public class vConsultarEquipos {


    private ImageIcon imagenInstagram;
    private ImageIcon imagenTwitter;
    private ImageIcon imagenTwitch;

    private ImageIcon imagenUsuario;

    private ImageIcon imagenEquipo;

    private ImageIcon LogoKingsLeague;

    private ImageIcon ImagenNoticia;
    private JPanel pPrincipal;
    private JPanel pHeader;
    private JMenuBar jmheader;
    private JMenu mEquipos;
    private JMenuItem jmiConsultarEquipos;
    private JMenu mJugadores;
    private JMenu mPartidos;
    private JMenuItem jmiVer;
    private JMenu mClasificacion;
    private JMenu mUsuario;
    private JMenuItem jmiVerPerfil;
    private JMenuItem jmiCerrarSesion;
    private JLabel fLogoKingsLeague;
    private JPanel pFooter;
    private JLabel fTwitch;
    private JLabel fInstagram;
    private JLabel fTwitter;
    private JPanel pContenido;
    private JPanel pDegradado;
    private JPanel pBotones;
    private JButton bEquipo1;
    private JButton bEquipo2;
    private JButton bEquipo3;
    private JButton bEquipo4;
    private JButton bEquipo5;
    private JButton bEquipo6;
    private JButton bEquipo7;
    private JButton bEquipo8;
    private JButton bEquipo9;
    private JButton bEquipo10;
    private JButton bEquipo11;
    private JButton bEquipo12;
    private JMenu jmInicio;
    private JMenu mPrincipal;
    private JMenuItem jmiPrincipal;


    public vConsultarEquipos() throws MalformedURLException {
        jmInicio.requestFocus();
        ArrayList<JButton> botones = new ArrayList<>();
        botones.add(bEquipo1);
        botones.add(bEquipo2);
        botones.add(bEquipo3);
        botones.add(bEquipo4);
        botones.add(bEquipo5);
        botones.add(bEquipo6);
        botones.add(bEquipo7);
        botones.add(bEquipo8);
        botones.add(bEquipo9);
        botones.add(bEquipo10);
        botones.add(bEquipo11);
        botones.add(bEquipo12);

        try {
            ArrayList<Equipo> listaEquipos = Main.rellenarBotones();

            for (int x = 0; x < 12; x++)
            {
                Color colorFondo = Color.decode(listaEquipos.get(x).getColor());

                botones.get(x).setText(listaEquipos.get(x).getNombre());

                imagenEquipo = new ImageIcon(new URL(listaEquipos.get(x).getLogoImg()));
                Image imgEquipo = imagenEquipo.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                ImageIcon eqIcono = new ImageIcon(imgEquipo);
                botones.get(x).setIcon(eqIcono);

                botones.get(x).setBackground(colorFondo);

            }
        }catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Eror de SQL");
        }

        pPrincipal = new JPanel(new BorderLayout());

        pPrincipal = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g;


                Color colorInicio = new Color(233, 86, 31);
                Color colorFin = new Color(247, 169, 33);

                GradientPaint gradient = new GradientPaint(
                        0, 0, colorInicio,
                        getWidth(), getHeight(), colorFin);

                g2d.setPaint(gradient);

                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        pPrincipal.add(pDegradado, BorderLayout.CENTER);


        // Poner la imagen del logo oficial de la Kings League
        LogoKingsLeague = new ImageIcon(new URL("https://seeklogo.com/images/K/kings-league-logo-CEDD6AED72-seeklogo.com.png"));
        Image LogoNuevo = LogoKingsLeague.getImage().getScaledInstance(300, 122, Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(LogoNuevo);
        fLogoKingsLeague.setIcon(newIcon);

        // Poner las imágenes del footer
        imagenTwitch = new ImageIcon(new URL("https://icones.pro/wp-content/uploads/2021/05/symbole-twitch-logo-icone-noir.png"));
        Image imgTwitch = imagenTwitch.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
        ImageIcon twitchIcono = new ImageIcon(imgTwitch);
        fTwitch.setIcon(twitchIcono);

        imagenInstagram = new ImageIcon(new URL("https://icones.pro/wp-content/uploads/2021/02/instagram-icone-noir.png"));
        Image imgIg = imagenInstagram.getImage().getScaledInstance(38, 39, Image.SCALE_SMOOTH);
        ImageIcon igIcono = new ImageIcon(imgIg);
        fInstagram.setIcon(igIcono);

        imagenTwitter = new ImageIcon(new URL("https://icones.pro/wp-content/uploads/2021/02/icones-twitter-noires.png"));
        Image imgTw = imagenTwitter.getImage().getScaledInstance(38, 39, Image.SCALE_SMOOTH);
        ImageIcon twIcono = new ImageIcon(imgTw);
        fTwitter.setIcon(twIcono);

        // Poner la imagen del usuario
        imagenUsuario = new ImageIcon(new URL("https://assets.stickpng.com/images/585e4beacb11b227491c3399.png"));
        Image imgUsuario = imagenUsuario.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon UsuIcono = new ImageIcon(imgUsuario);
        mUsuario.setIcon(UsuIcono);

        mEquipos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                mEquipos.setBackground(Color.orange);
                mEquipos.setOpaque(true);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                mEquipos.setBackground(UIManager.getColor("Menu.background"));
                mEquipos.setOpaque(false);
            }
        });
        mJugadores.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                mJugadores.setBackground(Color.orange);
                mJugadores.setOpaque(true);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                mJugadores.setBackground(UIManager.getColor("Menu.background"));
                mJugadores.setOpaque(false);
            }
        });
        mClasificacion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                mClasificacion.setBackground(Color.orange);
                mClasificacion.setOpaque(true);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                mClasificacion.setBackground(UIManager.getColor("Menu.background"));
                mClasificacion.setOpaque(false);
            }
        });
        mPartidos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                mPartidos.setBackground(Color.orange);
                mPartidos.setOpaque(true);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                mPartidos.setBackground(UIManager.getColor("Menu.background"));
                mPartidos.setOpaque(false);
            }
        });
        mUsuario.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                mUsuario.setBackground(Color.orange);
                mUsuario.setOpaque(true);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                mUsuario.setBackground(UIManager.getColor("Menu.background"));
                mUsuario.setOpaque(false);
            }
        });
        jmiPrincipal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.Principal();
            }
        });
        jmiCerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    Main.cerrarSesion();
            }
        });
        bEquipo1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Main.setNombreEquipo(bEquipo1.getText());
                    Main.generarVentanaJugadores();
                } catch (MalformedURLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        bEquipo2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Main.setNombreEquipo(bEquipo2.getText());
                    Main.generarVentanaJugadores();
                } catch (MalformedURLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        bEquipo3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Main.setNombreEquipo(bEquipo3.getText());
                    Main.generarVentanaJugadores();
                } catch (MalformedURLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        bEquipo4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Main.setNombreEquipo(bEquipo4.getText());
                    Main.generarVentanaJugadores();
                } catch (MalformedURLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        bEquipo5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Main.setNombreEquipo(bEquipo5.getText());
                    Main.generarVentanaJugadores();
                } catch (MalformedURLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        bEquipo6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Main.setNombreEquipo(bEquipo6.getText());
                    Main.generarVentanaJugadores();
                } catch (MalformedURLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        bEquipo7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Main.setNombreEquipo(bEquipo7.getText());
                    Main.generarVentanaJugadores();
                } catch (MalformedURLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        bEquipo8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Main.setNombreEquipo(bEquipo8.getText());
                    Main.generarVentanaJugadores();
                } catch (MalformedURLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        bEquipo9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Main.setNombreEquipo(bEquipo9.getText());
                    Main.generarVentanaJugadores();
                } catch (MalformedURLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        bEquipo10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Main.setNombreEquipo(bEquipo10.getText());
                    Main.generarVentanaJugadores();
                } catch (MalformedURLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        bEquipo11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Main.setNombreEquipo(bEquipo11.getText());
                    Main.generarVentanaJugadores();
                } catch (MalformedURLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        bEquipo12.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Main.setNombreEquipo(bEquipo12.getText());
                    Main.generarVentanaJugadores();
                } catch (MalformedURLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public static void main(String[] args) throws MalformedURLException {
        JFrame frame = new JFrame("vConsultarEquipos");
        frame.setContentPane(new vConsultarEquipos().pPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
    }

    public JPanel getpPrincipal() {
        return pPrincipal;
    }
}
