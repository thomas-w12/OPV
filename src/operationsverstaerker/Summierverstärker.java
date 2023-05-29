package operationsverstaerker;

import widerstand.Widerstand;

/**
 * Klasse für Summierverstärker
 * 
 * @author Marin Juric, Clarice Wichert
 */
public class Summierverstärker extends Operationsverstärker {

	private Widerstand[] R_e;
	private double[] U_e;

	public Widerstand[] getR_e() {
		return this.R_e;
	}

	public void setR_e(Widerstand[] R_e) {
		this.R_e = R_e;
	}

	public double[] getU_e() {
		return this.U_e;
	}

	public void setU_e(double[] U_e) {

		this.U_e = U_e;

	}

	/**
	 * @return Ausgangsspannung
	 */
	public double berechneU_a() {
		double U_a = 0;
		for (int i = 0; i < U_e.length; i++) {
			U_a += (getR_k().getWiderstandswert() / getR_e()[i].getWiderstandswert()) * U_e[i];
		}
		return -U_a;
	}

	/**
	 * Erzeugt einen Summierverstärker mit beliebig vielen Eingangsspannungen
	 * 
	 * @param rk Kopplungswiderstandswert
	 * @param re Array der Eingangswiderstandswerte
	 * @param ue Array der Eingangsspannungen
	 */
	public Summierverstärker(double rk, double[] re, double[] ue) {
		super(rk);
		int re_länge = re.length;
		int ue_länge = ue.length;
		if (re_länge != ue_länge) {
			System.out.println("konnte nicht erzeugt werden, Anzahl Widerstände/Spannungen stimmt nicht überein");
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
	 * @param rk  Kopplungswiderstandswert
	 * @param re1 Wert des ersten Eingsngswiderstands
	 * @param re2 Wert des zweiten Eingsngswiderstands
	 * @param ue1 erste Eingangsspannung
	 * @param ue2 zweite Eingangsspannung
	 */
	public Summierverstärker(double rk, double re1, double re2, double ue1, double ue2) {
		super(rk);
		R_e = new Widerstand[2];
		U_e = new double[2];
		R_e[0] = new Widerstand(re1, getEReihe());
		R_e[1] = new Widerstand(re2, getEReihe());
		U_e[0] = ue1;
		U_e[1] = ue2;
	}
}
