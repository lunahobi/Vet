package sample.vet;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    public static Stage stage;
    @Override
    public void start(Stage s) throws IOException {
        stage = s;
        stage.setTitle("Veterinary clinic");
        Parent screen = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("log-in-view.fxml")));
        stage.setScene(new Scene(screen));
        stage.show();
    }
    public static void changeScene(String name_Scene){
        try {
            Parent screen = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource(name_Scene)));
            stage.setScene(new Scene(screen));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}