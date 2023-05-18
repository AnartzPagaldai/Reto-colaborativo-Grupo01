package Vista;

import Controlador.Main;
import Modelo.Personal.TPersonal;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class vActualizarPersonal {
    private JPanel pPrincipal;
    private JPanel pDegradado;
    private JPanel pDatos;
    private JPanel pIniciarSesion;
    private JLabel jlApellidos;
    private JLabel jlDni;
    private JLabel jlTelefono;
    private JLabel jlOficio;
    private JLabel jlImagen;
    private JTextField tfImagen;
    private JTextField tfApellidos;
    private JTextField tfNombre;
    private JFormattedTextField ftfDni;
    private JFormattedTextField ftfTelefono;
    private JComboBox cbOficio;
    private JLabel jlNombre;
    private JLabel JLThegrefg;
    private JLabel JLIbai;
    private JPanel pBotones;
    private JButton bAceptar;
    private JButton bAtras;
    private JPanel pHeader;
    private JLabel fLogoKingsLeague;
    private ImageIcon LogoKingsLeague;
    private static boolean correcto;

    public vActualizarPersonal() throws MalformedURLException {

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


        pPrincipal.add(pDegradado, BorderLayout.CENTER);


        // Poner la imagen del logo oficial de la Kings League
        LogoKingsLeague = new ImageIcon(new URL("https://seeklogo.com/images/K/kings-league-logo-CEDD6AED72-seeklogo.com.png"));
        Image LogoNuevo = LogoKingsLeague.getImage().getScaledInstance(300, 122, Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(LogoNuevo);
        fLogoKingsLeague.setIcon(newIcon);
        cbOficio.addItem("Presidente");
        cbOficio.addItem("Entrenador");


        bAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validarNombre(tfNombre.getText());
                validarApellidos(tfApellidos.getText());
                validarOficio(cbOficio.getSelectedIndex());
                validarImagen(tfImagen.getText());
                // TODO: si todo correcto actualizar datos
                if (correcto)
                    if (Main.actualizarPersonal(tfNombre.getText(), tfApellidos.getText(), ftfDni.getText(), ftfTelefono.getText(), String.valueOf(cbOficio.getSelectedItem()), tfImagen.getText()))
                        JOptionPane.showMessageDialog(null, "se a insertado el personal");
                    else
                        JOptionPane.showMessageDialog(null, "no se a insertado el parsonal");
            }
        });
        bAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.PrincipalAdmin();
            }
        });

        ftfDni.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                validarDni(ftfDni.getText());

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
        if (Main.buscarDniPersoal(ftfDni.getText())) {
            JOptionPane.showMessageDialog(null, "no esite nadie con ese dni");
            correcto = false;
        }
    }


    public static boolean validarNombre(String nombre){
        Matcher encaja;
        Pattern pat = Pattern.compile("^[A-Z][a-z]+( [A-Z][a-z]+)*$");
        encaja = pat.matcher(nombre);
        if (!encaja.matches()){
            JOptionPane.showMessageDialog(null,"El nombre debe empezar por una mayúscula y seguir con minúsculas.");
            correcto=false;
        }
        else
            correcto=true;
        return correcto;
    }
    public static boolean validarApellidos(String apellidos){
        Matcher encaja;
        Pattern pat = Pattern.compile("^[A-Z][a-z]+( [A-Z][a-z]+)*$");
        encaja = pat.matcher(apellidos);
        if (!encaja.matches()){
            JOptionPane.showMessageDialog(null,"Los apellidos deben empezar por una mayúscula y seguir con minúsculas.");
            correcto=false;
        }
        else
            correcto=true;
        return correcto;
    }
    public static boolean validarOficio(int opcion) {

        if (opcion==0){
            JOptionPane.showMessageDialog(null,"El oficio debe ser 'presidente' o 'entrenador'.");
            correcto=false;
        }
        else
            correcto=true;
        return correcto;
    }
    public static boolean validarImagen(String imagen){

        Matcher encaja;
        // TODO: poner patrón correcto
        Pattern pat = Pattern.compile("^$");
        encaja = pat.matcher(imagen);
        if (!encaja.matches()){
            JOptionPane.showMessageDialog(null,"La imagen debe empezar por 'https' y terminar con '.png'.");
            correcto=false;
        }
        else
            correcto=true;
        return correcto;
    }

    private void createUIComponents() throws Exception {
        try {
            ftfDni = new JFormattedTextField(new MaskFormatter("########U"));
            ftfTelefono = new JFormattedTextField((new MaskFormatter("#########")));
        }
        catch (ParseException e){
            throw new Exception("Algún campo no cumple con el formato establecido.");
        }
    }


    public JPanel getpPrincipal() {
        return pPrincipal;
    }

    public static void main(String[] args) throws MalformedURLException {
        JFrame frame = new JFrame("vActualizarPersonal");
        frame.setContentPane(new vActualizarPersonal().pPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
    }

}
