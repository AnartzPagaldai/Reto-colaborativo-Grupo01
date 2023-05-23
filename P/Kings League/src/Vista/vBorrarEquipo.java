package Vista;

import Controlador.Main;
import Modelo.Equipo.Equipo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class vBorrarEquipo extends JDialog {
    private JPanel pPrincipal;
    private JButton bAceptar;
    private JButton bCancelar;
    private JPanel pDegradado;
    private JPanel pDatos;
    private JPanel pIniciarSesion;
    private JLabel jlNombre;
    private JPanel pFooter;
    private JPanel pBotones;
    private JComboBox cbNombres;
    private Equipo equipo;

    public vBorrarEquipo() {
        ArrayList<String> nombres=Main.selectNombresEquipos();
        for (int x=0; x<nombres.size();x++){
            cbNombres.addItem(nombres.get(x));
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
        cbNombres.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cbNombres.getSelectedIndex()==0){
                    JOptionPane.showMessageDialog(null, "Seleccione un nombre correcto");
                }else {
                    equipo = Main.equipoPorNombre(cbNombres.getSelectedItem().toString());
                }
            }
        });
    }


    private void onOK() {
        boolean delete;
        delete=Main.deleteEquipo(equipo.getNombre());
        if (delete){
            JOptionPane.showMessageDialog(null, "¡Equipo borrado con exito!");
        }else JOptionPane.showMessageDialog(null, "Problemas al eliminar");
    }

    private void onCancel() {
        this.dispose();
    }

    public JPanel getpPrincipal() {
        return pPrincipal;
    }

    public static void main(String[] args) {
        vBorrarEquipo dialog = new vBorrarEquipo();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
