package operationsverstaerker;

import widerstand.Widerstand;

/**
 * Klasse für Subtrahierverstärker
 * 
 * @author Marin Juric, Clarice Wichert
 */
public class Subtrahierverstärker extends Operationsverstärker {

	private Widerstand[] R_e;
	private double[] U_e;
	private Widerstand R_q;

	public Widerstand[] getR_e() {
		return this.R_e;
	}

	public void setR_e(Widerstand[] R_e) {
		if (R_e.length != 2) {
			System.out.println("Anzahl Eingangswiderstände muss 2 betragen");
		} else {
			this.R_e = R_e;
		}
	}

	public Widerstand getR_q() {
		return this.R_q;
	}

	public void setR_q(Widerstand R_q) {
		this.R_q = R_q;
	}

	public double[] getU_e() {
		return this.U_e;
	}

	public void setU_e(double[] U_e) {
		if (U_e.length != 2) {
			System.out.println("Anzahl Engangsspannungen muss 2 betragen");
		} else {
			this.U_e = U_e;
		}
	}

	public double berechneU_a() {
		double re1 = getR_e()[0].getWiderstandswert();
		double re2 = getR_e()[1].getWiderstandswert();
		double rk = getR_k().getWiderstandswert();
		double rq = getR_q().getWiderstandswert();
		double ue1 = getU_e()[0];
		double ue2 = getU_e()[1];
		double U_a = (rq * (re1 + rk)) / (re1 * (re2 + rq)) * ue2 - (rk / re1) * ue1;
		return U_a;
	}

	/**
	 * Getter-und Setter Methoden für Rq, Re und Ue ermöglichen den Zugriff auf
	 * deren Werte
	 * 
	 * @param rk
	 * @param rq
	 * @param re
	 * @param ue
	 */
	public Subtrahierverstärker(double rk, double rq, double[] re, double[] ue) {
		super(rk);
		R_e = new Widerstand[2];
		U_e = new double[2];
		if (ue.length != 2 || re.length != 2) {
			System.out.println("Anzahl Engangsspannungen und Eingangswiderstände muss 2 betragen");
		} else {
			R_e[0] = new Widerstand(re[0], getEReihe());
			R_e[1] = new Widerstand(re[1], getEReihe());
			U_e[0] = ue[0];
			U_e[1] = ue[1];
			R_q = new Widerstand(rq, getEReihe());

		}
	}

	/**
	 * Gibt eine Fehlermeldung bei zu vielen Eingangsspannungen bzw.
	 * Eingangswiderständen aus
	 * Falls die Werte im Grenzbereich sind, werden die Werte übermittelt
	 * 
	 * @param rk
	 * @param rq
	 * @param re1
	 * @param re2
	 * @param ue1
	 * @param ue2
	 */
	public Subtrahierverstärker(double rk, double rq, double re1, double re2, double ue1, double ue2) {
		super(rk);
		R_e = new Widerstand[2];
		U_e = new double[2];
		R_e[0] = new Widerstand(re1, getEReihe());
		R_e[1] = new Widerstand(re2, getEReihe());
		U_e[0] = ue1;
		U_e[1] = ue2;
		R_q = new Widerstand(rq, getEReihe());
		/* Definierung von Re1,2 und Ue1,2 */
	}
}
