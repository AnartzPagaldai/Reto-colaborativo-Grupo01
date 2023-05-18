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
import java.util.HashMap;
import java.util.concurrent.TimeoutException;

public class  vPrincipalUsuario {
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
    private JMenuItem jmiConsultarEquipos;
    private JMenu mJugadores;
    private JMenu mPartidos;
    private JMenuItem jmiVer;
    private JMenu mClasificacion;
    private JLabel jlImagen;
    private JPanel pFooter;
    private JLabel fTwitch;
    private JLabel fInstagram;
    private JLabel fTwitter;
    private JMenu mUsuario;
    private JMenuItem jmiVerPerfil;
    private JMenuItem jmiClasi;
    private JMenuItem jmiCerrarSesion;
    private JLabel fLogoKingsLeague;
    private JLabel jlImagenFran;
    private JLabel jlImagenDavid;
    private JButton bGolazos;
    private JPanel pBotonGolazos;
    private JButton bTrailer;
    private JPanel pBotonTrailer;
    private JPanel pDegradado;


    public vPrincipalUsuario() throws MalformedURLException {


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

        // Poner vídeo de YouTube sobre los golazos
        String videoId = "k5jRcR6QP4Q";
        String youtubeLink = generateYouTubeLink(videoId);

        // Poner vídeo de YouTube sobre la Kings League
        String idVideo = "y1eFrUSam1k";
        String linkYoutube = generateYouTubeLink(idVideo);

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
                Main.cerrarSesion();
            }
        });
        bGolazos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openLink(youtubeLink);
            }
        });
        jmiConsultarEquipos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Main.generarVentanaEquipos();
                } catch (MalformedURLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        bTrailer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openLink(linkYoutube);
            }
        });

        jmiVerPerfil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Main.generarVentanaAjustesUsuario();
                } catch (MalformedURLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        jmiClasi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Main.generarVentanaClasificacion();
                } catch (MalformedURLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public JPanel getpPrincipal() {
        return pPrincipal;
    }

    public static void main(String[] args) throws MalformedURLException {
        JFrame frame = new JFrame("vPrincipal");
        frame.setContentPane(new vPrincipalUsuario().pPrincipal);
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