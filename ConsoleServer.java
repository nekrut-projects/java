package Lesson_6.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.logging.*;

public class ConsoleServer {
    private Vector<ClientHandler> clients;
    private Logger logger;

    public ConsoleServer() {
        clients = new Vector<>();
        logger = Logger.getLogger(ConsoleServer.class.getName());
        logger.setLevel(Level.ALL);
        try {
            Handler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(Level.ALL);
            consoleHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(consoleHandler);
            Handler fileHandler = new FileHandler("server.log", true);
            fileHandler.setFormatter(new XMLFormatter());
            fileHandler.setLevel(Level.INFO);
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ServerSocket server = null;
        Socket socket = null;

        try {
            server = new ServerSocket(8189);
            logger.log(Level.INFO, "Сервер запущен!");

            while (true) {
                socket = server.accept();
                logger.log(Level.INFO, "Клиент подключился");
                clients.add(new ClientHandler(this, socket));
            }

        } catch (IOException e) {
            logger.log(Level.SEVERE, "Сервер не запустился!");
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                logger.log(Level.INFO, "Сервер остановлен");
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void broadcastMsg(String msg) {
        logger.log(Level.FINE, "Клиент отправил сообщение: " + msg);
        for (ClientHandler o : clients) {
            o.sendMsg(msg);
        }
    }
}
