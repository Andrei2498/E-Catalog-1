package view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class AdaugareAbsenta implements Initializable {

    @FXML
    private Text idElev;
    @FXML
    private Text numeElev;
    @FXML
    private Text prenumeElev;


    public void buttonAction(ActionEvent event){
        System.out.println("DE IMPLEMENTAT");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idElev.setText(Client.action.idElev.toString());
        numeElev.setText(Client.action.numeElev);
        prenumeElev.setText(Client.action.prenumeElev);
    }
}
