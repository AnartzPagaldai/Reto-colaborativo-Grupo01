package Vista;

import Controlador.Main;

import javax.swing.*;
import java.awt.event.*;

public class vTipoSplit extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JRadioButton rbInvierno;
    private JRadioButton rbVerano;
    private JLabel jlTipoSplit;
    private JButton buttonCancel;

    public vTipoSplit() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
    }

    private void onOK() {
        if (rbInvierno.isSelected()) {
            Main.crearSplit("invierno");
        } else if (rbVerano.isSelected()) {
            Main.crearSplit("verano");
        }
        dispose();
    }



    public static void main(String[] args) {
        vTipoSplit dialog = new vTipoSplit();
        dialog.pack();
        dialog.setVisible(true);
    }
}
