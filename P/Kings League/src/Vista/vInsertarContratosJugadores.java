package Vista;

import Controlador.Main;
import Modelo.Enumeraciones.TipoSueldo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class vInsertarContratosJugadores {
    private JPanel pDegradado;
    private JPanel pDatos;
    private JPanel pIniciarSesion;
    private JLabel jlPresupuesto;
    private JLabel jlNombre;

    private JLabel jlClausula;
    private JTextField tfClausula;
    private JButton bAceptar;
    private JPanel pHeader;
    private JLabel fLogoKingsLeague;
    private JMenu mUsuario;
    private JPanel pPrincipal;
    private JComboBox cbNombres;
    private JComboBox cbJugadores;
    private JTextField tfDorsal;
    private JTextField tfSueldo;
    private JComboBox cbSueldo;
    private JTextField tfFechaFin;
    private JButton bAtras;
    private JRadioButton rb10m;
    private JRadioButton rb105m;
    private JRadioButton rb15m;
    private JRadioButton rb225m;
    private static final String patronFecha="\\d{4}-\\d{2}-\\d{2}";
    private boolean fechaCorrecta;
    private boolean dorsalCorrecto;
    private boolean clasulaCorrecta;

    private TipoSueldo sueldo;


    public JPanel getpPrincipal() {
        return pPrincipal;
    }

    public vInsertarContratosJugadores() throws MalformedURLException, SQLException {

        ArrayList<String> nombres=Main.selectNombresEquipos();
        for (int x=0; x<nombres.size();x++){
            cbNombres.addItem(nombres.get(x));
        }

        ArrayList<String> jugadoresSinContrato = Main.getDNISinContrato();
        for (int x = 0; x< jugadoresSinContrato.size(); x++)
        {
            cbJugadores.addItem(jugadoresSinContrato.get(x));
        }

        for (TipoSueldo valor: TipoSueldo.values()){
            cbSueldo.addItem(valor.getValor());
        }

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
        ImageIcon LogoKingsLeague = new ImageIcon(new URL("https://seeklogo.com/images/K/kings-league-logo-CEDD6AED72-seeklogo.com.png"));
        Image LogoNuevo = LogoKingsLeague.getImage().getScaledInstance(300, 122, Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(LogoNuevo);
        fLogoKingsLeague.setIcon(newIcon);


        bAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    boolean insertar;
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
                        insertar = Main.insertarContratoJugadores(cbNombres.getSelectedItem().toString(), cbJugadores.getSelectedItem().toString(), tfFechaFin.getText(), tfClausula.getText(), tfDorsal.getText(), String.valueOf(sueldo.getValor()));
                        if (insertar) {
                            JOptionPane.showMessageDialog(null, "¡Contrato hecho con exito!");
                            tfDorsal.setText("");
                            tfClausula.setText("");
                            tfFechaFin.setText("");
                            cbSueldo.setSelectedIndex(0);
                            cbJugadores.setSelectedIndex(0);
                            cbNombres.setSelectedIndex(0);
                            tfClausula.setBackground(new Color(255, 233, 176));
                            tfFechaFin.setBackground(new Color(255, 233, 176));
                        } else throw new Exception("Fallos al insertar el contrato");
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

    public static void main(String[] args) throws MalformedURLException, SQLException {
        JFrame frame = new JFrame("vInsertarContratosJugadores");
        frame.setContentPane(new vInsertarContratosJugadores().pPrincipal);
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
