
// import java.awt.Color;

// import widerstand.*;
// import operationsverstaerker.*;

// public class TestApp {
//     public static void main(String[] args) {

//         double eReihenWerte[] = { 1.3, 5.2, 3.4, 7.6, 4.3 };
//         EReihe testEReihe = new EReihe(eReihenWerte);
//         EReihe nullEReihe = new EReihe();

//         /*
//         Widerstand testWiderstand = new Widerstand(4136, testEReihe);
//         System.out.println(testWiderstand.getWert());
//         System.out.println("Gewählter Widerstand: " + testWiderstand.getWiderstandswert());
//         */
//         Operationsverstärker.setEReihe(nullEReihe);
//         /*
//          * Invertierer testInvertierer = new Invertierer(5689, 4136, 4.5);
//          * System.out.println(testInvertierer.getR_e().getWiderstandswert());
//          * System.out.println(testInvertierer.getR_k().getWiderstandswert());
//          * double UA = testInvertierer.berechneU_a();
//          * 
//          * System.out.println(UA);
//          */

//         Subtrahierverstärker subtrahierer = new Subtrahierverstärker(4213, 8964, 1235, 4523, 4, 8);
//         System.out.println("R_e1: " + subtrahierer.getR_e()[0].getWiderstandswert());
//         System.out.println("R_e1: " + subtrahierer.getR_e()[1].getWiderstandswert());

//         System.out.println("R_k: " + subtrahierer.getR_k().getWiderstandswert());
//         System.out.println("R_q: " + subtrahierer.getR_q().getWiderstandswert());

//         /*
//          * for (int i = 0; i < testWiderstand.getFarbringe().length; i++) {
//          * System.out.println(testWiderstand.getFarbringe()[i]);
//          * }
//          */

//     }
// }
