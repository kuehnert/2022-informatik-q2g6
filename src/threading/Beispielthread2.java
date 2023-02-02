package threading;

public class Beispielthread2 {
    public static void main(String[] args) {
        Aufgabe2 a1 = new Aufgabe2(1);
        Aufgabe2 a2 = new Aufgabe2(2);
        Aufgabe2 a3 = new Aufgabe2(3);
        System.out.println("START");
        a1.start();
        a2.start();
        a3.start();
        System.out.println("ENDE");
    }
}

class Aufgabe2 extends Thread {
    int nummer;

    public Aufgabe2(int nummer) {
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