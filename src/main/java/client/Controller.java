package client;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import shared.Marker;

public class Controller implements Initializable {

    public ListView cListView;
    public TextField cTextField;
    public Button downloadButton;
    public Button uploadButton;
    public ListView sListView;
    public TextField sTextField;
    private Network network;
    private String myDir = "MyDir";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        network = new Network(msg -> {
            byte marker = msg.readByte();

            if(marker == Marker.DOWNLOAD_FILE.getValue()) {
                int lengthName = msg.readInt();
                StringBuilder name = new StringBuilder();
                for (int i = 0; i < lengthName; i++) {
                    name.append((char) msg.readByte());
                }
                ByteBuffer contentFile = msg.nioBuffer();

                Path path = Paths.get(myDir, name.toString());
                RandomAccessFile file = new RandomAccessFile(path.toFile(), "rw");
                FileChannel channel = file.getChannel();
                channel.write(contentFile);
                channel.close();
            } else if (marker == Marker.SEND_LIST_FILES.getValue()) {
                updateServerListFiles(msg);
            }
        });

        Platform.runLater(()-> cListView.getItems().addAll(getFiles(myDir)));
    }

    public void upload(ActionEvent actionEvent) {
        try {
            Object file = cListView.getSelectionModel().getSelectedItem();
            if (file == null){
                return;
            }
            String fileName = file.toString();
            int nameLength = fileName.getBytes(StandardCharsets.UTF_8).length;
            byte[] contentFile = Files.readAllBytes(Paths.get(myDir, fileName));
            ByteBuffer message = ByteBuffer.allocate(1 + 4 + nameLength + contentFile.length);
            message.put(Marker.UPLOAD_FILE.getValue())
                   .putInt(nameLength)
                   .put(fileName.getBytes(StandardCharsets.UTF_8))
                   .put(contentFile)
                   .flip();
            network.sendMessage(Unpooled.wrappedBuffer(message));
            message.clear();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void download(ActionEvent actionEvent) {
        Object file = sListView.getSelectionModel().getSelectedItem();
        if (file == null){
            return;
        }
        String fileName = file.toString();
        ByteBuffer message = ByteBuffer.allocate(1 + 4 + fileName.getBytes().length);
        message.put(Marker.DOWNLOAD_FILE.getValue())
                .putInt(fileName.getBytes().length)
                .put(fileName.getBytes())
                .flip();
        network.sendMessage(Unpooled.wrappedBuffer(message));
        message.clear();
    }

    public List<String> getFiles(String nameDir) {
        Path dir = Paths.get(nameDir);
        List<String> listFiles = new LinkedList<>();
        try {
            Files.list(dir)
                    .forEach(path -> listFiles.add(path.getFileName().toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listFiles;
    }


    public void updateServerListFiles(ByteBuf msg) {
        StringBuilder listFiles = new StringBuilder();
        while (msg.isReadable()) {
            listFiles.append((char) msg.readByte());
        }
        Platform.runLater(()-> {
            sListView.getItems().clear();
            sListView.getItems().addAll(Arrays.asList(listFiles.toString()
                                                                .split(",")));
        });
    }
}
