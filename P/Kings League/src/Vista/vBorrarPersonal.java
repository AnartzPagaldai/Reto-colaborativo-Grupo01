package Vista;

import Controlador.Main;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * Generar la clase vBorrarPersonal.
 * Esta clase tiene el contenido y los métodos necesarios para ejecutar la ventana destinada a eliminar un miembro del personal.
 */
public class vBorrarPersonal extends JDialog {
    private JPanel pPrincipal;
    private JPanel pDegradado;
    private JPanel pDatos;
    private JPanel pIniciarSesion;
    private JLabel jlDni;
    private JPanel pFooter;
    private JPanel pBotones;
    private JButton bAceptar;
    private JButton bCancelar;
    private JComboBox cbDNIS;
    private JFormattedTextField ftfDni;
    private JButton buttonOK;
    private JButton buttonCancel;
    private static boolean existe;

    public vBorrarPersonal() {
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

        generarCombo();

        setContentPane(pPrincipal);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        bAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean borrar;
                try {
                    borrar=Main.borrarPersonal(cbDNIS.getSelectedItem().toString());
                    if (borrar){
                        JOptionPane.showMessageDialog(null,"¡Personal borrado con exito!");
                        cbDNIS.removeAllItems();
                        generarCombo();
                    }
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });

        bCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        pPrincipal.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() throws Exception {
        // TODO: comprobar que existe el dni y si es así; borrarlo
        boolean borrar;
        if (jlDni.getText().isEmpty()) {
            throw new Exception("No puede estar el campo vacio");
        }
        borrar = Main.borrarJugador(jlDni.getText());
        if (borrar) {
            JOptionPane.showMessageDialog(null, "¡Jugador borrado con exito!");
            jlDni.setText("");

        }
    }

    private void onCancel() {
        Main.PrincipalAdmin();
    }

    private void createUIComponents() throws Exception {
        try {
            ftfDni = new JFormattedTextField(new MaskFormatter("########U"));
        } catch (ParseException e) {
            throw new Exception("El DNI no cumple con el formato establecido.");
        }
    }

    public JPanel getpPrincipal() {
        return pPrincipal;
    }

    public static void main(String[] args) {
        vBorrarPersonal dialog = new vBorrarPersonal();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
    public void generarCombo(){
        ArrayList<String> dni= Main.selectDNIPersonal();
        for (int x=0; x<dni.size();x++){
            cbDNIS.addItem(dni.get(x));
        }
    }
}
