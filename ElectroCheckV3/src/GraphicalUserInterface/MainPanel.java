package GraphicalUserInterface;

import javax.swing.*;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		this.add(menuBar, BorderLayout.NORTH);
		
		//Menuleiste erstellen
		JMenu benutzerMenu = new JMenu("Benutzer"); 
		benutzerMenu.setBackground(Color.GRAY);
		benutzerMenu.setForeground(Color.BLACK);
		benutzerMenu.setFont(new Font("Segoe UI", Font.BOLD, 16));
		menuBar.add(benutzerMenu);
	    
	    //Eintrage der Menueleiste
	    JMenuItem pruefer = new JMenuItem("Pruefer");
	    JMenuItem verwalter = new JMenuItem("Verwalter");
	    
	    //Aktionlistener für die Menueeintraege
	    pruefer.addActionListener(new ActionListener() {
	    	@Override
            public void actionPerformed(ActionEvent e) { 
	    		PrueferPanel prueferPanel = new PrueferPanel(MainPanel.this);
	    		MainPanel.this.add(prueferPanel, BorderLayout.CENTER);
	    		MainPanel.this.revalidate();
	    	    MainPanel.this.repaint();
	    	}
	    }
	    );
	    
	    verwalter.addActionListener(new ActionListener() {
	    	@Override
            public void actionPerformed(ActionEvent e) {
	    		VerwalterPanel verwalterPanel = new VerwalterPanel();
	    		MainPanel.this.add(verwalterPanel, BorderLayout.CENTER);
	    		MainPanel.this.revalidate();
	    	    MainPanel.this.repaint();
	    	}
	    }
	    );
	    
	    benutzerMenu.add(pruefer);
	    benutzerMenu.add(verwalter);
	    
	}
	
	//Get für GeräteListe
    List<Geraet> getGeraeteListe(){
    	return this.geraeteList;
    }
    
    
}
