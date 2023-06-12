package operationsverstaerker;

import widerstand.Widerstand;
import exceptions.*;

/**
 * Klasse für Summierverstärker
 * 
 * @author Marin Juric, Clarice Wichert
 */
public class Summierverstärker extends Operationsverstärker {

	private Widerstand[] R_e;
	private double[] U_e;

	/**
	 * Methode zum Erhalt von R_e
	 * 
	 * @return Eingangswiderstände R_e
	 */
	public Widerstand[] getR_e() {
		return this.R_e;
	}

	/**
	 * Methode zum setzen von R_e
	 * 
	 * @param R_e Eingangswiderstände R_e
	 */
	public void setR_e(Widerstand[] R_e) {
		this.R_e = R_e;
	}

	/**
	 * Methode zum Erhalt von U_e
	 * 
	 * @return Eingangsspannungen U_e
	 */
	public double[] getU_e() {
		return this.U_e;
	}

	/**
	 * Methode zum Setzen von U_e
	 * 
	 * @param U_e Eingangsspannungen U_e
	 */
	public void setU_e(double[] U_e) {
		this.U_e = U_e;
	}

	/**
	 * 
	 * berechneU_a
	 * summiert die Verstärkten Signale der Eingangspannungen
	 * die Verstärkungen setzen sich aus dem Verhältnis von R_k zu Re_1 bzw. Re_2
	 * zusammen
	 * 
	 * @return Ausgangsspannung U_a
	 */
	public double berechneU_a() {
		double U_a = 0;
		for (int i = 0; i < U_e.length; i++) {
			U_a += (getR_k().getWiderstandswert() / getR_e()[i].getWiderstandswert()) * U_e[i];
		}
		return -U_a;
	}

	/**
	 * 
	 * Summierverstärker
	 * überprüft die Anzahl der Eingangswiderstände und Eingangssignale
	 * falls die Anzahl in Ordnung ist, ordnet man den einzelnen Plätzen im Array
	 * Werte zu
	 * 
	 * @param rk Kopplungswiderstandswert (Widerstand zwischen Ausgang und
	 *           invertierenden Eingang)
	 * @param re Array der Eingangswiderstandswerte (Verschiedene Widersände die an
	 *           den Eingängen liegen)
	 * @param ue Array der Eingangsspannungen (Verschiedene Spannungswerte die an
	 *           den jeweiligen Eingängen liegen)
	 * @throws FalseInputException Fehler, wenn Anzahl der Eingangsspannungen und
	 *                             Widerstände nicht übereinstimmt oder Widerstände
	 *                             kleiner gleich 0 sind
	 */
	public Summierverstärker(double rk, double[] re, double[] ue) throws FalseInputException {
		super(rk);
		int re_länge = re.length;
		int ue_länge = ue.length;
		if (re_länge != ue_länge) {
			throw new FalseInputException(
					"konnte nicht erzeugt werden, Anzahl Widerstände/Spannungen stimmt nicht überein");
		} else {
			R_e = new Widerstand[re.length];
			U_e = new double[ue.length];
			for (int i = 0; i < re_länge; i++) {
				R_e[i] = new Widerstand(re[i], getEReihe());
			}
			U_e = ue;
		}
	}

	/**
	 * Erzeugt einen Summiervesstärker mit zwei Eingangsspannungen
	 * 
	 * @param rk  Kopplungswiderstandswert (Widerstand zwischen Ausgang und
	 *            invertierenden Eingang)
	 * @param re1 Wert des ersten Eingsngswiderstands (Widerstand welcher am ersten
	 *            Eingang des OPV`s anliegt)
	 * @param re2 Wert des zweiten Eingsngswiderstands (Widerstand welcher am
	 *            zweiten Eingang des OPV`s anliegt)
	 * @param ue1 erste Eingangsspannung (Spannung welche am ersten Eingang des
	 *            OPV`s anliegt)
	 * @param ue2 zweite Eingangsspannung (Spannung welche am zweiten Eingang des
	 *            OPV`s anliegt)
	 * @throws FalseInputException Fehler, wenn Widerstände kleiner gleich 0 sind
	 */
	public Summierverstärker(double rk, double re1, double re2, double ue1, double ue2) throws FalseInputException {
		super(rk);
		R_e = new Widerstand[2];
		U_e = new double[2];
		R_e[0] = new Widerstand(re1, getEReihe());
		R_e[1] = new Widerstand(re2, getEReihe());
		U_e[0] = ue1;
		U_e[1] = ue2;
	}
}
