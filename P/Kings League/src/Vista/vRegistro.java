package Vista;
import Controlador.Main;
import Modelo.Usuario.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private JPanel pDegradado;
    private JPanel pClave;
    private JPasswordField pfClaveAdmin;
    private JPanel pCrearDatos;
    private JButton atrasButton;
    private Usuario.TipoUsuario tipo;
    private String tipoUsuario;
    private boolean correoCorrecto;
    private final String clave="12345";
    private static final String patronEmail = "^[\\w-\\.]+@gmail\\.com$";
//TODO:LOS RADIOS BUTTON TIENE QUE ESTAR EN UN GRUPOS
    public vRegistro() throws MalformedURLException {
        pPrincipal = new JPanel(new BorderLayout());
        pPrincipal= new JPanel() {
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
                boolean insertar;
                try {
                    if (rbAdmin.isSelected()){
                        tipoUsuario=tipo.Admin.toString();
                        if (!pfClaveAdmin.getText().equals(clave)){
                            pfClaveAdmin.setBackground(Color.red);
                            throw new Exception( "La clave de admin no es la correcta");
                        }
                    }else tipoUsuario=tipo.Usuario.toString();
                    insertar=Main.crearUsuario(tfNombre.getText().toUpperCase(), tfCorreo.getText().toUpperCase(), pfContrasena.getText().toUpperCase(), Usuario.TipoUsuario.valueOf(tipoUsuario));
                    if (!correoCorrecto){
                        throw new Exception("El correo no es valido");
                    }
                    if (tfCorreo.getText().isEmpty() || tfNombre.getText().isEmpty()|| pfContrasena.getText().isEmpty() || !rbAdmin.isSelected() || !rbUsuarioNomal.isSelected()){
                        throw new Exception("No pueden haber campos vacios");
                    }else if (insertar && correoCorrecto){
                        JOptionPane.showMessageDialog(null, "Usuario creado");
                        tfNombre.setText("");
                        tfCorreo.setText("");
                        pfContrasena.setText("");
                        pfClaveAdmin.setText("");
                        pfClaveAdmin.setBackground(new Color(255, 233, 176));
                        tfNombre.setBackground(new Color(255, 233, 176));
                        tfCorreo.setBackground(new Color(255, 233, 176));
                    }else {
                        tfNombre.setBackground(Color.red);
                        tfCorreo.setBackground(Color.red);
                        throw new Exception("Ya hay un usuario con el mismo nombre o email");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
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
        tfCorreo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                Pattern pattern = Pattern.compile(patronEmail);
                Matcher matcher = pattern.matcher(tfCorreo.getText());
                if (!matcher.matches()){
                    correoCorrecto=false;
                    tfCorreo.setBackground(Color.red);
                    JOptionPane.showMessageDialog(null, "Email no valido");
                }else {
                    tfCorreo.setBackground(Color.green);
                    correoCorrecto=true;
                }
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
