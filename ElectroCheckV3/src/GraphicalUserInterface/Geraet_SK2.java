package GraphicalUserInterface;

import java.util.EnumSet;

public class Geraet_SK2 extends Geraet 
{
	// Konstruktor für SK2
	// Kommentare siehe SK1
	public Geraet_SK2(String name, Boolean pruefungBestanden, int tageBisAbgelaufen) 
	{
		super(name, pruefungBestanden, tageBisAbgelaufen, "II");
		this.grundeErlaubt = EnumSet.of(Grund.values()[0], Grund.values()[2], Grund.values()[4], Grund.values()[5], Grund.values()[6]);
	}
}
