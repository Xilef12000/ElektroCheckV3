package GraphicalUserInterface;

import java.util.EnumSet;

public class Geraet_SK3 extends Geraet 
{
	public Geraet_SK3(String name, Boolean pruefungBestanden, int tageBisAbgelaufen) 
	{
		super(name, pruefungBestanden, tageBisAbgelaufen);
		this.grundeErlaubt = EnumSet.of(Grund.values()[0], Grund.values()[2], Grund.values()[5], Grund.values()[6]);
	}
}
