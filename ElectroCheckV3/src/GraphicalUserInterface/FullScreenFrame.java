package GraphicalUserInterface;

import javax.swing.JFrame;
import java.awt.*;

public class FullScreenFrame extends JFrame 
{
    public FullScreenFrame() 
    {
        super("Vollbild ohne Taskleiste");

        // Bildschirmgröße und Insets (Taskleiste etc.) ermitteln
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        Rectangle screenBounds = gd.getDefaultConfiguration().getBounds();

        Insets screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(gd.getDefaultConfiguration());

        // Größe berechnen: Bildschirmgröße minus Insets
        int width = screenBounds.width - screenInsets.left - screenInsets.right;
        int height = screenBounds.height - screenInsets.top - screenInsets.bottom;

        // JFrame positionieren und Größe setzen
        setBounds(screenBounds.x + screenInsets.left, screenBounds.y + screenInsets.top, width, height);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}