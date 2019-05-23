package view;

import javafx.application.Application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

public class Client extends Application implements Initializable {

    @FXML
    private TextField userName;
    @FXML
    private PasswordField password;
    @FXML
    private Text invalidLoginMsg;

    public static ButtonAction action=new ButtonAction();

    public static void main(String[] args){
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root= FXMLLoader.load(getClass().getResource("Main.fxml"));
        primaryStage.setTitle("E-Catalog");
        primaryStage.resizableProperty().setValue(Boolean.FALSE);
        primaryStage.setScene(new Scene(root ));
//        primaryStage.getIcons().add(new Image("file:///C:/Users/Andrei/Desktop/JavaIcon.jpg"));
        primaryStage.show();
    }


    public void pressButton(ActionEvent event){

            //userName.clear();
            //password.clear();
            if(userName.getText().compareTo("user")==0&&password.getText().compareTo("pass")==0) {
                createSecondStage("Student.fxml");
                ((Node) event.getSource()).getScene().getWindow().hide();
                System.out.printf("Text field: %b%n", invalidLoginMsg);
                action.setTypeAccount("Student");
            }
            else if(userName.getText().compareTo("user1")==0&&password.getText().compareTo("pass")==0){
                createSecondStage("Parinte.fxml");
                ((Node) event.getSource()).getScene().getWindow().hide();
                action.setTypeAccount("Parinte");
            }
            else if(userName.getText().compareTo("user2")==0&&password.getText().compareTo("pass")==0){
                createSecondStage("profilProfesor.fxml");
                ((Node) event.getSource()).getScene().getWindow().hide();
                action.setTypeAccount("Profesor");
            }
            else
                invalidLoginMsg.setVisible(true);
    }


    private void createSecondStage(String fisierFXML){
        try{
            Stage primaryStage=new Stage();
            Parent root= FXMLLoader.load(getClass().getResource(fisierFXML));
            primaryStage.setTitle("E-Catalog Student");
            primaryStage.resizableProperty().setValue(Boolean.FALSE);
            Scene scene=new Scene(root);
            primaryStage.setScene(scene);
//            primaryStage.getIcons().add(new Image("file:///C:/Users/Andrei/Desktop/JavaIcon.jpg"));
            primaryStage.show();
        }
        catch (IOException e){e.printStackTrace();}
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
