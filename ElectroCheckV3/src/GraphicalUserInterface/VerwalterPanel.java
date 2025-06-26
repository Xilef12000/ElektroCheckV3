package GraphicalUserInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class VerwalterPanel extends JPanel
{
	private MainPanel mainPanel;
	protected Geraet aktuellesGeraet;
	public ScrollPanePanel scrollPanePanel;
	
	private ActionListener geraetAnlegenAL;
	private JTextField nameField;
	private JSpinner daysField;
	private JComboBox<String> geraeteTypenBox;
	private JPanel submitExitPanel;
	private JButton erstellenButton;
	private JButton abbrechenButton;
	
	private JPanel eigenschaftenPanel;
	private JCheckBox bestandenBox;
	private JTextField grundText;
	private JTextField naechstePText;
	
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
        JButton geraetDruckenButton = new JButton("Gerät drucken");
        JButton geraetAnlegen = new JButton("Gerät anlegen");
        JButton geraetLoeschen = new JButton("Gerät löschen");
        
        JLabel funktionen = new JLabel("Funktionen");
        funktionen.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        
        //Buttons in Panel einfügen
        buttonPanel.add(geraetAnlegen);
        buttonPanel.add(geraetDruckenButton);
        buttonPanel.add(geraetLoeschen);
        
        funktionsPanel.add(funktionen, BorderLayout.NORTH);
        funktionsPanel.add(buttonPanel, BorderLayout.CENTER);
        
        this.add(funktionsPanel, BorderLayout.EAST);
        
        
        //Erstellen der ActionListener für die beiden Button
        ActionListener druckenAL = new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                if (aktuellesGeraet != null) 
                {
                	JFileChooser fileChooser = new JFileChooser();
            		int result = fileChooser.showSaveDialog(null);
            		
            		if(result == JFileChooser.APPROVE_OPTION) {
            			
            			File selectedFile = fileChooser.getSelectedFile();
            			// Falls keine Endung angegeben wurde, .txt anhängen
            		    if (!selectedFile.getName().toLowerCase().endsWith(".txt")) {
            		        selectedFile = new File(selectedFile.getAbsolutePath() + ".txt");
            		    }
            			
            			try(PrintWriter printWriter = new PrintWriter(selectedFile)){ 		// braucht keinen extra filewriter, erstellt printwriter autom., wenn man ihm datei übergibt
        					aktuellesGeraet.drucken(printWriter);
        					JOptionPane.showMessageDialog(null, "Geraet wurde erfolgreich in die ausgewählte Datei gedruckt.");
        				
            			}
            			catch(Exception ex) {
            				JOptionPane.showMessageDialog(null, "Druckvorgang fehlgeschlagen, bitte versuchen Sie es erneut!" + ex.getMessage());
            			}
            		}
                } 
                else 
                {
                	JOptionPane.showMessageDialog(null, "Bitte ein Gerät auswählen.");
                }

            }
        };
        geraetDruckenButton.addActionListener(druckenAL);
        
        
        geraetAnlegenAL = new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
            	scrollPanePanel.clearSelection();
            	nameField.setText("");
    			nameField.setEnabled(true);
    			daysField.setValue(365);
    			daysField.setEnabled(true);
    			geraeteTypenBox.setEnabled(true);
    			geraeteTypenBox.setSelectedIndex(0);
    			submitExitPanel.setVisible(true);
    			erstellenButton.setEnabled(true);
    			abbrechenButton.setEnabled(true);
    			
    			eigenschaftenPanel.setVisible(false);
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
		geratErstellenPanel.setLayout(new GridLayout(2, 1));
		geratErstellenPanel.setBackground(Color.WHITE);
		geratErstellenPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		JPanel basicInfoPanel  = new JPanel(new GridLayout(5, 1));
		geratErstellenPanel.add(basicInfoPanel);
		
		JPanel namePanel = new JPanel();
		namePanel.setLayout(new BorderLayout());
		basicInfoPanel.add(namePanel);
		JLabel nameLabel = new JLabel("Geräte Name: ");
		nameField = new JTextField(100);
		namePanel.add(nameLabel, BorderLayout.WEST);
		namePanel.add(nameField, BorderLayout.CENTER);
		
		JPanel daysPanel = new JPanel();
		daysPanel.setLayout(new BorderLayout());
		basicInfoPanel.add(daysPanel);
		JLabel daysLabel = new JLabel("Gültichkeit Prüfung (in Tagen): ");
		daysField = new JSpinner(new SpinnerNumberModel(365, 1, 730, 1));
		daysPanel.add(daysLabel, BorderLayout.WEST);
		daysPanel.add(daysField, BorderLayout.CENTER);
		
		JPanel klassePanel = new JPanel();
		klassePanel.setLayout(new BorderLayout());
		basicInfoPanel.add(klassePanel);
		JLabel klasseLabel = new JLabel("Geräte Klasse: ");
		String[] geraeteTypen = { "Schutzklasse I", "Schutzklasse II", "Schutzklasse III"};
		geraeteTypenBox = new JComboBox<>(geraeteTypen);
		klassePanel.add(klasseLabel, BorderLayout.WEST);
		klassePanel.add(geraeteTypenBox, BorderLayout.CENTER);
		
		submitExitPanel = new JPanel();
		submitExitPanel.setLayout(new BorderLayout());
		basicInfoPanel.add(submitExitPanel);
		erstellenButton = new JButton("Gerät erstellen");
		abbrechenButton = new JButton("Eingabe Löschen");
		submitExitPanel.add(erstellenButton, BorderLayout.WEST);
		submitExitPanel.add(abbrechenButton, BorderLayout.EAST);
		
		eigenschaftenPanel = new JPanel();
		eigenschaftenPanel.setVisible(false);
		eigenschaftenPanel.setLayout(new GridLayout(5, 2));
		geratErstellenPanel.add(eigenschaftenPanel);
		
		JLabel bestanden = new JLabel("Prüfung bestanden: ");
		eigenschaftenPanel.add(bestanden);
		
		bestandenBox = new JCheckBox();
		bestandenBox.setEnabled(false);
		eigenschaftenPanel.add(bestandenBox);
		
		JLabel grund = new JLabel("Grund: ");
		eigenschaftenPanel.add(grund);
		
		grundText = new JTextField("");
		grundText.setEnabled(false);
		eigenschaftenPanel.add(grundText);
		
		JLabel naechsteP = new JLabel("Nächste pruefung: ");
		eigenschaftenPanel.add(naechsteP);
		
		naechstePText= new JTextField("");
		naechstePText.setEnabled(false);
		eigenschaftenPanel.add(naechstePText);
		
		ActionListener erstellenButtonAL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (!nameField.getText().isBlank()) {
            		String schutzklasse = (String) geraeteTypenBox.getSelectedItem();
                	if (geraeteTypen[0].equals(schutzklasse)) {
                		mainPanel.addGeraet(new Geraet_SK1(nameField.getText(), true, (int) daysField.getValue()));
                	} else if (geraeteTypen[1].equals(schutzklasse)) {
                		mainPanel.addGeraet(new Geraet_SK2(nameField.getText(), true, (int) daysField.getValue()));
                	} else if (geraeteTypen[2].equals(schutzklasse)) {
                		mainPanel.addGeraet(new Geraet_SK3(nameField.getText(), true, (int) daysField.getValue()));
                	}
                	scrollPanePanel.updateListModel();
            	}
            	else 
                {
                	JOptionPane.showMessageDialog(null, "Bitte Gerätenamen eingeben.");
                }
            }
        };
        erstellenButton.addActionListener(erstellenButtonAL);
        abbrechenButton.addActionListener(geraetAnlegenAL);
		
		erstellenPanel.add(geratErstellenPanel, BorderLayout.CENTER);		
		this.add(erstellenPanel, BorderLayout.CENTER);
	}
	public void aktualisiereGeraetEigenschaften() {
		if (aktuellesGeraet != null) {
			nameField.setText(aktuellesGeraet.getName());
			nameField.setEnabled(false);
			daysField.setValue(aktuellesGeraet.getTageBisAbgelaufen());
			daysField.setEnabled(false);
			if (aktuellesGeraet instanceof Geraet_SK1) {
				geraeteTypenBox.setSelectedIndex(0);
			}
			else if (aktuellesGeraet instanceof Geraet_SK2) {
				geraeteTypenBox.setSelectedIndex(1);			
			}
			else if (aktuellesGeraet instanceof Geraet_SK3) {
				geraeteTypenBox.setSelectedIndex(2);
			}
			geraeteTypenBox.setEnabled(false);
			submitExitPanel.setVisible(false);
			erstellenButton.setEnabled(false);
			abbrechenButton.setEnabled(false);
			bestandenBox.setSelected(aktuellesGeraet.getPruefungBestanden());
			grundText.setText(aktuellesGeraet.getGrund());
			naechstePText.setText(aktuellesGeraet.getNaechstepruefung().format(DateTimeFormatter.ofPattern("dd-MMM-yy")));
			System.out.println(aktuellesGeraet.getNaechstepruefung().toString());
			eigenschaftenPanel.setVisible(true);
		}
	}
}