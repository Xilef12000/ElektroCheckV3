package GraphicalUserInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class PrueferPanel extends JPanel
{
	private MainPanel mainPanel;
	private DefaultListModel<Geraet> listModel;
	private JList<Geraet> geraetJList;
	private Geraet aktuellesGeraet;

	public PrueferPanel(MainPanel mainPanel) 
	{
		this.mainPanel = mainPanel;
		
		//Definiere Layout
		this.setLayout(new BorderLayout());
				
		//Geräteliste erstellen
		createScrollPane();
						
		//Button-Leiste erstellen
		createButtonBar();
		
		//Prüfungs-Panel erstellen
		createPruefungDokPanel();
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
        JButton pruefungDokButton = new JButton("Prüfung dokumentieren");
        
        JLabel funktionen = new JLabel("Funktionen");
        funktionen.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        
        //Buttons in Panel einfügen
        buttonPanel.add(pruefungDokButton);
        buttonPanel.add(einsehenButton);
        
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
        
        
        ActionListener pruefungDokAL = new ActionListener() 
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
		
		//Internes Panel zur Funktionsimplementierung	
		JPanel pruefungsPanel = new JPanel();
		pruefungsPanel.setLayout(new GridLayout(20, 1));
		pruefungsPanel.setBackground(Color.WHITE);
		pruefungsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		String geraeteName = (aktuellesGeraet != null) ? aktuellesGeraet.getName() : "";
		JLabel ausgewaehltesGeraet = new JLabel("Ausgewähltes Gerät: " + geraeteName);
		ausgewaehltesGeraet.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
		ausgewaehltesGeraet.setFont(ausgewaehltesGeraet.getFont().deriveFont(18f));
		
		String geraeteSK = (aktuellesGeraet != null) ? aktuellesGeraet.getSchutzklasse() : "";
		JLabel schutzklasse = new JLabel(geraeteSK);
		schutzklasse.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
		schutzklasse.setFont(schutzklasse.getFont().deriveFont(14f));
		
		//Panel für Dokumentation des Prüfstatus
		JPanel pruefStatus = new JPanel();
		pruefStatus.setLayout(new BorderLayout());
		pruefStatus.setBackground(Color.WHITE);
		
		//Checkbox Prüfung bestanden
		JCheckBox pruefBestanden = new JCheckBox("Prüfung bestanden");
		pruefBestanden.setHorizontalTextPosition(SwingConstants.LEFT);
		pruefBestanden.setBackground(Color.WHITE);
		pruefBestanden.setFont(pruefBestanden.getFont().deriveFont(14f));
		pruefStatus.add(pruefBestanden, BorderLayout.WEST);
		
		//Checkbox Prüfung nicht bestanden
		JCheckBox pruefNichtBestanden = new JCheckBox("Prüfung nicht bestanden");
		pruefNichtBestanden.setHorizontalTextPosition(SwingConstants.LEFT);
		pruefNichtBestanden.setBackground(Color.WHITE);
		pruefNichtBestanden.setFont(pruefNichtBestanden.getFont().deriveFont(14f));
		pruefNichtBestanden.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		pruefStatus.add(pruefNichtBestanden, BorderLayout.CENTER);
		
		pruefungsPanel.add(ausgewaehltesGeraet);
		pruefungsPanel.add(schutzklasse);
		pruefungsPanel.add(pruefStatus);
		dokuPanel.add(pruefungsPanel, BorderLayout.CENTER);		
		this.add(dokuPanel, BorderLayout.CENTER);
		
		//ActionListener für die Checkboxen
		ActionListener checkboxListener = e -> 
		{
			Object source = e.getSource();
			if (source == pruefBestanden && pruefBestanden.isSelected()) 
			{
				pruefNichtBestanden.setSelected(false);
			} 
			else if (source == pruefNichtBestanden && pruefNichtBestanden.isSelected()) 
			{
				pruefBestanden.setSelected(false);
			}
		};
		pruefBestanden.addActionListener(checkboxListener);
		pruefNichtBestanden.addActionListener(checkboxListener);
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
