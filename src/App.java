import widerstand.EReihe;
import widerstand.Widerstand;

import java.awt.Color;
import java.awt.color.*;

public class App {
    public static void main(String[] args) throws Exception {
        // System.out.println("Faktor Test");

        double eReihenWerte[] = {1.3, 5.2,3.4,7.6,4.3};
        EReihe testEReihe = new EReihe(eReihenWerte);

        //Widerstand test1 = new Widerstand(135, -2, testEReihe);
        //test1.farbringeFinden();

        Widerstand test2 = new Widerstand(2236.0);
        System.out.println(test2.getWert());
        Color[] farbringe = test2.farbringeFinden();
        for (int i = 0; i < farbringe.length; i++) {
            System.out.println(farbringe[i]);
        }
        // Double widerstand1 = 1500.0;
        // System.out.println("Widerstand: " + widerstand1);
        // System.out.println("Faktor: " + testEReihe.faktorFinden(widerstand1));
        // System.out.println("passender Widerstand aus EReihe: " + testEReihe.widerstandWaehlen(widerstand1));

        // Double widerstand2 = 0.13;
        // System.out.println("Widerstand: " + widerstand2);
        // System.out.println("Faktor: " + testEReihe.faktorFinden(widerstand2));
        // System.out.println("passender Widerstand aus EReihe: " + testEReihe.widerstandWaehlen(widerstand2));

        // Double widerstand3 = 2.5;
        // System.out.println("Widerstand: " + widerstand3);
        // System.out.println("Faktor: " + testEReihe.faktorFinden(widerstand3));
        // System.out.println("passender Widerstand aus EReihe: " + testEReihe.widerstandWaehlen(widerstand3));

        // Double testfaktor = 4.5;
        // System.out.println("Testfaktor: " + testfaktor);
        // System.out.println("Mal 10: " + testfaktor*10);
        // System.out.println("Modulo zehn: " + testfaktor*10%10);
        // System.out.println("Mal 100: " + testfaktor*100);
        // System.out.println("Modulo zehn: " + testfaktor*100%10);
        // if (testfaktor*100%10 > 0) {
        //     System.out.println("Fail");
        // }
        // System.out.println(Color.red);


        
        
    }
}
