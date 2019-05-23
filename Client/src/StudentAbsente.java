import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.scene.control.TableView;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class StudentAbsente implements Initializable {

    /*----Date despre tabela cu absente----*/
    @FXML
    TableView tabelaAbsente;
    @FXML
    TableColumn<Absenta, LocalDate> coloanaData;
    @FXML
    TableColumn<Absenta,String> coloanaMaterie;
    @FXML
    TableColumn<Absenta,String> coloanaProfesor;

    /*----Date despre absente, relevante pentru utilizator----*/
    @FXML
    Text numarAbsente;
    @FXML
    Text penalizareActuala;
    @FXML
    Text pragPenalizare;
    @FXML
    Text indicePromovare;

    /*----Date despre graficul cu absente per timp----*/
    @FXML
    PieChart pieChart;


    /*Button pannel methods for changing scene.*/

    public void buttonProfilePressed(ActionEvent event){
        Client.action.setEvent(event);
        Client.action.setStage("Profile");
    }

    public void buttonMarksPressed(ActionEvent event){
        Client.action.setEvent(event);
        Client.action.setStage("Note");
    }

    /*---- Populare lista cu absentele studentului.----*/
    private ObservableList<Absenta> getAbsente(){
        ObservableList<Absenta> absente= FXCollections.observableArrayList();
        Absenta absenta1=new Absenta(LocalDate.of(2019,02,17),"Romana","Ionescu");
        Absenta absenta2=new Absenta(LocalDate.of(2019,07,15),"Matematica","Popescu");
        Absenta absenta3=new Absenta(LocalDate.of(2019,05,25),"Engleza","Florescu");
        absente.add(absenta1);
        absente.add(absenta2);
        absente.add(absenta3);
        return absente;
    }

    /*----Populare lista de categorii din PieChart----*/
    private ObservableList<PieChart.Data> getData(){
    ObservableList<PieChart.Data> data=FXCollections.observableArrayList();
    data.addAll(new PieChart.Data("Romana",33),
            new PieChart.Data("Matematica",33),
            new PieChart.Data("Engleza",33));
    return data;
    }

    /*---- Initializare variabile.----*/
    //DE IMPLEMENTAT METODELE DE INITIALIZARE!!!!
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        coloanaData.setCellValueFactory(new PropertyValueFactory<>("data"));
        coloanaProfesor.setCellValueFactory(new PropertyValueFactory<>("profesor"));
        coloanaMaterie.setCellValueFactory(new PropertyValueFactory<>("materie"));
        tabelaAbsente.setItems(getAbsente());
        pieChart.setData(getData());
        //de setat
    }
}
