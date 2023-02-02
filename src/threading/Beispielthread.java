package threading;

public class Beispielthread {
    public static void main(String[] args) {
        Aufgabe a1 = new Aufgabe();
        Aufgabe a2 = new Aufgabe();
        Aufgabe a3 = new Aufgabe();
        System.out.println("START");
        a1.start();
        a2.start();
        a3.start();
        System.out.println("ENDE");
    }

}

class Aufgabe {
    public void start() {
        System.out.println("Aufgabe START");

        try {
            Thread.sleep(2_000);
        } catch (InterruptedException e) {
        }

        System.out.println("Aufgabe ENDE");
    }
}