package Vista;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

public class vPerfilUsuario {


    private ImageIcon imagenUsuario;

    private ImageIcon LogoKingsLeague;


    private JPanel pPrincipal;
    private JPanel pDegradado;
    private JLabel fTwitch;
    private JLabel fInstagram;
    private JLabel fTwitter;
    private JLabel jlImagenUsuario;
    private JLabel jlLogoKingsLeague;
    private JTextField tfNombre;
    private JTextField tfCorreo;
    private JPasswordField pfContrasena;
    private JPanel pContenido;
    private JButton bAceptarCambios;
    private JButton bVolver;
    private JPanel pHeader;
    private JLabel fLogoKingsLeague;


    public vPerfilUsuario() throws MalformedURLException {




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
            }

            ;

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







        // Poner la imagen del usuario
        /*
        imagenIbai = new ImageIcon(new URL("https://kingsleague.pro/wp-content/uploads/2022/12/IBAI-LLANOS-PORCINOS-FC.png"));
        Image imgIbai = imagenIbai.getImage().getScaledInstance(416, 600, Image.SCALE_SMOOTH);
        ImageIcon ibaiicono = new ImageIcon(imgIbai);
        JLIbai.setIcon(ibaiicono);

        Poner la imagen en un botón, y cuando se le clique que aparezcan posibles opciones diferentes abajo [+ la actual]
        (al clicar cada imagen la de arriba se cambiará. Todas son sacadas con enlace de google)
         */

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
