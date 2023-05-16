package Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

public class vActualizarEquipo {
    private JPanel pPrincipal;
    private JPanel pDegradado;
    private JButton bAceptar;
    private JPanel pDatos;
    private JPanel pIniciarSesion;
    private JTextField tfNombre;
    private JLabel jlPresupuesto;
    private JLabel jlNombre;
    private JTextField tfPresupuesto;
    private JLabel jlImagen;
    private JTextField tfImagen;
    private JPanel pHeader;
    private JLabel fLogoKingsLeague;
    private ImageIcon LogoKingsLeague;


    public vActualizarEquipo() throws MalformedURLException {
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

        bAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }


    public JPanel getpPrincipal() {
        return pPrincipal;
    }

    public static void main(String[] args) throws MalformedURLException {
        JFrame frame = new JFrame("vActualizarEquipo");
        frame.setContentPane(new vActualizarEquipo().pPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
    }
}
