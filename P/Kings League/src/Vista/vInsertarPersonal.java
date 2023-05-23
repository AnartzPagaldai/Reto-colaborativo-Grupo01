package Vista;

import Controlador.Main;
import Modelo.Enumeraciones.TipoPersonal;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Generar la clase vInsertarPersonal.
 * Esta clase tiene el contenido y los métodos necesarios para ejecutar la ventana destinada a insertar a los miembros del personal.
 */
public class vInsertarPersonal {
    private JPanel pPrincipal;
    private JPanel pDegradado;
    private JPanel pDatos;
    private JPanel pIniciarSesion;
    private JLabel jlNombre;
    private JLabel jlTelefono;
    private JTextField tfNombre;
    private JPanel pHeader;
    private JLabel fLogoKingsLeague;
    private JLabel jlApellidos;
    private JLabel jlDni;
    private JLabel jlOficio;
    private JLabel jlImagen;
    private JTextField tfApellido;
    private JFormattedTextField tfDNI;
    private JFormattedTextField tfTelefono;
    private JComboBox<String> cbOficio;
    private JTextField tfIMG;
    private JButton bCrear;
    private JButton bAtras;
    private JPanel pBotones;
    private ImageIcon LogoKingsLeague;
    private static final String patronEnlace = "^(https?://)?([\\w.-]+)\\.([a-zA-Z]{2,})(/[\\w.-]*)*/?\\.(png)$";
    private boolean enlaceCorrecto;
    private TipoPersonal personal;

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

        ArrayList<String> oficios= new ArrayList<>();
        oficios.add("SELECCIONE UNA OPCION");
        oficios.add("ENTRENADOR");
        oficios.add("PRESIDENTE");

        for (int x=0;x<oficios.size(); x++){
            cbOficio.addItem(oficios.get(x));
        }

        pPrincipal.add(pDegradado, BorderLayout.CENTER);


        // Poner la imagen del logo oficial de la Kings League
        LogoKingsLeague = new ImageIcon(new URL("https://seeklogo.com/images/K/kings-league-logo-CEDD6AED72-seeklogo.com.png"));
        Image LogoNuevo = LogoKingsLeague.getImage().getScaledInstance(300, 122, Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(LogoNuevo);
        fLogoKingsLeague.setIcon(newIcon);


        bCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean insertar;
                try {
                    enlaceCorrecto();
                    if (cbOficio.getSelectedIndex()==0){
                        throw new Exception("Por favor, seleccione un oficios correcto");
                    }
                    if (!enlaceCorrecto){
                        throw new Exception("El enlace es incorrecto");
                    }
                    if (tfNombre.getText().isEmpty()||tfApellido.getText().isEmpty()||tfDNI.getText().isEmpty()||tfApellido.getText().isEmpty()||tfTelefono.getText().isEmpty() || tfIMG.getText().isEmpty()){
                        throw new Exception("No pueden haber campos vacios");
                    }
                    if (cbOficio.getSelectedIndex()==1){
                        personal=TipoPersonal.ENTRENADOR;
                    }else personal=TipoPersonal.PRESIDENTE;
                    insertar= Main.insertarPersonal(tfNombre.getText(), tfApellido.getText(), tfDNI.getText(), tfTelefono.getText(), personal, tfIMG.getText());
                    if (insertar){
                        JOptionPane.showMessageDialog(null, "¡Personal creado con exito!");
                        tfNombre.setText("");
                        tfIMG.setText("");
                        tfApellido.setText("");
                        tfDNI.setText("");
                        tfTelefono.setText("");
                        cbOficio.setSelectedIndex(0);
                    }
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

    private void createUIComponents() throws Exception {
        tfTelefono = new JFormattedTextField(new MaskFormatter("#########"));
        tfDNI = new JFormattedTextField(new MaskFormatter("########U"));
    }

    public void enlaceCorrecto(){
        Pattern pattern = Pattern.compile(patronEnlace);
        Matcher matcher = pattern.matcher(tfIMG.getText());
        if (!matcher.matches()){
            enlaceCorrecto=false;
            tfIMG.setBackground(Color.red);
        }else {
            tfIMG.setBackground(Color.green);
            enlaceCorrecto=true;
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
