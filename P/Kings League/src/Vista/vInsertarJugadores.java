package Vista;

import Controlador.Main;
import Modelo.Jugador.Jugador;
import Modelo.Usuario.Usuario;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class vInsertarJugadores {
    private JPanel pPrincipal;
    private ImageIcon LogoKingsLeague;
    private ImageIcon imagenInstagram;
    private ImageIcon imagenTwitter;
    private ImageIcon imagenTwitch;
    private JPanel pDegradado;
    private JPanel pDatos;
    private JPanel pCrearJugador;
    private JTextField tfNombre;
    private JLabel jlContrasena;
    private JLabel jlNombre;
    private JButton bCrear;
    private JLabel JLThegrefg;
    private JLabel JLIbai;
    private JLabel fTwitch;
    private JLabel fInstagram;
    private JLabel fTwitter;
    private JPanel pHeader;
    private JLabel fLogoKingsLeague;
    private JPanel pEstadisticas;
    private JButton bAtras;
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

    private JFormattedTextField ftTelefono;
    private JTextField tfIMG;
    private JFormattedTextField tfDNI;
    private JComboBox cbPosicion;
    private JPanel pVelocidad;
    private JPanel pFisico;
    private JPanel pTalento;
    private JPanel pPase;
    private JPanel pDefensa;
    private JPanel pTiro;
    private JComboBox cbTipo;
    private JSpinner sVelocidad;
    private JFormattedTextField tfVelocidad;
    private SpinnerNumberModel modelVelocidad = new SpinnerNumberModel(0,0,99,1);
    private SpinnerNumberModel modelPase= new SpinnerNumberModel(0,0,99,1);
    private SpinnerNumberModel modelTalento = new SpinnerNumberModel(0,0,99,1);
    private SpinnerNumberModel modelFisico = new SpinnerNumberModel(0,0,99,1);
    private SpinnerNumberModel modelDefensa = new SpinnerNumberModel(0,0,99,1);
    private SpinnerNumberModel modelTiro= new SpinnerNumberModel(0,0,99,1);
    private static final String patronEnlace = "^(https?://)?([\\w.-]+)\\.([a-zA-Z]{2,})(/[\\w.-]*)*/?\\.(png)$";
    private boolean enlaceCorrecto;
    private String posicion;
    private String tipoJugadorString;
    private Jugador.TipoJugador tipoJugador;
    private Jugador.TipoPosicion TipoPosicion;
    public vInsertarJugadores() throws MalformedURLException {
        JSpinner sVelocidad = new JSpinner(modelVelocidad);
        pVelocidad.add(sVelocidad);
        JSpinner sTiro = new JSpinner(modelTiro);
        pTiro.add(sTiro);
        JSpinner sTalento = new JSpinner(modelTalento);
        pTalento.add(sTalento);
        JSpinner sPase = new JSpinner(modelPase);
        pPase.add(sPase);
        JSpinner sFisico = new JSpinner(modelFisico);
        pFisico.add(sFisico);
        JSpinner sDefensa = new JSpinner(modelDefensa);
        pDefensa.add(sDefensa);

    public vInsertarJugadores() throws MalformedURLException {

        // Poner la imagen del logo oficial de la Kings League
        LogoKingsLeague = new ImageIcon(new URL("https://seeklogo.com/images/K/kings-league-logo-CEDD6AED72-seeklogo.com.png"));
        Image LogoNuevo = LogoKingsLeague.getImage().getScaledInstance(300, 122, Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(LogoNuevo);
        fLogoKingsLeague.setIcon(newIcon);

        // Poner la imagen del usuario
        ImageIcon imagenUsuario = new ImageIcon(new URL("https://assets.stickpng.com/images/585e4beacb11b227491c3399.png"));
        Image imgUsuario = imagenUsuario.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon UsuIcono = new ImageIcon(imgUsuario);
        mUsuario.setIcon(UsuIcono);
        pPrincipal = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g;



                Color colorInicio = new Color(253, 214, 44);
                Color colorFin = new Color(239, 122, 14);

                GradientPaint gradient = new GradientPaint(
                        0, 0, colorInicio,
                        0, getHeight(), colorFin);

                g2d.setPaint(gradient);

                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        pPrincipal.add(pDegradado, BorderLayout.CENTER);





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

    }

    public static void main(String[] args) throws MalformedURLException {
        JFrame frame = new JFrame("vInsertarJugadores");
        frame.setContentPane(new vInsertarJugadores().pPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
    }

    public JPanel getpPrincipal() {
        return pPrincipal;
    }

    private void createUIComponents() throws ParseException {
        // TODO: place custom component creation code here
        ftTelefono = new JFormattedTextField(new MaskFormatter("#########"));
        tfDNI = new JFormattedTextField(new MaskFormatter("########U"));
    }
    public void enlaceCorrecto(){
        Pattern pattern = Pattern.compile(patronEnlace);
        Matcher matcher = pattern.matcher(tfIMG.getText());
        if (!matcher.matches()){
            enlaceCorrecto=false;
            tfIMG.setBackground(Color.red);
        }else {
            tfIMG.setBackground(Color.green);
            enlaceCorrecto=true;
        }
    }
}
