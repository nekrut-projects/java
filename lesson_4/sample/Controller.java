package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    public TextArea textArea;
    @FXML
    public TextField textField;

    public void onClickSend(ActionEvent actionEvent) {
        textArea.setText(textArea.getText() + textField.getText() + "\n");
        textField.setText("");
        textField.requestFocus();
    }

    public void onClickExit(ActionEvent actionEvent) {
        Platform.exit();
    }
}
