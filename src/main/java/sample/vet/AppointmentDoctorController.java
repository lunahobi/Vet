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

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import static sample.vet.DbConnection.*;

public class AppointmentDoctorController implements Initializable {
    @FXML
    private Button button_logout;


    @FXML
    private Button button_profile;

    @FXML
    private ImageView iv_user;

    @FXML
    private Label label_first_name;

    @FXML
    private TableColumn<AppointmentDoctorInfo, Integer> tc_animal_id1;

    @FXML
    private TableColumn<AppointmentDoctorInfo, Integer> tc_animal_id2;

    @FXML
    private TableColumn<AppointmentDoctorInfo, String> tc_breed1;

    @FXML
    private TableColumn<AppointmentDoctorInfo, String> tc_breed2;

    @FXML
    private TableColumn<AppointmentDoctorInfo, String> tc_name1;

    @FXML
    private TableColumn<AppointmentDoctorInfo, String> tc_name2;

    @FXML
    private TableColumn<AppointmentDoctorInfo, String> tc_owner1;

    @FXML
    private TableColumn<AppointmentDoctorInfo, String> tc_owner2;

    @FXML
    private TableColumn<AppointmentDoctorInfo, String> tc_time1;

    @FXML
    private TableColumn<AppointmentDoctorInfo, String> tc_time2;

    @FXML
    private TableView<AppointmentDoctorInfo> tv_visits_all;
    @FXML
    private TableColumn<AppointmentDoctorInfo, String> tc_date;

    @FXML
    private TableView<AppointmentDoctorInfo> tv_visits_today;
    private final ObservableList<AppointmentDoctorInfo> data = FXCollections.observableArrayList();
    private final ObservableList<AppointmentDoctorInfo> data1 = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateTable();
        updateTable1();
        File userFile = new File("images/user_white.png");
        Image userImage = new Image(userFile.toURI().toString());
        iv_user.setImage(userImage);
        label_first_name.setText(LogInController.doctor.getFirst_name());
        button_logout.setOnAction(actionEvent -> {Main.changeScene("log-in-view.fxml");});
        button_profile.setOnAction(actionEvent -> {Main.changeScene("doctor-view.fxml");});
    }
    private void updateTable(){
        tv_visits_today.getItems().clear();
        getInfo();
        tv_visits_today.setItems(data);
    }
    private void updateTable1(){
        tv_visits_all.getItems().clear();
        getInfo1();
        tv_visits_all.setItems(data1);
    }
    public void getInfo() {
        data.addAll(getVisitsTodayByDoctorId());
        tc_time1.setCellValueFactory(new PropertyValueFactory<>("time"));
        tc_name1.setCellValueFactory(new PropertyValueFactory<>("animal_name"));
        tc_owner1.setCellValueFactory(new PropertyValueFactory<>("owner_name"));
        tc_animal_id1.setCellValueFactory(new PropertyValueFactory<>("animal_id"));
        tc_breed1.setCellValueFactory(new PropertyValueFactory<>("breed"));
    }
    public void getInfo1() {
        data1.addAll(getVisitsByDoctorId());
        tc_time2.setCellValueFactory(new PropertyValueFactory<>("time"));
        tc_name2.setCellValueFactory(new PropertyValueFactory<>("animal_name"));
        tc_owner2.setCellValueFactory(new PropertyValueFactory<>("owner_name"));
        tc_animal_id2.setCellValueFactory(new PropertyValueFactory<>("animal_id"));
        tc_breed2.setCellValueFactory(new PropertyValueFactory<>("breed"));
        tc_date.setCellValueFactory(new PropertyValueFactory<>("date"));
    }

}
