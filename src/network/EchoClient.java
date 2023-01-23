package network;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {
    public static void main(String[] args) {
        try {
            // Öffne eine Verbindung zum Server
            Socket socket = new Socket("10.2.129.148", 10007);
            // Erstelle einen Scanner reader zum Lesen von Daten über die Socket
            Scanner reader = new Scanner(socket.getInputStream());
            // Erstelle einen Writer zum Schreiben über die Socket
            PrintWriter writer = new PrintWriter(socket.getOutputStream());

            // Erstelle einen 2. Scanner für die Tastatureingaben
            Scanner tastatur = new Scanner(System.in);
            // ----------------------------------------------------------------

            System.out.println("Lucas kleiner ECHO-Client. Tippe 'quit' zum " +
                    "Beenden");

            // Empfange die Begrüßung vom Server & gib sie aus
            String input = reader.nextLine();
            System.out.println("RECEIVED: " + input);
            // Empfange Leerzeile, Fehler im Server
            input = reader.nextLine();
            System.out.println("RECEIVED: " + input);
            String botschaft = "";

            // Hier kommt eine Schleife drumherum:
            while (true) {
                // Erfrage eine Eingabe vom Nutzer und sende sie über die Socket
                System.out.print("Eingabe: ");
                botschaft = tastatur.nextLine();

                if (!botschaft.equalsIgnoreCase("quit")) {
                    System.out.println("EINGABE: " + botschaft);
                    writer.println(botschaft);
                    writer.flush();

                    // Empfange die Antwort vom Server & gib sie aus
                    input = reader.nextLine();
                    System.out.println("RECEIVED: " + input);
                } else {
                    break;
                }
            }

            socket.close();
            System.out.println("Verbindung getrennt. Programmende.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
