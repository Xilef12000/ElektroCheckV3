package GraphicalUserInterface;

import javax.swing.*;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends JPanel
{
	private List<Geraet> geraeteList = new ArrayList<>();
	
	public MainPanel() 
	{
		geraeteList.add(new Geraet_SK1("Test Gerät", true, 100)); // testgerät, to be deleted
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
		
		
		//Benutzer als Menuebareintrag
		JMenu benutzerMenu = new JMenu("Benutzer"); 
		benutzerMenu.setBackground(Color.GRAY);
		benutzerMenu.setForeground(Color.BLACK);
		benutzerMenu.setFont(new Font("Segoe UI", Font.BOLD, 16));
		menuBar.add(benutzerMenu);
	    
	    //Eintrage der Menueleiste
	    JMenuItem pruefer = new JMenuItem("Pruefer");
	    JMenuItem verwalter = new JMenuItem("Verwalter");
	    benutzerMenu.add(pruefer);
	    benutzerMenu.add(verwalter);
	    
	    //Aktionlistener für Pruefer
	    pruefer.addActionListener(new ActionListener() {
	    	@Override
            public void actionPerformed(ActionEvent e) { 
	    		//Aktuelles Panel in MainPanel BorderLayout(CENTER)
	    		Component centerComp =  ((BorderLayout) MainPanel.this.getLayout()).getLayoutComponent(BorderLayout.CENTER);
	    		if (centerComp != null && centerComp instanceof JPanel) {
	    	        JPanel deletePanel = (JPanel) centerComp;
	    	        MainPanel.this.remove(deletePanel);
	    	    }
	    		//Center mit PrueferPanel überschreiben 
	    		PrueferPanel prueferPanel = new PrueferPanel(MainPanel.this);
	    		MainPanel.this.add(prueferPanel, BorderLayout.CENTER);
	    		MainPanel.this.revalidate();
	    	    MainPanel.this.repaint();
	    	}
	    }
	    );
	    
	    //Aktionlistener für Verwalter
	    verwalter.addActionListener(new ActionListener() {
	    	@Override
            public void actionPerformed(ActionEvent e) {
	    		//Aktuelles Panel in MainPanel BorderLayout(CENTER)
	    		Component centerComp =  ((BorderLayout) MainPanel.this.getLayout()).getLayoutComponent(BorderLayout.CENTER);
	    		if (centerComp != null && centerComp instanceof JPanel) {
	    	        JPanel deletePanel = (JPanel) centerComp;
	    	        MainPanel.this.remove(deletePanel);
	    	    }
	    		VerwalterPanel verwalterPanel = new VerwalterPanel(MainPanel.this);
	    		MainPanel.this.add(verwalterPanel, BorderLayout.CENTER);
	    		MainPanel.this.revalidate();
	    	    MainPanel.this.repaint();
	    	}
	    }
	    );
	    
	    //Datei als Menubareintrag
		JMenu dateiMenu = new JMenu("Datei"); 
		dateiMenu.setBackground(Color.GRAY);
		dateiMenu.setForeground(Color.BLACK);
		dateiMenu.setFont(new Font("Segoe UI", Font.BOLD, 16));
		menuBar.add(dateiMenu);
	    
	    //Eintraege der Dateileiste
	    JMenuItem importieren = new JMenuItem("Importieren Geräteliste");
	    JMenuItem exportieren = new JMenuItem("Exportieren Geräteliste");
	    dateiMenu.add(importieren);
	    dateiMenu.add(exportieren);
	  	    
	  	//Aktionlistener für Importieren
	    importieren.addActionListener(new ActionListener() {
	    	@Override
            public void actionPerformed(ActionEvent e) {
	    	
	    	}
	    }
	    );
	    
	    //Aktionlistener für Exportieren
	    exportieren.addActionListener(new ActionListener() {
	    	@Override
            public void actionPerformed(ActionEvent e) {
	    		
	    	}
	    }
	    );

	    //About als Menubareintrag
  		JMenu aboutMenu = new JMenu("About"); 
  		aboutMenu.setBackground(Color.GRAY);
  		aboutMenu.setForeground(Color.BLACK);
  		aboutMenu.setFont(new Font("Segoe UI", Font.BOLD, 16));
  		menuBar.add(aboutMenu);
  	    
  	    //Eintrage der Aboutleiste
  	    JMenuItem aboutUs = new JMenuItem("About Us");
  	    JMenuItem aboutSoftware = new JMenuItem("About Software");
  	    aboutMenu.add(aboutUs);
  	    aboutMenu.add(aboutSoftware);
  	    
  	    //Aktionlistener für aboutUs
	    aboutUs.addActionListener(new ActionListener() {
	    	@Override
            public void actionPerformed(ActionEvent e) {
	    		JOptionPane.showMessageDialog(null, "Gruppe: \nN. Bachmann \nV. Bahatyani \nM. Baron \nM. König \nN.Weigold");
	    	}
	    }
	    );
	    
	    //Aktionlistener für aboutSoftware
	    aboutSoftware.addActionListener(new ActionListener() {
	    	@Override
            public void actionPerformed(ActionEvent e) {
	    		
	    	}
	    }
	    );

	    //Programm beenden als Menubareintrag
  		JMenuItem programmBeenden = new JMenuItem("Programm beenden"); 
  		programmBeenden.setForeground(Color.BLACK);
  		programmBeenden.setFont(new Font("Segoe UI", Font.BOLD, 16));
  		programmBeenden.addActionListener(e -> System.exit(0)); 
  		menuBar.add(programmBeenden);

	}
	
	
	//Methoden
	
	//Getter für GeräteListe
	protected List<Geraet> getGeraeteListe(){
    	return this.geraeteList;
    }
    
	protected void addGeraet(Geraet geraet){
		geraeteList.add(geraet);
		System.out.println(geraeteList);
    }
	protected void removeGeraet(Geraet geraet){
		geraeteList.remove(geraet);
		System.out.println(geraeteList);
    }
    
    
}
