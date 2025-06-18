package GraphicalUserInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class PrueferPanel extends JPanel
{
	private MainPanel mainPanel;
	private JList<Geraet> geraetJList;

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
		
        //Liste der Geräte aus MainPanel holen
        List<Geraet> geraete = mainPanel.getGeraeteListe();

        // JList erstellen mit DefaultListModel
        DefaultListModel<Geraet> listModel = new DefaultListModel<>();
        for (Geraet g : geraete) 
        {
            listModel.addElement(g);
        }

        geraetJList = new JList<>(listModel);
        geraetJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        geraetJList.setVisibleRowCount(10);

        JScrollPane scrollPane = new JScrollPane(geraetJList);
        scrollPane.setPreferredSize(new Dimension(300, 200));
        
        JLabel gereateUebersicht = new JLabel("Geräte-Übersicht");
        gereateUebersicht.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        
        scrollPanePanel.add(gereateUebersicht, BorderLayout.NORTH);
        scrollPanePanel.add(scrollPane, BorderLayout.CENTER);
        this.add(scrollPanePanel, BorderLayout.WEST);
    }
	
	//Erstellen der ButtonBar zum Auswählen der gewünschten Aktion
	private void createButtonBar() 
	{
        JPanel funktionsPanel = new JPanel();
        funktionsPanel.setLayout(new BorderLayout());
        funktionsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(15, 1));
        
        //Buttons erstellen
        JButton einsehenButton = new JButton("Geraet einsehen");
        JButton pruefungDokButton = new JButton("Pruefung dokumentieren");
        JButton programmBeenden = new JButton("Programm beenden");
        
        JLabel funktionen = new JLabel("Funktionen");
        funktionen.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        
        //Buttons in Panel einfügen
        buttonPanel.add(pruefungDokButton);
        buttonPanel.add(einsehenButton);
        buttonPanel.add(programmBeenden);
        
        funktionsPanel.add(funktionen, BorderLayout.NORTH);
        funktionsPanel.add(buttonPanel, BorderLayout.CENTER);
        
        this.add(funktionsPanel, BorderLayout.EAST);
        
        
        //Erstellen der ActionListener für die beiden Button
        ActionListener einsehenAL = new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                Geraet selected = geraetJList.getSelectedValue();
                if (selected != null) 
                {
                   
                } 
                else 
                {
                	
                }

            }
        };

        einsehenButton.addActionListener(einsehenAL);
        
        
        ActionListener pruefungDokAL = new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                Geraet selected = geraetJList.getSelectedValue();
                if (selected != null) 
                {
                   
                } 
                else 
                {

                }

            }
        };

        pruefungDokButton.addActionListener(pruefungDokAL);         
	}
	
	//Panel zur Prüfungsdokumentation einzelner Geräte
	void createPruefungDokPanel()
	{
		JPanel pruefungsPanel = new JPanel();
		pruefungsPanel.setLayout(new BorderLayout());
		pruefungsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		JLabel dokuUebersicht = new JLabel("Dokumentationsübersicht");
		dokuUebersicht.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		
		pruefungsPanel.add(dokuUebersicht, BorderLayout.NORTH);
		
		this.add(pruefungsPanel, BorderLayout.CENTER);
	}
}
