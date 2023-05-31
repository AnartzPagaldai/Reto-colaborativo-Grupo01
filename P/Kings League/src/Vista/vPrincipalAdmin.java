package Vista;

import Controlador.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;

/**
 * Generar la clase vPrincipalAdmin.
 * Esta clase tiene el contenido y los métodos necesarios para ejecutar la ventana principal del usuario admin.
 */
public class vPrincipalAdmin {
    private JPanel pPrincipal;
    private JPanel pDegradado;
    private JMenu mEquipos;
    private JMenuItem jmiConsultarEquipos;
    private JMenu mJugadores;
    private JMenuItem jmiConsultarJugadores;
    private JMenu mPartidos;
    private JMenuItem jmiConsultarPartidos;
    private JMenu mClasificacion;
    private JMenu mUsuario;
    private JMenuItem jmiVerPerfil;
    private JMenuItem jmiCerrarSesion;
    private JLabel fLogoKingsLeague;
    private JLabel fTwitch;
    private JLabel fInstagram;
    private JLabel fTwitter;
    private JPanel pHeader;
    private JMenuBar jmheader;
    private JMenuItem jmiVer;
    private JPanel pContenido;
    private JPanel pCrudEquipos;
    private JButton bInsertarEquipos;
    private JButton bUpdateEquipos;
    private JButton bDeleteEquipos;
    private JButton bInsertarJugadores;
    private JButton bUpdateJugadores;
    private JButton bDeleteJugadores;
    private JPanel pJugadores;
    private JPanel pPersonal;
    private JButton bInsertarPersonal;
    private JButton bUpdatePersonal;
    private JButton bDeletePersonal;
    private JButton bInsertarContratoJugadores;
    private JButton bUpdateContratoJugadores;
    private JButton bDeleteContratoJugadores;
    private JButton bInsertarContratosPersonal;
    private JButton bUpdateContratosPersonal;
    private JButton bDeleteContratosPersonal;
    private JButton bEmparejar;
    private JButton bActualizarXML;
    private JButton bPOCuartos;
    private JButton bPOSemi;
    private JButton bPOFinal;
    private JButton bGenerarPlayOff;
    private JMenuItem jmiInsertarEquipos;
    private JMenuItem jmiActualizarEquipos;
    private JMenuItem jmiBorrarEquipos;
    private JMenuItem jmiInsertarJugadores;
    private JMenuItem jmiActualizarJugadores;
    private JMenuItem jmiBorrararJugadores;
    private ImageIcon LogoKingsLeague;
    private ImageIcon imagenTwitch;
    private ImageIcon imagenInstagram;
    private ImageIcon imagenTwitter;
    private ImageIcon imagenUsuario;
    private JComboBox cbDNIS;
    private JButton bgenerarSplit;
    private JButton bInsertarResul;
    private JButton bVerPatidos;
    private JMenuItem jmiClasi;


    public vPrincipalAdmin() throws MalformedURLException {


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
                        getWidth(), getHeight(), colorFin);

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
        imagenUsuario = new ImageIcon(new URL("https://assets.stickpng.com/images/585e4beacb11b227491c3399.png"));
        Image imgUsuario = imagenUsuario.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon UsuIcono = new ImageIcon(imgUsuario);
        mUsuario.setIcon(UsuIcono);

        ListenersRepetidos.mouseListenerBarraDeNavegacion(mEquipos, mPartidos, mClasificacion);
        ListenersRepetidos.actionListenerBarraDeNavegacion(jmiConsultarEquipos, jmiVer, jmiClasi, jmiCerrarSesion, jmiVerPerfil);

        bInsertarJugadores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Main.generarInsertarJugadores();
                } catch (MalformedURLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        bDeleteJugadores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Main.generarBorrarJugadores();
                } catch (MalformedURLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });



        bPOCuartos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Main.crearPlayOff();
                    JOptionPane.showMessageDialog(null, "Se ha credo el playoff");
                    Main.generarXml();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
        bPOSemi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Main.crearJornadaPlayOff(true);
                    JOptionPane.showMessageDialog(null, "Se ha creado la semifinal");
                    Main.generarXml();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
        bPOFinal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Main.crearJornadaPlayOff(false);
                    JOptionPane.showMessageDialog(null, "Se ha creado la final");
                    Main.generarXml();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
        bInsertarPersonal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Main.generarInsertarPersonal();
                } catch (MalformedURLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        bUpdatePersonal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Main.generarActualizarPersonal();
                } catch (MalformedURLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al crear la ventana");
                }
            }
        });
        bDeletePersonal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Main.generarBorrarPersonales();
                } catch (MalformedURLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        bUpdateJugadores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Main.generarActualizarJugadores();
                } catch (MalformedURLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        bInsertarEquipos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Main.generarVentanaInsertarEquipos();
                } catch (MalformedURLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        bUpdateEquipos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Main.generarActualizarEquipo();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        bActualizarXML.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.generarXml();
                JOptionPane.showMessageDialog(null, "xml actualizado");
            }
        });
        bDeleteEquipos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Main.generarBorrarEquipos();
                } catch (MalformedURLException ex) {

                }
            }
        });
        bInsertarContratoJugadores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Main.generarInsertarContratosJugador();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al crear la ventana");
                }
            }
        });
        bActualizarXML.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.generarXml();
            }
        });

        bUpdateContratoJugadores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Main.generarUpdateContratosJugadores();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        bgenerarSplit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.crearVentanaSplit();
            }
        });
        bEmparejar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Main.generarJornadas();
                    JOptionPane.showMessageDialog(null, "Emparejamientos creados");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });
        bInsertarResul.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Main.generarInsertarResultados();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al abrir la ventana");
                }
            }
        });

        bVerPatidos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Main.generarVerPartidos();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al abrir la ventana");
                }
            }
        });

        bDeleteContratoJugadores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Main.generarBorrarContratoJugadores();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al abrir la ventana");
                }
            }
        });

        bInsertarContratosPersonal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Main.generarInsertarContratosPersonal();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al crear la ventana");
                }
            }
        });

        bUpdateContratosPersonal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Main.generarActualizarContratosPersonal();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al crear la ventana");
                }
            }
        });

        bDeleteContratosPersonal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                   Main.generarBorrarContratoPersonal();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al crear la ventana");
                }
            }
        });

    }


    public JPanel getpPrincipal() {
        return pPrincipal;
    }

    public static void main(String[] args) throws MalformedURLException {
        JFrame frame = new JFrame("vPrincipalAdmin");
        frame.setContentPane(new vPrincipalAdmin().pPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
    }

}
