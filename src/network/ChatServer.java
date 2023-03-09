package network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/*
 * Beliebig viele Clients können sich über Port 10666 verbinden und miteinander
 * chatten. Nachrichten werden asynchron empfangen und gesendet
 *
 * Erweiterungen sind Befehle, die Aktionen auslösen
 * /name XXX : Ändere den Namen des Users auf XXX
 */
public class ChatServer {
    public static final int PORT = 10666;
    private ServerSocket serverSocket;
    private ArrayList<ChatServerClientThread> clientThreads;

    public ChatServer() {
        try {
            serverSocket = new ServerSocket(PORT);
            clientThreads = new ArrayList<>();
        } catch (IOException e) {
            System.err.println("Fehler beim Öffnen von Port " + PORT);
            System.exit(1);
        }
    }

    public void run() {
        System.out.println("ChatServer gestartet auf Port " + PORT);

        while (true) {
            try {
                // Warte auf Client
                Socket neueClientSocket = serverSocket.accept();
                // Wenn verbunden, starte einen ClientThread
                var neuerClient = new ChatServerClientThread(this,
                        neueClientSocket);
                clientThreads.add(neuerClient);
                neuerClient.start();
                System.out.println("Client Nr. " + clientThreads.size() + " " +
                        "verbunden!");
            } catch (IOException e) {
                System.err.println("Fehler bei Verbindung mit Client");
            }
        }
    }

    public void sendeAnAlle(ChatServerClientThread client, String botschaft) {
        for (var empfaenger: clientThreads) {
            empfaenger.sende(client.getName() + ": " + botschaft);
        }
    }

    public static void main(String[] args) {
        new ChatServer().run();
    }
}

class ChatServerClientThread extends Thread {
    private ChatServer server;
    private Socket clientSocket;
    private BufferedReader reader;
    private PrintWriter writer;
    private String name;

    public ChatServerClientThread(ChatServer server, Socket clientSocket) {
        this.server = server;
        this.clientSocket = clientSocket;

        try {
            name = clientSocket.getInetAddress().getCanonicalHostName();
            reader =
                    new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            writer =
                    new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        } catch (IOException e) {
            // TODO
            System.err.println("Fehler beim Schreiben auf dem Client " + name);
            System.exit(2);
        }
    }

    public void sende(String botschaft) {
        writer.println(botschaft);
        writer.flush();
    }

    @Override
    public void run() {
        String empfangen;
        System.out.println("ClientThread gestartet mit: " + name);
        writer.println("Willkommen beim Chat-Server! Gib QUIT ein um zu " +
                "beenden");
        writer.flush();

        while (true) {
            try {
                empfangen = reader.readLine();
                System.out.println(name + ": " + empfangen);

                if (empfangen == null || empfangen.equalsIgnoreCase("QUIT")) {
                    break;
                }

                server.sendeAnAlle(this, empfangen);
            } catch (IOException e) {
                System.err.println("Fehler beim Empfangen: Client hat " +
                        "die Verbindung beendet");
                break;
            }
        }

        try {
            writer.close();
            clientSocket.close();
        } catch (IOException e) {
            // Ignore silently
        }

        System.out.println("ClientThread beendet: " + name);
    }
}