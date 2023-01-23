package network;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class POP3Client {
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
            socket = new Socket("10.2.129.148", 10110);
            reader = new Scanner(socket.getInputStream());
            writer = new PrintWriter(socket.getOutputStream());
            System.out.println("erfolgreich.");
        } catch (IOException e) {
            System.err.println("Fehler beim Verbinden mit 10.2.129.148 auf " + "Port 10110");
            System.exit(1); // Status 1 bedeutet allgemeiner Fehler
        }
    }

    /**
     * Sende den String "USER anna" "+OK": Hat geklappt "-...": Fehler, beende
     * Programm Und danach "PASS geheim"
     */
    private void anmelden() {
        System.out.println("Hier kommt die Anmeldung hin");
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
