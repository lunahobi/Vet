package sample.vet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class MainController implements Initializable {
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
    public static Owner owner;
    public static Doctor doctor;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File logoFile = new File("images/logo.png");
        Image logoImage = new Image(logoFile.toURI().toString());
        logoImageView.setImage(logoImage);
        button_signup.setOnAction(actionEvent -> {Main.changeScene("sign-up-view.fxml");});

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
    private void validateLogin() throws ClassNotFoundException, SQLException {
        DbConnection connection = DbConnection.getInstance();
        String verifyLogin = "SELECT COUNT(1) FROM Users WHERE username = '" + tf_username.getText() + "' AND password = '" + User.hashPass(pf_password.getText())+"'";
        try {
            Statement statement = connection.getConnection().createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()){
                if (queryResult.getInt(1) == 1){
                    int role_id = DbConnection.getInstance().getRoleIdByUsername(tf_username.getText());
                    if (role_id == 1){

                        doctor = DbConnection.getInstance().getDoctorByUsername(tf_username.getText());
                        Main.changeScene("doctor-view.fxml");
                    }
                    else if (role_id == 2){
                        owner = DbConnection.getInstance().getOwnerByUsername(tf_username.getText());
                        Main.changeScene("owner-view.fxml");
                    }
                    else{
                        Main.changeScene("admin-view.fxml");
                    }
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
