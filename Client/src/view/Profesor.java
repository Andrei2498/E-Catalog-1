package view;

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
    @FXML
    private Text materieId;

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

    public void buttonAbsentePressed(ActionEvent event){
        Client.action.setPane(rootPane);
        Client.action.setEvent(event);
        Client.action.setStage("Absente");
    }

    public void buttonMarksPressed(ActionEvent event){
        Client.action.setPane(rootPane);
        Client.action.setEvent(event);
        Client.action.setStage("Note");
    }

    public void buttonStatisticiPressed(ActionEvent event){
        Client.action.setPane(rootPane);
        Client.action.setEvent(event);
        Client.action.setStage("Statistici");
    }

    public void buttonObservatiiPressed(ActionEvent event){
        Client.action.setPane(rootPane);
        Client.action.setEvent(event);
        Client.action.setStage("Observatii");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        firstName.setText(Client.profesor.getNume());
        secondName.setText(Client.profesor.getPrenume());
        studentAge.setText(Integer.toString(Client.profesor.getVarsta()));
        studentGender.setText(Client.profesor.getGen());
        titluLiceu.setText((Client.profesor.getLiceu().getTitlu()));
        numeLiceu.setText(Client.profesor.getLiceu().getNume());
        orasLiceu.setText(Client.profesor.getLiceu().getJudet());
        anClasa.setText(Integer.toString(Client.profesor.getMaterie().getClasa()));
        if(Client.profesor.getMaterie().getProfil().compareTo("real") == 0){
            profilStudent.setLayoutX(profilStudent.getLayoutX()+10);
            profilStudent.setText("Real");
        } else {
            profilStudent.setText("Uman");
        }
        studentJudet.setText(Client.profesor.getLiceu().getJudet());
        studentLocalitate.setText(studentJudet.getText());
        String[] adresa = Client.profesor.getAdresa().split(">>");
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
        studentNumarTelefon.setText(Client.profesor.getNr_telefon());
        studentEmail.setText(Client.profesor.getEmail());
        String string = Client.profesor.getMaterie().getNume();
        materieId.setText(string);
//        System.out.println(Client.profesor.getMaterie().toString());
        File file;
        if(studentGender.getText().compareTo("Masculin") == 0){
            file=new File("src/img/male.png");
        } else {
            file=new File("src/img/female.png");
        }
        Image image=new Image(file.toURI().toString());
        imagineProfil.setImage(image);
    }
}
