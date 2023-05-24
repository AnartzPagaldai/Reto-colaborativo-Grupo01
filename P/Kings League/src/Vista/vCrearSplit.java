package Vista;

import Controlador.Main;
import Modelo.Jornada.Jornada;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Generar la clase vCrearSplit.
 * Esta clase tiene el contenido y los métodos necesarios para ejecutar la ventana destinada a insertar un split.
 */
public class vCrearSplit {
    private JPanel pPrincipal;
    private JPanel pDegradado;
    private JLabel jlTipoSplit;
    private JRadioButton rbInvierno;
    private JRadioButton rbVerano;
    private JButton buttonOK;


    public static void main(String[] args) {
        JFrame frame = new JFrame("vCrearSplit");
        frame.setContentPane(new vCrearSplit().pPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public JPanel getpPrincipal() {
        return pPrincipal;
    }

    public vCrearSplit() {

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


        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tipo;
                if (!rbInvierno.isSelected() && !rbVerano.isSelected()) {
                    JOptionPane.showMessageDialog(null, "Tienes que elegir un tipo de split.");
                } else {
                    if (rbVerano.isSelected())
                        tipo = rbVerano.getText();
                    else
                        tipo = rbInvierno.getText();
                    try {
                        if (!Main.crearSplit(tipo.toUpperCase())) throw new Exception("no se a creado el split");
                        JOptionPane.showMessageDialog(null, "se a creado el split");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                }
                Main.PrincipalAdmin();
            }

        });


    }
}
