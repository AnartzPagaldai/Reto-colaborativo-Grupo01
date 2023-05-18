package Vista;

import Controlador.Main;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.event.*;
import java.text.ParseException;

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
    private JFormattedTextField ftfDni;
    private JButton buttonOK;
    private JButton buttonCancel;

    public vBorrarPersonal() {

        setContentPane(pPrincipal);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
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
        // TODO: comprobar que existe el dni y si es así; borrarlo
    }

    private void onCancel() {
        Main.PrincipalAdmin();
    }

    private void createUIComponents() throws Exception {
        try {
            ftfDni = new JFormattedTextField(new MaskFormatter("########U"));
        }
        catch (ParseException e){
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

}
