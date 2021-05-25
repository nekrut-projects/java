package client;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import shared.Marker;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientController implements Initializable {
    public Button outButton;
    public Button inButton;
    public ListView<String> cListView;
    public ListView<String> sListView;
    public TextField cTextField;
    public TextField sTextField;

    DataInputStream ins;
    DataOutputStream outs;

    private String currentDir = "../";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        getListUserFiles();
        try {
            Socket socket = new Socket("localhost", 8189);
            outs = new DataOutputStream(socket.getOutputStream());
            ins = new DataInputStream(socket.getInputStream());

            outs.write(Marker.SEND_LIST_FILES.ordinal());
            outs.flush();

            Thread readThread = new Thread(() -> {
                try {
                    while (true){
                        int marker = ins.read();
                        System.out.println(marker);
                        if (marker == Marker.SEND_LIST_FILES.ordinal()) {
//                            System.out.println("********************44");
                            byte[] buffer = new byte[8192];
                            StringBuffer sb = new StringBuffer();
                            int length;
                            while ((length = ins.read(buffer)) > 0) {
                                sb.append(length);
                            }
                            String[] names = sb.toString().split(",");
                            Platform.runLater(() -> sListView.getItems().addAll(names));
                        } else if (marker == Marker.DOWNLOAD_FILE.ordinal()) {

                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            readThread.setDaemon(true);
            readThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outs.close();
                ins.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void upload(ActionEvent actionEvent) {
        try {
            outs.writeUTF(cTextField.getText());
            outs.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        cTextField.clear();
    }

    public void download(ActionEvent actionEvent) {
        try {
            outs.writeUTF(sTextField.getText());
            outs.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        cTextField.clear();
    }


    private void getListUserFiles() {
        File dir = new File(currentDir);
        for(String n : dir.list()) {
            Platform.runLater(() -> cListView.getItems().add(n));
        }
    }

    private void acceptListFiles(DataInputStream ins) throws IOException {
        while (true) {
            String nameFile = ins.readUTF();
            if (nameFile.length() == 0) {
                break;
            }
            Platform.runLater(() -> sListView.getItems().add(nameFile));

        }
    }

}