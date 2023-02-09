package network;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Date;

public class DaytimeServer {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(10013);
        } catch (IOException e) {
            System.err.println("FEHLER: Port 10013 bereits geöffnet");
            System.exit(1);
        }

        // accept wartet 1. auf einen Client, der sich verbinden will
        // und 2. stellt die Verbindung her
        try {
            Socket socket = serverSocket.accept();
            PrintWriter writer = new PrintWriter(socket.getOutputStream());

            writer.println(new Date());
            writer.println(LocalDateTime.now());
            writer.flush();
            socket.close();
        } catch (IOException e) {
            System.err.println("Fehler beim Verbinden mit dem neuen Client");
        }
    }
}
