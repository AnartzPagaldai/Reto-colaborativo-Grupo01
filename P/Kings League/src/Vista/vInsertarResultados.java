package Vista;

import Controlador.Main;
import Modelo.Partido.TPartido;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;


public class vInsertarResultados {
    private JPanel pPrincipal;
    private JPanel pDegradado;

    private static ImageIcon escudoEquipo1;

    private ImageIcon escudoEquipo2;
    private JPanel pHeader;
    private JMenuBar jmheader;
    private JMenu mEquipos;
    private JMenuItem jmiConsultarEquipos;
    private JMenu mPartidos;
    private JMenuItem jmiVer;
    private JMenu mClasificacion;
    private JMenu mUsuario;
    private JMenuItem jmiVerPerfil;
    private JMenuItem jmiCerrarSesion;
    private JLabel fLogoKingsLeague;
    private JPanel pContenido;
    private JPanel pTocho;
    private JPanel pTocho2;
    private JLabel jlEquipo1;
    private JLabel jlEquipo2;
    private JLabel jlEquipo1Par2;
    private JLabel jlEquipo1Par3;
    private JLabel jlEquipo1Par4;
    private JLabel jlEquipo1Par5;
    private JLabel jlEquipo1Par6;
    private JLabel jlEquipo2Par2;
    private JLabel jlEquipo2Par3;
    private JLabel jlEquipo2Par4;
    private JLabel jlEquipo2Par5;
    private JLabel jlEquipo2Par6;
    private JComboBox cbJornada;
    private JTextField tfGoles1Par1;
    private JTextField tfGoles2Par1;
    private JTextField tfGoles1Par2;
    private JTextField tfGoles2Par2;
    private JTextField tfGoles1Par3;
    private JTextField tfGoles2Par3;
    private JTextField tfGoles1Par4;
    private JTextField tfGoles2Par4;
    private JTextField tfGoles1Par6;
    private JTextField tfGoles2Par6;
    private JTextField tfGoles1Par5;
    private JTextField tfGoles2Par5;
    private JButton bConfirmar;
    private JLabel barra1;
    private JLabel barra2;
    private JLabel barra3;
    private JLabel barra4;
    private JLabel barra5;
    private JLabel barra6;
    private JPanel panelGanador;
    private JLabel jEquipoGanador;
    private JButton bRefrescar;
    private JMenu jmInicio;
    private JMenuItem jmiPrincipal;

    ArrayList<JLabel> nombresEquipos1 = new ArrayList<>();

    ArrayList<JLabel> nombresEquipos2 = new ArrayList<>();
    ArrayList<JTextField> golesEq1 = new ArrayList<>();
    ArrayList<JTextField> golesEq2 = new ArrayList<>();
    ArrayList<JLabel> barras = new ArrayList<>();

    static HashMap<String, String>[] partidos;

    public vInsertarResultados() throws Exception {


        nombresEquipos1.add(jlEquipo1);
        nombresEquipos1.add(jlEquipo1Par2);
        nombresEquipos1.add(jlEquipo1Par3);
        nombresEquipos1.add(jlEquipo1Par4);
        nombresEquipos1.add(jlEquipo1Par5);
        nombresEquipos1.add(jlEquipo1Par6);


        nombresEquipos2.add(jlEquipo2);
        nombresEquipos2.add(jlEquipo2Par2);
        nombresEquipos2.add(jlEquipo2Par3);
        nombresEquipos2.add(jlEquipo2Par4);
        nombresEquipos2.add(jlEquipo2Par5);
        nombresEquipos2.add(jlEquipo2Par6);


        golesEq1.add(tfGoles1Par1);
        golesEq1.add(tfGoles1Par2);
        golesEq1.add(tfGoles1Par3);
        golesEq1.add(tfGoles1Par4);
        golesEq1.add(tfGoles1Par5);
        golesEq1.add(tfGoles1Par6);


        golesEq2.add(tfGoles2Par1);
        golesEq2.add(tfGoles2Par2);
        golesEq2.add(tfGoles2Par3);
        golesEq2.add(tfGoles2Par4);
        golesEq2.add(tfGoles2Par5);
        golesEq2.add(tfGoles2Par6);


        barras.add(barra1);
        barras.add(barra2);
        barras.add(barra3);
        barras.add(barra4);
        barras.add(barra5);
        barras.add(barra6);

        ArrayList<Integer> numJornadas = Main.getJornadas();

        if (numJornadas.size() == 0) {
            pContenido.setVisible(false);
        }
        for (int x = 0; x < numJornadas.size(); x++) {
            cbJornada.addItem(numJornadas.get(x));
        }

        cbJornada.setSelectedIndex(cbJornada.getItemCount() - 1);
        partidos = Main.getJornada(cbJornada.getItemCount());
        rellenarPartido();

        pPrincipal = new JPanel(new BorderLayout());

        pPrincipal = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g;

                Color colorInicio = new Color(233, 86, 31);
                Color colorFin = new Color(247, 169, 33);

                GradientPaint gradient = new GradientPaint(0, 0, colorInicio, 0, getHeight(), colorFin);

                g2d.setPaint(gradient);

                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        // Agrega pHeader al norte
        pPrincipal.add(pDegradado, BorderLayout.CENTER);

        // Poner la imagen del logo oficial de la Kings League
        ImageIcon LogoKingsLeague = new ImageIcon(new URL("https://seeklogo.com/images/K/kings-league-logo-CEDD6AED72-seeklogo.com.png"));
        Image LogoNuevo = LogoKingsLeague.getImage().getScaledInstance(300, 122, Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(LogoNuevo);
        fLogoKingsLeague.setIcon(newIcon);

        // Poner la imagen del usuario
        ImageIcon imagenUsuario = new ImageIcon(new URL("https://assets.stickpng.com/images/585e4beacb11b227491c3399.png"));
        Image imgUsuario = imagenUsuario.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon UsuIcono = new ImageIcon(imgUsuario);
        mUsuario.setIcon(UsuIcono);

        Image imgRefrescar = new ImageIcon(new URL("https://cdn-icons-png.flaticon.com/512/126/126561.png?w=826&t=st=1684772091~exp=1684772691~hmac=7ff49a98fc4fafc7ccaa276213e1b005678078194f2ea106495526feaeec7ef8")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        bRefrescar.setIcon(new ImageIcon(imgRefrescar));
        cbJornada.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                partidos = Main.getJornada((Integer) cbJornada.getSelectedItem());
                rellenarPartido();
            }
        });


        bConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    for (int x = 0; x < partidos.length; x++) {
                        if (golesEq1.get(x).getText().equals(golesEq2.get(x).getText())) {
                            throw new Exception("no pueden empartar");
                        }
                        if (!Main.ActualizarPartido(nombresEquipos1.get(x).getText(), nombresEquipos2.get(x).getText(), golesEq1.get(x).getText(), golesEq2.get(x).getText())) {
                            throw new Exception("no se a actualizado toda la jornada");
                        }
                    }
                    JOptionPane.showMessageDialog(null, "se a actualizado la jornada");
                } catch (Exception ex) {
                   JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                Main.generarXml();
            }
        });
        bRefrescar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Main.getJornadas();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                partidos = Main.getJornada((Integer) cbJornada.getSelectedItem());
                rellenarPartido();
            }
        });
        jmiPrincipal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.Principal();
            }
        });
    }

    private void rellenarPartido() {

        for (int x = 0, i = barras.size() - 1; x < partidos.length || i > partidos.length - 1; x++, i--) {
            if (x < partidos.length) {
                nombresEquipos1.get(x).setText(partidos[x].get("nombre_equiop1"));
                try {
                    escudoEquipo1 = new ImageIcon(new URL(partidos[x].get("logoEquipo1")));
                } catch (MalformedURLException ex) {
                    ex.printStackTrace();
                }
                Image LogoNuevo = escudoEquipo1.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                ImageIcon newIcon = new ImageIcon(LogoNuevo);
                nombresEquipos1.get(x).setIcon(newIcon);

                nombresEquipos2.get(x).setText(partidos[x].get("nombre_equiop2"));

                try {
                    escudoEquipo2 = new ImageIcon(new URL(partidos[x].get("logoEquipo2")));
                } catch (MalformedURLException ex) {
                    throw new RuntimeException(ex);
                }
                Image LogoEquipo = escudoEquipo2.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                ImageIcon iconoEquipo = new ImageIcon(LogoEquipo);
                nombresEquipos2.get(x).setIcon(iconoEquipo);

                golesEq1.get(x).setText(partidos[x].get("golesEquipo1"));
                golesEq2.get(x).setText(partidos[x].get("golesEquipo2"));

                golesEq1.get(x).setVisible(true);
                golesEq2.get(x).setVisible(true);
                nombresEquipos1.get(x).setVisible(true);
                nombresEquipos2.get(x).setVisible(true);

                barras.get(x).setText("-");
            }

            if (i > partidos.length - 1) {
                barras.get(i).setText("");
                golesEq1.get(i).setVisible(false);
                golesEq1.get(i).setText("");
                golesEq2.get(i).setVisible(false);
                golesEq2.get(i).setText("");
                nombresEquipos1.get(i).setVisible(false);
                nombresEquipos2.get(i).setVisible(false);
            }
            if ((Integer.parseInt(cbJornada.getSelectedItem().toString()) == 13 && golesEq1.get(2).getText().equals("0") && golesEq2.get(2).getText().equals("0"))) {
                golesEq1.get(2).setText("sin");
                golesEq2.get(2).setText("jugar");
            }
            panelGanador.setVisible(false);
            if (x == 2 && Integer.parseInt(cbJornada.getSelectedItem().toString()) == 13 && barras.get(2).getText().equals("-") && !golesEq1.get(2).getText().equals("sin")) {
                if (Integer.parseInt(golesEq1.get(x).getText()) > Integer.parseInt(golesEq2.get(x).getText())) {
                    jEquipoGanador.setText(nombresEquipos1.get(x).getText());
                    jEquipoGanador.setIcon(nombresEquipos1.get(x).getIcon());
                } else {
                    jEquipoGanador.setText(nombresEquipos2.get(x).getText());
                    jEquipoGanador.setIcon(nombresEquipos2.get(x).getIcon());
                }
                panelGanador.setVisible(true);
            }

        }
    }

    public JPanel getpPrincipal() {
        return pPrincipal;
    }

    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame("vInsertarResultados");
        frame.setContentPane(new vInsertarResultados().pPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
    }

}
