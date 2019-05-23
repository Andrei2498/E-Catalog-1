package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class Profesor implements Initializable {
    /*----Date personale despre student----*/
    @FXML
    private Text firstName;
    @FXML
    private Text secondName;
    @FXML
    private Text studentAge;
    @FXML
    private Text studentGender;

    /*----Date despre institutia de invatamant si clasa pe care o frecventeaza studentul----*/
    @FXML
    private Text titluLiceu;
    @FXML
    private Text numeLiceu;
    @FXML
    private Text orasLiceu;
    @FXML
    private Text anClasa;
    @FXML
    private Text profilStudent;

    /*----Date de contact----*/
    @FXML
    private Text studentJudet;
    @FXML
    private Text studentLocalitate;
    @FXML
    private Text studentStrada;
    @FXML
    private Text studentBloc;
    @FXML
    private Text studentScara;
    @FXML
    private Text studentNumarLocuinta;
    @FXML
    private Text studentNumarTelefon;
    @FXML
    private Text studentEmail;


    /*----Date scena----*/
    @FXML
    private AnchorPane rootPane;
    @FXML
    private ImageView imagineProfil;

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
        firstName.setText(Client.profesor.getNume());
        secondName.setText(Client.profesor.getPrenume());
        
    }
}
