package Vista;

import Controlador.Main;
import Modelo.Equipo.Equipo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class vConsultarJugadores {

    private ImageIcon imagenInstagram;
    private ImageIcon imagenTwitch;
    private ImageIcon imagenTwitter;
    private ImageIcon imagenUsuario;
    private ImageIcon LogoKingsLeague;
    private JPanel pPrincipal;
    private JPanel pDegradado;
    private JPanel pHeader;
    private JMenuBar jmheader;
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
    private JLabel fTwitch;
    private JLabel fInstagram;
    private JLabel fTwitter;
    private JPanel pContenido;
    private JPanel pEstadisticas;
    private JLabel jlVelocidad;
    private JLabel jlDefensa;
    private JLabel jlTalento;
    private JLabel jlFisico;
    private JLabel jlPase;
    private JLabel jlTiro;
    private JTextArea taVelocidad;
    private JTextArea taFisico;
    private JTextArea taTiro;
    private JTextArea taPase;
    private JTextArea taTalento;
    private JTextArea taDefensa;
    private JButton bIzquierda;
    private JButton bDerecha;
    private JLabel jlNombre;
    private JLabel jlPosicion;
    private JPanel pimagen;
    private JLabel jlImagen;
    private JMenu mPrincipal;
    private JMenuItem jmiPrincipal;
    private JLabel jaOficio;
    private int posicion=0;
    private int maximo;
    private Equipo equipo;

    public vConsultarJugadores() throws MalformedURLException {
        Main.setObjetosInformeEquipo(Main.getNombreEquipo());
        HashMap<String, String> persona= Main.getPersonaPorPosicion(posicion);
        maximo=Main.getCantidadPersonas();
        mostrarPersona();

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
        jmiPrincipal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.PrincipalUsuario();
            }
        });

        pPrincipal.add(pDegradado, BorderLayout.CENTER);


        // Poner la imagen del logo oficial de la Kings League
        LogoKingsLeague = new ImageIcon(new URL("https://seeklogo.com/images/K/kings-league-logo-CEDD6AED72-seeklogo.com.png"));
        Image LogoNuevo = LogoKingsLeague.getImage().getScaledInstance(300, 122, Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(LogoNuevo);
        fLogoKingsLeague.setIcon(newIcon);

        imagenUsuario = new ImageIcon(new URL("https://assets.stickpng.com/images/585e4beacb11b227491c3399.png"));
        Image imgUsuario = imagenUsuario.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon UsuIcono = new ImageIcon(imgUsuario);
        mUsuario.setIcon(UsuIcono);
        bIzquierda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                posicion--;
                minimoReset();
                mostrarPersona();
            }
        });
        bDerecha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                posicion++;
                maximoReset();
                mostrarPersona();
            }
        });
    }

    public static void main(String[] args) throws MalformedURLException {
        JFrame frame = new JFrame("vConsultarJugadores");
        frame.setContentPane(new vConsultarJugadores().pPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
    }

    public JPanel getpPrincipal() {
        return pPrincipal;
    }

    private void comprobarTipo(int posicion){
        if (posicion==0 || posicion==1){
            jlDefensa.setVisible(false);
            jlFisico.setVisible(false);
            jlPase.setVisible(false);
            jlTalento.setVisible(false);
            jlTiro.setVisible(false);
            jlVelocidad.setVisible(false);
            taDefensa.setVisible(false);
            taFisico.setVisible(false);
            taPase.setVisible(false);
            taTalento.setVisible(false);
            taTiro.setVisible(false);
            taVelocidad.setVisible(false);
        }else {
            jlDefensa.setVisible(true);
            jlFisico.setVisible(true);
            jlPase.setVisible(true);
            jlTalento.setVisible(true);
            jlPosicion.setVisible(true);
            jlTiro.setVisible(true);
            jlVelocidad.setVisible(true);
            taDefensa.setVisible(true);
            taFisico.setVisible(true);
            taPase.setVisible(true);
            taTalento.setVisible(true);
            taTiro.setVisible(true);
            taVelocidad.setVisible(true);
        }
    }
    private void maximoReset(){
        if (posicion==maximo){
            posicion=0;
        }
    }
    private void minimoReset(){
        if (posicion==-1){
            posicion=maximo-1;
        }
    }
    private void mostrarPersona(){
        comprobarTipo(posicion);
        HashMap<String, String> persona= Main.getPersonaPorPosicion(posicion);
        jlNombre.setText(persona.get("nombre"));
        if (posicion==0 || posicion==1) {
            jlPosicion.setText(persona.get("oficio"));
        }else {
            jlPosicion.setText(persona.get("posicion"));
        }
        ImageIcon Jugador = null;
        try {
            Jugador = new ImageIcon(new URL(persona.get("img")));
        } catch (MalformedURLException ex) {
            throw new RuntimeException(ex);
        }
        Image Logojug = Jugador.getImage().getScaledInstance(456, 642, Image.SCALE_SMOOTH);
        ImageIcon icono = new ImageIcon(Logojug);
        jlImagen.setIcon(icono);
        taVelocidad.setText(persona.get("velocidad"));
        taTiro.setText(persona.get("tiro"));
        taTalento.setText(persona.get("talento"));
        taFisico.setText(persona.get("fisico"));
        taDefensa.setText(persona.get("defensa"));
        taPase.setText(persona.get("pase"));
    }
}
