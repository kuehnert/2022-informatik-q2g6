package network;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class POP3Client {
    private static final String SERVER = "10.2.129.148";
    private static final int PORT = 10110;
    private static final String USERNAME = "anna";
    private static final String PASSWORD = "geheim";
    private Socket socket;
    private Scanner reader;
    private PrintWriter writer;

    public POP3Client() {
        verbinden();
        anmelden();
        listeMails();
        beendeVerbindung();
    }

    private void verbinden() {
        System.out.print("Versuche Verbindung... ");

        try {
            socket = new Socket(SERVER, PORT);
            reader = new Scanner(socket.getInputStream());
            writer = new PrintWriter(socket.getOutputStream());
            System.out.println("erfolgreich.");

            String anmeldungstext = reader.nextLine();
            System.out.println("EMPFANGEN: " + anmeldungstext);
        } catch (IOException e) {
            System.err.println("Fehler beim Verbinden mit " + SERVER + " auf "
                    + "Port " + PORT);
            System.exit(1); // Status 1 bedeutet allgemeiner Fehler
        }
    }

    /**
     * Sende den String "USER anna" "+OK": Hat geklappt "-...": Fehler, beende
     * Programm Und danach "PASS geheim"
     */
    private void anmelden() {
        System.out.println("Hier kommt die Anmeldung hin");
        writer.println("USER " + USERNAME);
        writer.flush();

        reader.nextLine();
        String antwort = reader.nextLine();
        System.out.println("EMPFANGEN: " + antwort);

        // Sende Passwort
        writer.println("PASS " + PASSWORD);
        writer.flush();

        reader.nextLine();
        antwort = reader.nextLine();
        System.out.println("EMPFANGEN: " + antwort);

        if (!antwort.startsWith("+OK")) {
            // Fehlerfall
            System.err.println("Fehler bei Benutzername/Passwort");
            try {
                socket.close();
            } catch (IOException e) {
            }

            System.exit(1);
        }
    }

    private void listeMails() {
        System.out.println("Hole Liste von E-Mails.");

        writer.println("LIST"); writer.flush();
        String antwort = reader.nextLine();
        if (antwort.startsWith("-ERR")) {
            System.out.println("Fehler beim Listen der Mails");
            try {
                socket.close();
            } catch (IOException e) {
            }
            System.exit(1);
        }

        System.out.println(antwort);
        ArrayList<Integer> idListe = new ArrayList<>();

        while (true) {
            antwort = reader.nextLine();

            if (antwort.equals("")) {
                // starte nächsten Schleifendurchlauf
                continue;
            } else if (antwort.equals(".")) {
                // breche die Schleife ab
                break;
            }

            // Wir bekommen eine Zeile der Form "15 45378437"
            String idString = antwort.split(" ")[0];
            int id = Integer.parseInt(idString);
            idListe.add(id);

            System.out.println("EMPFANGEN: " + antwort);
        }

        // SAFE
        System.out.println("IDs: " + idListe);

        for (int id: idListe) {
            holeMail(id);
        }
    }

    private void holeMail(int id) {
        System.out.println("Hole Mail mit ID " + id);
    }

    private void beendeVerbindung() {
        System.out.println("Beende Verbindung");
        try {
            socket.close();
        } catch (IOException e) {
        }

        System.out.println("Ciao, Bello.");
    }

    public static void main(String[] args) {
        new POP3Client();
    }
}
