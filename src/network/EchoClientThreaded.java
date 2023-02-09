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
            socket = new Socket("10.2.129.148", 10007);
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
        System.out.println("Willkommen beim Echo-Client. Gib 'quit' ein, um " + "das Programm zu beenden.");

        String eingabe;
        while (empfangsThread.isAlive()) {
            eingabe = tastatur.nextLine();

            if (!empfangsThread.isAlive()) {
                System.out.println("Empfangsthread hat die Nachricht " +
                        "bekommen, dass der Server die Verbindung beendet hat");
                break;
            } else if (eingabe.equalsIgnoreCase("quit")) {
                System.out.println("Hier bin ich nicht");
                break;
            } else {
                System.out.println("SENDE: " + eingabe);
                writer.println(eingabe);
                writer.flush();
                System.out.println("Gesendet");
            }
        }

        try {
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Ciao.");
    }

    public static void main(String[] args) {
        new EchoClientThreaded().starteChat();
    }
}

class EchoClientEmpfangsThread extends Thread {
    BufferedReader reader;

    public EchoClientEmpfangsThread(BufferedReader reader) {
        this.reader = reader;
    }

    @Override
    public void run() {
        System.out.println("Empfangsthread gestartet");
        String response;

        while (true) {
            try {
                response = reader.readLine();
                if (response == null) {
                    System.out.println("Server hat die Verbindung getrennt.");
                    break;
                }

                System.out.println("EMPFANGEN: " + response);
            } catch (IOException e) {
                // Thread beenden
                System.err.println("Thread wurde gekillt weil wir die " +
                        "Verbindung beenden. Auslöser ist Schließen der " +
                        "Socket");
                break;
            }
        }

        System.out.println("Empfangs-Thread beendet.");
    }
}
