package Vista;

import Controlador.Main;
import Modelo.Enumeraciones.TipoPersonal;
import Modelo.Personal.Personal;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Generar la clase vActualizarPersonal.
 * Esta clase tiene el contenido y los métodos necesarios para ejecutar la ventana destinada a actualizar un miembro del personal.
 */
public class vActualizarPersonal {
    private JPanel pPrincipal;
    private JPanel pDegradado;
    private JPanel pDatos;
    private JPanel pCrearJugador;
    private ImageIcon LogoKingsLeague;
    private JButton bCrear;
    private JPanel pDatosPersonales;
    private JLabel jlNombre;
    private JTextField tfNombre;
    private JTextField tfApellido;
    private JLabel jlContrasena;
    private JTextField tfIMG;
    private JComboBox cbOficio;
    private JFormattedTextField tfTelefono;
    private JFormattedTextField tfDNI;
    private JButton bAtras;
    private JComboBox cbDNIS;
    private JLabel JLThegrefg;
    private JLabel JLIbai;
    private JPanel pHeader;
    private Personal personal;
    private JLabel fLogoKingsLeague;
    private static final String patronEnlace = "^(https?://)?([\\w.-]+)\\.([a-zA-Z]{2,})(/[\\w.-]*)*/?\\.(png)$";
    private boolean enlaceCorrecto;
    private TipoPersonal tipoPersonal;
    public vActualizarPersonal() throws MalformedURLException{
        pPrincipal = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g;



                Color colorInicio = new Color(253, 214, 44);
                Color colorFin = new Color(239, 122, 14);

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

        ArrayList<String> oficios= new ArrayList<>();
        oficios.add("SELECCIONE UNA OPCION");
        oficios.add("ENTRENADOR");
        oficios.add("PRESIDENTE");

        for (int x=0;x<oficios.size(); x++){
            cbOficio.addItem(oficios.get(x));
        }
        ArrayList<String> dni= Main.selectDNIPersonal();
        for (int x=0; x<dni.size();x++){
            cbDNIS.addItem(dni.get(x));
        }
        cbDNIS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cbDNIS.getSelectedIndex()==0){
                    pDatosPersonales.setVisible(false);
                }else{
                    pDatosPersonales.setVisible(true);
                    personal=Main.personalPorDNI(cbDNIS.getSelectedItem().toString());
                    tfNombre.setText(personal.getNombre());
                    tfApellido.setText(personal.getApellidos());
                    tfDNI.setText(personal.getDni());
                    tfIMG.setText(personal.getImg());
                    tfTelefono.setText(personal.getTelefono());
                    cbDNIS.setToolTipText(personal.getOficio().toString());
                }
            }
        });
        bCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean update;
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
                        tipoPersonal=TipoPersonal.ENTRENADOR;
                    }else tipoPersonal=TipoPersonal.PRESIDENTE;
                    update= Main.updatePersonal(personal.getId(),tfNombre.getText(), tfApellido.getText(), tfDNI.getText(), tfTelefono.getText(), tipoPersonal, tfIMG.getText());
                    if (update){
                        JOptionPane.showMessageDialog(null, "¡Personal actualizado con exito!");
                    }
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
    }
    public static void main(String[] args) throws MalformedURLException {
        JFrame frame = new JFrame("vActualizarPersonal");
        frame.setContentPane(new vActualizarPersonal().pPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    private void createUIComponents() throws ParseException {
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
}
