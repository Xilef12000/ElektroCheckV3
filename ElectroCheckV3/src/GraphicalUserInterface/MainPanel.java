package GraphicalUserInterface;

import javax.swing.*;
import java.util.*;
import java.util.List;
import java.awt.*;

public class MainPanel extends JPanel
{
	List<Geraet> geraeteList = new ArrayList<>();
	
	public MainPanel() 
	{
		//Layout setzten
		this.setLayout(new BorderLayout());
		
		//Menuebar erstellen
		JMenuBar menuBar = new JMenuBar(); // Menüleiste)
		menuBar.setFont(new Font("Arial", Font.PLAIN, 16));
		
		//Menuleiste erstellen
		JMenu benutzerMenu = new JMenu("Benuter"); 
		benutzerMenu.setBackground(Color.GRAY);
		benutzerMenu.setForeground(Color.BLACK);
		benutzerMenu.setFont(new Font("Segoe UI", Font.BOLD, 16));
	    
	    //Eintrage der Menueleiste
	    JMenuItem Pruefer = new JMenuItem("Pruefer");
	    JMenuItem Verwalter = new JMenuItem("Verwalter");
	    benutzerMenu.add(Pruefer);
	    benutzerMenu.add(Verwalter);
	    
	    this.add(menuBar, BorderLayout.NORTH);
	}
	
	//Get für GeräteListe
    List<Geraet> getGeraeteListe(){
    	return this.geraeteList;
    }
    
    
}
