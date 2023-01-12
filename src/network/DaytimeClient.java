package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

public class DaytimeClient {
    public static void main(String[] args) {
        System.out.println("START vom Daytime-Client");

        try {
            // Socket socket = new Socket("10.2.129.148", 10013);
            Socket socket = new Socket("time.fu-berlin.de", 13);
            Scanner reader = new Scanner(socket.getInputStream());

            String input = reader.nextLine();
            System.out.println(input);

            if (input.contains("9:19")) {
                System.out.println("Hurra, nur noch 11 Minuten");
            }

            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("ENDE  vom Daytime-Client");
    }
}
