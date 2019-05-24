package view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.image.ImageView;



import javafx.event.ActionEvent;
import oracle.ons.Cli;

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
        firstName.setText(Client.elev.getNume());
        secondName.setText(Client.elev.getPrenume());
        studentAge.setText(Integer.toString(Client.elev.getVarsta()));
        studentGender.setText(Client.elev.getGen());
        titluLiceu.setText(Client.elev.getLiceu().getTitlu());
        numeLiceu.setText(Client.elev.getLiceu().getNume());
        orasLiceu.setText(Client.elev.getLiceu().getJudet());
        anClasa.setText(Client.elev.getClasa());
        if(Client.elev.getProfil().compareTo("real") == 0){
            profilStudent.setLayoutX(profilStudent.getLayoutX()+10);
            profilStudent.setText("Real");
        } else {
            profilStudent.setText("Uman");
        }
        studentJudet.setText(Client.elev.getLiceu().getJudet());
        studentLocalitate.setText(studentJudet.getText());
        String[] adresa = Client.elev.getAdresa().split(">>");
        studentStrada.setText(adresa[0]);
        if(adresa[1].compareTo("-") == 0){
            studentBloc.setText("-");
            studentScara.setText("-");
            studentNumarLocuinta.setText(adresa[3]);
        } else {
            studentBloc.setText(adresa[1]);
            studentScara.setText(adresa[2]);
            studentNumarLocuinta.setText(adresa[3]);
        }
        studentNumarTelefon.setText(Client.elev.getNr_telefon());
        studentEmail.setText(Client.elev.getEmail());
        File file;
        if(Client.elev.getGen().compareTo("Masculin") == 0){
            file =new File("src/img/male.png");
            Image image=new Image(file.toURI().toString());
            imagineProfil.setImage(image);
        } else {
            file =new File("src/img/female.png");
            Image image=new Image(file.toURI().toString());
            imagineProfil.setImage(image);
        }
    }
}
