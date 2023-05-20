package Vista;

import Controlador.Main;
import Modelo.Enumeraciones.TipoPersonal;
import Modelo.Personal.TPersonal;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class vInsertarPersonal {
    private JPanel pPrincipal;
    private JPanel pDegradado;
    private JPanel pDatos;
    private JPanel pIniciarSesion;
    private JLabel jlNombre;
    private JLabel jlTelefono;
    private JTextField tfNombre;
    private JLabel JLThegrefg;
    private JLabel JLIbai;
    private JPanel pHeader;
    private JLabel fLogoKingsLeague;
    private JLabel jlApellidos;
    private JLabel jlDni;
    private JLabel jlOficio;
    private JLabel jlImagen;
    private JTextField tfApellidos;
    private JFormattedTextField ftfDni;
    private JFormattedTextField ftfTelefono;
    private JComboBox<String> cbOficio;
    private JTextField tfImagen;
    private JButton bCrear;
    private JButton bAtras;
    private JPanel pBotones;
    private ImageIcon LogoKingsLeague;
    private static boolean correcto = true;


    public vInsertarPersonal() throws MalformedURLException {

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
            }
        };

        cbOficio.addItem("Presidente");
        cbOficio.addItem("Entrenador");
        pPrincipal.add(pDegradado, BorderLayout.CENTER);


        // Poner la imagen del logo oficial de la Kings League
        LogoKingsLeague = new ImageIcon(new URL("https://seeklogo.com/images/K/kings-league-logo-CEDD6AED72-seeklogo.com.png"));
        Image LogoNuevo = LogoKingsLeague.getImage().getScaledInstance(300, 122, Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(LogoNuevo);
        fLogoKingsLeague.setIcon(newIcon);


        bCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validarNombre(tfNombre.getText());
                validarApellidos(tfApellidos.getText());
                validarOficio(cbOficio.getSelectedIndex());
                validarImagen(tfImagen.getText());
                validarDni(ftfDni.getText());

                if (correcto){
                    try {
                        Main.insertarPersonal(tfNombre.getText(), tfApellidos.getText(), ftfDni.getText(), ftfTelefono.getText(), String.valueOf(cbOficio.getSelectedItem()), tfImagen.getText());
                        JOptionPane.showMessageDialog(null, "Personal insertado correctamente.");
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    tfNombre.setText("");
                    tfApellidos.setText("");
                    ftfDni.setText("");
                    ftfTelefono.setText("");
                    cbOficio.setSelectedIndex(0);
                    tfImagen.setText("");
                    JOptionPane.showMessageDialog(null, "personal insetado");
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

    private void validarDni(String dni) {
        Matcher encaja;
        Pattern pat = Pattern.compile("[0-9][A-Z a-z]");
        encaja = pat.matcher(dni);
        if (!encaja.matches()) {
            JOptionPane.showMessageDialog(null, "el dni no es correcto");
            correcto = false;
        } else
            correcto = true;
        if (!Main.buscarDni(ftfDni.getText())) {
            JOptionPane.showMessageDialog(null, "ya hay una persona con este dni");
            correcto = false;
        }
    }

    private void createUIComponents() throws Exception {
        try {
            ftfDni = new JFormattedTextField(new MaskFormatter("########U"));
            ftfTelefono = new JFormattedTextField((new MaskFormatter("#########")));
        } catch (ParseException e) {
            throw new Exception("Algún campo no cumple con el formato establecido.");
        }
    }

    public static void validarNombre(String nombre) {
        Matcher encaja;
        Pattern pat = Pattern.compile("^[A-Z][a-z]+( [A-Z][a-z]+)*$");
        encaja = pat.matcher(nombre);
        if (!encaja.matches()) {
            JOptionPane.showMessageDialog(null, "El nombre debe empezar por una mayúscula y seguir con minúsculas.");
            correcto = false;
        }
    }

    public static void validarApellidos(String apellidos) {
        Matcher encaja;
        Pattern pat = Pattern.compile("^[A-Z][a-z]+( [A-Z][a-z]+)*$");
        encaja = pat.matcher(apellidos);
        if (!encaja.matches()) {
            JOptionPane.showMessageDialog(null, "Los apellidos deben empezar por una mayúscula y seguir con minúsculas.");
            correcto = false;
        }
    }

    public static void validarOficio(int opcion) {

        if (opcion == 0) {
            JOptionPane.showMessageDialog(null, "El oficio debe ser 'presidente' o 'entrenador'.");
            correcto = false;
        }
    }

    public static void validarImagen(String imagen) {

        Matcher encaja;
        Pattern pat = Pattern.compile("^(https?://)?([\\w.-]+)\\.([a-zA-Z]{2,})(/[\\w.-]*)*/?\\.(png)$");
        encaja = pat.matcher(imagen);
        if (!encaja.matches()) {
            JOptionPane.showMessageDialog(null, "La imagen debe empezar por 'https' y terminar con '.png'.");
            correcto = false;
        }
    }


    public JPanel getpPrincipal() {
        return pPrincipal;
    }

    public static void main(String[] args) throws MalformedURLException {
        JFrame frame = new JFrame("vInsertarPersonal");
        frame.setContentPane(new vInsertarPersonal().pPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
    }

}
