package GraphicalUserInterface;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel
{
	private JFrame mainWindow;

	public MainPanel(JFrame mainWindow) 
	{
		this.mainWindow = mainWindow;
		
		//Definiere Layout
		this.setLayout(new BorderLayout());
	}

}
