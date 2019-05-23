import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.image.ImageView;



import javafx.event.ActionEvent;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Student implements Initializable {

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

    /*---- Initializare variabile.----*/
    //DE IMPLEMENTAT METODELE DE INITIALIZARE!!!!
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        firstName.setText("Miron");
        secondName.setText("Andrei Daniel");
        numeLiceu.setText("sasdsadasd");
        File file=new File("src/img/male.png");
        Image image=new Image(file.toURI().toString());
        imagineProfil.setImage(image);
        System.out.println(numeLiceu.getLayoutX());
        numeLiceu.setLayoutX(numeLiceu.getLayoutX()-numeLiceu.getText().length()*4.5);
    }
}
