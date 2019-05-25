package view;

import javafx.beans.binding.Bindings;
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
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ProfesorAbsente implements Initializable {

    /*----Tabela de adaugare nota----*/
    @FXML
    TableView<AbsenteElevi> tabelaAdaugareAbsente;
    @FXML
    TableColumn<AbsenteElevi, Integer> coloanaID;
    @FXML
    TableColumn<AbsenteElevi, String> coloanaAbsentaNume;
    @FXML
    TableColumn<AbsenteElevi,String> coloanaAbsentaPrenume;
    @FXML
    private TextField filterFieldAdaugare;

    /*----Tabela de vizionare nota----*/
    @FXML
    TableView<AfisareAbsente> tabelaVizionareAbsente;
    @FXML
    TableColumn<AfisareAbsente,String> coloanaVizionareNume;
    @FXML
    TableColumn<AfisareAbsente,String> coloanaVizionarePrenume;
    @FXML
    TableColumn<AfisareAbsente, LocalDate> coloanaVizionareData;
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

    private ObservableList<AbsenteElevi> getAbsenteAdaugare(){
        ObservableList<AbsenteElevi> absente= FXCollections.observableArrayList();
        absente.add(new AbsenteElevi(1,"Miron","Andrei"));
        absente.add(new AbsenteElevi(2,"Pirlog","Marcel"));
        absente.add(new AbsenteElevi(3,"adasd","3sdfsdfsdf"));
        return absente;
    }

    private ObservableList<AfisareAbsente> getAbsente(){
        ObservableList<AfisareAbsente> absente=FXCollections.observableArrayList();
        absente.add(new AfisareAbsente("Miron","Andrei",LocalDate.of(2019,2,17)));
        absente.add(new AfisareAbsente("Pirlog","Marcel",LocalDate.of(2019,7,15)));
        return absente;

    }

    private void addAbsenta(int ID,String nume,String prenume){
        Client.action.setElevSelectat(ID,nume,prenume);
        try{
            Stage primaryStage=new Stage();
            Parent root= FXMLLoader.load(getClass().getResource("adaugareAbsenta.fxml"));
            primaryStage.setTitle("E-Catalog Adaugare Nota");
            primaryStage.resizableProperty().setValue(Boolean.FALSE);
            Scene scene=new Scene(root);
            primaryStage.setScene(scene);
            //     primaryStage.getIcons().add(new Image("file:///C:/Users/Andrei/Desktop/JavaIcon.jpg"));
            primaryStage.show();
        }
        catch (IOException er){er.printStackTrace();}
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        coloanaID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        coloanaAbsentaNume.setCellValueFactory(cellData -> cellData.getValue().numeProperty());
        coloanaAbsentaPrenume.setCellValueFactory(cellData->cellData.getValue().prenumeProperty());
        FilteredList<AbsenteElevi> filteredData = new FilteredList<>(getAbsenteAdaugare(), p -> true);
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
        SortedList<AbsenteElevi> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tabelaAdaugareAbsente.comparatorProperty());
        tabelaAdaugareAbsente.setItems(sortedData);
        tabelaAdaugareAbsente.setRowFactory(tv->{
            TableRow<AbsenteElevi> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (! row.isEmpty() && event.getButton()== MouseButton.SECONDARY) {
                    AbsenteElevi clickedRow = row.getItem();
                    addAbsenta(clickedRow.getID(),clickedRow.getNume().getValue(),clickedRow.getPrenume().getValue());
                }
            });
            return row ;
        });



        coloanaVizionareNume.setCellValueFactory(cellData -> cellData.getValue().numeProperty());
        coloanaVizionarePrenume.setCellValueFactory(cellData->cellData.getValue().prenumeProperty());
        coloanaVizionareData.setCellValueFactory(new PropertyValueFactory<>("data"));
        FilteredList<AfisareAbsente> filteredData1 = new FilteredList<>(getAbsente(), p -> true);
        filterFieldAfisare.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData1.setPredicate(person -> {
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
        SortedList<AfisareAbsente> sortedData1 = new SortedList<>(filteredData1);
        sortedData1.comparatorProperty().bind(tabelaVizionareAbsente.comparatorProperty());
        tabelaVizionareAbsente.setItems(sortedData1);
    }
}
