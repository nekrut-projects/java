package lesson_6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static final String EXIT = "/end";
    private static final int PORT = 15000;
    private ServerSocket serverSocket;
    private Socket socket;
    private DataInputStream inputFromClient;
    private DataOutputStream outputToClient;

    public Server() {
        try {
            serverSocket = new ServerSocket(PORT);
            socket = serverSocket.accept();
            inputFromClient = new DataInputStream(socket.getInputStream());
            outputToClient = new DataOutputStream(socket.getOutputStream());
            System.out.println("Client connect.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        new Server().runServer();
    }
    private void closeResources() {
        try {
            inputFromClient.close();
            outputToClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMsg(String message) {
        try {
            outputToClient.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void runServer() {
        Scanner keyboardInput = new Scanner(System.in);
        Thread keyboard = new Thread(() -> {
            String msg = "";
            while (true){
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
            String msgFromClient = "";
            try {
                while (true){
                    msgFromClient = inputFromClient.readUTF();
                    if (msgFromClient.equalsIgnoreCase(EXIT)) {
                        sendMsg(EXIT);
                        break;
                    }
                    System.out.println("Client: " + msgFromClient);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                closeResources();
            }
        }).start();
    }
}
