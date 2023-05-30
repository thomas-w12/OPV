package widerstand;

import java.awt.*;
import java.util.HashMap;
import exceptions.*;

/**
 * Diese Klasse bildet einen Widerstand ab.
 * Der Widerstand wird als eine Kombination aus ganzzahligen Werten und
 * ganzzahligen Potenzen gespeichert, um
 * Rundungsfehler durch Gleitkommazahlen zu vermeiden.
 * Dennoch ist die Erstellung von Widerständen aus Gleitkommazahlen möglich.
 * 
 * @author Tobias Fritz, Thomas Wegele
 */
public class Widerstand {

	private static final HashMap<Integer, java.awt.Color> werteFarbcodes = new HashMap<Integer, java.awt.Color>() {
		{
			put(0, Color.black);
			put(1, new Color(105, 65, 34)); // Braun
			put(2, Color.red);
			put(3, Color.orange);
			put(4, Color.yellow);
			put(5, Color.green);
			put(6, Color.blue);
			put(7, new Color(139, 57, 178)); // Lila
			put(8, Color.gray);
			put(9, Color.white);
		}
	};

	private static final HashMap<Integer, java.awt.Color> potenzFarbcodes = new HashMap<Integer, java.awt.Color>() {
		{
			put(-2, new Color(221, 221, 221)); // Silber
			put(-1, new Color(255, 238, 145)); // Gold
			put(0, Color.black);
			put(1, new Color(105, 65, 34)); // Braun
			put(2, Color.red);
			put(3, Color.orange);
			put(4, Color.yellow);
			put(5, Color.green);
			put(6, Color.blue);
			put(7, new Color(139, 57, 178)); // Lila
		}
	};

	private int wert;

	private int potenz;

	private java.awt.Color farbringe[];

	private EReihe eReihe;

	/**
	 * Methode zur Rückgabe des Wertes
	 * 
	 * @return Wert
	 */
	public int getWert() {
		return this.wert;
	}

	/**
	 * Methode zum Setzen des Widerstandswertes, darf maximal 3 Stellen haben,
	 * darüber hinaus nur mit Potenz - NICHT FÜR EXTERNE VERWENDUNG
	 * 
	 * @param wert Widerstandswert
	 */
	public void setWert(int wert) throws FalseInputException{
		if (wert < 1000 && wert > 0) {
			this.wert = wert;
		} else {
			throw new FalseInputException("Wert darf maximal 3 Stellen haben!");
		}
	}

	/**
	 * Methode zur RÜckgabe der Potenz
	 * 
	 * @return Potenz
	 */
	public int getPotenz() {
		return this.potenz;
	}

	/**
	 * Methode zum Setzen der Potenz
	 * 
	 * @param potenz Potenz
	 */
	public void setPotenz(int potenz) {
		this.potenz = potenz;
	}

	/**
	 * Methode zur Rückgabe der Farbringe des Widerstandes
	 * 
	 * @return Farbringe
	 */
	public java.awt.Color[] getFarbringe() {
		return this.farbringe;
	}

	/**
	 * Methode zum Setzen der Fabringe - nur zu Testzwecken, überschreibt normales
	 * Verhalten
	 * 
	 * @param fabringe Farbringe
	 */
	public void setFarbringe(java.awt.Color[] fabringe) {
		this.farbringe = fabringe;
	}

	/**
	 * Methode zur Rückgabe der E-Reihe des Widerstandes
	 * 
	 * @return E-Reihe
	 */
	public EReihe getEReihe() {
		return this.eReihe;
	}

	/**
	 * Methode zum Setzen der E-Reihe
	 * 
	 * @param eReihe E-Reihe
	 */
	public void setEReihe(EReihe eReihe) {
		this.eReihe = eReihe;
	}

	/**
	 * Methode zur Rückgabe des Widerstandwertes
	 * 
	 * @return Widerstandswert
	 */
	public double getWiderstandswert() {
		return this.wert * Math.pow(10, this.potenz);
	}

	/**
	 * Methode zum Setzen des Widerstandswertes, nicht zur externen Verwendung
	 * 
	 * @param widerstandswert Widerstandswert als Double
	 */
	private void setWiderstandswert(double widerstandswert) {
		double temp;
		int tempPotenz = 0;
		if (widerstandswert <= 0) {
			temp = 1;
		} else {
			temp = widerstandswert;
		}

		while (temp >= 1000) {
			temp = Math.round(temp / 10);
			tempPotenz++;
		}
		while (tempPotenz > -2 && temp < 100) {
			temp *= 10;
			tempPotenz--;
		}
		this.wert = (int) Math.round(temp);
		this.potenz = tempPotenz;
	}

	/**
	 * Diese Methode weist dem Widerstand die passenden Farbringe zu
	 * 
	 * @return Array der Farbringe
	 */
	public java.awt.Color[] farbringeFinden() {
		// Array der Werte der Farbringe
		int farbringeWerte[] = new int[4];
		for (int i = 0; i < farbringeWerte.length - 1; i++) {
			farbringeWerte[i] = this.wert % (int) (Math.pow(10, farbringeWerte.length - 1 - i))
					/ (int) (Math.pow(10, (farbringeWerte.length - 2 - i)));
		}
		farbringeWerte[3] = potenz;
		// Array der Farbringe
		java.awt.Color farbringe[] = new Color[farbringeWerte.length];
		for (int i = 0; i < farbringeWerte.length - 1; i++) {
			farbringe[i] = werteFarbcodes.get(farbringeWerte[i]); // Farbringe den Werten zuweisen
		}
		farbringe[farbringeWerte.length - 1] = potenzFarbcodes.get(farbringeWerte[farbringeWerte.length - 1]); // Potenz
		return farbringe;
	}

	// Konstruktoren

	/**
	 * Konstruktor zum Erstellen eines Widerstandes ohne E-Reihe
	 * 
	 * @param widerstandswert Widerstandswert als Double
	 */
	public Widerstand(double widerstandswert) {
		this.setWiderstandswert(widerstandswert);
		this.farbringe = null;
		this.eReihe = null;
	}

	/**
	 * Konstruktor zum Erstellen eines Widerstandes aus einer E-Reihe
	 * 
	 * @param widerstandswert Widerstandswert als Double
	 * @param eReihe          E-Reihe
	 */
	public Widerstand(double widerstandswert, EReihe eReihe) {
		this.eReihe = eReihe;
		if (this.eReihe == null || this.eReihe.getEReihenWerte().length == 0) {
			this.setWiderstandswert(widerstandswert);
			this.farbringe = null;
		} else {
			double widerstandsWertEReihe = eReihe.widerstandWaehlen(widerstandswert);
			this.setWiderstandswert(widerstandsWertEReihe);
			this.farbringe = this.farbringeFinden();
		}

	}

}
