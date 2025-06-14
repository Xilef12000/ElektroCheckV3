package GraphicalUserInterface;

import javax.swing.*;
import java.util.*;
import java.util.List;
import java.awt.*;


public class MainPanelTest extends JPanel
{
	List<Geraet> geraeteList = new ArrayList<>();

	public MainPanelTest() 
	{
		//Layout setzten
		this.setLayout(new BorderLayout());
		
		Geraet geraet1 = new Geraet_SK1("test123", true, 365);;
		Geraet geraet2 = new Geraet_SK1("test123", true, 365);;
		Geraet geraet3 = new Geraet_SK1("test123", true, 365);;
		Geraet geraet4 = new Geraet_SK1("test123", true, 365);;
		
		geraeteList.add(geraet1);
		geraeteList.add(geraet2);
		geraeteList.add(geraet3);
		geraeteList.add(geraet4);
	
	}
	
	//Get für GeräteListe
    List<Geraet> getGeraeteListe()
    {
    	return this.geraeteList;
    }

}
