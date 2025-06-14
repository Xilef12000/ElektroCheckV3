package GraphicalUserInterface;

import javax.swing.*;

public class Main {

	public static void main(String[] args) 
	{
		JFrame MainWindow = new JFrame();
		MainPanelTest mainPanelTest = new MainPanelTest();
		PrueferPanel prueferPanel = new PrueferPanel(mainPanelTest);
		MainWindow.setSize(900,600);
		MainWindow.setContentPane(mainPanelTest);
		MainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MainWindow.setVisible(true);
	}

}
