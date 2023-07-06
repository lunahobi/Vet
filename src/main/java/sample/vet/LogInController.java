package sample.vet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.controlsfx.control.action.Action;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.Stack;

public class LogInController implements Initializable {
    @FXML
    private Button button_login;

    @FXML
    private Button button_signup;
    @FXML
    private TextField tf_username;
    @FXML
    private PasswordField pf_password;
    @FXML
    private Label label_text;
    @FXML
    private ImageView logoImageView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File logoFile = new File("images/logo.png");
        Image logoImage = new Image(logoFile.toURI().toString());
        logoImageView.setImage(logoImage);

    }
    public void loginButtonOnAction(ActionEvent event) throws ClassNotFoundException, SQLException {
        if (tf_username.getText().isBlank() == false && pf_password.getText().isBlank() == false){
            validateLogin();
        }
        else if (tf_username.getText().isBlank() == false && pf_password.getText().isBlank() == true){
            label_text.setText("Вы не ввели пароль");
        }
        else if (tf_username.getText().isBlank() == true && pf_password.getText().isBlank() == false){
            label_text.setText("Вы не ввели логин");
        }
        else{
            label_text.setText("Вы не ввели логин и пароль");
        }
    }
    public void signUpButtonOnAction(ActionEvent event){
        Transition.changeScene(event, "sign-up-view.fxml", 418, 604);
    }
    private void validateLogin() throws ClassNotFoundException, SQLException {
        DbConnection connection = DbConnection.getInstance();
        String verifyLogin = "SELECT COUNT(1) FROM Users WHERE username = '" + tf_username.getText() + "' AND password = '" + User.hashPass(pf_password.getText())+"'";
        try {
            Statement statement = connection.getConnection().createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()){
                if (queryResult.getInt(1) == 1){
                    label_text.setText("Поздравляю!");
                }
                else {
                    label_text.setText("Неправильные логин или пароль");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

}
