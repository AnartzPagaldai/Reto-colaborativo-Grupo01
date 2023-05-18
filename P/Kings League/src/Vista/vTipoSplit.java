package Vista;

import javax.swing.*;
import java.awt.event.*;

public class vTipoSplit extends JDialog {
    private JPanel pPrincipal;
    private JButton buttonOK;
    private JRadioButton rbInvierno;
    private JRadioButton rbVerano;
    private JLabel jlTipoSplit;
    private JButton buttonCancel;

    public vTipoSplit() {
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
        // add your code here

    }

    private void onCancel() {
        dispose();
    }


    public JPanel getpPrincipal() {
        return pPrincipal;
    }

    public static void main(String[] args) {
        vTipoSplit dialog = new vTipoSplit();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
