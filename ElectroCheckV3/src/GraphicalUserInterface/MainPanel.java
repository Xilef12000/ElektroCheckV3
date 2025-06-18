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
		
		//Text im Startbildschirm
		 // Panel mit BoxLayout für das Zentrum
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        this.add(centerPanel, BorderLayout.CENTER);
        
        //Textfelder erstellen und ins Centerpanel hinzufügen
		JLabel startText = new JLabel("Wilkommen bei ElektroCheckV3");
		startText.setFont(new Font("Arial", Font.BOLD, 18));
		startText.setAlignmentX(Component.CENTER_ALIGNMENT);
		JLabel taetigeAuswahl = new JLabel("Bitte wählen Sie einen Benutzer aus!");
		taetigeAuswahl.setFont(new Font("Arial", Font.PLAIN, 12));
		taetigeAuswahl.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		centerPanel.add(Box.createVerticalGlue());
        centerPanel.add(startText);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(taetigeAuswahl);
        centerPanel.add(Box.createVerticalGlue());

		
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
	    		MainPanel.this.remove(centerPanel);
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
	    		MainPanel.this.remove(centerPanel);
	    		VerwalterPanel verwalterPanel = new VerwalterPanel(MainPanel.this);
	    		MainPanel.this.add(verwalterPanel, BorderLayout.CENTER);
	    		MainPanel.this.revalidate();
	    	    MainPanel.this.repaint();
	    	}
	    }
	    );
	    
	    benutzerMenu.add(pruefer);
	    benutzerMenu.add(verwalter);
	    
	}
	
	
	//Methoden
	
	//Getter für GeräteListe
    List<Geraet> getGeraeteListe(){
    	return this.geraeteList;
    }
    
    
}
