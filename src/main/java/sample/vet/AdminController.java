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
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    @FXML
    private Button button_logout;

    @FXML
    private Button button_sign_up;

    @FXML
    private Button button_users;

    @FXML
    private ImageView iv_user;

    @FXML
    private Label label_message_text;

    @FXML
    private PasswordField pf_confirm_password;

    @FXML
    private PasswordField pf_password;

    @FXML
    private TextField tf_address;

    @FXML
    private TextField tf_first_name;

    @FXML
    private TextField tf_last_name;

    @FXML
    private TextField tf_login;

    @FXML
    private TextField tf_phone_number;

    @FXML
    private TextField tf_second_name;

    @FXML
    void signUpButtonOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (pf_confirm_password.getText().equals(pf_password.getText())){
            if(tf_last_name.getText().isBlank() || tf_first_name.getText().isBlank() || tf_address.getText().isBlank() || tf_phone_number.getText().isBlank() || tf_login.getText().isBlank() || pf_password.getText().isBlank()){
                label_message_text.setText("Не все обязательные поля заполнены");
            }
            else if(DbConnection.getInstance().isUserNameExists(tf_login.getText())){
                label_message_text.setText("Логин занят");
            }
            else{
                registerDoctor();
                label_message_text.setText("Вы успешно зарегистрировались!");
            }
        } else{
            label_message_text.setText("Пароли не совпадают");
        }
    }
    private void registerDoctor() throws SQLException, ClassNotFoundException {
        String last_name = tf_last_name.getText();
        String first_name = tf_first_name.getText();
        String second_name = tf_second_name.getText();
        String address = tf_address.getText();
        String phone_number = tf_phone_number.getText();
        String username = tf_login.getText();
        String password = User.hashPass(pf_password.getText());

        Doctor doctor = new Doctor(last_name, first_name, second_name, address, phone_number, username, password);
        DbConnection.getInstance().registerDoctor(doctor);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File userFile = new File("images/user_white.png");
        Image userImage = new Image(userFile.toURI().toString());
        iv_user.setImage(userImage);
        button_logout.setOnAction(actionEvent -> {Main.changeScene("log-in-view.fxml");});
    }
}