package GraphicalUserInterface;

import javax.swing.*;

public class Main {

	public static void main(String[] args) 
	{
		FullScreenFrame MainWindow = new FullScreenFrame();
		MainPanel MainPanel = new MainPanel();
		MainWindow.setContentPane(MainPanel);
		MainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MainWindow.setVisible(true);
	}
}
