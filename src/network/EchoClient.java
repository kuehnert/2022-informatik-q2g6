package network;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("10.2.129.148", 10007);
            Scanner reader = new Scanner(socket.getInputStream());

            String input = reader.nextLine();
            System.out.println(input);

            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
