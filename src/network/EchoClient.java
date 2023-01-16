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

            // Empfange die Begrüßung vom Server & gib sie aus
            String input = reader.nextLine();
            System.out.println("RECEIVED: " + input);
            // Empfange Leerzeile, Fehler im Server
            input = reader.nextLine();
            System.out.println("RECEIVED: " + input);

            // Hier kommt eine Schleife drumherum:
                // Erfrage eine Eingabe vom Nutzer und sende sie über die Socket
                System.out.print("Eingabe: ");
                String botschaft = tastatur.nextLine();
                System.out.println("EINGABE: " + botschaft);
                writer.println(botschaft);
                writer.flush();

                // Empfange die Antwort vom Server & gib sie aus
                input = reader.nextLine();
                System.out.println("RECEIVED: " + input);

            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
