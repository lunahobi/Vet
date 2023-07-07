package sample.vet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Timer;

import static sample.vet.DbConnection.*;

public class AppointmentContoller implements Initializable {
    @FXML
    private Button buttonPets;

    @FXML
    private Button button_logout;

    @FXML
    private Button button_profile;

    @FXML
    private Button button_visits;

    @FXML
    private ImageView iv_user;

    @FXML
    private Label label_first_name;

    @FXML
    private Label label_message;
    @FXML
    private TableColumn<AppointmentInfo, Integer> tc_animal_id;

    @FXML
    private TableColumn<AppointmentInfo, String> tc_date;

    @FXML
    private TableColumn<AppointmentInfo, String> tc_doctor_name;

    @FXML
    private TableColumn<AppointmentInfo, Integer> tc_id;

    @FXML
    private TableColumn<AppointmentInfo, String> tc_name;

    @FXML
    private TableColumn<AppointmentInfo, String> tc_time;
    @FXML
    private TableView<Disease> tv_diseases;
    @FXML
    private TableColumn<Disease, String> tc_common_name;
    @FXML
    private TableColumn<Disease, String> tc_scientific_name;
    int id = 0;

    @FXML
    private TableView<AppointmentInfo> tv_appointment;
    private final ObservableList<AppointmentInfo> data = FXCollections.observableArrayList();
    private final ObservableList<Disease> data1 = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateTable();
        File userFile = new File("images/user_white.png");
        Image userImage = new Image(userFile.toURI().toString());
        iv_user.setImage(userImage);
        label_first_name.setText(LogInController.owner.getFirst_name());
        button_logout.setOnAction(actionEvent -> {Main.changeScene("log-in-view.fxml");});
        button_profile.setOnAction(actionEvent -> {Main.changeScene("owner-view.fxml");});
        buttonPets.setOnAction(actionEvent -> {Main.changeScene("animal-view.fxml");});
    }
    private void updateTable(){
        tv_appointment.getItems().clear();
        getInfo();
        tv_appointment.setItems(data);
    }
    private void updateTable1(){
        tv_diseases.getItems().clear();
        getInfo1();
        tv_diseases.setItems(data1);
    }
    public void getInfo() {
        data.addAll(getVisitsByOwnerId());
        tc_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tc_name.setCellValueFactory(new PropertyValueFactory<>("animal_name"));
        tc_doctor_name.setCellValueFactory(new PropertyValueFactory<>("doctor_name"));
        tc_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        tc_time.setCellValueFactory(new PropertyValueFactory<>("time"));
        tc_animal_id.setCellValueFactory(new PropertyValueFactory<>("animal_id"));
    }
    public void getInfo1() {
        data1.addAll(getDiseasesByVisitId(id));
        tc_common_name.setCellValueFactory(new PropertyValueFactory<>("common_name"));
        tc_scientific_name.setCellValueFactory(new PropertyValueFactory<>("scientific_name"));
    }
    @FXML
    void getData(MouseEvent event) {
        AppointmentInfo appointmentInfo = tv_appointment.getSelectionModel().getSelectedItem();
        id = appointmentInfo.getId();
        updateTable1();

    }

}
