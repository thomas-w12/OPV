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
 

	public Widerstand getR_k() {
		return this.R_k;
	}

	public void setR_k(Widerstand R_k) {
		this.R_k = R_k;
	}

	public static void setEReihe(EReihe ereihe) {
		Operationsverstärker.ereihe = ereihe;
	}

	public static EReihe getEReihe() {
		return ereihe;
	}


	public Operationsverstärker(double rk) throws FalseInputException {
		this.R_k = new Widerstand(rk, ereihe);
	}

	public Operationsverstärker() {
	}
}
