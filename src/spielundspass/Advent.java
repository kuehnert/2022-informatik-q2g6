package spielundspass;

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
            if (wi == pred ) {
                break;
            } else {
                pred = wi;
            }
        }

        return wert;
    }

    public static void main(String[] args) {
        piannaehrung();
    }
}
