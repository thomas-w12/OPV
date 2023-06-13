package operationsverstaerker;

import exceptions.FalseInputException;
import widerstand.EReihe;
import widerstand.Widerstand;

/**
 * Basisklasse für alle Operationsverstärker
 * @author Marin Juric, Clarice Wichert
 */
public abstract class Operationsverstärker {

	private Widerstand R_k;

	private static EReihe ereihe;

	public abstract double berechneU_a();
 

	/**
	 * Methode zum Erhalt von R_k
	 * @return R_k
	 */
	public Widerstand getR_k() {
		return this.R_k;
	}

	/**
	 * Methode zum Setzen von R_k
	 * @param R_k Widerstand R_k
	 */
	public void setR_k(Widerstand R_k) throws FalseInputException {
		if (R_k.getWiderstandswert() > 0) {
			this.R_k = R_k;
		} else {
			throw new FalseInputException("Widerstand kleiner 0");
		}
	}

	/**
	 * Methode zum Setzen der E-Reihe. 
	 * Diese wird vom gesamten Programm als globale Variable verwendet.
	 * @param ereihe E-Reihe
	 */
	public static void setEReihe(EReihe ereihe) {
		Operationsverstärker.ereihe = ereihe;
	}

	/**
	 * Methode zum Erhalt der aktuellen E-Reihe
	 * @return E-Reihe
	 */
	public static EReihe getEReihe() {
		return ereihe;
	}


	/**
	 * Konstruktor für die abstrakte Klasse Operationsverstärker
	 * @param rk Kopplungswiderstand
	 * @throws FalseInputException Fehler, wenn Widerstand kleiner gleich 0 ist
	 */
	public Operationsverstärker(double rk) throws FalseInputException {
		this.R_k = new Widerstand(rk, ereihe);
	}

	/**
	 * Standardkonstruktor des Operationsverstärkers, setzt R_k auf 10000
	 */
	public Operationsverstärker() {
		try {
			this.R_k = new Widerstand(10000, ereihe);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
