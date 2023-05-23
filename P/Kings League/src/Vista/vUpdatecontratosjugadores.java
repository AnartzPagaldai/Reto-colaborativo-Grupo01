package Vista;

import Controlador.Main;
import Modelo.Enumeraciones.TipoSueldo;
import Modelo.Jugador.ContratoJugador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Generar la clase vUpdatecontratosjugadores.
 * Esta clase tiene el contenido y los métodos necesarios para ejecutar la ventana destinada a actualizar los contratos de los jugadores.
 */
public class vUpdatecontratosjugadores {
    private JPanel pDegradado;
    private JPanel pDatos;
    private JPanel pIniciarSesion;
    private JLabel jlPresupuesto;
    private JLabel jlNombre;
    private JButton bAceptar;
    private JLabel jlClausula;
    private JTextField tfClausula;
    private JTextField tfDorsal;
    private JComboBox cbEquipos;
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
    private JComboBox cbID;
    private JPanel pPrincipal;
    private JTextField tfJugador;
    private JButton bAtras;
    private JTextField tfFechaFin;
    private ContratoJugador contratoJugador;
    private static final String patronFecha="\\d{4}-\\d{2}-\\d{2}";
    private boolean fechaCorrecta;
    private boolean dorsalCorrecto;
    private boolean clasulaCorrecta;
    private TipoSueldo sueldo;
    public vUpdatecontratosjugadores() throws MalformedURLException, SQLException{
        ArrayList<String> id= Main.getIDContratosJugadores();
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


        cbID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    contratoJugador=Main.contratosJugadorPorID(cbID.getSelectedItem().toString());
                    tfJugador.setText(contratoJugador.getJugador().getDni());
                    tfClausula.setText(String.valueOf(contratoJugador.getClausula()));
                    cbEquipos.setSelectedItem(contratoJugador.getEquipo().getNombre());
                    tfDorsal.setText(contratoJugador.getDorsal());
                    cbSueldo.setSelectedItem(contratoJugador.getTipoSueldo().getValor());
                    tfFechaFin.setText(contratoJugador.getFechaFin().toString());

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
        bAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    boolean update;
                    fechaCorrecta();
                    dorsalCorrecto();
                    clasulaCorrecto();
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
                    if (fechaCorrecta && dorsalCorrecto && clasulaCorrecta) {
                        update = Main.updateContratosJugadores(cbEquipos.getSelectedItem().toString(), tfFechaFin.getText(), tfClausula.getText(), tfDorsal.getText(), sueldo);
                        if (update) {
                            JOptionPane.showMessageDialog(null, "¡Contrato actualizado con exito!");
                            tfDorsal.setText("");
                            tfClausula.setText("");
                            tfFechaFin.setText("");
                            cbSueldo.setSelectedIndex(0);
                            tfJugador.setText("");
                            cbEquipos.setSelectedIndex(0);
                            tfClausula.setBackground(new Color(255, 233, 176));
                            tfFechaFin.setBackground(new Color(255, 233, 176));
                        } else throw new Exception("Fallos al actualizar el contrato");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
    }

    public JPanel getpPrincipal() {
        return pPrincipal;
    }

    public static void main(String[] args) throws MalformedURLException, SQLException {
        JFrame frame = new JFrame("vUpdatecontratosjugadores");
        frame.setContentPane(new vUpdatecontratosjugadores().pPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
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
    public void dorsalCorrecto() throws Exception {
        int dorsal= Integer.parseInt(tfDorsal.getText());
        if (dorsal<0 || dorsal>99){
            dorsalCorrecto=false;
            tfDorsal.setBackground(Color.red);
            throw new Exception("El dorsal tiene que estar entre 0 y 99");
        }else dorsalCorrecto=true;
    }
    public void clasulaCorrecto() throws Exception {
        int clausula= Integer.parseInt(tfClausula.getText());
        if (clausula<1000000){
            clasulaCorrecta=false;
            tfClausula.setBackground(Color.red);
            throw new Exception("La clausula ha de ser mayor que 1.000.000");
        }else clasulaCorrecta=true;
    }
}
