package GraphicalUserInterface;

import java.util.EnumSet;

public class Geraet_SK1 extends Geraet 
{
	public Geraet_SK1(String name, Boolean pruefungBestanden, int tageBisAbgelaufen) 
	{
		super(name, pruefungBestanden, tageBisAbgelaufen);
		this.grundeErlaubt = EnumSet.of(Grund.values()[0], Grund.values()[1], Grund.values()[2], Grund.values()[3], Grund.values()[4], Grund.values()[5], Grund.values()[6]);
	}
}
