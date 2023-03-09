package network;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

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

    public ChatServer() {
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            System.err.println("Fehler beim Öffnen von Port " + PORT);
            System.exit(1);
        }
    }

    public void run() {
        while (true) {
            try {
                // Warte auf Client
                Socket neuerClient = serverSocket.accept();
                // Wenn verbunden, starte einen ClientThread
                new ChatServerClientThread(neuerClient).start();
            } catch (IOException e) {
                System.err.println("Fehler bei Verbindung mit Client");
            }
        }
    }

    public static void main(String[] args) {
        new ChatServer().run();
    }
}

class ChatServerClientThread extends Thread {
    private Socket clientSocket;
    private PrintWriter writer;

    public ChatServerClientThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
        try {
            writer =
                    new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        } catch (IOException e) {
            // TODO
            System.err.println("Fehler beim Schreiben auf dem Client");
            System.exit(2);
        }
    }

    @Override
    public void run() {
        writer.println("Schön dass Du da bist!");
        writer.flush();
        writer.close();
        try {
            clientSocket.close();
        } catch (IOException e) {
            // Ignore silently
        }
    }
}