import javafx.collections.FXCollections;
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




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idElev.setText(Client.action.idElev.toString());
        numeElev.setText(Client.action.numeElev);
        prenumeElev.setText(Client.action.prenumeElev);
        valoareNota.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10));
    }
}
