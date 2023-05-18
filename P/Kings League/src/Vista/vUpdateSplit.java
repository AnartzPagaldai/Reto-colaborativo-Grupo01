package Vista;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

public class vUpdateSplit {
    private JPanel pPrincipal;
    private JPanel pDegradado;
    private JPanel pDatos;
    private JPanel pIniciarSesion;
    private JTextField tfNombre;
    private JLabel jlPresupuesto;
    private JLabel jlNombre;
    private JButton bAceptar;
    private JLabel jlImagen;
    private JPanel pHeader;
    private JLabel fLogoKingsLeague;
    private JMenuBar jmheader;
    private JMenu mEquipos;
    private JMenuItem jmiConsultarEquipos;
    private JMenu mPartidos;
    private JMenuItem jmiVer;
    private JMenu mClasificacion;
    private JMenu mUsuario;
    private JMenuItem jmiVerPerfil;
    private JMenuItem jmiCerrarSesion;
    private JRadioButton ABIERTORadioButton;
    private JRadioButton CERRADORadioButton;
    private JRadioButton VERANORadioButton;
    private JRadioButton INVIERNORadioButton;
    private JButton bAtras;

    public vUpdateSplit() throws MalformedURLException {

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
        ImageIcon LogoKingsLeague = new ImageIcon(new URL("https://seeklogo.com/images/K/kings-league-logo-CEDD6AED72-seeklogo.com.png"));
        Image LogoNuevo = LogoKingsLeague.getImage().getScaledInstance(300, 122, Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(LogoNuevo);
        fLogoKingsLeague.setIcon(newIcon);

        ImageIcon imagenUsuario = new ImageIcon(new URL("https://assets.stickpng.com/images/585e4beacb11b227491c3399.png"));
        Image imgUsuario = imagenUsuario.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon UsuIcono = new ImageIcon(imgUsuario);
        mUsuario.setIcon(UsuIcono);
    }


    public JPanel getpPrincipal() {
        return pPrincipal;
    }
    
    public static void main(String[] args) throws MalformedURLException {
        JFrame frame = new JFrame("vUpdateSplit");
        frame.setContentPane(new vUpdateSplit().pPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
    }
}
