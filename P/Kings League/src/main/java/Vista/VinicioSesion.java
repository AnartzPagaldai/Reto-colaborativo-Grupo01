package Vista;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

public class VinicioSesion {
    private JPanel pPrincipal;
    private JPanel pLogo;
    private ImageIcon LogoKingsLeague;
    private JLabel Logo;

    public VinicioSesion() throws MalformedURLException {

        LogoKingsLeague = new ImageIcon(new URL("https://seeklogo.com/images/K/kings-league-logo-CEDD6AED72-seeklogo.com.png"));
        Image LogoNuevo = LogoKingsLeague.getImage().getScaledInstance(300, 122, Image.SCALE_SMOOTH);
        ImageIcon newIcon= new ImageIcon(LogoNuevo);
        Logo.setIcon(newIcon);

    }

    public static void main(String[] args) throws MalformedURLException {
        JFrame frame = new JFrame("VinicioSesion");
        frame.setContentPane(new VinicioSesion().pPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
