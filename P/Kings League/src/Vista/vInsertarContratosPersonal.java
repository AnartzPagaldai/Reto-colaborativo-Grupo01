package Vista;

import Controlador.Main;
import Modelo.Enumeraciones.TipoSueldo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Generar la clase vInsertarContratosPersonal.
 * Esta clase tiene el contenido y los métodos necesarios para ejecutar la ventana destinada a insertar los contratos del personal.
 */
public class vInsertarContratosPersonal {
    private JPanel pPrincipal;
    private JPanel pDegradado;
    private JPanel pDatos;
    private JPanel pIniciarSesion;
    private JLabel jlPresupuesto;
    private JLabel jlNombre;
    private JButton bAceptar;
    private JComboBox cbEquipos;
    private JComboBox cbPersonal;
    private JPanel pHeader;
    private JLabel fLogoKingsLeague;
    private JMenuBar jmheader;
    private JMenu jmInicio;
    private JMenuItem jmiPrincipal;
    private JMenu mEquipos;
    private JMenuItem jmiConsultarEquipos;
    private JMenu mPartidos;
    private JMenuItem jmiVer;
    private JMenu mClasificacion;
    private JMenu mUsuario;
    private JMenuItem jmiVerPerfil;
    private JMenuItem jmiCerrarSesion;
    private JComboBox cbSueldo;
    private JButton bAtras;
    private JTextField tfFechaFin;
    private static final String patronFecha="\\d{4}-\\d{2}-\\d{2}";
    private TipoSueldo sueldo;

    private boolean fechaCorrecta;
    public vInsertarContratosPersonal() throws MalformedURLException {
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

        for (TipoSueldo valor: TipoSueldo.values()){
            cbSueldo.addItem(valor.getValor());
        }
        generarCombo();
        ArrayList<String> nombres=Main.selectNombresEquipos();
        for (int x=0; x<nombres.size();x++){
            cbEquipos.addItem(nombres.get(x));
        }


        // Poner la imagen del logo oficial de la Kings League
        ImageIcon LogoKingsLeague = new ImageIcon(new URL("https://seeklogo.com/images/K/kings-league-logo-CEDD6AED72-seeklogo.com.png"));
        Image LogoNuevo = LogoKingsLeague.getImage().getScaledInstance(300, 122, Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(LogoNuevo);
        fLogoKingsLeague.setIcon(newIcon);

        // Poner la imagen del usuario
        ImageIcon imagenUsuario = new ImageIcon(new URL("https://assets.stickpng.com/images/585e4beacb11b227491c3399.png"));
        Image imgUsuario = imagenUsuario.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon UsuIcono = new ImageIcon(imgUsuario);
        mUsuario.setIcon(UsuIcono);
        bAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.PrincipalAdmin();
            }
        });
        bAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean insertar;
                try {
                    fechaCorrecta();
                    if (cbSueldo.getSelectedIndex()==0){
                        sueldo=TipoSueldo.DIEZ_MILLONES;
                    }
                    if(cbSueldo.getSelectedIndex()==1){
                        sueldo=TipoSueldo.DIEZ_MILLONES_MEDIO;
                    }
                    if(cbSueldo.getSelectedIndex()==2){
                        sueldo=TipoSueldo.QUINCE_MILLONES;
                    }
                    if(cbSueldo.getSelectedIndex()==3){
                        sueldo=TipoSueldo.VEINTIDOS_MILLONES_MEDIO;
                    }
                    if (fechaCorrecta) {
                        insertar = Main.insertarContratoPersonal(cbPersonal.getSelectedItem().toString(), cbEquipos.getSelectedItem().toString(), tfFechaFin.getText(), sueldo);
                        if (insertar) {
                            JOptionPane.showMessageDialog(null, "¡Contrato hecho con exito!");
                            cbPersonal.removeAllItems();
                            generarCombo();
                            tfFechaFin.setText("");
                            cbSueldo.setSelectedIndex(0);
                            cbPersonal.setSelectedIndex(0);
                            cbEquipos.setSelectedIndex(0);
                            tfFechaFin.setBackground(new Color(255, 233, 176));
                        } else throw new Exception("Fallos al insertar el contrato");
                    }
                }catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
    }

    public static void main(String[] args) throws MalformedURLException {
        JFrame frame = new JFrame("vInsertarContratosPersonal");
        frame.setContentPane(new vInsertarContratosPersonal().pPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
    }
    public void fechaCorrecta() throws Exception {
        Pattern pattern = Pattern.compile(patronFecha);
        Matcher matcher = pattern.matcher(tfFechaFin.getText());
        if (!matcher.matches()){
            fechaCorrecta=false;
            tfFechaFin.setBackground(Color.red);
            throw new Exception("La fecha es incorrecta");
        }else {
            tfFechaFin.setBackground(Color.green);
            fechaCorrecta=true;
        }
    }
    private void generarCombo(){
        ArrayList<String> personalSinContrato = Main.getDNISinContratoPersonal();
        for (int x = 0; x< personalSinContrato.size(); x++)
        {
            cbPersonal.addItem(personalSinContrato.get(x));
        }
    }
}
