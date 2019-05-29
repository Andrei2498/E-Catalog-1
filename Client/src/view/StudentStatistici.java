package view;

import controller.ElevController;
import entity.Elev;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.MenuItem;
import javafx.util.Pair;
import oracle.ons.Cli;

import java.net.URL;
import java.util.ArrayList;
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
                graficNote.getData().clear();
                graficLiceu.getData().clear();
                setStatistici();
                setTop10clasa();
                break;
            case "Repartizare elevi":
                graficNote.setVisible(false);
                graficLiceu.setVisible(false);
                repartizareClasa.setVisible(true);
                repartizareProfil.setVisible(true);
                getData1();
                getData2();
                break;
        }
    }

    private ObservableList<PieChart.Data> getData1(){
        ObservableList<PieChart.Data> data= FXCollections.observableArrayList();
        int numarReal=new ElevController().getNumberReal(Client.elev.getLiceu().getId());
        int numarUman=new ElevController().getNumberUman(Client.elev.getLiceu().getId());
        data.add(new PieChart.Data("Real",(numarReal*100)/(numarReal+numarUman)));
        data.add(new PieChart.Data("Uman", (numarUman*100)/(numarReal+numarUman)));
        return data;
    }

    private ObservableList<PieChart.Data> getData2(){
        ObservableList<PieChart.Data> data=FXCollections.observableArrayList();
        int numarStudenti=new ElevController().getNumberElevi(Client.elev.getLiceu().getId());
        switch (Client.action.anElev){
            case "9":
                data.addAll(new PieChart.Data("9A",(new ElevController().getNumberPerClass(Client.elev.getLiceu().getId(),"9A")*100)/numarStudenti),
                        new PieChart.Data("9B",(new ElevController().getNumberPerClass(Client.elev.getLiceu().getId(),"9B")*100)/numarStudenti),
                        new PieChart.Data("9C",(new ElevController().getNumberPerClass(Client.elev.getLiceu().getId(),"9C")*100)/numarStudenti),
                        new PieChart.Data("9D",(new ElevController().getNumberPerClass(Client.elev.getLiceu().getId(),"9D")*100)/numarStudenti),
                        new PieChart.Data("9E",(new ElevController().getNumberPerClass(Client.elev.getLiceu().getId(),"9E")*100)/numarStudenti),
                        new PieChart.Data("9F",(new ElevController().getNumberPerClass(Client.elev.getLiceu().getId(),"9F")*100)/numarStudenti),
                        new PieChart.Data("9G",(new ElevController().getNumberPerClass(Client.elev.getLiceu().getId(),"9G")*100)/numarStudenti)
                );
                break;
            case "10":
                data.addAll(new PieChart.Data("10A",(new ElevController().getNumberPerClass(Client.elev.getLiceu().getId(),"10A")*100)/numarStudenti),
                        new PieChart.Data("10B",(new ElevController().getNumberPerClass(Client.elev.getLiceu().getId(),"10B")*100)/numarStudenti),
                        new PieChart.Data("10C",(new ElevController().getNumberPerClass(Client.elev.getLiceu().getId(),"10C")*100)/numarStudenti),
                        new PieChart.Data("10D",(new ElevController().getNumberPerClass(Client.elev.getLiceu().getId(),"10D")*100)/numarStudenti),
                        new PieChart.Data("10E",(new ElevController().getNumberPerClass(Client.elev.getLiceu().getId(),"10E")*100)/numarStudenti),
                        new PieChart.Data("10F",(new ElevController().getNumberPerClass(Client.elev.getLiceu().getId(),"10F")*100)/numarStudenti),
                        new PieChart.Data("10G",(new ElevController().getNumberPerClass(Client.elev.getLiceu().getId(),"10G")*100)/numarStudenti)
                );
                break;
            case "11":
                data.addAll(new PieChart.Data("11A",(new ElevController().getNumberPerClass(Client.elev.getLiceu().getId(),"11A")*100)/numarStudenti),
                        new PieChart.Data("11B",(new ElevController().getNumberPerClass(Client.elev.getLiceu().getId(),"11B")*100)/numarStudenti),
                        new PieChart.Data("11C",(new ElevController().getNumberPerClass(Client.elev.getLiceu().getId(),"11C")*100)/numarStudenti),
                        new PieChart.Data("11D",(new ElevController().getNumberPerClass(Client.elev.getLiceu().getId(),"11D")*100)/numarStudenti),
                        new PieChart.Data("11E",(new ElevController().getNumberPerClass(Client.elev.getLiceu().getId(),"11E")*100)/numarStudenti),
                        new PieChart.Data("11F",(new ElevController().getNumberPerClass(Client.elev.getLiceu().getId(),"11F")*100)/numarStudenti),
                        new PieChart.Data("11G",(new ElevController().getNumberPerClass(Client.elev.getLiceu().getId(),"11G")*100)/numarStudenti)
                );
                break;
            case "12":
                data.addAll(new PieChart.Data("12A",(new ElevController().getNumberPerClass(Client.elev.getLiceu().getId(),"12A")*100)/numarStudenti),
                        new PieChart.Data("12B",(new ElevController().getNumberPerClass(Client.elev.getLiceu().getId(),"12B")*100)/numarStudenti),
                        new PieChart.Data("12C",(new ElevController().getNumberPerClass(Client.elev.getLiceu().getId(),"12C")*100)/numarStudenti),
                        new PieChart.Data("12D",(new ElevController().getNumberPerClass(Client.elev.getLiceu().getId(),"12D")*100)/numarStudenti),
                        new PieChart.Data("12E",(new ElevController().getNumberPerClass(Client.elev.getLiceu().getId(),"12E")*100)/numarStudenti),
                        new PieChart.Data("12F",(new ElevController().getNumberPerClass(Client.elev.getLiceu().getId(),"12F")*100)/numarStudenti),
                        new PieChart.Data("12G",(new ElevController().getNumberPerClass(Client.elev.getLiceu().getId(),"12G")*100)/numarStudenti)
                );
                break;


        }
        return data;
    }

    private void setStatistici(){
        XYChart.Series<String, Double> series = new XYChart.Series<>();
        for (Pair<Elev, Double>  pair: new ElevController().get10Liceu(Client.elev.getId()) ) {
            series.getData().add(new XYChart.Data<>(pair.getKey().getNume(),pair.getValue()));
        }
        graficLiceu.getData().add(series);
    }

    private void setTop10clasa(){
        XYChart.Series<String, Double> series = new XYChart.Series<>();
        for (Pair<Elev, Double>  pair: new ElevController().get10Clasa(Client.elev.getId()) ) {
            series.getData().add(new XYChart.Data<>(pair.getKey().getNume(),pair.getValue()));
        }
        graficNote.getData().add(series);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setStatistici();
        setTop10clasa();
        repartizareClasa.setData(getData2());
        repartizareProfil.setData(getData1());

    }
}
