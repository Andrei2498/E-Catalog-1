package view;

import controller.MaterieController;
import controller.NotaController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import oracle.ons.Cli;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class StudentNote implements Initializable {

    /*----Date despre materii-----*/
    @FXML
    private MenuItem romana;
    @FXML
    private MenuItem istorie;
    @FXML
    private MenuItem geografie;
    @FXML
    private MenuItem logica;
    @FXML
    private MenuItem filozofie;
    @FXML
    private MenuItem franceza;
    @FXML
    private MenuItem engleza;
    @FXML
    private MenuItem psihologie;
    @FXML
    private MenuItem economie;
    @FXML
    private MenuItem universala;
    @FXML
    private MenuItem matematica;
    @FXML
    private MenuItem fizica;
    @FXML
    private MenuItem chimie;
    @FXML
    private MenuItem biologie;
    @FXML
    private MenuItem informatica;
    @FXML
    private MenuItem tic;
    @FXML
    private MenuItem desen;
    @FXML
    private MenuItem muzica;
    @FXML
    private MenuItem sport;
    @FXML
    private MenuItem religie;

    /*----Date tabela note.----*/
    @FXML
    TableView tabelaNote;
    @FXML
    TableColumn<Nota,Integer> coloanaNota;
    @FXML
    TableColumn<Nota, LocalDate> coloanaData;
    @FXML
    TableColumn<Nota,String> coloanaProfesor;
    @FXML
    TableColumn<Nota,String> coloanaTeza;

    /*----Date grafic note per timp----*/
    @FXML
    LineChart<String,Integer> graficNote;

    /*----Date despre note, relevante utilizatorului----*/
    @FXML
    Text numarNote;
    @FXML
    Text numarNoteNecesar;
    @FXML
    Text indicePromovare;
    @FXML
    Text medieCurenta;


    public String actiune = ((MenuItem)Client.action.event.getSource()).getText();

    public ActionEvent event;
    /*Button pannel methods for changing scene.*/

    private void setareButoaneNote(){
        System.out.println("Client profil: " + Client.action.profilelev);
        switch (Client.action.profilelev){
            case "uman":
                setareButoaneUmanNote();
                break;
            case "real":
                setareButoaneRealNote();
                break;
        }
    }

    private void setareButoaneUmanNote(){
        System.out.println("Clasa elev: " + Client.action.anElev);
        switch (Client.action.anElev){
            case "9":
                filozofie.setVisible(false);
                psihologie.setVisible(false);
                economie.setVisible(false);
                universala.setVisible(false);
                informatica.setVisible(false);
                break;
            case "10":
                filozofie.setVisible(false);
                logica.setVisible(false);
                economie.setVisible(false);
                universala.setVisible(false);
                informatica.setVisible(false);
                break;
            case "11":
                psihologie.setVisible(false);
                logica.setVisible(false);
                economie.setVisible(false);
                fizica.setVisible(false);
                informatica.setVisible(false);
                desen.setVisible(false);
                muzica.setVisible(false);
                chimie.setVisible(false);
                biologie.setVisible(false);
                break;
            case "12":
                psihologie.setVisible(false);
                logica.setVisible(false);
                filozofie.setVisible(false);
                fizica.setVisible(false);
                informatica.setVisible(false);
                desen.setVisible(false);
                muzica.setVisible(false);
                chimie.setVisible(false);
                biologie.setVisible(false);
                break;

        }
    }

    private void setareButoaneRealNote(){
        System.out.println("Clasa elev: " + Client.action.anElev);
        switch (Client.action.anElev){
            case "9":
                filozofie.setVisible(false);
                psihologie.setVisible(false);
                economie.setVisible(false);
                universala.setVisible(false);
                break;
            case "10":
                logica.setVisible(false);
                filozofie.setVisible(false);
                economie.setVisible(false);
                universala.setVisible(false);
                break;
            case "11":
                logica.setVisible(false);
                psihologie.setVisible(false);
                economie.setVisible(false);
                universala.setVisible(false);
                desen.setVisible(false);
                muzica.setVisible(false);
                tic.setVisible(false);
                break;
            case "12":
                logica.setVisible(false);
                psihologie.setVisible(false);
                filozofie.setVisible(false);
                universala.setVisible(false);
                desen.setVisible(false);
                muzica.setVisible(false);
                tic.setVisible(false);
                break;
        }
    }

    public void buttonProfilePressed(ActionEvent event){
        Client.action.setEvent(event);
        Client.action.setStage("Profile");
    }

    public void buttonAbsentePressed(ActionEvent event){
        Client.action.setEvent(event);
        Client.action.setStage("Absente");
    }

    public void buttonStatisticiPressed(ActionEvent event){
        Client.action.setEvent(event);
        Client.action.setStage("Statistici");
    }

    public void buttonMateriiPressed(ActionEvent event){
        Client.action.setEvent(event);
        actiune=((MenuItem)event.getSource()).getText();
        System.out.println(actiune );
        tabelaNote.setItems(getNote());
        calculateAverage();
        graficNote.getData().clear();
        setChart();
    }

    /*---- Populare lista cu notele studentului.----*/
    private ObservableList<Nota> getNote(){
        ObservableList<Nota> note= FXCollections.observableArrayList();
//        MaterieController materieController = new MaterieController();
//        System.out.println(Integer.parseInt(Client.action.anElev));
//        System.out.println(actiune);
//        System.out.println(Client.action.profilelev);
//        System.out.println(materieController.getSpecificMateire(Integer.parseInt(Client.action.anElev),actiune, Client.action.profilelev));
        List<entity.Nota> notes = new NotaController().getNota(Client.elev.getId(),new MaterieController().getSpecificMateire(Integer.parseInt(Client.action.anElev),actiune, Client.action.profilelev));
        for (entity.Nota nota: notes) {
            note.add(new Nota(nota.getNota(),nota.getDataNotare(),nota.getProfesor().getNume(),"Nu"));
        }
        return note;
    }
    // 895IWF878ILV
    // E.agae.ana

    /*---- Initializare variabile.----*/
    //DE IMPLEMENTAT METODELE DE INITIALIZARE!!!!


    private void calculateAverage(){
        Integer sumaNote=0;
        Integer numarNoteTabela=0;
        float medieNote=0;
        for(Object nota : tabelaNote.getItems()){
            sumaNote=sumaNote+coloanaNota.getCellObservableValue((Nota)nota).getValue();
            numarNoteTabela++;
        }
        if(numarNoteTabela!=0)
            medieNote=sumaNote/numarNoteTabela;
        numarNote.setText(numarNoteTabela.toString());
        medieCurenta.setText(Float.toString(medieNote));
        if(medieNote>5.0&&numarNoteTabela>3)
            indicePromovare.setText("Promovat");
        else indicePromovare.setText("Nepromovat");
    }

    private void setChart(){
        System.out.println("Sunt aici");
        List<Integer> columnNota = new ArrayList<>();
        List<LocalDate> columnData= new ArrayList<>();
        for (Object item : tabelaNote.getItems()) {
            columnNota.add(coloanaNota.getCellObservableValue((Nota)item).getValue());
            columnData.add(coloanaData.getCellObservableValue((Nota)item).getValue());
        }
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        for(int i=0;i<columnData.size();i++) {
            series.getData().add(new XYChart.Data<>(columnData.get(i).toString(),columnNota.get(i)));
        }
        graficNote.getData().add(series);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        coloanaNota.setCellValueFactory(new PropertyValueFactory<>("nota"));
        coloanaData.setCellValueFactory(new PropertyValueFactory<>("data"));
        coloanaProfesor.setCellValueFactory(new PropertyValueFactory<>("profesor"));
        coloanaTeza.setCellValueFactory(new PropertyValueFactory<>("isTeza"));
        setareButoaneNote();
        numarNoteNecesar.setText("3");
        tabelaNote.setItems(getNote());
        setChart();
        calculateAverage();
    }
}
