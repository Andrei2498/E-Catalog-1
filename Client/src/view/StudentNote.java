package view;

import controller.NotaController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class StudentNote implements Initializable {

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

    @FXML
    Text limbaRomana;
    /*Button pannel methods for changing scene.*/

    public void buttonProfilePressed(ActionEvent event){
        Client.action.setEvent(event);
        Client.action.setStage("Profile");
    }

    public void buttonAbsentePressed(ActionEvent event){
        Client.action.setEvent(event);
        Client.action.setStage("Absente");
    }


    /*---- Populare lista cu notele studentului.----*/
    private ObservableList<Nota> getNote(){
        ObservableList<Nota> note= FXCollections.observableArrayList();
        List<entity.Nota> notes = new NotaController().getNota(1,81);
        for (entity.Nota nota: notes) {
            note.add(new Nota(nota.getNota(),nota.getDataNotare(),nota.getProfesor().getNume(),"Nu"));
        }
//        Nota nota=new Nota(3,LocalDate.of(2019,02,17),"Andrei","Nu");
//        Nota nota1=new Nota(9,LocalDate.of(2019,07,15),"Andrsdsei","Da");
//        Nota nota2=new Nota(6,LocalDate.of(2019,05,25),"Andrefdgdfgi","Da");
//        note.add(nota);
//        note.add(nota1);
//        note.add(nota2);
        return note;
    }
    // 895IWF878ILV
    // E.agae.ana

    /*---- Initializare variabile.----*/
    //DE IMPLEMENTAT METODELE DE INITIALIZARE!!!!

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        float medie=0;
        limbaRomana.setVisible(true);
        coloanaNota.setCellValueFactory(new PropertyValueFactory<>("nota"));
        coloanaData.setCellValueFactory(new PropertyValueFactory<>("data"));
        coloanaProfesor.setCellValueFactory(new PropertyValueFactory<>("profesor"));
        coloanaTeza.setCellValueFactory(new PropertyValueFactory<>("isTeza"));
        tabelaNote.setItems(getNote());
        List<Integer> columnNota = new ArrayList<>();
        List<LocalDate> columnData= new ArrayList<>();
        for (Object item : tabelaNote.getItems()) {
            columnNota.add(coloanaNota.getCellObservableValue((Nota)item).getValue());
            columnData.add(coloanaData.getCellObservableValue((Nota)item).getValue());
        }
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        for(int i=0;i<columnData.size();i++) {
            series.getData().add(new XYChart.Data<>(columnData.get(i).toString(),columnNota.get(i)));
            medie+=columnNota.get(i);
        }
        medie=medie/columnNota.size();
        medieCurenta.setText(String.valueOf(medie));
        numarNote.setText(String.valueOf(columnNota.size()));
        numarNoteNecesar.setText("3");
        if(medie>5.0)
            indicePromovare.setText("Promovat");
        else indicePromovare.setText("Nepromovat");
        graficNote.getData().add(series);

    }
}
