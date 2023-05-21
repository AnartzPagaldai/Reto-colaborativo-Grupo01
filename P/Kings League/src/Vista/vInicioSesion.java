package Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.net.URL;

import Controlador.Main;
import Modelo.Usuario.Usuario;

import static Controlador.Main.*;

public class vInicioSesion {
    private JPanel pDatos;
    private JPanel pPrincipal;
    private ImageIcon LogoKingsLeague;
    private ImageIcon imagenTheGrefg;
    private ImageIcon imagenIbai;
    private ImageIcon imagenInstagram;
    private ImageIcon imagenTwitter;
    private ImageIcon imagenTwitch;
    private JLabel fLogoKingsLeague;
    private JPanel pIniciarSesion;
    private JTextField tfNombre;
    private JLabel jlNombre;
    private JPanel pDegradado;
    private JLabel jlContrasena;
    private JButton bIniciarSesion;
    private JPasswordField pfContrasena;
    private JLabel JLThegrefg;
    private JLabel JLIbai;
    private JPanel pHeader;
    private JPanel pFooter;
    private JLabel fTwitch;
    private JLabel fInstagram;
    private JLabel fTwitter;
    private JButton crearCuentaButton;
    private Usuario usuario;


    public vInicioSesion() throws MalformedURLException {

        pPrincipal = new JPanel(new BorderLayout());
        pPrincipal = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g;

                Color colorInicio = new Color(239, 122, 14);
                Color colorFin = new Color(253, 214, 44);

                GradientPaint gradient = new GradientPaint(
                        0, 0, colorInicio,
                        0, getHeight(), colorFin);

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

        // Poner la imagen de THE GREFG
        imagenTheGrefg = new ImageIcon(new URL("https://raw.githubusercontent.com/OiherOleaga/Reto-colaborativo-Grupo01/6ee3a0729ffdc90dd1217e0d5d400f6cda1f7f66/P/Kings%20League/src/main/java/Vista/Imagenes/THEGREFG.png"));
        Image imgGrefg = imagenTheGrefg.getImage().getScaledInstance(420, 570, Image.SCALE_SMOOTH);
        ImageIcon nuevoIcono = new ImageIcon(imgGrefg);
        JLThegrefg.setIcon(nuevoIcono);

        // Poner la imagen de Ibai Llanos
        imagenIbai = new ImageIcon(new URL("https://kingsleague.pro/wp-content/uploads/2022/12/IBAI-LLANOS-PORCINOS-FC.png"));
        Image imgIbai = imagenIbai.getImage().getScaledInstance(416, 600, Image.SCALE_SMOOTH);
        ImageIcon ibaiicono = new ImageIcon(imgIbai);
        JLIbai.setIcon(ibaiicono);

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

        bIniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                entrar();
            }
        });
        crearCuentaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    generarVentanaRegistro();
                } catch (MalformedURLException ex) {
                    System.out.println("error");
                }
            }
        });
        pfContrasena.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == 10) {
                    entrar();
                }
            }
        });
    }

    private void entrar() {
        boolean existe = false;
        try {
            existe = Main.selectUsuario(tfNombre.getText().toUpperCase(), pfContrasena.getText().toUpperCase());
            String tipo = getUsuarioTipo(tfNombre.getText(), pfContrasena.getText());
            if (existe) {
                if (tipo.equalsIgnoreCase("usuario")) {
                    Main.generarVentanaPrincipalUsuario();
                } else Main.generarVentanaPrincipalAdmin();
                tfNombre.setText("");
                pfContrasena.setText("");
            } else throw new Exception("Usuario o contraseña incorrectos");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public JPanel getpPrincipal() {
        return pPrincipal;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("vInicioSesion");
        try {
            frame.setContentPane(new vInicioSesion().pPrincipal);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
    }

}
