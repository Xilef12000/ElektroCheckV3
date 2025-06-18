package GraphicalUserInterface;

import java.time.*;
import java.util.*;

public abstract class Geraet {
	private String name;
	private Boolean pruefungBestanden;
	private LocalDateTime naechstepruefung;
	private int tageBisAbgelaufen;
	protected EnumSet<Grund> grundeErlaubt;
	private Grund grund;
	
	
	public Geraet(String name, Boolean pruefungBestanden, int tageBisAbgelaufen) 
	{
		this.name = name;
		this.pruefungBestanden = pruefungBestanden;
		this.tageBisAbgelaufen = tageBisAbgelaufen;
		this.naechstepruefung = LocalDateTime.now().plusDays(tageBisAbgelaufen);
		//this.grund = null;
	}
	
	public String getName() {
		return name;
	}
	public String toString() {
		return name;
	}
	public Boolean getPruefungBestanden() {
		return pruefungBestanden;
	}
	public LocalDateTime getNaechstepruefung() {
		return naechstepruefung;
	}
	public void print() {
		System.out.println(String.format("name: %s", name));
		System.out.println(String.format("pruefungBestanden: %b", pruefungBestanden));
		if (pruefungBestanden) {
			System.out.println(String.format("naechstepruefung: %s", naechstepruefung.toString()));
		}
		else {
			System.out.println(String.format("grund: %s", grund.toString()));
		}
	}
	public void setPruefungBestanden() {
		pruefungBestanden = true;
		naechstepruefung = LocalDateTime.now().plusDays(tageBisAbgelaufen);
		this.grund = null;
	}
	public void setPruefungNichtBestanden(int grundi) {
		pruefungBestanden = false;
		naechstepruefung = LocalDateTime.now();
		this.grund = new ArrayList<>(grundeErlaubt).get(grundi);
	}
	public List<String> getGruende(){
		List<String> StrGruende = new ArrayList<>();
		for (Iterator<Grund> iter = grundeErlaubt.iterator(); iter.hasNext(); ) {
		    Grund element = iter.next();
		    StrGruende.add(element.toString());
		}
		return StrGruende;
	}
}
