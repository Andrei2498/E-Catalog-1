package view;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class ProfesorAbsente {

    /*----Tabela de adaugare nota----*/
    @FXML
    TableView<AfisareNotaSimpla> tabelaAdaugareAbsente;
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
    TableView<AfisareNote> tabelaVizionareAbsente;
    @FXML
    TableColumn<AfisareNote,String> coloanaVizionareNume;
    @FXML
    TableColumn<AfisareNote,String> coloanaVizionarePrenume;
    @FXML
    TableColumn<AfisareNote, LocalDate> coloanaVizionareData;
    @FXML
    private TextField filterFieldAfisare;





}
