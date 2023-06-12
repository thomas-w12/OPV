package operationsverstaerker;

import widerstand.Widerstand;
import exceptions.*;

/**
 * Klasse für Subtrahierverstärker
 * 
 * @author Marin Juric, Clarice Wichert
 */
public class Subtrahierverstärker extends Operationsverstärker {

	private Widerstand[] R_e;
	private double[] U_e;
	private Widerstand R_q;

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
	 * @throws FalseInputException Fehler, wenn die Anzahl der Widerstände nicht 2
	 *                             beträgt
	 */
	public void setR_e(Widerstand[] R_e) throws FalseInputException {
		if (R_e.length != 2) {
			throw new FalseInputException("Anzahl Eingangswiderstände muss 2 betragen");
		} else {
			this.R_e = R_e;
		}
	}

	/**
	 * Methode zum Erhalt von R_q
	 * 
	 * @return R_q
	 */
	public Widerstand getR_q() {
		return this.R_q;
	}

	/**
	 * Methode zum Setzen von R_q
	 * 
	 * @param R_q R_q
	 */
	public void setR_q(Widerstand R_q) {
		this.R_q = R_q;
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
	 * @throws FalseInputException Fehler, wenn Anzahl der Eingangsspannung nicht 2
	 *                             ist
	 */
	public void setU_e(double[] U_e) throws FalseInputException {
		if (U_e.length != 2) {
			throw new FalseInputException("Anzahl Engangsspannungen muss 2 betragen");
		} else {
			this.U_e = U_e;
		}
	}

	/**
	 * berechneU_a
	 * um U_a zu berechnen wird folgende Formel verwendet, welche sich aus einer
	 * Subtraktion der verstärkten Eingangssignale zusammensetzt.
	 * Die Verstärkung für die jeweiligen Eingangsignale ergibt sich aus dem
	 * Verhältnis rq*(re1+rk) zu re1*(re2+rq) und rk zu re1.
	 * Die benötigten Werte der Widerstände werden aus den Arrays zugeordnet
	 * 
	 * @return Ausgangsspannung U_a
	 */
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
	 * Subtrahierverstärker
	 * Überprüft ob zwei Eingangssignale bzw. zwei Eingangswiderstände anliegen und
	 * falls ja ordnet man den einzelnen Plätzen im Array Werte zu
	 * 
	 * @param rk Kopplungswiderstand (Widerstand zwischen Ausgang und invertierenden
	 *           Eingang)
	 * @param rq Querwiderstand (Widerstand vom nichtinvertierenden Eingang zur
	 *           Masse)
	 * @param re Array der Eingangswiderstände (Verschiedene Widersände die an den
	 *           Eingängen liegen)
	 * @param ue Array der Eingangspannungen (Verschiedene Spannungswerte die an den
	 *           jeweiligen Eingängen liegen)
	 * @throws FalseInputException Fehler, wenn Anzahl der Eingangsspannungen und
	 *                             Widerstände nicht 2 ist oder Widerstände kleiner
	 *                             gleich 0 sind
	 */
	public Subtrahierverstärker(double rk, double rq, double[] re, double[] ue) throws FalseInputException {
		super(rk);
		R_e = new Widerstand[2];
		U_e = new double[2];
		if (ue.length != 2 || re.length != 2) {
			throw new FalseInputException("Anzahl Engangsspannungen und Eingangswiderstände muss 2 betragen");
		} else {
			R_e[0] = new Widerstand(re[0], getEReihe());
			R_e[1] = new Widerstand(re[1], getEReihe());
			U_e[0] = ue[0];
			U_e[1] = ue[1];
			R_q = new Widerstand(rq, getEReihe());

		}
	}

	/**
	 * 
	 * *Subtrahierverstärker
	 * Definierung von Re1,2 und Ue1,2
	 * 
	 * @param rk  Kopplungswiderstand (Widerstand zwischen Ausgang und
	 *            invertierenden Eingang)
	 * @param rq  Querwiderstand (Widerstand vom nichtinvertierenden Eingang zur
	 *            Masse)
	 * @param re1 1.Eingangswiderstand (Widerstand welcher am ersten Eingang des
	 *            OPV`s anliegt)
	 * @param re2 2.Eingangswiderstand (Widerstand welcher am ersten Eingang des
	 *            OPV`s anliegt)
	 * @param ue1 1.Eingangspannung (Spannung welche am ersten Eingang des OPV´s
	 *            anliegt)
	 * @param ue2 2.Eingangsspannung (Spannung welche am zweiten Eingang des OPV´s
	 *            anliegt)
	 * @throws FalseInputException Fehler, wenn Widerstände kleiner gleich 0 sind
	 */
	public Subtrahierverstärker(double rk, double rq, double re1, double re2, double ue1, double ue2) throws FalseInputException {
		super(rk);
		R_e = new Widerstand[2];
		U_e = new double[2];
		R_e[0] = new Widerstand(re1, getEReihe());
		R_e[1] = new Widerstand(re2, getEReihe());
		U_e[0] = ue1;
		U_e[1] = ue2;
		R_q = new Widerstand(rq, getEReihe());
	}
}
