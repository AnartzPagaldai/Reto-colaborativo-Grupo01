package Vista;

import Controlador.Main;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * Generar la clase vBorrarJugador.
 * Esta clase tiene el contenido y los métodos necesarios para ejecutar la ventana destinada a eliminar un jugador.
 */
public class vBorrarJugador extends JDialog {
    private JPanel pPrincipal;
    private JPanel pDegradado;
    private JPanel pDatos;
    private JPanel pIniciarSesion;
    private JLabel jlDni;
    private JButton bAceptar;
    private JButton bCancelar;
    private JPanel pFooter;
    private JPanel pBotones;
    private JComboBox cbDNIS;
    private JFormattedTextField tfDni;


    public vBorrarJugador() {
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
        getRootPane().setDefaultButton(bAceptar);

        bAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    onOK();
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

    private void onOK() {
        // add your code here
        try {
            boolean borrar;
            borrar = Main.borrarJugador(cbDNIS.getSelectedItem().toString());
            if (borrar) {
                JOptionPane.showMessageDialog(null, "¡Jugador borrado con exito!");
                cbDNIS.removeAllItems();
                generarCombo();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

    }

    private void onCancel() {
        this.dispose();
    }

    public JPanel getpPrincipal() {
        return pPrincipal;
    }

    public static void main(String[] args) {
        vBorrarJugador dialog = new vBorrarJugador();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    /**
     * Método para rellenar el JComboBox con los DNIs de los jugadores existentes.
     * Se llamará a un método del Main en el que se obtienen los DNIs y tras obtener ese ArrayList se rellenará el JComboBox.
     */
    private void generarCombo(){
        ArrayList<String> dni=Main.selectDNI();
        for (int x=0; x<dni.size();x++){
            cbDNIS.addItem(dni.get(x));
        }
    }
}
