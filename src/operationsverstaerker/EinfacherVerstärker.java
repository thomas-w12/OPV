package operationsverstaerker;

import widerstand.Widerstand;
import exceptions.*;

/**
 * Einfacher Verstärker, Basisklasse für Invertierer und Nichtinvertierer
 * 
 * @author Simon Prießnitz
 */
public abstract class EinfacherVerstärker extends Operationsverstärker {

	private Widerstand R_e;
	private double U_e;

	/**
	 * Abstract Methode zur Berechnung der Verstärkung
	 * 
	 * @return die Verstärkung des Verstärkers
	 */
	abstract double berechneVerstärkung();

	/**
	 * Die Eingangsspannung des Verstärkers wird abgefragt
	 * 
	 * @return die Eingangsspannung des Verstärkers
	 */
	public double getU_e() {
		return U_e;
	}

	/**
	 * Setzt die Eigangsspannung
	 * 
	 * @param ue Einangsspannung
	 */
	public void setU_e(double ue) {
		U_e = ue;
	}

	/**
	 * Der Eingangswiderstand des Verstärkers wird abgefragt
	 * 
	 * @return der Eingangswiderstand des Verstärkers
	 */
	public Widerstand getR_e() {
		return R_e;
	}

	/**
	 * 
	 * @param re Eingangswiderstand(muss > 0 sein)
	 */
	public void setR_e(Widerstand re) throws FalseInputException{
		if (re.getWiderstandswert() > 0) {
			R_e = re;
		} else {
			throw new FalseInputException("R_e muss größer 0 sein");
		}
	}

	/**
	 * Basiskonstruktor zum erstellen eines einfachen Verstärkers durch den Nutzer
	 * 
	 * @param rk Kopplungswiderstandswert
	 * @param re Eingangswiderstandwert
	 * @param ue Eingangsspannung
	 * @throws FalseInputException Fehler, wenn Widerstand kleiner 0 ist
	 */
	public EinfacherVerstärker(double rk, double re, double ue) throws FalseInputException {
		super(rk);
		U_e = ue;
		R_e = new Widerstand(re, getEReihe());
	}

	/**
	 * Basiskonstruktor zum Erstellen von einfachen Verstärkern mit vorgegebenen Attributen
	 * (R_k=10000 R_e=5000 U_e=10)
	 * @throws FalseInputException Fehler, wenn Widerstand kleiner 0 ist
	 */
	public EinfacherVerstärker() throws FalseInputException {
		super(10000);
		U_e = 10;
		R_e = new Widerstand(5000, getEReihe());
	}
}
