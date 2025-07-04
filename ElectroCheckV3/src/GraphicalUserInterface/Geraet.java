package GraphicalUserInterface;

import java.time.*;
import java.util.*;
import java.io.*;

public abstract class Geraet implements Serializable{
	private static final long serialVersionUID = 1L;
	private String name;
	private Boolean pruefungBestanden;
	private LocalDateTime naechstepruefung;
	private int tageBisAbgelaufen;
	private String schutzklasse;
	private String notizen;
	
	// 'grund' vom Typ 'Grund' (Enum)
	// kann alle möglichen Gründe annehmen, auch die die nicht zur SK passen
	private Grund grund;
	
	// Enumset 'grundeErlaubt' wie eine Liste (aber keine Liste)
	// in der alle Elemente 'Grund' entghalten sind die erlaubt werden sollen
	// wird im Konstuktor der SK gesetzt
	protected EnumSet<Grund> grundeErlaubt;
	
	
	// Konstruktor
	public Geraet(String name, Boolean pruefungBestanden, int tageBisAbgelaufen, String schutzklasse) 
	{
		this.name = name;
		this.pruefungBestanden = pruefungBestanden;
		this.tageBisAbgelaufen = tageBisAbgelaufen;
		// setze 'naechstepruefung' auf Aktuelles Datum + 'tageBisAbgelaufen' Tage
		this.naechstepruefung = LocalDateTime.now().plusDays(tageBisAbgelaufen);
		this.grund = null;
		this.schutzklasse = schutzklasse;
	}
	
	public String getName() {
		return name;
	}
	public int getTageBisAbgelaufen() {
		return tageBisAbgelaufen;
	}
	public void setNotizen(String notizen) {
		this.notizen = notizen;
	}
	public String getNotizen() {
		if (notizen != null && !notizen.isBlank()) {
			return notizen;
		}
		return "";
	}
	public String getGrund() {
		if (grund != null) {
			return grund.toString();
		}
		return "";
	}
	
	
	public String toString() {
		String ret = name + " - SK" + schutzklasse;
		if (pruefungBestanden) {
			if (LocalDateTime.now().isAfter(naechstepruefung)) {
				ret += " - ⚠";
			}
			else {
				ret += " - ✔";
			}
		}
		else {
			ret += " - ✘";
		}
		return ret;
	}
	
	public String getSchutzklasse() {
		return schutzklasse;
	}
	
	public Boolean getPruefungBestanden() {
		return pruefungBestanden;
	}
	
	public LocalDateTime getNaechstepruefung() {
		return naechstepruefung;
	}
	
	public void print() {
		System.out.println("Geraeteinformationen:");
		System.out.println("Geraetename: " + name);
		System.out.println("Pruefung Bestanden: " + pruefungBestanden);
		if(!pruefungBestanden) {
			System.out.println("Grund: " + getGrund());
		}
		System.out.println("Notizen: " + getNotizen());
		System.out.println("Gueltigkeit Pruefung: " + tageBisAbgelaufen);
		System.out.println("Naechste Pruefung" + naechstepruefung);
	}
	
	// Geräteinformationen in Textdatei exportieren.
	public void drucken(PrintWriter printWriterinDatei) {
		printWriterinDatei.println("Geraeteinformationen:");
		printWriterinDatei.println("Geraetename: " + name);
		printWriterinDatei.println("Pruefung Bestanden: " + pruefungBestanden);
		if(!pruefungBestanden) {
			printWriterinDatei.println("Grund: " + getGrund());
		}
		printWriterinDatei.println("Notizen: " + getNotizen());
		printWriterinDatei.println("Gueltigkeit Pruefung: " + tageBisAbgelaufen);
		printWriterinDatei.println("Naechste Pruefung" + naechstepruefung);
	}
	
	public void setPruefungBestanden() {
		// wenn Prüfung bestanden ist:
		//	- setze pruefungBestanden
		//	- setze 'naechstepruefung' auf Aktuelles Datum + 'tageBisAbgelaufen' Tage
		pruefungBestanden = true;
		naechstepruefung = LocalDateTime.now().plusDays(tageBisAbgelaufen);
		grund = null;
	}
	
	public void setPruefungNichtBestanden(int grundi) {
		// wenn Prüfung bestanden ist:
		//	- setze pruefungBestanden auf false
		//	- setze grund
		// Parameter: Index von Grund in 'grundeErlaubt' EnumSet/Liste
		pruefungBestanden = false;
		// konvertiere EnumSet zu neuer Liste
		// finde element i/'grundi'
		// setze 'grund' auf dieses Element
		// Element von Datentyp/Klasse 'Grund'(Enum)
		this.grund = new ArrayList<>(grundeErlaubt).get(grundi);
	}
	
	public List<String> getGruende(){
		// gibt String Liste aller Möglichen Gründe dieses Gerätes/dieser Geräteklasse zurück
		// Reihenfolge entspricht 'grundeErlaubt' (wie im Konstruktor der SKs)
		
		// Leere String Liste 'StrGruende' erstellen
		List<String> StrGruende = new ArrayList<>();
		
		// für jedes Element 'element' vom Datentyp/Klasse 'Grund' in 'grundeErlaubt'
		for (Iterator<Grund> iter = grundeErlaubt.iterator(); iter.hasNext(); ) {
		    Grund element = iter.next();
		    
		    // füre 'element' als String zu String Liste 'StrGruende' hinzu
		    StrGruende.add(element.toString());
		}
		return StrGruende;
	}
}
