package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

import javafx.event.ActionEvent;
import java.io.IOException;

public class ButtonAction {

    ActionEvent event;
    AnchorPane rootPane;
    String typeAccount;
    Integer idElev;
    String numeElev;
    String prenumeElev;

    public void setEvent(ActionEvent event){
        this.event=event;
    }

    public void setPane(AnchorPane rootPane){
        this.rootPane=rootPane;
    }

    public void setTypeAccount(String typeAccount){this.typeAccount=typeAccount;}

    public void setStage(String FXMLName){
        rootPane.getChildren().clear();
        switch (typeAccount){
            case "Student":
                setStudentStage(FXMLName);
                break;
            case "Parinte":
                setParinteStage(FXMLName);
                break;
            case "Profesor":
                setProfesorStage(FXMLName);
                break;
        }

        System.out.println(((MenuItem)event.getSource()).getText()); //asa luam numele de la event, FOARTE IMPORTANT

    }

    private void setStudentStage(String FXMLName){
    try{Parent root;
        switch (FXMLName){
            case "Note":
                root = FXMLLoader.load(getClass().getResource("studentNote.fxml"));
                rootPane.getChildren().add(root);
                break;
            case "Absente":
                root = FXMLLoader.load(getClass().getResource("studentAbsente.fxml"));
                rootPane.getChildren().add(root);
                break;
            case "Profile":
                root = FXMLLoader.load(getClass().getResource("Student.fxml"));
                rootPane.getChildren().add(root);
                break;
            case "Statistici":
                root=FXMLLoader.load(getClass().getResource("studentStatistici.fxml"));
                rootPane.getChildren().add(root);
                break;
        }
    }
    catch (IOException er){er.printStackTrace();}

    }

    private void setParinteStage(String FXMLName){
        try{Parent root;
            switch (FXMLName){
                case "Note":
                    root = FXMLLoader.load(getClass().getResource("studentNote.fxml"));
                    rootPane.getChildren().add(root);
                    break;
                case "Absente":
                    root = FXMLLoader.load(getClass().getResource("studentAbsente.fxml"));
                    rootPane.getChildren().add(root);
                    break;
                case "Profile":
                    root = FXMLLoader.load(getClass().getResource("Parinte.fxml"));
                    rootPane.getChildren().add(root);
                    break;
                case "Statistici":
                    root=FXMLLoader.load(getClass().getResource("studentStatistici.fxml"));
                    rootPane.getChildren().add(root);
                    break;
            }
        }
        catch (IOException er){er.printStackTrace();}
    }

    public void setElevSelectat(Integer idElev,String numeElev,String prenumeElev){
        this.idElev=idElev;
        this.numeElev=numeElev;
        this.prenumeElev=prenumeElev;
    }

    private void setProfesorStage(String FXMLName){
        try {
            Parent root;
            switch (FXMLName) {
                case "Note":
                    root = FXMLLoader.load(getClass().getResource("profesorNote.fxml"));
                    rootPane.getChildren().add(root);
                    break;
                case "Absente":
                    root = FXMLLoader.load(getClass().getResource("profesorAbsente.fxml"));
                    rootPane.getChildren().add(root);
                    break;
                case "Profile":
                    root = FXMLLoader.load(getClass().getResource("profilProfesor.fxml"));
                    rootPane.getChildren().add(root);
                    break;
                case "Observatii":
                    root = FXMLLoader.load(getClass().getResource("profesorObservatii.fxml"));
                    rootPane.getChildren().add(root);
                    break;
                case "Statistici":
                    // DE MODIFICAT DACA SE CONSTRUIESTE XML PENTRU STATISTICI PROFESOR
                    root=FXMLLoader.load(getClass().getResource("studentStatistici.fxml"));
                    rootPane.getChildren().add(root);
                    break;

            }
        }
        catch (IOException er){er.printStackTrace();}

    }
}
