package trees;

import java.util.Locale;
import java.util.Scanner;

public class TiereRaten {
    Scanner eingabe = new Scanner(System.in);
    Frage wurzel;

    public TiereRaten() {
        wurzel = new Frage("Ist es ein Saeugetier");
        wurzel.ja = new Frage("Loewe");
        wurzel.nein = new Frage("Papagei");
    }

    public void spiele() {
        System.out.println("Willkommen beim Expe--rtensystem");
        boolean weiter = true;

        while (weiter) {
            spieleRunde();
            weiter = jaNeinFrage("Noch einmal spielen");
        }
    }

    public void spieleRunde() {
        Frage runner = wurzel;
        Frage pred = null;

        // Schleife
        while (runner != null) {
            if (runner.istTier()) {
                boolean antwortJa =
                        jaNeinFrage("Ist es ein(e) " + runner.inhalt);

                if (antwortJa) {
                    System.out.println("Hurrah, ich habe das Tier geraten " + "und" + " damit gewonnen!");
                } else {
                    System.out.println("Oh je, ich bin am Ende meines " +
                            "Lateins. Welches Tier hast Du Dir gedacht? ");
                    String neuesTierStr = eingabe.nextLine();
                    System.out.println("Stelle eine Frage, um " + runner.inhalt + " und " + neuesTierStr + " zu unterscheiden, und fuer die 'ja' die Antwort fuer " + neuesTierStr + " ist. ");
                    String neueFrageStr = eingabe.nextLine();

                    runner.ja = new Frage(neuesTierStr);
                    runner.nein = new Frage(runner.inhalt);
                    runner.inhalt = neueFrageStr;

                    System.out.println("Neuer Baum: ");
                    System.out.println(wurzel);
                }

                runner = null;
            } else {
                boolean antwortJa = jaNeinFrage(runner.inhalt);

                pred = runner;
                if (antwortJa) {
                    runner = runner.ja;
                } else {
                    runner = runner.nein;
                }
            }
        }
    }

    private boolean jaNeinFrage(String prompt) {
        System.out.print(prompt + " (j/n)? ");
        return eingabe.nextLine().toLowerCase(Locale.ROOT).startsWith("j");
    }

    public String toString() {
        if (wurzel == null) {
            return "Baum leer";
        } else {
            return wurzel.toString();
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

    @Override
    public String toString() {
        String output = "" + inhalt;

        if (ja != null) {
            output += "[" + ja + "]";
        }

        if (nein != null) {
            output += "<" + nein + ">";
        }

        return output;
    }
}