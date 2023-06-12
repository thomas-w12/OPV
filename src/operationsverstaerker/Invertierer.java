package operationsverstaerker;

import exceptions.FalseInputException;

/**
 * Klasse für Invertierer
 * 
 * @author Simon Prießnitz
 */
public class Invertierer extends EinfacherVerstärker {

	/**
	 * Berechnet aus den Attributen des Invertierers die Ausgangsspannung
	 * 
	 * @return die Ausgangsspannung des Invertierers nach der Formel: -(R_k/R_e)*U_e
	 */
	public double berechneU_a() {
		double U_a = -(getR_k().getWiderstandswert()/getR_e().getWiderstandswert())* getU_e();
		return U_a;
	}

	/**
	 * Berechnet aus den Attributen des Nichtinvertierers die Verstärkung
	 * 
	 * @return die Verstärkung des Nichtinvertierers nach der Formel: -(R_k/R_e)
	 */
	public double berechneVerstärkung() {
		double verstärkung = -(getR_k().getWiderstandswert() / getR_e().getWiderstandswert());
		return verstärkung;
	}

	/**
	 * Konstruktor zum erstellen von Invertierern durch den Nutzer
	 * 
	 * @param rk Kopplungswiderstandswert
	 * @param re Eingangswiderstandwert
	 * @param ue Eingangsspannung
	 * @throws FalseInputException Fehler, wenn Widerstand kleiner 0 ist
	 */
	public Invertierer(double rk, double re, double ue) throws FalseInputException {
		super(rk, re, ue);
	}

	/**
	 * Erstellt ein Objekt der Klasse Invertierer mit vorgegebenen Attributen
	 * (R_k=10000 R_e=5000 U_e=10)
	 * @throws FalseInputException Fehler, wenn Widerstand kleiner 0 ist
	 */
	public Invertierer() throws FalseInputException {
		super();
	}

}
