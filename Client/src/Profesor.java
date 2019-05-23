import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class Profesor implements Initializable {
    @FXML
    AnchorPane rootPane;

    public void buttonAbsentePressed(javafx.event.ActionEvent event){
        Client.action.setPane(rootPane);
        Client.action.setEvent(event);
        Client.action.setStage("Absente");
    }

    public void buttonMarksPressed(ActionEvent event){
        Client.action.setPane(rootPane);
        Client.action.setEvent(event);
        Client.action.setStage("Note");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
