package sample.vet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static sample.vet.DbConnection.getInfoAboutUsers;

public class UserController implements Initializable {

    @FXML
    private Button button_add;

    @FXML
    private Button button_clear;

    @FXML
    private Button button_delete;

    @FXML
    private Button button_logout;

    @FXML
    private Button button_update;

    @FXML
    private Button button_users;

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private ImageView iv_user;

    @FXML
    private TableView<User> tv_users;
    @FXML
    private TableColumn<User, Integer> tc_role_id;

    @FXML
    private TableColumn<User, Integer> tc_id;

    @FXML
    private TableColumn<User, String> tc_password;

    @FXML
    private TableColumn<User, String> tc_username;
    @FXML
    private PasswordField tf_confirm_password;

    @FXML
    private PasswordField tf_password;
    @FXML
    private Label label_message;

    @FXML
    private TextField tf_username;
    @FXML
    private Button button_reg;
    private String[] roles = {"Админ", "Доктор", "Владелец"};
    private int role_id;
    int id = 0;
    private final ObservableList<User> data = FXCollections.observableArrayList();

    @FXML
    void addUser(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (choiceBox.equals("Админ")){
            role_id = 3;
        }
        else if (choiceBox.equals("Доктор")){
            role_id = 1;
        }
        else{
            role_id = 2;
        }
        if (tf_password.getText().equals("") || tf_confirm_password.getText().equals("") || tf_username.getText().equals("") || choiceBox.getSelectionModel().isEmpty()){
            label_message.setText("Не все поля заполнены");
        }
        else if(!tf_password.getText().equals(tf_confirm_password.getText())){
            label_message.setText("Пароли не совпадают");
        }
        else if(DbConnection.getInstance().isUserNameExists(tf_username.getText())){
            label_message.setText("Логин занят");
        }
        else{
            DbConnection.getInstance().addUser(tf_username.getText(), User.hashPass(tf_password.getText()), role_id);
            updateTable();
        }
    }

    @FXML
    void clearFields(ActionEvent event) {
        tf_username.setText("");
        tf_password.setText("");
        tf_confirm_password.setText("");
        choiceBox.getSelectionModel().clearSelection();
        label_message.setText("");
        button_add.setDisable(false);
    }

    @FXML
    void deleteUser(ActionEvent event) throws SQLException, ClassNotFoundException {
        DbConnection.getInstance().deleteUser(id);
        if (id == 0){
            label_message.setText("Не выбрана запись из таблицы");
        }
        else{
            label_message.setText("Успешно удалены записи о питомце с id "+ id);
        }
        updateTable();
    }

    @FXML
    void updateUser(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (choiceBox.getSelectionModel().isSelected(0)){
            role_id = 3;
        }
        else if (choiceBox.getSelectionModel().isSelected(1)){
            role_id = 1;
        }
        else{
            role_id = 2;
        }

        if (id == 0){
            label_message.setText("Не выбрана запись из таблицы");
        }
        else if (tf_password.getText().equals("") || tf_confirm_password.getText().equals("") || tf_username.getText().equals("") || choiceBox.getSelectionModel().isEmpty()){
            label_message.setText("Не все поля заполнены");
        }
        else if(!tf_password.getText().equals(tf_confirm_password.getText())){
            label_message.setText("Пароли не совпадают");
        }
        else if(DbConnection.getInstance().isUserNameExists(tf_username.getText())){
            label_message.setText("Логин занят");
        }
        else{
            DbConnection.getInstance().updateUser(id, tf_username.getText(), User.hashPass(tf_password.getText()),  role_id);
            updateTable();
            label_message.setText("Успешно обновлена запись о питомце с id "+ id);
        }

    }
    private void updateTable() {
        tv_users.getItems().clear();
        getInfo();
        tv_users.setItems(data);
    }

    public void getInfo() {
        data.addAll(getInfoAboutUsers());
        tc_id.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        tc_username.setCellValueFactory(new PropertyValueFactory<>("username"));
        tc_password.setCellValueFactory(new PropertyValueFactory<>("password"));
        tc_role_id.setCellValueFactory(new PropertyValueFactory<>("role_id"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateTable();
        File userFile = new File("images/user_white.png");
        Image userImage = new Image(userFile.toURI().toString());
        iv_user.setImage(userImage);
        button_logout.setOnAction(actionEvent -> {Main.changeScene("main-view.fxml");});
        button_reg.setOnAction(actionEvent -> {Main.changeScene("admin-view.fxml");});
        choiceBox.getItems().addAll(roles);
        if (choiceBox.equals("Админ")){
            role_id = 3;
        }
        else if (choiceBox.equals("Доктор")){
            role_id = 1;
        }
        else{
            role_id = 2;
        }
    }
    @FXML
    void getData(MouseEvent event) {
        User user = tv_users.getSelectionModel().getSelectedItem();
        id = (int) user.getUser_id();
        tf_username.setText(user.getUsername());
        tf_password.setText(user.getPassword());
        int r_id = (int) user.getRole_id();
        button_add.setDisable(true);
        if (r_id == 1){
            choiceBox.getSelectionModel().select(1);
        }
        else if (r_id == 2){
            choiceBox.getSelectionModel().select(2);
        }
        else {
            choiceBox.getSelectionModel().select(0);
        }
    }
}
