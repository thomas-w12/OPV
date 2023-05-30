package operationsverstaerker;

/**
 * Klasse für Nichtinvertierer
 * 
 * @author Simon Prießnitz
 */
public class NichtInvertierer extends EinfacherVerstärker {

	/**
	 * Berechnet aus den Attributen des Nichtinvertierers die Ausgangsspannung
	 * 
	 * @return die Ausgangsspannung des Nichtinvertierers nach der Formel:
	 *         (1+R_k/R_e)*U_e
	 */
	public double berechneU_a() {
		double U_a = (1 + getR_k().getWiderstandswert() / getR_e().getWiderstandswert()) * getU_e();
		return U_a;
	}

	/**
	 * Berechnet aus den Attributen des Nichtinvertierers die Verstärkung
	 * 
	 * @return die Verstärkung des Nichtinvertierers nach der Formel: 1+R_k/R_e
	 */
	public double berechneVerstärkung() {
		double Verstärkung = 1 + getR_k().getWiderstandswert() / getR_e().getWiderstandswert();
		return Verstärkung;
	}

	/**
	 * Konstruktor zum erstellen von NichtInvertierern durch den Nutzer
	 * 
	 * @param rk Kopplungswiderstand
	 * @param re Eingangswiderstand
	 * @param ue Eingangsspannung
	 */
	public NichtInvertierer(double rk, double re, double ue) {
		super(rk, re, ue);
	}

	/**
	 * Erstellt ein Objekt der Klasse NichtInvertierer mit vorgegebenen Attributen
	 * (R_k=10000 R_e=5000 U_e=10)
	 */
	public NichtInvertierer() {
		super(10000, 5000, 10);
	}

}
