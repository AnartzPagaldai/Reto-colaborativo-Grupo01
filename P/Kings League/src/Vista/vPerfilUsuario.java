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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class vPerfilUsuario {


    private ImageIcon imagenUsuario;

    private ImageIcon LogoKingsLeague;


    private JPanel pPrincipal;
    private JPanel pDegradado;
    private JLabel jlImagenUsuario;
    private JTextField tfNombre;
    private JTextField tfCorreo;
    private JPasswordField pfContrasena;
    private JPanel pContenido;
    private JButton bAceptarCambios;
    private JButton bVolver;
    private JPanel pHeader;
    private JLabel fLogoKingsLeague;

    private Usuario usuarioAntes;

    private boolean correoCorrecto;

    private static String PATRONEMAIL = "^[\\w-\\.]+@gmail\\.com";



    public vPerfilUsuario() throws MalformedURLException {
        tfNombre.setText(Main.buscarNombre());
        tfCorreo.setText(Main.buscarCorreo().toLowerCase());
        pfContrasena.setText(Main.buscarContrasena());
        usuarioAntes =Main.getUsuario(tfNombre.getText(), tfCorreo.getText());

            // Poner fondo degradado
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

                    pPrincipal.add(pDegradado, BorderLayout.CENTER);


                }
            };

        // Poner la imagen del logo oficial de la Kings League
        LogoKingsLeague = new ImageIcon(new URL("https://seeklogo.com/images/K/kings-league-logo-CEDD6AED72-seeklogo.com.png"));
        Image LogoNuevo = LogoKingsLeague.getImage().getScaledInstance(300, 122, Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(LogoNuevo);
        fLogoKingsLeague.setIcon(newIcon);

        // Poner la imagen del usuario
        imagenUsuario = new ImageIcon(new URL("https://assets.stickpng.com/images/585e4beacb11b227491c3399.png"));
        Image imgUsuario = imagenUsuario.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon UsuIcono = new ImageIcon(imgUsuario);
        jlImagenUsuario.setIcon(UsuIcono);




        bAceptarCambios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validarCorreo();
                Usuario usuarioActu = new Usuario(tfNombre.getText(), pfContrasena.getText(), tfCorreo.getText());
                try {
                if (!correoCorrecto){
                    try {
                        throw new Exception("El correo no es válido.");
                    } catch (Exception exc) {
                        throw new RuntimeException(exc);
                    }
                }
                    if (tfCorreo.getText().isEmpty() || tfNombre.getText().isEmpty()|| pfContrasena.getText().isEmpty()){
                        try {
                            throw new Exception("No puede haber campos vacíos.");
                        } catch (Exception exc) {
                            throw new RuntimeException(exc);
                        }
                    }else if (correoCorrecto){
                        Main.actalizarUsuario( usuarioAntes, usuarioActu);
                        tfNombre.setText(usuarioActu.getNombre());
                        tfCorreo.setText(usuarioActu.getCorreo());
                        pfContrasena.setText(usuarioActu.getContrasena());
                        tfNombre.setBackground(new Color(255, 233, 176));
                        tfCorreo.setBackground(new Color(255, 233, 176));
                        JOptionPane.showMessageDialog(null, "Usuario actualizado correctamente.");
                    }else {
                        tfNombre.setBackground(Color.red);
                        tfCorreo.setBackground(Color.red);
                        try {
                            throw new Exception("Ya hay un usuario con el mismo nombre o email.");
                        } catch (Exception exc) {
                            throw new RuntimeException(exc);
                        }
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });



        tfCorreo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                validarCorreo();
            }
        });
        bVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.PrincipalUsuario();
            }
        });
    }

    public void validarCorreo(){
        Pattern pattern = Pattern.compile(PATRONEMAIL);
        Matcher matcher = pattern.matcher(tfCorreo.getText());
        if (!matcher.matches()){
            correoCorrecto=false;
            tfCorreo.setBackground(Color.red);
        }else {
            tfCorreo.setBackground(Color.green);
            correoCorrecto=true;
        }
    }


    public JPanel getpPrincipal() {
        return pPrincipal;
    }

    public static void main(String[] args) throws MalformedURLException {
        JFrame frame = new JFrame("vPerfilUsuario");
        frame.setContentPane(new vPerfilUsuario().pPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
    }

}