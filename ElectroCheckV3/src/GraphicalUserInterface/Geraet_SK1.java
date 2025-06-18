package GraphicalUserInterface;

import java.util.EnumSet;

public class Geraet_SK1 extends Geraet 
{
	// Konstruktor für SK1
	public Geraet_SK1(String name, Boolean pruefungBestanden, int tageBisAbgelaufen) 
	{
		// Constuctor der abstact superclass aufrufen
		super(name, pruefungBestanden, tageBisAbgelaufen);
		
		// zuweisen von 'grundeErlaubt'
		// Elemente von 'grundeErlaubt' sind vom Datentyp/Klasse 'Grund'
		// Grund.values()[x] ist der x te Grund vom Enum 'Grund'
		this.grundeErlaubt = EnumSet.of(Grund.values()[0], Grund.values()[1], Grund.values()[2], Grund.values()[3], Grund.values()[4], Grund.values()[5], Grund.values()[6]);
	}
}
