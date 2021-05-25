package server;

import org.omg.CORBA.portable.OutputStream;
import shared.*;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Handler implements Runnable, Closeable {
    private final Socket socket;
    private String userDir;
    private File[] userFiles;

    public Handler(Socket socket) {
        this.socket = socket;
        userDir = "test_folder";

        File file = new File(userDir);
        userFiles = file.listFiles();
    }
    @Override
    public void close() throws IOException {
        socket.close();
    }
    @Override
    public void run() {
        try (DataOutputStream os = new DataOutputStream(socket.getOutputStream());
            DataInputStream is = new DataInputStream(socket.getInputStream())) {
//            sendListUserFiles(os);
            while (true) {
                int marker = is.read();
//                if (marker == 77) {
//                    System.out.println("********");
//                }
                if (marker == Marker.DOWNLOAD_FILE.ordinal()) {
//                    String msg = is.readUTF();
//                    System.out.println("received: " + msg);
//                    os.writeUTF(msg);
                } else if (marker == Marker.SEND_LIST_FILES.ordinal()){
                    os.write(Marker.SEND_LIST_FILES.ordinal());
                    os.flush();
                    System.out.println("------------");
                    for (File f : userFiles) {
                        System.out.println(f.getName());
                        os.write(f.getName().getBytes());
//                        os.flush();
                        os.write(",".getBytes());
                        os.flush();
                    }

//                    os.write("test1");
//                    os.writeUTF("test2");

                } else if (marker == Marker.UPLOAD_FILE.ordinal()) {
                    ////
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void sendListUserFiles(DataOutputStream outs){
        try {
            outs.writeUTF("77");
            for (File f : userFiles) {
                outs.writeUTF(f.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println(userFiles.toString());
//        outs.write(3);

    }
}
