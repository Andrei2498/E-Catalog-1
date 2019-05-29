package view;

import controller.NotaController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import javafx.scene.control.*;
import java.net.URL;
import java.util.ResourceBundle;

public class AdaugareNota implements Initializable {

    @FXML
    private Text idElev;
    @FXML
    private Text numeElev;
    @FXML
    private Text prenumeElev;
    @FXML
    private Spinner<Integer> valoareNota;
    @FXML
    private CheckBox isTeza;
    @FXML
    private Button butonAdaugare;

    public void buttonAdaugare(ActionEvent event){
        NotaController nota= new NotaController();
        if (isTeza.isSelected()){
            nota.insertNota(Integer.parseInt(idElev.getText()),Client.profesor.getMaterie().getId(),Client.profesor.getId(),valoareNota.getValue(),true);
        } else {
            nota.insertNota(Integer.parseInt(idElev.getText()),Client.profesor.getMaterie().getId(),Client.profesor.getId(),valoareNota.getValue(),false);
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idElev.setText(Client.action.idElev.toString());
        numeElev.setText(Client.action.numeElev);
        prenumeElev.setText(Client.action.prenumeElev);
        valoareNota.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10));
    }
}
