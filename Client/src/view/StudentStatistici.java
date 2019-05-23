package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.MenuItem;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentStatistici implements Initializable {

    @FXML
    private BarChart<String,Double> graficNote;
    @FXML
    private BarChart<String,Double> graficLiceu;
    @FXML
    PieChart repartizareProfil;
    @FXML
    PieChart repartizareClasa;

    public void buttonProfilePressed(ActionEvent event){
        Client.action.setEvent(event);
        Client.action.setStage("Profile");
    }

    public void buttonAbsentePressed(ActionEvent event){
        Client.action.setEvent(event);
        Client.action.setStage("Absente");
    }

    public void buttonMarksPressed(ActionEvent event){
        Client.action.setEvent(event);
        Client.action.setStage("Note");
    }

    public void buttonStatisticsPressed(ActionEvent event){
        switch (((MenuItem)event.getSource()).getText()){
            case "Top 10":
                repartizareProfil.setVisible(false);
                repartizareClasa.setVisible(false);
                graficNote.setVisible(true);
                graficLiceu.setVisible(true);
                break;
            case "Repartizare elevi":
                graficNote.setVisible(false);
                graficLiceu.setVisible(false);
                repartizareClasa.setVisible(true);
                repartizareProfil.setVisible(true);
                break;
        }
    }

    private ObservableList<PieChart.Data> getData1(){
        ObservableList<PieChart.Data> data= FXCollections.observableArrayList();
        data.addAll(new PieChart.Data("Real",45),
                new PieChart.Data("Uman",55));
        return data;
    }

    private ObservableList<PieChart.Data> getData2(){
        ObservableList<PieChart.Data> data=FXCollections.observableArrayList();
        data.addAll(new PieChart.Data("9A",40),
                new PieChart.Data("9B",25),
                new PieChart.Data("9C",35));
        return data;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        XYChart.Series<String, Double> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>("Andrei",5.6));
        series.getData().add(new XYChart.Data<>("Andrei1",5.8));
        series.getData().add(new XYChart.Data<>("Andrei2",6.5));
        series.getData().add(new XYChart.Data<>("Andrei3",7.2));
        series.getData().add(new XYChart.Data<>("Andrei4",7.8));
        series.getData().add(new XYChart.Data<>("Andrei5",8.2));
        series.getData().add(new XYChart.Data<>("Andrei6",8.6));
        series.getData().add(new XYChart.Data<>("Andrei7",8.9));
        series.getData().add(new XYChart.Data<>("Andrei8",9.2));
        series.getData().add(new XYChart.Data<>("Andrei9",9.9));
        graficNote.getData().add(series);
        graficLiceu.getData().add(series);
        repartizareClasa.setData(getData2());
        repartizareProfil.setData(getData1());
    }
}
