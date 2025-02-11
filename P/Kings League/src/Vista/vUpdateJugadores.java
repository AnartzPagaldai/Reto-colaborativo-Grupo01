package Vista;

import Controlador.Main;
import Modelo.Jugador.Jugador;

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
 * Generar la clase vUpdateJugadores.
 * Esta clase tiene el contenido y los métodos necesarios para ejecutar la ventana destinada a actualizar un jugador.
 */
public class vUpdateJugadores {
    private JPanel pPrincipal;
    private ImageIcon LogoKingsLeague;
    private ImageIcon imagenInstagram;
    private ImageIcon imagenTwitter;
    private ImageIcon imagenTwitch;
    private JPanel pDegradado;
    private JPanel pDatos;
    private JPanel pCrearJugador;
    private JTextField tfNombre;
    private JLabel jlContrasena;
    private JLabel jlNombre;
    private JButton bCrear;
    private JLabel JLThegrefg;
    private JLabel JLIbai;
    private JLabel fTwitch;
    private JLabel fInstagram;
    private JLabel fTwitter;
    private JPanel pHeader;
    private JLabel fLogoKingsLeague;
    private JPanel pEstadisticas;
    private JButton bAtras;
    private JTextField tfApellido;

    private JFormattedTextField ftTelefono;
    private JTextField tfIMG;
    private JFormattedTextField tfDNI;
    private JComboBox cbPosicion;
    private JPanel pVelocidad;
    private JPanel pFisico;
    private JPanel pTalento;
    private JPanel pPase;
    private JPanel pDefensa;
    private JPanel pTiro;
    private JComboBox cbTipo;
    private JComboBox cbDNIS;
    private JPanel pDatosPersonales;
    private JSpinner sVelocidad;
    private JFormattedTextField tfVelocidad;
    private SpinnerNumberModel modelVelocidad = new SpinnerNumberModel(0,0,99,1);
    private SpinnerNumberModel modelPase= new SpinnerNumberModel(0,0,99,1);
    private SpinnerNumberModel modelTalento = new SpinnerNumberModel(0,0,99,1);
    private SpinnerNumberModel modelFisico = new SpinnerNumberModel(0,0,99,1);
    private SpinnerNumberModel modelDefensa = new SpinnerNumberModel(0,0,99,1);
    private SpinnerNumberModel modelTiro= new SpinnerNumberModel(0,0,99,1);
    private static final String patronEnlace = "^(https?://)?([\\w.-]+)\\.([a-zA-Z]{2,})(/[\\w.-]*)*/?\\.(png)$";
    private boolean enlaceCorrecto;
    private String posicion;
    private Jugador.TipoJugador tipoJugador;
    private Jugador.TipoPosicion TipoPosicion;
    private Jugador jugador;
    public vUpdateJugadores() throws MalformedURLException {

        JSpinner sVelocidad = new JSpinner(modelVelocidad);
        pVelocidad.add(sVelocidad);
        JSpinner sTiro = new JSpinner(modelTiro);
        pTiro.add(sTiro);
        JSpinner sTalento = new JSpinner(modelTalento);
        pTalento.add(sTalento);
        JSpinner sPase = new JSpinner(modelPase);
        pPase.add(sPase);
        JSpinner sFisico = new JSpinner(modelFisico);
        pFisico.add(sFisico);
        JSpinner sDefensa = new JSpinner(modelDefensa);
        pDefensa.add(sDefensa);

        ArrayList<String> posiciones= new ArrayList<>();
        posiciones.add("SELECCIONE UNA OPCION");
        posiciones.add("PORTERO");
        posiciones.add("DEFENSA");
        posiciones.add("MEDIO");
        posiciones.add("DELANTERO");

        for (int x=0;x<posiciones.size(); x++){
            cbPosicion.addItem(posiciones.get(x));
        }

        ArrayList<String> tipo= new ArrayList<>();
        tipo.add("SELECCIONE UNA OPCION");
        tipo.add("DRAFT");
        tipo.add("WILD-CARD");

        for (int x=0;x<tipo.size(); x++){
            cbTipo.addItem(tipo.get(x));
        }

        ArrayList<String> dni=Main.selectDNI();
        for (int x=0; x<dni.size();x++){
            cbDNIS.addItem(dni.get(x));
        }

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


        bCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean updateJugador;
                try {
                    if (cbPosicion.getSelectedIndex()==0 || cbTipo.getSelectedIndex()==0){
                        throw new Exception("Por favor, seleccione una posicion o tipo valido");
                    }enlaceCorrecto();
                    if (!enlaceCorrecto){
                        throw new Exception("El enlace no es valido");
                    }
                    if (tfNombre.getText().isEmpty()||tfApellido.getText().isEmpty()||tfDNI.getText().isEmpty()||tfApellido.getText().isEmpty()||ftTelefono.getText().isEmpty()){
                        throw new Exception("No pueden haber campos vacios");
                    }
                    if (cbPosicion.getSelectedIndex()==1){
                        posicion= TipoPosicion.PORTERO.toString();
                    }
                    if (cbPosicion.getSelectedIndex()==2){
                        posicion= TipoPosicion.DEFENSA.toString();
                    }
                    if (cbPosicion.getSelectedIndex()==3){
                        posicion= TipoPosicion.MEDIO.toString();
                    }
                    if (cbPosicion.getSelectedIndex()==4){
                        posicion= TipoPosicion.DELANTERO.toString();
                    }
                    if (cbTipo.getSelectedIndex()==1){
                        tipoJugador= Jugador.TipoJugador.DRAFT;
                    }else tipoJugador= Jugador.TipoJugador.WILDCARD;
                    updateJugador= Main.updateJugador(jugador.getId(),tfNombre.getText(), tfApellido.getText(), tfDNI.getText(),
                            ftTelefono.getText(), Jugador.TipoPosicion.valueOf(posicion), tipoJugador,tfIMG.getText(), Integer.parseInt(sVelocidad.getValue().toString()),
                            Integer.parseInt(sFisico.getValue().toString()), Integer.parseInt(sDefensa.getValue().toString()),
                            Integer.parseInt(sPase.getValue().toString()),Integer.parseInt(sTiro.getValue().toString()),Integer.parseInt(sTalento.getValue().toString()));
                    if (updateJugador){
                        JOptionPane.showMessageDialog(null, "¡Jugador actualizado con exito!");
                        tfNombre.setText("");
                        tfIMG.setText("");
                        tfIMG.setBackground(new Color(255, 233, 176));
                        tfApellido.setText("");
                        tfDNI.setText("");
                        sDefensa.setValue(0);
                        sFisico.setValue(0);
                        sPase.setValue(0);
                        sTalento.setValue(0);
                        sTiro.setValue(0);
                        sVelocidad.setValue(0);
                        cbTipo.setSelectedIndex(0);
                        cbDNIS.setSelectedIndex(0);
                        cbPosicion.setSelectedIndex(0);
                        ftTelefono.setText("");
                    }else throw new Exception("No se ha podido actualizar");
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
        cbDNIS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cbDNIS.getSelectedIndex()==0){
                    pDatosPersonales.setVisible(false);
                    pEstadisticas.setVisible(false);
                    bCrear.setVisible(false);
                }else {
                    pDatosPersonales.setVisible(true);
                    pEstadisticas.setVisible(true);
                    bCrear.setVisible(true);
                    jugador = Main.jugadorPorDNI(cbDNIS.getSelectedItem().toString());
                    tfNombre.setText(jugador.getNombre());
                    tfApellido.setText(jugador.getApellidos());
                    tfDNI.setText(jugador.getDni());
                    tfIMG.setText(jugador.getImg());
                    ftTelefono.setText(jugador.getTelefono());
                    cbTipo.setSelectedItem(jugador.getTipoJugador().getValor());
                    cbPosicion.setSelectedItem(jugador.getTipoPosicion().toString());
                    sDefensa.setValue(jugador.getDefensa());
                    sFisico.setValue(jugador.getFisico());
                    sTiro.setValue(jugador.getTiro());
                    sPase.setValue(jugador.getPase());
                    sTalento.setValue(jugador.getTalento());
                    sVelocidad.setValue(jugador.getVelocidad());
                }
            }
        });
    }

    public static void main(String[] args) throws MalformedURLException {
        JFrame frame = new JFrame("vUpdateJugadores");
        frame.setContentPane(new vUpdateJugadores().getpPrincipal());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }

    public JPanel getpPrincipal() {
        return pPrincipal;
    }

    private void createUIComponents() throws ParseException {
        ftTelefono = new JFormattedTextField(new MaskFormatter("#########"));
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
