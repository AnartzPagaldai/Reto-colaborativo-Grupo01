package Vista;

import Controlador.Main;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class vPartidos {
    private JPanel pPrincipal;
    private JPanel pDegradado;
    private ImageIcon escudoEquipo1;

    private ImageIcon escudoEquipo2;



    private ImageIcon LogoKingsLeague;
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
    private JLabel fLogoKingsLeague;
    private JLabel fTwitch;
    private JLabel fInstagram;
    private JLabel fTwitter;
    private JPanel pContenido;
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
    private JPanel pTocho;
    private JPanel pTocho2;
    private JLabel barra1;
    private JLabel barra2;
    private JLabel barra3;
    private JLabel barra4;
    private JLabel barra5;
    private JLabel barra6;

    public vPartidos() throws Exception {

        ArrayList<JLabel> nombresEquipos1 = new ArrayList<>();

        nombresEquipos1.add(jlEquipo1);
        nombresEquipos1.add(jlEquipo1Par2);
        nombresEquipos1.add(jlEquipo1Par3);
        nombresEquipos1.add(jlEquipo1Par4);
        nombresEquipos1.add(jlEquipo1Par5);
        nombresEquipos1.add(jlEquipo1Par6);

        ArrayList<JLabel> nombresEquipos2 = new ArrayList<>();

        nombresEquipos2.add(jlEquipo2);
        nombresEquipos2.add(jlEquipo2Par2);
        nombresEquipos2.add(jlEquipo2Par3);
        nombresEquipos2.add(jlEquipo2Par4);
        nombresEquipos2.add(jlEquipo2Par5);
        nombresEquipos2.add(jlEquipo2Par6);

        ArrayList<JLabel> golesEquipos1 = new ArrayList<>();

        golesEquipos1.add(jlGoles1Par1);
        golesEquipos1.add(jlGoles1Par2);
        golesEquipos1.add(jlGoles1Par3);
        golesEquipos1.add(jlGoles1Par4);
        golesEquipos1.add(jlGoles1Par5);
        golesEquipos1.add(jlGoles1Par6);

        ArrayList<JLabel> golesEquipos2 = new ArrayList<>();

        golesEquipos2.add(jlGoles2Par1);
        golesEquipos2.add(jlGoles2Par2);
        golesEquipos2.add(jlGoles2Par3);
        golesEquipos2.add(jlGoles2Par4);
        golesEquipos2.add(jlGoles2Par5);
        golesEquipos2.add(jlGoles2Par6);

        ArrayList<JLabel> barras = new ArrayList<>();

        barras.add(barra1);
        barras.add(barra2);
        barras.add(barra3);
        barras.add(barra4);
        barras.add(barra5);
        barras.add(barra6);


        HashMap <String, String> [] partidos = Main.getUltimaJornada();
        for (int x = 0; x < partidos.length; x++) {

            nombresEquipos1.get(x).setText(partidos[x].get("nombre_equiop1"));

            escudoEquipo1 = new ImageIcon(new URL(partidos[x].get("logoEquipo1")));
            Image LogoNuevo = escudoEquipo1.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            ImageIcon newIcon = new ImageIcon(LogoNuevo);
            nombresEquipos1.get(x).setIcon(newIcon);

            nombresEquipos2.get(x).setText(partidos[x].get("nombre_equiop2"));

            escudoEquipo2 = new ImageIcon(new URL(partidos[x].get("logoEquipo2")));
            Image LogoEquipo = escudoEquipo2.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            ImageIcon iconoEquipo = new ImageIcon(LogoEquipo);
            nombresEquipos2.get(x).setIcon(iconoEquipo);

            golesEquipos1.get(x).setText(partidos[x].get("golesEquipo1"));
            golesEquipos2.get(x).setText(partidos[x].get("golesEquipo2"));

            barras.get(x).setText("-");


        }


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


    }


    public JPanel getpPrincipal() {
        return pPrincipal;
    }

    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame("vPartidos");
        frame.setContentPane(new vPartidos().pPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
    }
}
