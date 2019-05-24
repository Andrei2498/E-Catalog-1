package view;

import entity.Elev;
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
        Elev elev = Client.parinte.getElev();
        File file;
        if(Client.parinte.getGen().compareTo("Masculin") == 0){
           file =new File("src/img/male.png");
        } else {
            file =new File("src/img/female.png");
        }
        Image image=new Image(file.toURI().toString());
        imagineProfil.setImage(image);
        numeElev.setText(elev.getNume());
        prenumeElev.setText(elev.getPrenume());
        varstaElev.setText(Integer.toString(elev.getVarsta()));
        genElev.setText(elev.getGen());
        liceuElev.setText(elev.getLiceu().getNume());
        clasaElev.setText(elev.getClasa());
        numeParinte.setText(Client.parinte.getNume());
        prenumeParinte.setText(Client.parinte.getPrenume());
        varstaParinte.setText(Integer.toString(Client.parinte.getVarsta()));
        genParinte.setText(Client.parinte.getGen());
        parinteJudet.setText(elev.getLiceu().getJudet());
        parinteLocalitate.setText(elev.getLiceu().getJudet());
        String[] adresa = Client.parinte.getAdresa().split(">>");
        parinteStrada.setText(adresa[0]);
        if(adresa[1].compareTo("-") == 0){
            parinteBloc.setText("-");
            parinteScara.setText("-");
            parinteNumarLocuinta.setText(adresa[3]);
        } else {
            parinteBloc.setText(adresa[1]);
            parinteScara.setText(adresa[2]);
            parinteNumarLocuinta.setText(adresa[3]);
        }
        parinteNumarTelefon.setText(Client.parinte.getNr_telefon());
        parinteEmail.setText(Client.parinte.getEmail());
    }
}
