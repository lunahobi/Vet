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

import static sample.vet.DbConnection.getInfoAboutPet;

public class AnimalController implements Initializable {
    @FXML
    private Label label_first_name;
    @FXML
    private Button button_logout;
    @FXML
    private Button button_profile;
    @FXML
    private Button button_add;
    @FXML
    private Button button_update;
    @FXML
    private Button button_clear;
    @FXML
    private Button button_delete;
    @FXML
    ImageView iv_user;
    @FXML
    TableView<Animal> tv_pets;
    @FXML
    TableColumn<Animal, Long> tc_id;
    @FXML
    TableColumn<Animal, String > tc_name;
    @FXML
    TableColumn<Animal, Integer> tc_breed;
    @FXML
    TextField tf_name;
    @FXML
    Label label_message;
    @FXML
    TextField tf_breed;
    @FXML
    private Button button_visits;
    long id = 0;
    private final ObservableList<Animal> data = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateTable();
        File userFile = new File("images/user_white.png");
        Image userImage = new Image(userFile.toURI().toString());
        iv_user.setImage(userImage);
        label_first_name.setText(UserController.owner.getFirst_name());
        button_logout.setOnAction(actionEvent -> {Main.changeScene("log-in-view.fxml");});
        button_profile.setOnAction(actionEvent -> {Main.changeScene("owner-view.fxml");});
        button_visits.setOnAction(actionEvent -> {Main.changeScene("appointment-view.fxml");});
    }
    private void updateTable() {
        tv_pets.getItems().clear();
        getInfo();
        tv_pets.setItems(data);
    }

    public void getInfo() {
        data.addAll(getInfoAboutPet());
        tc_id.setCellValueFactory(new PropertyValueFactory<>("animal_id"));
        tc_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        if (tc_breed !=null){
            tc_breed.setCellValueFactory(new PropertyValueFactory<>("breed"));
        }
    }
    @FXML
    void addPet(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (tf_name.getText().equals("") || tf_breed.getText().equals("")){
            label_message.setText("Не все поля заполнены");
        }
        else{
            DbConnection.getInstance().addAnimal(tf_name.getText(), tf_breed.getText());
            updateTable();
        }

    }
    @FXML
    void deletePet(ActionEvent event) throws SQLException, ClassNotFoundException {
        DbConnection.getInstance().deleteAnimal(id);
        if (id == 0){
            label_message.setText("Не выбрана запись из таблицы");
        }
        else{
            label_message.setText("Успешно удалены записи о питомце с id "+ id);
        }
        updateTable();
    }
    @FXML
    void updatePet(ActionEvent event) throws SQLException, ClassNotFoundException {
        DbConnection.getInstance().updateAnimal(id, tf_name.getText(), tf_breed.getText());
        if (id == 0){
            label_message.setText("Не выбрана запись из таблицы");
        }
        else{
            label_message.setText("Успешно обновлена запись о питомце с id "+ id);
        }
        updateTable();
    }
    @FXML
    void clearFields(){
        tf_breed.setText("");
        tf_name.setText("");
        label_message.setText("");
        button_add.setDisable(false);
    }
    @FXML
    void getData(MouseEvent event) {
        Animal animal = tv_pets.getSelectionModel().getSelectedItem();
        id = animal.getAnimal_id();
        tf_name.setText(animal.getName());
        tf_breed.setText(animal.getBreed());
        button_add.setDisable(true);
    }

}
