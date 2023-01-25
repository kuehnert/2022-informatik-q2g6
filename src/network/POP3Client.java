package network;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
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
        System.out.println("Hä?");

        reader.nextLine();
        String antwort = reader.nextLine();
        System.out.println("EMPFANGEN: " + antwort);

    }

    private void listeMails() {
        System.out.println("Hier Arbeit für Felix und Nicht-Felixe.");
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
        // String input = reader.nextLine();
        // botschaft.startsWith("+")
        // writer.println(botschaft);
        // writer.flush();
    }
}
