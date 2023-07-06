package sample.vet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignUpController {
    @FXML
    private Button button_sign_up;
    @FXML
    private Label label_message_text;
    @FXML
    private PasswordField pf_password;
    @FXML
    private PasswordField pf_confirm_password;
    @FXML
    private TextField tf_last_name;
    @FXML
    private TextField tf_first_name;
    @FXML
    private TextField tf_second_name;
    @FXML
    private TextField tf_address;
    @FXML
    private TextField tf_phone_number;
    @FXML
    private Button button_login;
    public void logInButtonOnAction(ActionEvent event){
        Transition.changeScene(event, "log-in-view.fxml", 600, 400);
    }
}
