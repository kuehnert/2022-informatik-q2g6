package threading;

import java.util.Random;
import java.util.Scanner;

public class Spiel {
    private Scanner tastatur;
    private int punkte;

    public Spiel() {
        tastatur = new Scanner(System.in);
    }

    public void spielen() {
        // 10 Runden, in denen der User etwas eingeben kann
        Buchstabenwerfer werferin = new Buchstabenwerfer();
        punkte = 0;

        System.out.println("Willkommen beim Tipp-Spiel, los geht's!");
        werferin.start();

        for (int i = 0; i < 10; i++) {
            String eingabe = tastatur.nextLine();

            if (werferin.buchstabe == Buchstabenwerfer.LEERZEICHEN) {
                System.out.println("Zu spät!");
            } else if (eingabe.length() > 0 && eingabe.charAt(0) == werferin.buchstabe) {
                System.out.println("Toll, richtig!");
                // Teile Werferin mit, dass Buchstabe eingegeben wurde
            } else {
                System.out.println("Falsch!");
            }
        }

        werferin.stop();
        System.out.println("Ende vom Tipp-Spiel");
    }

    public static void main(String[] args) {
        new Spiel().spielen();
    }
}

/*
 * Alle 2-6 Sekunden, schreibe einen neuen Buchstaben auf die Konsole,
 * den der User drücken muss. Dafür hat eine Sekunde Zeit
 */
class Buchstabenwerfer extends Thread {
    public static final char LEERZEICHEN = '\00';
    private static Random gen = new Random();
    public char buchstabe = LEERZEICHEN;

    private void schlafe(int sekunden) {
        try {
            sleep(sekunden * 1000);
        } catch (InterruptedException e) {
        }
    }

    private void schlafe(int min, int max) {
        try {
            sleep(gen.nextInt(max - min) + min);
        } catch (InterruptedException e) {
        }
    }

    public void run() {
        // Endlosschleife
        while (true) {
            // Alle 2-6 Sekunden setze neuen Buchstaben
            schlafe(2, 6);

            buchstabe = (char) ('a' + gen.nextInt(26));
            System.out.println("BUCHSTABE: " + buchstabe);

            // Nach einer Sekunde, setze den Buchstaben auf '😎'
            schlafe(1);
            buchstabe = LEERZEICHEN;
        }
    }
}
