package trees;

import java.util.Scanner;

public class TiereRaten {
    Scanner eingabe = new Scanner(System.in);
    Frage wurzel;

    public TiereRaten() {
        wurzel = new Frage("Ist es ein Säugetier?");
        wurzel.ja = new Frage("Loewe");
        wurzel.nein = new Frage("Papagei");
    }

    public void spiele() {
        System.out.println("Willkommen beim Expertensystem");
        Frage runner = wurzel;
        Frage pred = null;

        // Schleife
        while (runner != null) {
            if (runner.istTier()) {
                System.out.println("Ist es ein(e) " + runner.inhalt + "? ");
                boolean antwortJa = eingabe.nextLine().startsWith("j");

                if (antwortJa) {
                    System.out.println("Hurrah, ich habe das Tier geraten und damit gewonnen!");
                } else {
                    System.out.println("Oh je, ich bin am Ende meines Lateins. Welches Tier hast Du Dir gedacht? ");
                    String neuesTier = eingabe.nextLine();
                    System.out.println("Stelle eine Frage, um altes Tier und neues Tier zu unterscheiden, und für die" +
                            " ja die Antwort für neues Tier ist. ");
                    String neueFrage = eingabe.nextLine();

                    // Vorsicht!
                    pred.nein = new Frage(neueFrage);
                    // Setze neuesTier auf linke Antwort
                    // Setze altesTier auf rechte Antwort

                }
            } else {
                System.out.println(runner.inhalt + " ");
                boolean antwortJa = eingabe.nextLine().startsWith("j");

                pred = runner;
                if (antwortJa) {
                    runner = runner.ja;
                } else {
                    runner = runner.nein;
                }
            }
        }
    }

    public static void main(String[] args) {
        TiereRaten tr = new TiereRaten();
        tr.spiele();
    }
}

class Frage {
    String inhalt;
    Frage ja, nein;

    public Frage(String inhalt) {
        this.inhalt = inhalt;
    }

    public boolean istTier() {
        return ja == null; // && nein == null;
    }
}