package GraphicalUserInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class VerwalterPanel extends JPanel
{
	private MainPanel mainPanel;
	private DefaultListModel<Geraet> listModel;
	private JList<Geraet> geraetJList;
	private Geraet aktuellesGeraet;

	public VerwalterPanel(MainPanel mainPanel) 
	{
		this.mainPanel = mainPanel;
		
		//Definiere Layout
		this.setLayout(new BorderLayout());
				
		//Geräteliste erstellen
		createScrollPane();
						
		//Button-Leiste erstellen
		createButtonBar();
		
		//Erstellen-Panel erstellen
		createErstellenPanel();
	}
	
	//Erstellen einer Scrollbaren Anzeige für alle angelegten Geräte
	private void createScrollPane() 
	{
		JPanel scrollPanePanel = new JPanel();
		scrollPanePanel.setLayout(new BorderLayout());
		scrollPanePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // JList erstellen mit DefaultListModel
        listModel = new DefaultListModel<>();

        geraetJList = new JList<>(listModel);
        // Erst 'geraetJList' zuweißen, dann erst 'listModel' aktualisieren
        updateListModel();
        geraetJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        geraetJList.setVisibleRowCount(10);

        JScrollPane scrollPane = new JScrollPane(geraetJList);
        scrollPane.setPreferredSize(new Dimension(300, 200));
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        JLabel gereateUebersicht = new JLabel("Geräte-Übersicht");
        gereateUebersicht.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        
        scrollPanePanel.add(gereateUebersicht, BorderLayout.NORTH);
        scrollPanePanel.add(scrollPane, BorderLayout.CENTER);
        this.add(scrollPanePanel, BorderLayout.WEST);
        
        //SelectionListener der ScrollPane um das ausgewählte Gerät zu aktuallisieren bei Auswahländerung
        geraetJList.addListSelectionListener(e -> 
        {
            if (!e.getValueIsAdjusting()) 
            {
                aktuellesGeraet = geraetJList.getSelectedValue();
            }
        }
        );
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
                	updateListModel();
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
            	updateListModel();
            }
        };
        erstellenButton.addActionListener(erstellenButtonAL);
		
		erstellenPanel.add(geratErstellenPanel, BorderLayout.CENTER);		
		this.add(erstellenPanel, BorderLayout.CENTER);
	}
	public void updateListModel() {
		listModel.clear();
		//Liste der Geräte aus MainPanel holen
        List<Geraet> geraete = mainPanel.getGeraeteListe();
        for (Geraet g : geraete) 
        {
            listModel.addElement(g);
        }
        geraetJList.revalidate();
    	geraetJList.repaint();
	}
}