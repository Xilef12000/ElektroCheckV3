package GraphicalUserInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class PrueferPanel extends JPanel
{
	private MainPanel mainPanel;
	private JList<Geraet> geraetJList;
	private JPanel statusPanel;
	protected Geraet aktuellesGeraet;
	public ScrollPanePanel scrollPanePanel;
	private JLabel ausgewaehltesGeraet;
	private JLabel schutzklasse;
	private int ausgewaehlterGrundIndex = 0;

	public PrueferPanel(MainPanel mainPanel) 
	{
		this.mainPanel = mainPanel;
		
		//Definiere Layout
		this.setLayout(new BorderLayout());
				
		//Geräteliste erstellen
		scrollPanePanel = new ScrollPanePanel(mainPanel, this);
						
		//Button-Leiste erstellen
		createButtonBar();
		
		//Prüfungs-Panel erstellen
		createPruefungDokPanel();
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
        JButton druckenButton = new JButton("Gerätedaten drucken");
        JButton pruefungDokButton = new JButton("Geräteprüfung dokumentieren");
        
        JLabel funktionen = new JLabel("Funktionen");
        funktionen.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        
        //Buttons in Panel einfügen
        buttonPanel.add(pruefungDokButton);
        buttonPanel.add(druckenButton);
        
        funktionsPanel.add(funktionen, BorderLayout.NORTH);
        funktionsPanel.add(buttonPanel, BorderLayout.CENTER);
        
        this.add(funktionsPanel, BorderLayout.EAST);
        
        
        //Erstellen der ActionListener für die beiden Button
        ActionListener druckenButtonAL = new ActionListener() 
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
        druckenButton.addActionListener(druckenButtonAL);
        
        
        ActionListener pruefungDokAL = new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {                
                if (aktuellesGeraet != null) 
                {
                	switch(PrueferPanel.this.ausgewaehlterGrundIndex) 
                	{
                		case 0: PrueferPanel.this.aktuellesGeraet.setPruefungBestanden();
                		
                		case 1: PrueferPanel.this.aktuellesGeraet.setPruefungBestanden();
                	}
                } 
                else 
                {
                	JOptionPane.showMessageDialog(null, "Bitte ein Gerät auswählen.");
                }

            }
        };
        pruefungDokButton.addActionListener(pruefungDokAL);         
	}
	
	//Panel zur Prüfungsdokumentation einzelner Geräte
	void createPruefungDokPanel() 
	{
	    JPanel dokuPanel = new JPanel();
	    dokuPanel.setLayout(new BorderLayout());
	    dokuPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

	    JLabel dokuUebersicht = new JLabel("Dokumentationsübersicht");
	    dokuUebersicht.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
	    dokuPanel.add(dokuUebersicht, BorderLayout.NORTH);

	    //Hauptbereich für Prüfungsinformationen
	    JPanel pruefungsPanel = new JPanel();
	    pruefungsPanel.setLayout(new BoxLayout(pruefungsPanel, BoxLayout.Y_AXIS));
	    pruefungsPanel.setBackground(Color.WHITE);

	    //Geräteinfo
	    JPanel geraetInfoPanel = new JPanel();
	    geraetInfoPanel.setLayout(new BoxLayout(geraetInfoPanel, BoxLayout.Y_AXIS));
	    geraetInfoPanel.setBackground(Color.WHITE);

	    this.ausgewaehltesGeraet = new JLabel("Ausgewähltes Gerät: " + 
	        (aktuellesGeraet != null ? aktuellesGeraet.getName() : "Keins ausgewählt"));
	    
	    this.schutzklasse = new JLabel("Geräteschutzklasse: " + 
	        (aktuellesGeraet != null ? aktuellesGeraet.getSchutzklasse() : "Unbekannt"));

	    this.ausgewaehltesGeraet.setAlignmentX(Component.LEFT_ALIGNMENT);
	    this.schutzklasse.setAlignmentX(Component.LEFT_ALIGNMENT);

	    this.ausgewaehltesGeraet.setMaximumSize(new Dimension(Integer.MAX_VALUE, ausgewaehltesGeraet.getPreferredSize().height));
	    this.schutzklasse.setMaximumSize(new Dimension(Integer.MAX_VALUE, schutzklasse.getPreferredSize().height));

	    geraetInfoPanel.add(ausgewaehltesGeraet);
	    geraetInfoPanel.add(schutzklasse); 
	    geraetInfoPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

	    JPanel geraetInfoWrapper = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    geraetInfoWrapper.setBackground(Color.WHITE);
	    geraetInfoWrapper.add(geraetInfoPanel);
	    
	    //Prüfstatus Panel
	    JPanel pruefStatus = new JPanel();
	    pruefStatus.setLayout(new FlowLayout(FlowLayout.LEFT));
	    pruefStatus.setBackground(Color.WHITE);
	    JCheckBox pruefBestanden = new JCheckBox("Prüfung bestanden");
	    JCheckBox pruefNichtBestanden = new JCheckBox("Prüfung nicht bestanden");

	    pruefBestanden.setBackground(Color.WHITE);
	    pruefNichtBestanden.setBackground(Color.WHITE);

	    pruefStatus.add(pruefBestanden);
	    pruefStatus.add(pruefNichtBestanden);

	    //Statusbereich für dynamische Inhalte
	    this.statusPanel = new JPanel();
	    statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.Y_AXIS));
	    statusPanel.setBackground(Color.WHITE);
	    
	    JPanel platzhalter = new JPanel();
	    platzhalter.setLayout(new BorderLayout());
	    platzhalter.setBackground(Color.WHITE);
	    
	    statusPanel.add(platzhalter);

	    //Hinzufügen aller Komponenten
	    pruefungsPanel.add(geraetInfoWrapper);
	    pruefungsPanel.add(pruefStatus);
	    pruefungsPanel.add(Box.createRigidArea(new Dimension(0,10)));
	    pruefungsPanel.add(statusPanel);

	    dokuPanel.add(pruefungsPanel, BorderLayout.CENTER);
	    this.add(dokuPanel, BorderLayout.CENTER);

	    // Listener
	    ActionListener checkboxListener = e -> 
	    {
	        if (aktuellesGeraet == null) 
	        {
	            JOptionPane.showMessageDialog(null, "Bitte erst ein Gerät auswählen.");
	            pruefBestanden.setSelected(false);
	            pruefNichtBestanden.setSelected(false);
	            return;
	        }

	        if (e.getSource() == pruefBestanden) 
	        {
	            pruefNichtBestanden.setSelected(false);
	            createBestandenPanel();
	        } 
	        else 
	        {
	            pruefBestanden.setSelected(false);
	            createNichtBestandenPanel();
	        }
	    };
	    pruefBestanden.addActionListener(checkboxListener);
	    pruefNichtBestanden.addActionListener(checkboxListener);
	}
	
	//Panel wenn Prüfung bestanden
	void createBestandenPanel()
	{
	    this.statusPanel.removeAll();
	    
	    JPanel gesamtPanel = new JPanel();
	    gesamtPanel.setLayout(new BorderLayout(10, 10));
	    gesamtPanel.setBackground(Color.WHITE);

	    JPanel pruefBestandenPanel = new JPanel(new GridLayout(0, 1, 5, 5));
	    pruefBestandenPanel.setBackground(Color.WHITE);
	    pruefBestandenPanel.setPreferredSize(new Dimension(400, 150));
	    pruefBestandenPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	    pruefBestandenPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

	    List<String> gruende = this.aktuellesGeraet.getGruende();

	    for (String grund : gruende)
	    {
	        JPanel zeile = new JPanel(new FlowLayout(FlowLayout.LEFT));
	        zeile.setBackground(Color.WHITE);

	        JLabel grundLabel = new JLabel(grund);
	        JLabel hakenLabel = new JLabel("\u2705");

	        zeile.add(grundLabel);
	        zeile.add(hakenLabel);
	        pruefBestandenPanel.add(zeile);
	    }
	    
	    JPanel platzhalter = new JPanel(new BorderLayout());
	    platzhalter.setBackground(Color.WHITE);
	    platzhalter.setPreferredSize(new Dimension(10, 150));
	    
	    gesamtPanel.add(pruefBestandenPanel, BorderLayout.CENTER);
	    gesamtPanel.add(platzhalter, BorderLayout.SOUTH);
	    

	    this.statusPanel.add(gesamtPanel, BorderLayout.CENTER);
	    this.statusPanel.revalidate();
	    this.statusPanel.repaint();
	}
	
	//Panel wenn Prüfung bestanden
	void createNichtBestandenPanel() 
	{
	    this.statusPanel.removeAll();

	    JPanel pruefNichtBestandenPanel = new JPanel(new BorderLayout(10, 10));
	    pruefNichtBestandenPanel.setBackground(Color.WHITE);

	    JPanel radioPanel = new JPanel();
	    radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.Y_AXIS));
	    radioPanel.setBackground(Color.WHITE);

	    ButtonGroup buttonGroup = new ButtonGroup();
	    List<String> gruende = this.aktuellesGeraet.getGruende();

	    for (int i = 0; i < gruende.size(); i++) 
	    {
	        String grund = gruende.get(i);
	        JRadioButton radioButton = new JRadioButton(grund);
	        radioButton.setBackground(Color.WHITE);
	        int index = i + 1; // Zähle ab 1

	        // Listener: speichert die Nummer des ausgewählten Grundes
	        radioButton.addActionListener(e -> 
	        {
	            this.ausgewaehlterGrundIndex = index;
	            System.out.println("Ausgewählter Grund: " + index);
	        });

	        buttonGroup.add(radioButton);
	        radioPanel.add(radioButton);
	    }
	    
	    JScrollPane scrollPane = new JScrollPane(radioPanel);
	    scrollPane.setPreferredSize(new Dimension(400, 150));
	    scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));

	    JTextArea kommentarArea = new JTextArea(4, 30);
	    kommentarArea.setLineWrap(true);
	    kommentarArea.setWrapStyleWord(true);
	    JScrollPane kommentarScroll = new JScrollPane(kommentarArea);
	    kommentarScroll.setBorder(BorderFactory.createTitledBorder("Kommentar"));

	    pruefNichtBestandenPanel.add(scrollPane, BorderLayout.CENTER);
	    pruefNichtBestandenPanel.add(kommentarScroll, BorderLayout.SOUTH);

	    this.statusPanel.add(pruefNichtBestandenPanel);
	    this.statusPanel.revalidate();
	    this.statusPanel.repaint();
	}
	
	//Aktuallisiere Panel
	protected void aktualisierePanel() 
	{
		if (ausgewaehltesGeraet != null && schutzklasse != null && aktuellesGeraet != null) 
		{
		    ausgewaehltesGeraet.setText("Ausgewähltes Gerät: " + aktuellesGeraet.getName());
		    schutzklasse.setText("Geräteschutzklasse: " + aktuellesGeraet.getSchutzklasse());
		}
	}
}
