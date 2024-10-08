package sample.vet;


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

public class DoctorController implements Initializable {
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
    private TableView<Doctor> table_info;
    @FXML
    private TableColumn<Doctor, String> last_name;
    @FXML
    private TableColumn<Doctor, String> first_name;
    @FXML
    private TableColumn<Doctor, String> second_name;
    @FXML
    private TableColumn<Doctor, String> address;
    @FXML
    private Button button_visits;
    @FXML
    private TableColumn<Doctor, String> phone_number;
    public static Doctor doctor = MainController.doctor;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateTable();
        File userFile = new File("images/user_white.png");
        Image userImage = new Image(userFile.toURI().toString());
        iv_user.setImage(userImage);
        label_first_name.setText(MainController.doctor.getFirst_name());
        button_logout.setOnAction(actionEvent -> {Main.changeScene("main-view.fxml");});
        button_visits.setOnAction(actionEvent -> {Main.changeScene("appointment-doctor-view.fxml");});

    }
    private void updateTable(){
        table_info.getItems().clear();
        getInfo();
        table_info.getItems().add(doctor);
    }

    private void getInfo() {
        first_name.setCellValueFactory(new PropertyValueFactory<>("first_name"));
        last_name.setCellValueFactory(new PropertyValueFactory<>("last_name"));
        second_name.setCellValueFactory(new PropertyValueFactory<>("second_name"));
        phone_number.setCellValueFactory(new PropertyValueFactory<>("phone_number"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
    }
    public void updateButtonOnAction() throws ClassNotFoundException, SQLException {
        if (pf_confirm_password.getText().equals(pf_password.getText())){
            if(tf_last_name.getText().isBlank() || tf_first_name.getText().isBlank() || tf_address.getText().isBlank() || tf_phone_number.getText().isBlank() || pf_password.getText().isBlank()){
                label_message.setText("Не все обязательные поля заполнены");
            }
            else{
                updateDoctor();
                label_message.setText("Вы успешно изменили данные!");
                updateTable();
            }
        } else{
            label_message.setText("Пароли не совпадают");
        }
    }
    private void updateDoctor() throws SQLException, ClassNotFoundException {
        String last_name = tf_last_name.getText();
        String first_name = tf_first_name.getText();
        String second_name = tf_second_name.getText();
        String address = tf_address.getText();
        String phone_number = tf_phone_number.getText();
        String password = User.hashPass(pf_password.getText());
        doctor.setLast_name(last_name);
        doctor.setFirst_name(first_name);
        doctor.setSecond_name(second_name);
        doctor.setAddress(address);
        doctor.setPhone_number(phone_number);
        doctor.setPassword(password);
        DbConnection.getInstance().updateDoctor(doctor);
        updateTable();
    }


}

