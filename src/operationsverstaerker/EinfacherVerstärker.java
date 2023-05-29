package operationsverstaerker;

import widerstand.Widerstand;

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
	public void setR_e(Widerstand re) {
		if (re.getWiderstandswert() > 0) {
			R_e = re;
		} else {
			System.out.println("R_e muss größer 0 sein");
		}
	}

	public EinfacherVerstärker(double rk, double re, double ue) {
		super(rk);
		U_e = ue;
		R_e = new Widerstand(re, getEReihe());
	}

	public EinfacherVerstärker() {

	}
}
