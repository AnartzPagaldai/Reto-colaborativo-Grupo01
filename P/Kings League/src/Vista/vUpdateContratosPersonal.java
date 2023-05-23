package Vista;

import Controlador.Main;
import Modelo.Enumeraciones.TipoSueldo;
import Modelo.Personal.ContratoPersonal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Generar la clase vUpdateContratosPersonal.
 * Esta clase tiene el contenido y los métodos necesarios para ejecutar la ventana destinada a actualizar los contratos del personal.
 */
public class vUpdateContratosPersonal {
    private JPanel pDegradado;
    private JPanel pDatos;
    private JPanel pIniciarSesion;
    private JLabel jlPresupuesto;
    private JLabel jlNombre;
    private JButton bAceptar;
    private JComboBox cbEquipos;
    private JPanel pHeader;
    private JLabel fLogoKingsLeague;
    private JMenuBar jmheader;
    private JMenu mUsuario;
    private JPanel pPrincipal;
    private JComboBox cbID;
    private JComboBox cbSueldo;
    private JButton bAtras;
    private JTextField tfPersonal;
    private JTextField tfFechaFin;
    private ContratoPersonal contratoPersonal;
    private static final String patronFecha="\\d{4}-\\d{2}-\\d{2}";
    private boolean fechaCorrecta;
    private TipoSueldo sueldo;

    // Poner fondo degradado


    public vUpdateContratosPersonal() throws MalformedURLException {
        ArrayList<String> id= Main.getIDContratosPersonales();
        for (int x=0; x<id.size();x++){
            cbID.addItem(id.get(x));
        }
        ArrayList<String> nombres=Main.selectNombresEquipos();
        for (int x=0; x<nombres.size();x++){
            cbEquipos.addItem(nombres.get(x));
        }

        for (TipoSueldo valor: TipoSueldo.values()){
            cbSueldo.addItem(valor.getValor());
        }
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
        cbID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    contratoPersonal=Main.contratosPersonalPorID(cbID.getSelectedItem().toString());
                    cbEquipos.setSelectedItem(contratoPersonal.getEquipo().getNombre());
                    tfPersonal.setText(contratoPersonal.getPersonal().getDni());
                    cbSueldo.setSelectedItem(contratoPersonal.getSueldo().getValor());
                    tfFechaFin.setText(String.valueOf(contratoPersonal.getFechaFin()));
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
        bAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    boolean update;
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
                        update = Main.updateContratosPersonal(contratoPersonal.getId(),cbEquipos.getSelectedItem().toString(), tfFechaFin.getText(), sueldo);
                        if (update) {
                            JOptionPane.showMessageDialog(null, "¡Contrato actualizado con exito!");
                            tfFechaFin.setText("");
                            cbSueldo.setSelectedIndex(0);
                            tfPersonal.setText("");
                            cbID.setSelectedIndex(0);
                            cbEquipos.setSelectedIndex(0);
                            tfFechaFin.setBackground(new Color(255, 233, 176));
                        } else throw new Exception("Fallos al actualizar el contrato");
                    }
                } catch (Exception ex) {
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

    public static void main(String[] args) throws MalformedURLException {
        JFrame frame = new JFrame("vUpdateContratosPersonal");
        frame.setContentPane(new vUpdateContratosPersonal().pPrincipal);
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
}
