package spielundspass;

import java.util.Random;

public class Advent {
    static double piannaehrung() {
        int v = +4;
        double wert = 0;
        int pred = 0;

        for (int n = 1; true; n += 2) {
            wert = wert + v * 1.0 / n;
            v *= -1;
            System.out.printf("%d: %.5f %n", n, wert);

            int wi = (int) (wert * 100_000);
            if (wi == pred) {
                break;
            } else {
                pred = wi;
            }
        }

        return wert;
    }

    public static void monteCarlo() {
        Random g = new Random();
        int innen = 0, gesamt = 0;
        double roundedPi = Math.floor(Math.PI * 10_000);
        double pi = 0.0;

        while (gesamt < 100_000) {
            double x = g.nextDouble();
            double y = g.nextDouble();

            // berechne die Länge der Hypothenuse
            double z = Math.sqrt(x * x + y * y);

            if (z <= 1.0) {
                innen++;
            }

            pi = 4.0 * innen / ++gesamt;

            if (gesamt % 1000 == 0) {
                System.out.println("Meine Pi-Annäherung (" + innen + "/" + gesamt + "): " + pi);
                System.out.println(Math.abs(Math.PI - pi));
            }

            if (Math.floor(pi * 10_000) == roundedPi) {
                break;
            }
        }

        System.out.println("Meine Pi-Annäherung: " + pi);
        System.out.println(Math.abs(Math.PI - pi));
    }

    public static void main(String[] args) {
        monteCarlo();
    }
}
