package threading;

public class Beispielthread3 {
    static long start;

    public static void missZeit(String name, long start) {
        long ende = System.currentTimeMillis();
        double dauer = (ende - start) / 1000.0;
        System.out.printf("%s DAUER: %.1f Sekunden. %n", name.toUpperCase(),
                dauer);
    }

    public static void main(String[] args) {
        Aufgabe3 a1 = new Aufgabe3(1);
        Aufgabe3 a2 = new Aufgabe3(2);
        Aufgabe3 a3 = new Aufgabe3(3);

        // Miss Zeit
        start = System.currentTimeMillis();

        System.out.println("START");
        a1.start();
        a2.start();
        a3.start();

        // Miss Zeit
        missZeit("Hauptprogramm", start);
    }
}

class Aufgabe3 extends Thread {
    int nummer;

    public Aufgabe3(int nummer) {
        this.nummer = nummer;
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        System.out.println("Aufgabe " + nummer + " START");

        try {
            Thread.sleep(2_000);
        } catch (InterruptedException e) {
        }

        Beispielthread3.missZeit("Aufgabe " + nummer, start);
    }
}