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
				
		//Menü-Leiste erstellen
		createScrollPane();
						
		//Button-Leiste erstellen
		createButtonBar();
	}
	
	//Erstellen einer Scrollbaren Anzeige für alle angelegten Geräte
	private void createScrollPane() 
	{
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

        this.add(scrollPane, BorderLayout.CENTER);
    }
	
	//Erstellen der ButtonBar zum Auswählen der gewünschten Aktion
	private void createButtonBar() 
	{
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        
        //Buttons erstellen
        JButton einsehenButton = new JButton("Geraet einsehen");
        JButton pruefungDokButton = new JButton("Pruefung dokumentieren");
        
        
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
}
