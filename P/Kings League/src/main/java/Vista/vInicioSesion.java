package Vista;
import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;

public class vInicioSesion {

    private JPanel pPrincipal; /*= new JPanel() {
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
    };*/

    private JPanel pDatos;
    private ImageIcon LogoKingsLeague;
    private ImageIcon ImagenBalonLlamas;
    private JLabel fLogoKingsLeague;
    private JPanel pIniciarSesion;
    private JTextField tfNombre;
    private JLabel jlNombre;
    private JLabel jlContrasena;
    private JButton bIniciarSesion;
    private JPasswordField contrasena;
    private JLabel fImagenBalonLlamas;


    public vInicioSesion() throws MalformedURLException {

        // Poner la imagen del logo oficial de la Kings League
        LogoKingsLeague = new ImageIcon(new URL("https://seeklogo.com/images/K/kings-league-logo-CEDD6AED72-seeklogo.com.png"));
        Image LogoNuevo = LogoKingsLeague.getImage().getScaledInstance(300, 122, Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(LogoNuevo);
        fLogoKingsLeague.setIcon(newIcon);

        // Poner la imagen del balón en llamas
        ImagenBalonLlamas = new ImageIcon(new URL("https://th.bing.com/th/id/R.23e581f9a4a52c70ed576c442d3e8611?rik=iSDaQxC2ztwQ2A&riu=http%3a%2f%2fclipartmag.com%2fimages%2fsoccer-ball-with-flames-clipart-43.png&ehk=o0wMuOb0oyNia65TeeMOa51boeR1lxpmg5jlPTYT16U%3d&risl=&pid=ImgRaw&r=0"));
        Image ImagenBalon = ImagenBalonLlamas.getImage().getScaledInstance(500, 390, Image.SCALE_SMOOTH);
        ImageIcon nuevoIcono = new ImageIcon(ImagenBalon);
        fImagenBalonLlamas.setIcon(nuevoIcono);

        bIniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // poner validación de datos e ir a la siguiente página

            }
        });
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

    private void createUIComponents() throws ParseException {
        // TODO: place custom component creation code here
        contrasena = new JPasswordField(new MaskFormatter(("UUUUUUUUUU")).getPlaceholderCharacter());
    }
}
