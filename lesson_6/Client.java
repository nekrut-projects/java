package lesson_6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static final String EXIT = "/end";
    private static final int PORT = 15000;
    public static final String ADDRESS_SERVER = "localhost";
    private Socket socket;
    private DataInputStream inputFromServer;
    private DataOutputStream outputFromServer;

    public Client() {
        try {
            socket = new Socket(ADDRESS_SERVER, PORT);
        } catch (IOException e) {
            System.out.println("Server don`t start.");
            System.out.println("Please start the server, before starting the client");
        }
        try {
            inputFromServer = new DataInputStream(socket.getInputStream());
            outputFromServer = new DataOutputStream(socket.getOutputStream());
            System.out.println("Chat start. To exit enter: /end");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Client().runClient();
    }

    private void closeResources() {
        try {
            inputFromServer.close();
            outputFromServer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMsg(String message) {
        try {
            outputFromServer.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void runClient() {
        Scanner keyboardInput = new Scanner(System.in);
        Thread keyboard = new Thread(() -> {
            String msg = "";
            while (true) {
                msg = keyboardInput.nextLine();
                if (msg.equalsIgnoreCase(EXIT)){
                    sendMsg(EXIT);
                    break;
                }
                sendMsg(msg);
            }
        });
        keyboard.setDaemon(true);
        keyboard.start();

        new Thread(() -> {
            String msgFromServer = "";
            try {
                while (true){
                    msgFromServer = inputFromServer.readUTF();
                    if (msgFromServer.equalsIgnoreCase(EXIT)) {
                        sendMsg(EXIT);
                        break;
                    }
                    System.out.println("Server: " + msgFromServer);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                closeResources();
            }
        }).start();
    }
}
