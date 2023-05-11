package Vista;
import Controlador.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

public class vRegistro {

    private ImageIcon LogoKingsLeague;
    private ImageIcon imagenTheGrefg;
    private ImageIcon imagenIbai;
    private ImageIcon imagenInstagram;
    private ImageIcon imagenTwitter;
    private ImageIcon imagenTwitch;
    private JLabel fLogoKingsLeague;
    private JLabel fTwitch;
    private JLabel fInstagram;
    private JLabel fTwitter;
    private JLabel JLThegrefg;
    private JLabel JLIbai;
    private JPanel pPrincipal;
    private JPanel pPrinc;
    private JPanel pHeader;
    private JPanel pFooter;
    private JPanel pDatos;
    private JPanel pIniciarSesion;
    private JTextField tfNombre;
    private JLabel jlContrasena;
    private JLabel jlNombre;
    private JPasswordField pfContrasena;
    private JButton bIniciarSesion;
    private JTextField tfCorreo;
    private JLabel jlCorreo;
    private JLabel jlTipo;
    private JRadioButton rbAdmin;
    private JRadioButton rbUsuarioNomal;
    private JLabel jlClave;
    private JPanel pTipoUsuario;
    private JPanel pClave;
    private JPasswordField pfClaveAdmin;
    private JPanel pCrearDatos;


    public vRegistro() throws MalformedURLException {

    // Poner la imagen del logo oficial de la Kings League
    LogoKingsLeague =new ImageIcon(new URL("https://seeklogo.com/images/K/kings-league-logo-CEDD6AED72-seeklogo.com.png"));
    Image LogoNuevo = LogoKingsLeague.getImage().getScaledInstance(300, 122, Image.SCALE_SMOOTH);
    ImageIcon newIcon = new ImageIcon(LogoNuevo);
        fLogoKingsLeague.setIcon(newIcon);

    // Poner la imagen de THE GREFG
    imagenTheGrefg =new ImageIcon(new URL("https://raw.githubusercontent.com/OiherOleaga/Reto-colaborativo-Grupo01/6ee3a0729ffdc90dd1217e0d5d400f6cda1f7f66/P/Kings%20League/src/main/java/Vista/Imagenes/THEGREFG.png"));
    Image imgGrefg = imagenTheGrefg.getImage().getScaledInstance(410, 570, Image.SCALE_SMOOTH);
    ImageIcon nuevoIcono = new ImageIcon(imgGrefg);
        JLThegrefg.setIcon(nuevoIcono);

    // Poner la imagen de Ibai Llanos
    imagenIbai =new ImageIcon(new URL("https://kingsleague.pro/wp-content/uploads/2022/12/IBAI-LLANOS-PORCINOS-FC.png"));
    Image imgIbai = imagenIbai.getImage().getScaledInstance(416, 600, Image.SCALE_SMOOTH);
    ImageIcon ibaiicono = new ImageIcon(imgIbai);
        JLIbai.setIcon(ibaiicono);

    // Poner las imágenes del footer
    imagenTwitch =new ImageIcon(new URL("https://icones.pro/wp-content/uploads/2021/05/symbole-twitch-logo-icone-noir.png"));
    Image imgTwitch = imagenTwitch.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
    ImageIcon twitchIcono = new ImageIcon(imgTwitch);
        fTwitch.setIcon(twitchIcono);

    imagenInstagram =new ImageIcon(new URL("https://icones.pro/wp-content/uploads/2021/02/instagram-icone-noir.png"));
    Image imgIg = imagenInstagram.getImage().getScaledInstance(38, 39, Image.SCALE_SMOOTH);
    ImageIcon igIcono = new ImageIcon(imgIg);
        fInstagram.setIcon(igIcono);

    imagenTwitter =new ImageIcon(new URL("https://icones.pro/wp-content/uploads/2021/02/icones-twitter-noires.png"));
    Image imgTw = imagenTwitter.getImage().getScaledInstance(38, 39, Image.SCALE_SMOOTH);
    ImageIcon twIcono = new ImageIcon(imgTw);
        fTwitter.setIcon(twIcono);

        bIniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // TODO : poner validar datos

                try {
                    Main.generarVentanaPrincipal();
                } catch (MalformedURLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        rbAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pClave.setVisible(true);
            }
        });
        rbUsuarioNomal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pClave.setVisible(false);
            }
        });
    }


    public JPanel getpPrincipal() {
        return pPrincipal;
    }

    public static void main(String[] args) throws MalformedURLException {
        JFrame frame = new JFrame("vRegistro");
        frame.setContentPane(new vRegistro().pPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
    }
}
