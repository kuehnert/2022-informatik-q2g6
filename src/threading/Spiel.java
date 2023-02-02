package threading;

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

        for (int i = 0; i < 10; i++) {
            String eingabe = tastatur.nextLine();
            if (eingabe.charAt(0) == werferin.buchstabe) {
                System.out.println("Toll, richtig!");
                // Teile Werferin mit, dass Buchstabe eingegeben wurde
            }
        }
    }

    public static void main(String[] args) {
        new Spiel();
    }
}

/*
 * Alle 2-6 Sekunden, schreibe einen neuen Buchstaben auf die Konsole,
 * den der User drücken muss. Dafür hat eine Sekunde Zeit
 */
class Buchstabenwerfer extends Thread {
    public char buchstabe = '_';

    public void run() {
        // Endlosschleife
        // Alle 2-6 Sekunden setze neuen Buchstaben
        // Nach einer Sekunde, setze den Buchstaben auf '😎'
    }
}
