package GraphicalUserInterface;

import java.awt.*;
import java.util.List;

import javax.swing.*;

public class ScrollPanePanel extends JPanel {
	private MainPanel mainPanel;
	private JPanel parentPanel;
	private DefaultListModel<Geraet> listModel;
	private JList<Geraet> geraetJList;
	
	public ScrollPanePanel(MainPanel mainPanel, JPanel parentPanel) {
		this.mainPanel = mainPanel;
		this.parentPanel = parentPanel;
		createScrollPane();
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
        
        parentPanel.add(scrollPanePanel, BorderLayout.WEST);
        
        //SelectionListener der ScrollPane um das ausgewählte Gerät zu aktuallisieren bei Auswahländerung
        geraetJList.addListSelectionListener(e -> 
        {
            if (!e.getValueIsAdjusting()) 
            {
            	if (parentPanel instanceof PrueferPanel) {
					((PrueferPanel) parentPanel).aktuellesGeraet = geraetJList.getSelectedValue();
					((PrueferPanel) parentPanel).aktualisierePanel();
				}
				else if (parentPanel instanceof VerwalterPanel) {
					((VerwalterPanel) parentPanel).aktuellesGeraet = geraetJList.getSelectedValue();
					((VerwalterPanel) parentPanel).aktualisiereGeraetEigenschaften();
				}
            }
        }
        );
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
	public void clearSelection() {
		geraetJList.clearSelection();
	}
}
