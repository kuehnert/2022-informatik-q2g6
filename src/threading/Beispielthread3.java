package threading;

public class Beispielthread3 {
    static long start;

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
        long ende = System.currentTimeMillis();
        double dauer = (ende - start) / 1000.0;
        System.out.printf("DAUER: %.1f Sekunden. %n", dauer);

        System.out.println("ENDE");
    }
}

class Aufgabe3 extends Thread {
    int nummer;

    public Aufgabe3(int nummer) {
        this.nummer = nummer;
    }

    @Override
    public void run() {
        System.out.println("Aufgabe " + nummer + " START");

        try {
            Thread.sleep(2_000);
        } catch (InterruptedException e) {
        }

        System.out.println("Aufgabe " + nummer + " ENDE");
    }
}