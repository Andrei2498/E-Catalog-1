import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Parinte implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private ImageView imagineProfil;

    /*-----Date elev-----*/
    @FXML
    private Text numeElev;
    @FXML
    private Text prenumeElev;
    @FXML
    private Text varstaElev;
    @FXML
    private Text genElev;
    @FXML
    private Text liceuElev;
    @FXML
    private Text clasaElev;

    /*----Date Personale Parinte----*/
    @FXML
    private Text numeParinte;
    @FXML
    private Text prenumeParinte;
    @FXML
    private Text varstaParinte;
    @FXML
    private Text genParinte;

    /*----Date Contact Parinte----*/
    @FXML
    private Text parinteJudet;
    @FXML
    private Text parinteLocalitate;
    @FXML
    private Text parinteStrada;
    @FXML
    private Text parinteBloc;
    @FXML
    private Text parinteScara;
    @FXML
    private Text parinteNumarLocuinta;
    @FXML
    private Text parinteNumarTelefon;
    @FXML
    private Text parinteEmail;


    /*Button pannel methods for changing scene.*/

    public void buttonMarksPressed(ActionEvent event){
        Client.action.setPane(rootPane);
        Client.action.setEvent(event);
        Client.action.setStage("Note");
    }

    public void buttonAbsentePressed(ActionEvent event){
        Client.action.setPane(rootPane);
        Client.action.setEvent(event);
        Client.action.setStage("Absente");
    }

    public void buttonStatisticiPressed(ActionEvent event){
        Client.action.setPane(rootPane);
        Client.action.setEvent(event);
        Client.action.setStage("Statistici");
    }

    /*The method for initiliazing all the variables in the current scene.*/
    //DE IMPLEMENTAT METODELE DE INITIALIZARE!!!!

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File file=new File("src/img/male.png");
        Image image=new Image(file.toURI().toString());
        imagineProfil.setImage(image);
    }
}
