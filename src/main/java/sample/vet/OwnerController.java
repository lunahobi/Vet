package sample.vet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class OwnerController implements Initializable {
    @FXML
    ImageView iv_user;
    @FXML
    Label label_first_name;
    @FXML
    Button button_logout;
    @FXML
    private Label label_message;
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
    private TableView<Owner> table_info;
    @FXML
    private TableColumn<Owner, String> last_name;
    @FXML
    private TableColumn<Owner, String> first_name;
    @FXML
    private TableColumn<Owner, String> second_name;
    @FXML
    private TableColumn<Owner, String> address;
    @FXML
    private TableColumn<Owner, String> phone_number;
    public static Owner owner = LogInController.owner;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateTable();
        File userFile = new File("images/user_white.png");
        Image userImage = new Image(userFile.toURI().toString());
        iv_user.setImage(userImage);
        label_first_name.setText(LogInController.owner.getFirst_name());
    }
    private void updateTable(){
        table_info.getItems().clear();
        getInfo();
        table_info.getItems().add(owner);
    }

    private void getInfo() {
        first_name.setCellValueFactory(new PropertyValueFactory<Owner, String>("first_name"));
        last_name.setCellValueFactory(new PropertyValueFactory<Owner, String>("last_name"));
        second_name.setCellValueFactory(new PropertyValueFactory<Owner, String>("second_name"));
        phone_number.setCellValueFactory(new PropertyValueFactory<Owner, String>("phone_number"));
        address.setCellValueFactory(new PropertyValueFactory<Owner, String>("address"));
    }
    public void logOutButtonOnAction(ActionEvent event){
        Main.changeScene("log-in-view.fxml");
    }
    public void updateButtonOnAction() throws ClassNotFoundException, SQLException {
        if (pf_confirm_password.getText().equals(pf_password.getText())){
            if(tf_last_name.getText().isBlank() || tf_first_name.getText().isBlank() || tf_address.getText().isBlank() || tf_phone_number.getText().isBlank() || pf_password.getText().isBlank()){
                label_message.setText("Не все обязательные поля заполнены");
            }
            else{
                updateOwner();
                label_message.setText("Вы успешно изменили данные!");
                updateTable();
            }
        } else{
            label_message.setText("Пароли не совпадают");
        }
    }
    private void updateOwner() throws SQLException, ClassNotFoundException {
        String last_name = tf_last_name.getText();
        String first_name = tf_first_name.getText();
        String second_name = tf_second_name.getText();
        String address = tf_address.getText();
        String phone_number = tf_phone_number.getText();
        String password = User.hashPass(pf_password.getText());
        owner.setLast_name(last_name);
        owner.setFirst_name(first_name);
        owner.setSecond_name(second_name);
        owner.setAddress(address);
        owner.setPhone_number(phone_number);
        owner.setPassword(password);
        DbConnection.getInstance().updateOwner(owner);
        updateTable();
    }


}
