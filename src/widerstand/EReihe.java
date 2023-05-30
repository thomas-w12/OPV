package widerstand;

/**
 * Diese Klasse stellt eine E-Reihe dar.
 * Die Werte der E-Reihe werden in einem Array gespeichert.
 * Bei Erstellung werden diese auf Gültigkeit geprüft und wenn nötig in die
 * passenden Werte umgewandelt.
 * 
 * @author Tobias Fritz, Thomas Wegele
 */
public class EReihe {

	private final static double[] E12Werte = {1.0, 1.2, 1.5, 1.8, 2.2, 2.7, 3.3, 3.9, 4.7, 5.6, 6.8, 8.2};

	private final static double[] E24Werte = {1.0, 1.1, 1.2, 1.3, 1.5, 1.6, 1.8, 2.0, 2.2, 2.4, 2.7, 3.0, 3.3, 3.6, 3.9, 4.3, 4.7, 5.1, 5.6, 6.2, 6.8, 7.5, 8.2, 9.1};
	public final static EReihe E12 = new EReihe(E12Werte);
	public final static EReihe E24 = new EReihe(E24Werte);

	// Werte der E-Reihe: dürfen nur Faktoren zwischen 1 und 10 sein!
	private double[] eReihenWerte;

	/**
	 * Methode zur Rückgabe der Werte der E-Reihe
	 * 
	 * @return Array der Werte der E-Reihe
	 */
	public double[] getEReihenWerte() {
		return this.eReihenWerte;
	}

	/**
	 * Methode zum Setzen der Werte der E-Reihe
	 * Die Werte sollen nur Faktoren >= 1 und < 10 sein. Falls größere Werte
	 * eingegeben werden, werden diese in Faktoren umgewandelt.
	 * 
	 * @param eReihenWerte Werte der E-Reihe
	 */
	public void setEReihenWerte(double[] eReihenWerte) {
		this.eReihenWerte = new double[eReihenWerte.length];
		for (int i = 0; i < eReihenWerte.length; i++) {
			this.eReihenWerte[i] = this.faktorFinden(eReihenWerte[i]);
		}
	}

	/**
	 * Diese Methode liefert den Faktor des eingegebenen Widerstandwertes zurück,
	 * sodass dieser entsprechend einer E-Reihe kategorisiert werden kann.
	 * 
	 * @param widerstandswert Wert des Widerstandes, aus dem der Faktor extrahiert
	 *                        werden soll
	 * @return Faktor des Widerstandes
	 */
	private double faktorFinden(double widerstandswert) {
		if (widerstandswert <= 0) {
			widerstandswert = 1;
		}
		double faktor = widerstandswert;
		int zehnerPotenz = 0;

		if (faktor >= 10) {
			while (faktor >= 10) {
				faktor = faktor / 10;
				zehnerPotenz++;
			}
		} else if (faktor < 1) {
			while (faktor < 1) {
				faktor = faktor * 10;
				zehnerPotenz--;
			}
		}
		return widerstandswert / (Math.pow(10, zehnerPotenz));
	}

	/**
	 * Liefert den passenden Widerstand aus der E-Reihe zurück.
	 * 
	 * @param widerstand: Widerstandswert, zu dem der passende Wert aus der EReihe
	 *                    gesucht werden soll.
	 * @return passender Widerstandswert aus der EReihe
	 */
	public double widerstandWaehlen(double widerstand) {
		if (widerstand <= 0) {
			widerstand = 1;
		}
		double faktor = this.faktorFinden(widerstand);
		double zehnerfaktor = widerstand / faktor;
		int index = 0;
		double differenz = Math.abs(this.eReihenWerte[0] - faktor);

		for (int i = 0; i < this.eReihenWerte.length; i++) {
			if (Math.abs(this.eReihenWerte[i] - faktor) < differenz) {
				differenz = Math.abs(this.eReihenWerte[i] - faktor);
				index = i;
			}
		}

		return this.eReihenWerte[index] * zehnerfaktor;
	}

	/**
	 * Konstruktor der E-Reihe
	 * 
	 * @param eReihenWerte Werte der E-Reihe
	 */
	public EReihe(double[] eReihenWerte) {
		this.setEReihenWerte(eReihenWerte);
	}

	/**
	 * Standardkonstruktor der E-Reihe, E-Reihe enthält keine Werte
	 */
	public EReihe() {
		this.setEReihenWerte(new double[0]);
	}

}
