package Vista;

import Controlador.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class vBorrarJugador extends JDialog {
    private JPanel pPrincipal;
    private JPanel pDegradado;
    private JPanel pDatos;
    private JPanel pIniciarSesion;
    private JTextField tfDni;
    private JLabel jlDni;
    private JButton bAceptar;
    private JButton bCancelar;
    private JPanel pFooter;
    private JPanel pBotones;


    public vBorrarJugador() {
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

/*
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
*/

    }

    private void onOK() {
        // todo: validar que existe el jugador y borrarlo + si va bien sacar mensaje
    }

    private void onCancel() {
        Main.PrincipalAdmin();
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
}
