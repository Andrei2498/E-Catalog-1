package view;

import controller.MaterieController;
import controller.NotaController;
import entity.Materie;
import entity.Nota;
import entity.Profesor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.scene.control.TableView;
import jdk.nashorn.internal.runtime.ListAdapter;
import oracle.ons.Cli;
import view.Absenta;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class StudentAbsente implements Initializable {


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

    private int inceputIntervalMaterii;
    private int sfarsitIntervalMaterii;


    /*Button pannel methods for changing scene.*/

    public void buttonProfilePressed(ActionEvent event){
        Client.action.setEvent(event);
        Client.action.setStage("Profile");
    }

    public void buttonMarksPressed(ActionEvent event){
        Client.action.setEvent(event);
        Client.action.setStage("Note");
    }

    public void buttonStatisticiPressed(ActionEvent event){
        Client.action.setEvent(event);
        Client.action.setStage("Statistici");
    }

    public void buttonAbsentePressed(ActionEvent event){
        Client.action.setEvent(event);
        getAbsente();
        getData();
    }


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
                inceputIntervalMaterii=59;
                sfarsitIntervalMaterii=73;
                filozofie.setVisible(false);
                psihologie.setVisible(false);
                economie.setVisible(false);
                universala.setVisible(false);
                informatica.setVisible(false);
                break;
            case "10":
                inceputIntervalMaterii=74;
                sfarsitIntervalMaterii=88;
                filozofie.setVisible(false);
                logica.setVisible(false);
                economie.setVisible(false);
                universala.setVisible(false);
                informatica.setVisible(false);
                break;
            case "11":
                inceputIntervalMaterii=89;
                sfarsitIntervalMaterii=99;
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
                inceputIntervalMaterii=100;
                sfarsitIntervalMaterii=110;
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
//        System.out.println("Clasa elev: " + Client.action.anElev);
        switch (Client.action.anElev){
            case "9":
                inceputIntervalMaterii=1;
                sfarsitIntervalMaterii=16;
                filozofie.setVisible(false);
                psihologie.setVisible(false);
                economie.setVisible(false);
                universala.setVisible(false);
                break;
            case "10":
                inceputIntervalMaterii=17;
                sfarsitIntervalMaterii=32;
                logica.setVisible(false);
                filozofie.setVisible(false);
                economie.setVisible(false);
                universala.setVisible(false);
                break;
            case "11":
                inceputIntervalMaterii=33;
                sfarsitIntervalMaterii=45;
                logica.setVisible(false);
                psihologie.setVisible(false);
                economie.setVisible(false);
                universala.setVisible(false);
                desen.setVisible(false);
                muzica.setVisible(false);
                tic.setVisible(false);
                break;
            case "12":
                inceputIntervalMaterii=46;
                sfarsitIntervalMaterii=58;
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

    /*---- Populare lista cu absentele studentului.----*/
    private ObservableList<Absenta> getAbsente() {
        ObservableList<Absenta> absente = FXCollections.observableArrayList();
        List<Nota> absenta = new NotaController().getAbsenteElevi(Client.elev.getId());
        System.out.println(absenta);
        for (Nota nota :absenta ) {
            absente.add(new Absenta(nota.getDataNotare(),nota.getProfesor().getNume() + ' ' + nota.getProfesor().getPrenume(),nota.getMaterie().getNume()));
        }
        return absente;
    }

    /*----Populare lista de categorii din PieChart----*/
    private void getData(){
        ObservableList<PieChart.Data> data=FXCollections.observableArrayList();
        int numarAbsenteTotal = new NotaController().getTotalAbsente(Client.elev.getId());
        List<Materie> materies = new LinkedList<>();
        for (Profesor profesor :Client.elev.getProfesors()  ) {
            materies.add(profesor.getMaterie());
        }
        for (Materie materie : materies ) {
            data.add(new PieChart.Data(materie.getNume(),(100 / numarAbsenteTotal) * new NotaController().getNumarAbsenta(materie.getId(),Client.elev.getId())));
        }
        setAbsentaData(numarAbsenteTotal);
        pieChart.setData(data);
    }

    public void setAbsentaData(int absente){
        numarAbsente.setText(Integer.toString(absente));
        penalizareActuala.setText(Integer.toString(absente / 10));
        pragPenalizare.setText(Integer.toString((absente / 10 + 1) * 10));
        if(absente > 40){
            indicePromovare.setText("Nepromovat");
        } else {
            indicePromovare.setText("Promovat");
        }
    }

    /*---- Initializare variabile.----*/
    //DE IMPLEMENTAT METODELE DE INITIALIZARE!!!!
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        coloanaData.setCellValueFactory(new PropertyValueFactory<>("data"));
        coloanaProfesor.setCellValueFactory(new PropertyValueFactory<>("profesor"));
        coloanaMaterie.setCellValueFactory(new PropertyValueFactory<>("materie"));
        tabelaAbsente.setItems(getAbsente());
        getData();
        setareButoaneNote();
        //de setat
    }
}
