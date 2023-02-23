package network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static final int PORT = 10007;
    ServerSocket serverSocket;

    public EchoServer() {
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.printf("Echo-Server gestartet auf Port %d%n", PORT);
        } catch (IOException e) {
            System.err.println("FEHLER: Port " + PORT + " kann nicht " + "ge" + "öff" + "net" + " werden.");
            System.exit(1);
        }
    }

    // Aufgaben
    // 1. Fixe die Exception bei Abbruch
    // 2. (opt) Verwende eine Schleife, um mehrere Clients hintereinander zu
    // bedienen
    // 3. Verwende Threads, um mehrere Clients _gleichzeitig_ zu behandeln
    public void run() {
        // Warte auf Client und verbinde Dich dann mit ihm
        try {
            // Aufbauarbeiten
            Socket clientVerbindung = serverSocket.accept();
            behandleClient(clientVerbindung);
        } catch (IOException e) {
            // TODO Sinnvolle Fehlerbehandlung
            System.err.println("Fehler beim Verbinden mit Client");
            System.exit(1);
        }
    }

    private void behandleClient(Socket clientVerbindung) {
        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            reader =
                    new BufferedReader(new InputStreamReader(clientVerbindung.getInputStream()));
            writer =
                    new BufferedWriter(new OutputStreamWriter(clientVerbindung.getOutputStream()));
        } catch (IOException e) {
            System.err.println("Fehler beim Kommunizieren mit dem Client. " + "Beende Thread.");
            return;
        }

        System.out.printf("Neue Verbindung mit %s%n",
                clientVerbindung.getInetAddress());

        // Eigentliche Arbeit mit dem Client
        while (true) {
            try {
                String response = reader.readLine();
                if (response.equalsIgnoreCase("QUIT")) {
                    break;
                }

                System.out.println("Empfangen: " + response);
                writer.write(response + "\n\r");
                writer.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        // Aufräumarbeiten
        try {
            reader.close();
            writer.close();
            clientVerbindung.close();
        } catch (IOException e) {
            System.err.println("Fehler beim Schließen der Client-Verbindung. "
                    + "Nicht schlimm.");
        }
    }

    public static void main(String[] args) {
        new EchoServer().start();
    }

    private void start() {
        run();
    }
}

// LF = Line Feed
// CR = Carriage Return
// Windows: <LF><CR> <10><13>
// Linux, Mac: <LF> <10>
