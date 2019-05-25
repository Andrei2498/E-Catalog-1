package view;

import controller.NotaController;
import entity.Elev;
import entity.Nota;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ProfesorNote implements Initializable {

    /*----Tabela de adaugare nota----*/
    @FXML
    TableView<AfisareNotaSimpla> tabelaAdaugareNote;
    @FXML
    TableColumn<AfisareNotaSimpla, Integer> coloanaID;
    @FXML
    TableColumn<AfisareNotaSimpla, String> coloanaNume;
    @FXML
    TableColumn<AfisareNotaSimpla,String> coloanaPrenume;
    @FXML
    private TextField filterFieldAdaugare;

    /*----Tabela de vizionare nota----*/
    @FXML
    TableView<AfisareNote> tabelaVizionareNote;
    @FXML
    TableColumn<AfisareNote,String> coloanaVizionareNume;
    @FXML
    TableColumn<AfisareNote,String> coloanaVizionarePrenume;
    @FXML
    TableColumn<AfisareNote,Integer> coloanaVizionareValoare;
    @FXML
    TableColumn<AfisareNote, LocalDate> coloanaVizionareData;
    @FXML
    private TextField filterFieldAfisare;


    public void buttonMarksPressed(ActionEvent event){
        Client.action.setEvent(event);
        Client.action.setStage("Note");
    }

    public void buttonAbsentePressed(ActionEvent event){
        Client.action.setEvent(event);
        Client.action.setStage("Absente");
    }

    public void buttonStatisticiPressed(ActionEvent event){
        Client.action.setEvent(event);
        Client.action.setStage("Statistici");
    }

    public void buttonProfilePressed(ActionEvent event){
        Client.action.setEvent(event);
        Client.action.setStage("Profile");
    }

    public void buttonObservatiiPressed(ActionEvent event){
        Client.action.setEvent(event);
        Client.action.setStage("Observatii");
    }

    private ObservableList<AfisareNotaSimpla> getNotaSimpla(){
        ObservableList<AfisareNotaSimpla> note= FXCollections.observableArrayList();
//        note.add(new AfisareNotaSimpla(1,"Miron","Andrei"));
//        note.add(new AfisareNotaSimpla(2,"Pirlog","Marcel"));
//        note.add(new AfisareNotaSimpla(3,"adasd","3sdfsdfsdf"));
        for (Elev elev : Client.elevs) {
            note.add(new AfisareNotaSimpla(elev.getId(),elev.getNume(),elev.getPrenume()));
        }
        return note;
    }

    private ObservableList<AfisareNote> getNote(){
        ObservableList<AfisareNote> note=FXCollections.observableArrayList();
        NotaController notaController = new NotaController();
        note.add(new AfisareNote("Miron","Andrei",6,LocalDate.of(2019,02,17)));
        note.add(new AfisareNote("Pirlog","Marcel",5,LocalDate.of(2019,07,15)));
//        for (Nota nota: notaController.getNota(1,65)) {
//            note.add(new AfisareNote(nota.getElev().getNume(),nota.getElev().getPrenume(),
//                        nota.getNota(),nota.getDataNotare()));
//        }
        return note;

    }

    private void addNota(int ID,String nume,String prenume){
        Client.action.setElevSelectat(ID,nume,prenume);
        try{
            Stage primaryStage=new Stage();
            Parent root= FXMLLoader.load(getClass().getResource("adaugareNota.fxml"));
            primaryStage.setTitle("E-Catalog Adaugare Nota");
            primaryStage.resizableProperty().setValue(Boolean.FALSE);
            Scene scene=new Scene(root);
            primaryStage.setScene(scene);
//            primaryStage.getIcons().add(new Image("file:///C:/Users/Andrei/Desktop/JavaIcon.jpg"));
            primaryStage.show();
        }
        catch (IOException er){er.printStackTrace();}
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        /*----Initializare tabela adaugare nota----*/

        coloanaID.setCellValueFactory(new PropertyValueFactory<>("idElev"));
        coloanaNume.setCellValueFactory(cellData -> cellData.getValue().getNume());
        coloanaPrenume.setCellValueFactory(cellData->cellData.getValue().getPrenume());
        FilteredList<AfisareNotaSimpla> filteredData = new FilteredList<>(getNotaSimpla(), p -> true);
        filterFieldAdaugare.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (person.getNume().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (person.getPrenume().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                return false; // Does not match.
            });
        });
        SortedList<AfisareNotaSimpla> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tabelaAdaugareNote.comparatorProperty());
        tabelaAdaugareNote.setItems(sortedData);

        tabelaAdaugareNote.setRowFactory(tv->{
            TableRow<AfisareNotaSimpla> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (! row.isEmpty() && event.getButton()== MouseButton.SECONDARY) {
                    AfisareNotaSimpla clickedRow = row.getItem();
                    addNota(clickedRow.getIdElev(),clickedRow.getNume().getValue(),clickedRow.getPrenume().getValue());
                }
            });
            return row ;
        });


        /*----Initializare tabela afisare note----*/
        coloanaVizionareNume.setCellValueFactory(cellData -> cellData.getValue().numeProperty());
        coloanaVizionarePrenume.setCellValueFactory(cellData -> cellData.getValue().prenumeProperty());
        coloanaVizionareValoare.setCellValueFactory(new PropertyValueFactory<>("valoare"));
        coloanaVizionareData.setCellValueFactory(new PropertyValueFactory<>("data"));
        System.out.println(getNote());
        FilteredList<AfisareNote> filteredDataAfisare = new FilteredList<>(getNote(), p -> true);
        filterFieldAfisare.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredDataAfisare.setPredicate(person -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                if (person.getNume().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (person.getPrenume().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                return false; // Does not match.
            });
        });

        SortedList<AfisareNote> sortedData1 = new SortedList<>(filteredDataAfisare);
        sortedData1.comparatorProperty().bind(tabelaVizionareNote.comparatorProperty());
        tabelaVizionareNote.setItems(sortedData1);


    }
}
