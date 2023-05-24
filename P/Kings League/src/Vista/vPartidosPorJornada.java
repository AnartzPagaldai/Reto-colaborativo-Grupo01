package Vista;

import Controlador.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Generar la clase vPartidosPorJornada.
 * Esta clase tiene el contenido y los métodos necesarios para ejecutar la ventana destinada a mostrar los partidos por jornada.
 */
public class vPartidosPorJornada {
    private JPanel pPrincipal;
    private JPanel pDegradado;
    private ImageIcon escudoEquipo1;

    private ImageIcon escudoEquipo2;



    private ImageIcon LogoKingsLeague;
    private JLabel fLogoKingsLeague;
    private JLabel fTwitch;
    private JLabel fInstagram;
    private JLabel fTwitter;
    private JLabel jlEquipo1;
    private JLabel jlGoles2Par1;
    private JLabel jlEquipo2;
    private JLabel jlEquipo2Par2;
    private JLabel jlGoles2Par2;
    private JLabel jlGoles1Par1;
    private JLabel jlGoles1Par2;
    private JLabel jlEquipo1Par2;
    private JLabel jlEquipo1Par3;
    private JLabel jlGoles1Par3;
    private JLabel jlGoles2Par3;
    private JLabel jlEquipo2Par3;
    private JLabel jlEquipo1Par4;
    private JLabel jlEquipo2Par4;
    private JLabel jlGoles2Par4;
    private JLabel jlGoles1Par4;
    private JLabel jlEquipo1Par5;
    private JLabel jlEquipo2Par5;
    private JLabel jlGoles1Par5;
    private JLabel jlGoles2Par5;
    private JLabel jlEquipo1Par6;
    private JLabel jlGoles1Par6;
    private JLabel jlGoles2Par6;
    private JLabel jlEquipo2Par6;
    private JPanel pCompleto;
    private JComboBox cbJornada;
    private JPanel pHeader;
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
    private JPanel pContenido;
    private JPanel pTocho;
    private JPanel pTocho2;
    private JLabel barra1;
    private JLabel barra2;
    private JLabel barra3;
    private JLabel barra4;
    private JLabel barra5;
    private JLabel barra6;
    private JPanel panelGanador;
    private JLabel jEquipoGanador;
    private JMenu jmInicio;
    private JMenuItem jmiPrincipal;

    private ArrayList<JLabel> nombresEquipos1 = new ArrayList<>();

    private ArrayList<JLabel> nombresEquipos2 = new ArrayList<>();
    private ArrayList<JLabel> golesEquipos1 = new ArrayList<>();
    private ArrayList<JLabel> golesEquipos2 = new ArrayList<>();
    private ArrayList<JLabel> barras = new ArrayList<>();
    private HashMap <String, String> [] partidos;
    public vPartidosPorJornada() throws Exception {


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


        golesEquipos1.add(jlGoles1Par1);
        golesEquipos1.add(jlGoles1Par2);
        golesEquipos1.add(jlGoles1Par3);
        golesEquipos1.add(jlGoles1Par4);
        golesEquipos1.add(jlGoles1Par5);
        golesEquipos1.add(jlGoles1Par6);


        golesEquipos2.add(jlGoles2Par1);
        golesEquipos2.add(jlGoles2Par2);
        golesEquipos2.add(jlGoles2Par3);
        golesEquipos2.add(jlGoles2Par4);
        golesEquipos2.add(jlGoles2Par5);
        golesEquipos2.add(jlGoles2Par6);

        barras.add(barra1);
        barras.add(barra2);
        barras.add(barra3);
        barras.add(barra4);
        barras.add(barra5);
        barras.add(barra6);

       ArrayList<Integer> numJornadas =  Main.getJornadas();

        if (numJornadas.size() == 0)
        {
            pContenido.setVisible(false);
        }
       for (int x = 0; x < numJornadas.size(); x++)
       {
           cbJornada.addItem(numJornadas.get(x));
       }
       partidos = Main.getJornada(cbJornada.getItemCount());
       cbJornada.setSelectedIndex(cbJornada.getItemCount()-1);

       rellenarPartido();


        pPrincipal = new JPanel(new BorderLayout());

        pPrincipal = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g;

                Color colorInicio = new Color(233, 86, 31);
                Color colorFin = new Color(247, 169, 33);

                GradientPaint gradient = new GradientPaint(
                        0, 0, colorInicio,
                        0, getHeight(), colorFin);

                g2d.setPaint(gradient);

                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        // Agrega pHeader al norte
        pPrincipal.add(pDegradado, BorderLayout.CENTER);


        // Poner la imagen del logo oficial de la Kings League
        LogoKingsLeague = new ImageIcon(new URL("https://seeklogo.com/images/K/kings-league-logo-CEDD6AED72-seeklogo.com.png"));
        Image LogoNuevo = LogoKingsLeague.getImage().getScaledInstance(300, 122, Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(LogoNuevo);
        fLogoKingsLeague.setIcon(newIcon);

        // Poner la imagen del usuario
        ImageIcon imagenUsuario = new ImageIcon(new URL("https://assets.stickpng.com/images/585e4beacb11b227491c3399.png"));
        Image imgUsuario = imagenUsuario.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon UsuIcono = new ImageIcon(imgUsuario);
        mUsuario.setIcon(UsuIcono);


        cbJornada.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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

                golesEquipos1.get(x).setText(partidos[x].get("golesEquipo1"));
                golesEquipos2.get(x).setText(partidos[x].get("golesEquipo2"));

                golesEquipos1.get(x).setVisible(true);
                golesEquipos2.get(x).setVisible(true);
                nombresEquipos1.get(x).setVisible(true);
                nombresEquipos2.get(x).setVisible(true);

                barras.get(x).setText("-");
            }

            if (i > partidos.length - 1) {
                barras.get(i).setText("");
                golesEquipos1.get(i).setVisible(false);
                golesEquipos2.get(i).setVisible(false);
                nombresEquipos1.get(i).setVisible(false);
                nombresEquipos2.get(i).setVisible(false);
            }
            if ((Integer.parseInt(cbJornada.getSelectedItem().toString()) == 13 && golesEquipos1.get(2).getText().equals("0") && golesEquipos2.get(2).getText().equals("0"))) {
                golesEquipos1.get(2).setText("sin");
                golesEquipos2.get(2).setText("jugar");
            }
            panelGanador.setVisible(false);
            if (x == 2 && Integer.parseInt(cbJornada.getSelectedItem().toString()) == 13 && barras.get(2).getText().equals("-") && !golesEquipos1.get(2).getText().equals("sin")) {
                if (Integer.parseInt(golesEquipos1.get(x).getText()) > Integer.parseInt(golesEquipos2.get(x).getText())) {
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
        JFrame frame = new JFrame("vPartidosPorJornada");
        frame.setContentPane(new vPartidosPorJornada().pPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
    }
}