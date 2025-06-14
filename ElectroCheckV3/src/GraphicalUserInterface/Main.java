package GraphicalUserInterface;

import javax.swing.*;

public class Main {

	public static void main(String[] args) 
	{
		JFrame MainWindow = new JFrame();
		MainPanel MainPanel = new MainPanel();
		MainWindow.setSize(900,600);
		MainWindow.setContentPane(MainPanel);
		MainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MainWindow.setVisible(true);
		Geraet myGeraet = new Geraet_SK1("test123", true, 365);
		myGeraet.print();
		myGeraet.setPruefungNichtBestanden(Grund.values()[2]);
		myGeraet.print();
	}
}
