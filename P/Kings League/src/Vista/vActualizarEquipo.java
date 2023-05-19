package Vista;

import Controlador.Main;
import Modelo.Equipo.Equipo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class vActualizarEquipo {
    private JPanel pPrincipal;
    private JPanel pDegradado;
    private JButton bAceptar;
    private JPanel pDatos;
    private JPanel pIniciarSesion;
    private JTextField tfNombre;
    private JLabel jlPresupuesto;
    private JLabel jlNombre;
    private JTextField tfPresupuesto;
    private JLabel jlImagen;
    private JTextField tfImagen;
    private JPanel pHeader;
    private JLabel fLogoKingsLeague;
    private JButton bSalir;
    private JComboBox cbNombres;
    private JPanel pDatosEquipo;
    private JTextField tfColor;
    private JMenuBar jmheader;
    private JMenu mEquipos;
    private JMenuItem jmiConsultarEquipos;
    private JMenu mJugadores;
    private JMenuItem jmiSeleccionar;
    private JMenu mPartidos;
    private JMenuItem jmiVer;
    private JMenu mClasificacion;
    private JMenu mUsuario;
    private JMenuItem jmiVerPerfil;
    private JMenuItem jmiCerrarSesion;
    private ImageIcon LogoKingsLeague;
    private boolean correcto;
    private Equipo equipo;
    private static final String patronLogo = "^(https?://)?([\\w.-]+)\\.([a-zA-Z]{2,})(/[\\w.-]*)*/?\\.(png)$";
    private static final String patronColor = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$";
    private static final String numeros= "^[0-9]+$";
    private boolean numerosCorrectos;
    private boolean enlaceCorrecto;
    private boolean colorCorrecto;


    public vActualizarEquipo() throws MalformedURLException{

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

        ArrayList<String> nombres=Main.selectNombresEquipos();
        for (int x=0; x<nombres.size();x++){
            cbNombres.addItem(nombres.get(x));
        }

        // Poner la imagen del logo oficial de la Kings League
        LogoKingsLeague = new ImageIcon(new URL("https://seeklogo.com/images/K/kings-league-logo-CEDD6AED72-seeklogo.com.png"));
        Image LogoNuevo = LogoKingsLeague.getImage().getScaledInstance(300, 122, Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(LogoNuevo);
        fLogoKingsLeague.setIcon(newIcon);


        bAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean update;
                try {
                    enlaceCorrecto();
                    colorCorrecto();
                    numerosCorrectos();
                    if (enlaceCorrecto && colorCorrecto && numerosCorrectos){
                        update= Main.updateEquipos(tfNombre.getText(), Double.parseDouble(tfPresupuesto.getText()), tfImagen.getText(), tfColor.getText());
                        if (update){
                            JOptionPane.showMessageDialog(null, "¡Equipo modificado con exito!");
                        }else throw new Exception("Error al actualizar");
                    }else throw new Exception("Introduzca bien los datos, por favor");
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }


            }
        });
        bSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.PrincipalAdmin();
            }
        });

        cbNombres.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cbNombres.getSelectedIndex()==0){
                    pDatosEquipo.setVisible(false);
                    bAceptar.setVisible(false);
                }else {
                    pDatosEquipo.setVisible(true);
                    bAceptar.setVisible(true);
                    equipo = Main.equipoPorNombre(cbNombres.getSelectedItem().toString());
                    tfNombre.setText(equipo.getNombre());
                    tfImagen.setText(equipo.getLogoImg());
                    tfPresupuesto.setText(String.valueOf(equipo.getPresupuestoAnual()));
                    tfColor.setText(equipo.getColor());
                }
            }
        });
    }


    public JPanel getpPrincipal() {
        return pPrincipal;
    }

    public static void main(String[] args) throws MalformedURLException {
        JFrame frame = new JFrame("vActualizarEquipo");
        frame.setContentPane(new vActualizarEquipo().pPrincipal);
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
        Matcher matcher = pattern.matcher(tfPresupuesto.getText());
        if (!matcher.matches()){
            numerosCorrectos=false;
            tfPresupuesto.setBackground(Color.red);
        }else {
            tfPresupuesto.setBackground(Color.green);
            numerosCorrectos=true;
        }
    }
}
