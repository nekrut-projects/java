package client;

import java.net.URL;
import java.util.ResourceBundle;

import io.netty.buffer.ByteBuf;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class Controller implements Initializable {

    public ListView cListView;
    public TextField cTextField;
    public Button downloadButton;
    public Button uploadButton;
    public ListView sListView;
    public TextField sTextField;
    private Network network;

    public void send(ActionEvent actionEvent) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
