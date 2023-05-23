package Vista;

import Controlador.Main;
import Modelo.Enumeraciones.TipoSueldo;
import Modelo.Jugador.Jugador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Generar la clase vInsertEquipos.
 * Esta clase tiene el contenido y los métodos necesarios para ejecutar la ventana destinada a insertar los equipos.
 */
public class vInsertEquipos {
    private JPanel pDegradado;
    private JPanel pDatos;
    private JPanel pIniciarSesion;
    private JTextField tfNombre;
    private JLabel jlPresupuesto;
    private JLabel jlNombre;
    private JButton bAceptar;
    private JPanel pHeader;
    private JLabel fLogoKingsLeague;
    private JPanel pPrincipal;
    private JLabel jlImagen;
    private JTextField tfImagen;
    private JButton bAtras;
    private JLabel lColor;
    private JTextField tfColor;
    private JTextField tfSueldo;
    private JComboBox cbPresupuesto;
    private JMenu mUsuario;
    private ImageIcon LogoKingsLeague;
    private static final String patronLogo = "^(https?://)?([\\w.-]+)\\.([a-zA-Z]{2,})(/[\\w.-]*)*/?\\.(png)$";
    private static final String patronColor = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$";
    private static final String numeros= "^[0-9]+$";
    private boolean numerosCorrectos;
    private boolean enlaceCorrecto;
    private boolean colorCorrecto;


    public vInsertEquipos() throws MalformedURLException {
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
                boolean insertar;
                try {
                    enlaceCorrecto();
                    colorCorrecto();
                    numerosCorrectos();
                    if (enlaceCorrecto && colorCorrecto && numerosCorrectos){
                        insertar= Main.insertarEquipo(tfNombre.getText(), Double.parseDouble(tfSueldo.getText()), tfImagen.getText(), tfColor.getText());
                        if (insertar){
                            JOptionPane.showMessageDialog(null, "¡Equipo creado con exito!");
                        }
                    }else throw new Exception("Introduzca bien los datos, por favor");
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }

            }
        });
        bAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.PrincipalAdmin();
            }
        });
    }


    public JPanel getpPrincipal() {
        return pPrincipal;
    }

    public static void main(String[] args) throws MalformedURLException {
        JFrame frame = new JFrame("vInsertEquipos");
        frame.setContentPane(new vInsertEquipos().pPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
    }
    public void enlaceCorrecto(){
        Pattern pattern = Pattern.compile(patronLogo);
        Matcher matcher = pattern.matcher(tfImagen.getText());
        if (!matcher.matches()){
            enlaceCorrecto=false;
            tfImagen.setBackground(Color.red);
        }else {
            tfImagen.setBackground(Color.green);
            enlaceCorrecto=true;
        }
    }

    public void colorCorrecto(){
        Pattern pattern = Pattern.compile(patronColor);
        Matcher matcher = pattern.matcher(tfColor.getText());
        if (!matcher.matches()){
            colorCorrecto=false;
            tfColor.setBackground(Color.red);
        }else {
            tfColor.setBackground(Color.green);
            colorCorrecto=true;
        }
    }

    public void numerosCorrectos(){
        Pattern pattern = Pattern.compile(numeros);
        Matcher matcher = pattern.matcher(tfSueldo.getText());
        if (!matcher.matches()){
            numerosCorrectos=false;
            tfSueldo.setBackground(Color.red);
        }else {
            tfSueldo.setBackground(Color.green);
            numerosCorrectos=true;
        }
    }
}
