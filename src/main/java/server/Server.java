package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Vector;

public class Server {
    static LinkedList<Handler> listUser = new LinkedList<>();

    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(8189);
            System.out.println("Server started");

            while (true) {
                Socket socket = server.accept();
                System.out.println("Client accepted");

                Handler handler = new Handler(socket);
                listUser.add(handler);
                new Thread(handler).start();
            }
        } catch (IOException e) {
            System.out.println("Connection was broken");
        }

    }
}
