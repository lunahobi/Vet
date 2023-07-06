package sample.vet;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Transition {
    public static void changeScene(ActionEvent event, String fxml, int width, int height){
        Parent root = null;

        try{
            root = FXMLLoader.load(Transition.class.getResource(fxml));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Vetclinic");
        stage.setScene(new Scene(root, width, height));
        stage.show();
    }
}