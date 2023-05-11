package Vista;

import Controlador.Main;
import java.awt.Desktop;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class vPrincipal {
    private JPanel pPrincipal;

    private ImageIcon imagenInstagram;
    private ImageIcon imagenTwitter;
    private ImageIcon imagenTwitch;

    private ImageIcon imagenUsuario;

    private ImageIcon LogoKingsLeague;

    private ImageIcon ImagenNoticia;
    private JPanel pHeader;
    private JPanel pImagenNoticia;
    private JPanel pContenido;
    private JMenu mEquipos;
    private JMenuBar jmheader;
    private JMenuItem jmiInsertarEquipos;
    private JMenuItem jmiEliminarEquipos;
    private JMenuItem jmiActualizarEquipos;
    private JMenuItem jmiConsultarEquipos;
    private JMenu mJugadores;
    private JMenuItem jmiInsertar;
    private JMenuItem jmiEliminar;
    private JMenuItem jmiActualizar;
    private JMenuItem jmiSeleccionar;
    private JMenu mPartidos;
    private JMenuItem jmiVer;
    private JMenuItem jmiResultados;
    private JMenu mClasificacion;
    private JLabel jlImagen;
    private JPanel pFooter;
    private JLabel fTwitch;
    private JLabel fInstagram;
    private JLabel fTwitter;
    private JMenu mUsuario;
    private JMenuItem jmiVerPerfil;
    private JMenuItem jmiCerrarSesion;
    private JLabel fLogoKingsLeague;
    private JLabel jlImagenFran;
    private JLabel jlImagenDavid;
    private JButton bEnlace;
    private JPanel pBoton;


    public vPrincipal() throws MalformedURLException {

        String videoId = "k5jRcR6QP4Q";
        String youtubeLink = generateYouTubeLink(videoId);


        // Poner la imagen del logo oficial de la Kings League
        LogoKingsLeague = new ImageIcon(new URL("https://seeklogo.com/images/K/kings-league-logo-CEDD6AED72-seeklogo.com.png"));
        Image LogoNuevo = LogoKingsLeague.getImage().getScaledInstance(300, 122, Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(LogoNuevo);
        fLogoKingsLeague.setIcon(newIcon);

        // Poner la imagen de la noticia de actualidad de la Kings League
        ImagenNoticia = new ImageIcon(new URL("https://s1.abcstatics.com/abc/www/multimedia/deportes/2023/03/24/DIRECTO-FINAL-KINGS-LEAGUE(1)-U16424855837hdU-1200x630@abc.jpg"));
        Image LogoNoticia = ImagenNoticia.getImage().getScaledInstance(900, 480, Image.SCALE_SMOOTH);
        ImageIcon NoticiaIcon = new ImageIcon(LogoNoticia);
        jlImagen.setIcon(NoticiaIcon);

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



        // Poner los ":hover" en los elemntos de la barra de navegación (al pasar el ratón por encima el fondo cambia)
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
        bEnlace.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openLink(youtubeLink);
            }
        });
    }

    public JPanel getpPrincipal() {
        return pPrincipal;
    }

    public static void main(String[] args) throws MalformedURLException {
        JFrame frame = new JFrame("vPrincipal");
        frame.setContentPane(new vPrincipal().pPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
    }

    public static String generateYouTubeLink(String videoId) {
        return "https://youtu.be/" + videoId;
    }

    public static void openLink(String link) {
        try {
            // Utilizar la clase Desktop para abrir el enlace en un navegador web
            Desktop.getDesktop().browse(new URI(link));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
