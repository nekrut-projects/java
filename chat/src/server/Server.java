package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Vector;

public class Server{
    List<ClientHandler> clients;
    private AuthService authService;

    private static int PORT = 8189;
    ServerSocket server = null;
    Socket socket = null;

    public Server() {
        clients = new Vector<>();
        authService = new SimpleAuthService();

        try {
            server = new ServerSocket(PORT);
            System.out.println("Сервер запущен");

            while (true) {
                socket = server.accept();
                System.out.println("Клиент подключился");

//                clients.add(new ClientHandler(this, socket));
//                subscribe(new ClientHandler(this, socket));
                new ClientHandler(this, socket);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void broadcastMsg(ClientHandler sender, String msg) {
        String message = String.format("%s : %s", sender.getNickName(), msg);
        for (ClientHandler client : clients) {
            client.sendMsg(message);
        }
    }

    public  void sendMsgUser(ClientHandler client, String msg) {
        String[] message = msg.split("\\s");

        if (message.length < 3) {
            return;
        }

        if (client.equals(getClient(message[1]))) {
            return;
        }
        StringBuffer buffer = new StringBuffer();

        for (int i = 2; i < message.length; i++) {
            buffer.append(message[i]);
            buffer.append(" ");
        }

        String str = String.format("%s : %s", client.getNickName(), buffer);

        getClient(message[1]).sendMsg(str);

        str = String.format("%s : %s", client.getNickName(), buffer);

        client.sendMsg(str);
    }

    public void subscribe(ClientHandler clientHandler) {
        clients.add(clientHandler);
    }

    public void unsubscribe(ClientHandler clientHandler) {
        clients.remove(clientHandler);
    }

    public AuthService getAuthService(){
        return authService;
    }

    public ClientHandler getClient(String nickname) {
        for(ClientHandler client : clients) {
            if(client.getNickName().equals(nickname)) {
                return client;
            }
        }
        return null;
    }
}
