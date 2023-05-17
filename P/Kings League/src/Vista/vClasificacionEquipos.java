package Vista;

import Controlador.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URL;

public class vClasificacionEquipos {
    private JPanel pPrincipal;
    private JPanel pDegradado;
    private JPanel pContenido;
    private JPanel pFooter;
    private JLabel fTwitch;
    private JLabel fInstagram;
    private JLabel fTwitter;
    private JPanel pHeader;
    private JMenuBar jmheader;
    private JMenu jmInicio;
    private JMenuItem jmiPrincipal;
    private JMenu mEquipos;
    private JMenuItem jmiConsultarEquipos;
    private JMenu mJugadores;
    private JMenuItem jmiSeleccionar;
    private JMenu mPartidos;
    private JMenuItem jmiVer;
    private JMenu mClasificacion;
    private JMenu mUsuario;
    private JMenuItem jmiVerPerfil;
    private JMenuItem jmiCerrarSesion;
    private JLabel fLogoKingsLeague;
    private JComboBox cbSplit;
    private JLabel posicion1;
    private JLabel posicion2;
    private JLabel posicion3;
    private JLabel posicion4;
    private JLabel posicion5;
    private JLabel posicion6;
    private JLabel posicion7;
    private JLabel posicion8;
    private JLabel posicion9;
    private JLabel posicion10;
    private JLabel posicion11;
    private JLabel posicion12;
    private JLabel equipo1;
    private JLabel equipo2;
    private JLabel equipo3;
    private JLabel equipo4;
    private JLabel equipo5;
    private JLabel equipo6;
    private JLabel equipo7;
    private JLabel equipo8;
    private JLabel equipo9;
    private JLabel equipo10;
    private JLabel equipo11;
    private JLabel equipo12;
    private JLabel logo1;
    private JLabel logo2;
    private JLabel logo3;
    private JLabel logo4;
    private JLabel logo5;
    private JLabel logo6;
    private JLabel logo7;
    private JLabel logo8;
    private JLabel logo9;
    private JLabel logo10;
    private JLabel logo11;
    private JLabel logo12;
    private JLabel victoria1;
    private JLabel victoria2;
    private JLabel victoria3;
    private JLabel victoria4;
    private JLabel victoria5;
    private JLabel victoria6;
    private JLabel victoria7;
    private JLabel victoria8;
    private JLabel victoria9;
    private JLabel victoria10;
    private JLabel victoria11;
    private JLabel victoria12;
    private JLabel golesFavor1;
    private JLabel golesFavor2;
    private JLabel golesFavor3;
    private JLabel golesFavor4;
    private JLabel golesFavor5;
    private JLabel golesFavor6;
    private JLabel golesFavor7;
    private JLabel golesFavor8;
    private JLabel golesFavor9;
    private JLabel golesFavor10;
    private JLabel golesFavor11;
    private JLabel golesFavor12;
    private JLabel golesContra1;
    private JLabel golesContra2;
    private JLabel golesContra3;
    private JLabel golesContra4;
    private JLabel golesContra5;
    private JLabel golesContra6;
    private JLabel golesContra7;
    private JLabel golesContra8;
    private JLabel golesContra9;
    private JLabel golesContra10;
    private JLabel golesContra11;
    private JLabel golesContra12;
    private JLabel diferencia1;
    private JLabel diferencia2;
    private JLabel diferencia3;
    private JLabel diferencia4;
    private JLabel diferencia5;
    private JLabel diferencia6;
    private JLabel diferencia7;
    private JLabel diferencia8;
    private JLabel diferencia9;
    private JLabel diferencia10;
    private JLabel diferencia11;
    private JLabel diferencia12;
    private ImageIcon LogoKingsLeague;
    private ImageIcon imagenTwitch;
    private ImageIcon imagenInstagram;
    private ImageIcon imagenTwitter;
    private ImageIcon imagenUsuario;


    public vClasificacionEquipos() throws MalformedURLException {


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
                        0, getHeight(), colorFin);

                g2d.setPaint(gradient);

                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        // Agrega pHeader al norte
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



        // Poner los ":hover" en los elementos de la barra de navegación (al pasar el ratón por encima el fondo cambia)
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


        // Poner los métodos
        jmiVerPerfil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO : poner que aparezca la ventana "vPerfilUsuario"
            }
        });

        jmiCerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Main.generarVentanaInicio();
                } catch (MalformedURLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        jmiConsultarEquipos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO : poner código necesario
            }
        });

        mClasificacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        cbSplit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });





    }


    public JPanel getpPrincipal() {
        return pPrincipal;
    }

    public static void main(String[] args) throws MalformedURLException {
        JFrame frame = new JFrame("vClasificacionEquipos");
        frame.setContentPane(new vClasificacionEquipos().pPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
