package trees;

import java.util.Scanner;

public class TiereRaten {
    Scanner eingabe = new Scanner(System.in);
    Frage wurzel;

    public TiereRaten() {
        wurzel = new Frage("Ist es ein Säugetier?");
        wurzel.ja = new Frage("Löwe");
        wurzel.nein =new Frage( "Papagei");
    }

    public void spiele() {
        System.out.println("Willkommen beim Expertensystem");
        Frage runner = wurzel;

        // Schleife
        System.out.println(runner.inhalt);
        String antwort = eingabe.nextLine();

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
}