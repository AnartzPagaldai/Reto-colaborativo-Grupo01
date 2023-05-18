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
import java.util.ArrayList;
import java.util.HashMap;

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
    private JLabel lSplit;
    private JPanel pSplit;
    private JMenuItem jmiClasi;
    private ImageIcon LogoKingsLeague;
    private ImageIcon imagenTwitch;
    private ImageIcon imagenInstagram;
    private ImageIcon imagenTwitter;
    private ImageIcon imagenUsuario;
    private ImageIcon logoEquipo;


    public vClasificacionEquipos() throws MalformedURLException {
        HashMap<String, String>[] equipos=Main.getClasificacion();
        ArrayList<JLabel> logos=new ArrayList<>();
        logos.add(logo1);
        logos.add(logo2);
        logos.add(logo3);
        logos.add(logo4);
        logos.add(logo5);
        logos.add(logo6);
        logos.add(logo7);
        logos.add(logo8);
        logos.add(logo9);
        logos.add(logo10);
        logos.add(logo11);
        logos.add(logo12);

        ArrayList<JLabel> posicion=new ArrayList<>();
        posicion.add(posicion1);
        posicion.add(posicion2);
        posicion.add(posicion3);
        posicion.add(posicion4);
        posicion.add(posicion5);
        posicion.add(posicion6);
        posicion.add(posicion7);
        posicion.add(posicion8);
        posicion.add(posicion9);
        posicion.add(posicion10);
        posicion.add(posicion11);
        posicion.add(posicion12);

        ArrayList<JLabel> nombres=new ArrayList<>();
        nombres.add(equipo1);
        nombres.add(equipo2);
        nombres.add(equipo3);
        nombres.add(equipo4);
        nombres.add(equipo5);
        nombres.add(equipo6);
        nombres.add(equipo7);
        nombres.add(equipo8);
        nombres.add(equipo9);
        nombres.add(equipo10);
        nombres.add(equipo11);
        nombres.add(equipo12);

        ArrayList<JLabel> victorias=new ArrayList<>();
        victorias.add(victoria1);
        victorias.add(victoria2);
        victorias.add(victoria3);
        victorias.add(victoria4);
        victorias.add(victoria5);
        victorias.add(victoria6);
        victorias.add(victoria7);
        victorias.add(victoria8);
        victorias.add(victoria9);
        victorias.add(victoria10);
        victorias.add(victoria11);
        victorias.add(victoria12);

        lSplit.setText(equipos[0].get("split"));
        for (int x=0; x<equipos.length-1; x++){
            posicion.get(x).setText(equipos[x+1].get("posicion"));
            logoEquipo = new ImageIcon(new URL(equipos[x+1].get("logoImg")));
            Image LogoEqu = logoEquipo.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            ImageIcon iconEq = new ImageIcon(LogoEqu);
            logos.get(x).setIcon(iconEq);
            nombres.get(x).setText(equipos[x+1].get("nombre_equipo"));
            victorias.get(x).setText(equipos[x+1].get("victorias"));
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
        jmInicio.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
               jmInicio.setBackground(Color.orange);
               jmInicio.setOpaque(true);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                jmInicio.setBackground(UIManager.getColor("Menu.background"));
                jmInicio.setOpaque(false);
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
                try {
                    Main.generarVentanaAjustesUsuario();
                } catch (MalformedURLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        jmiCerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.cerrarSesion();
            }
        });

        jmiConsultarEquipos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.vEquipos();
            }
        });

        mClasificacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        jmiPrincipal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.PrincipalUsuario();
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
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
    }

}
