package GraphicalUserInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class VerwalterPanel extends JPanel
{
	private MainPanel mainPanel;
	protected Geraet aktuellesGeraet;
	public ScrollPanePanel scrollPanePanel;

	public VerwalterPanel(MainPanel mainPanel) 
	{
		this.mainPanel = mainPanel;
		
		//Definiere Layout
		this.setLayout(new BorderLayout());
				
		//Geräteliste erstellen
		scrollPanePanel = new ScrollPanePanel(mainPanel, this);
						
		//Button-Leiste erstellen
		createButtonBar();
		
		//Erstellen-Panel erstellen
		createErstellenPanel();
	}
	
	//Erstellen der ButtonBar zum Auswählen der gewünschten Aktion
	private void createButtonBar() 
	{
        JPanel funktionsPanel = new JPanel();
        funktionsPanel.setLayout(new BorderLayout());
        funktionsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        funktionsPanel.setPreferredSize(new Dimension(300, 200));
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(15, 1));
        
        //Buttons erstellen
        JButton einsehenButton = new JButton("Gerät einsehen");
        JButton geraetAnlegen = new JButton("Gerät anlegen");
        JButton geraetLoeschen = new JButton("Gerät löschen");
        
        JLabel funktionen = new JLabel("Funktionen");
        funktionen.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        
        //Buttons in Panel einfügen
        buttonPanel.add(geraetAnlegen);
        buttonPanel.add(einsehenButton);
        buttonPanel.add(geraetLoeschen);
        
        funktionsPanel.add(funktionen, BorderLayout.NORTH);
        funktionsPanel.add(buttonPanel, BorderLayout.CENTER);
        
        this.add(funktionsPanel, BorderLayout.EAST);
        
        
        //Erstellen der ActionListener für die beiden Button
        ActionListener einsehenAL = new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                if (aktuellesGeraet != null) 
                {
                	
                } 
                else 
                {
                	JOptionPane.showMessageDialog(null, "Bitte ein Gerät auswählen.");
                }

            }
        };
        einsehenButton.addActionListener(einsehenAL);
        
        
        ActionListener geraetAnlegenAL = new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {                
        		System.out.println("test erstellen");
            }
        };
        geraetAnlegen.addActionListener(geraetAnlegenAL);
        
        
        ActionListener geraetLoeschenAL = new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {                
                if (aktuellesGeraet != null) 
                {
                	mainPanel.removeGeraet(aktuellesGeraet);
                	scrollPanePanel.updateListModel();
                } 
                else 
                {
                	JOptionPane.showMessageDialog(null, "Bitte ein Gerät auswählen.");
                }

            }
        };
        geraetLoeschen.addActionListener(geraetLoeschenAL);
	}
	
	//Panel zur Prüfungsdokumentation einzelner Geräte
	void createErstellenPanel()
	{
		JPanel erstellenPanel = new JPanel();
		erstellenPanel.setLayout(new BorderLayout());
		erstellenPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		JLabel erstellen = new JLabel("Gerät erstellen");
		erstellen.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));		
		erstellenPanel.add(erstellen, BorderLayout.NORTH);
		
		//Internes Panel zur Funktionsimplementierung	
		JPanel geratErstellenPanel = new JPanel();
		geratErstellenPanel.setLayout(new GridLayout(20, 1));
		geratErstellenPanel.setBackground(Color.WHITE);
		geratErstellenPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		JPanel namePanel = new JPanel();
		namePanel.setLayout(new BorderLayout());
		geratErstellenPanel.add(namePanel);
		JLabel nameLabel = new JLabel("Geräte Name: ");
		JTextField nameField = new JTextField(100);
		namePanel.add(nameLabel, BorderLayout.WEST);
		namePanel.add(nameField, BorderLayout.CENTER);
		
		JPanel klassePanel = new JPanel();
		klassePanel.setLayout(new BorderLayout());
		geratErstellenPanel.add(klassePanel);
		JLabel klasseLabel = new JLabel("Geräte Klasse: ");
		String[] geraeteTypen = { "Schutzklasse I", "Schutzklasse II", "Schutzklasse III"};
		JComboBox<String> geraeteTypenBox = new JComboBox<>(geraeteTypen);
		klassePanel.add(klasseLabel, BorderLayout.WEST);
		klassePanel.add(geraeteTypenBox, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BorderLayout());
		geratErstellenPanel.add(buttonPanel);
		JButton erstellenButton = new JButton("Gerät erstellen");
		JButton abbrechenButton = new JButton("Abbrechen");
		buttonPanel.add(erstellenButton, BorderLayout.WEST);
		buttonPanel.add(abbrechenButton, BorderLayout.EAST);
		
		ActionListener erstellenButtonAL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String schutzklasse = (String) geraeteTypenBox.getSelectedItem();
            	if (geraeteTypen[0].equals(schutzklasse)) {
            		mainPanel.addGeraet(new Geraet_SK1(nameField.getText(), true, 100));
            	} else if (geraeteTypen[1].equals(schutzklasse)) {
            		mainPanel.addGeraet(new Geraet_SK2(nameField.getText(), true, 100));
            	} else if (geraeteTypen[2].equals(schutzklasse)) {
            		mainPanel.addGeraet(new Geraet_SK3(nameField.getText(), true, 100));
            	}
            	scrollPanePanel.updateListModel();
            }
        };
        erstellenButton.addActionListener(erstellenButtonAL);
		
		erstellenPanel.add(geratErstellenPanel, BorderLayout.CENTER);		
		this.add(erstellenPanel, BorderLayout.CENTER);
	}
}