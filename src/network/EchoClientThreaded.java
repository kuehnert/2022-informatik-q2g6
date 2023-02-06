package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class EchoClientThreaded {
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    private Scanner tastatur;
    private EchoClientEmpfangsThread empfangsThread;

    public EchoClientThreaded() {
        tastatur = new Scanner(System.in);

        try {
            Socket socket = new Socket("10.2.129.148", 10007);
            reader =
                    new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream());
        } catch (IOException exception) {
            System.err.println("Fehler beim Verbinden. Beende Programm");
            System.exit(1);
        }
    }

    public void starteChat() {
        // 1. Starte Empfangsthread
        empfangsThread = new EchoClientEmpfangsThread(reader);
        empfangsThread.start();

        // 2. Lies Eingaben über Tastatur ein und
        //    sende sie über den writer
        //    es sei denn, es wird "quit" eingegeben

    }

    public static void main(String[] args) {
        new EchoClientThreaded().starteChat();
    }

    private static void ersatzteillager() {
        /*
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

         */
    }
}

class EchoClientEmpfangsThread extends Thread {
    BufferedReader reader;

    public EchoClientEmpfangsThread(BufferedReader reader) {
        this.reader = reader;
    }

    @Override
    public void run() {
        String response;

        while (true) {
            try {
                response = reader.readLine();
                System.out.println("EMPFANGEN: " + response);
            } catch (IOException e) {
                // Thread beenden
                return;
            }
        }
    }
}