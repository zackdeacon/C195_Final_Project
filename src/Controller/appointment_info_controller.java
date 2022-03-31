package Controller;

import Database.CustomerDao;
import Database.appointmentDao;
import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.ResourceBundle;

public class appointment_info_controller implements Initializable {

    @FXML
    public TableView appointmentTable;

    @FXML
    public TableColumn appIDCol;

    @FXML
    public TableColumn titleCol;

    @FXML
    public TableColumn descCol;

    @FXML
    public TableColumn locCol;

    @FXML
    public TableColumn contactCol;

    @FXML
    public TableColumn typeCol;

    @FXML
    public TableColumn startCol;

    @FXML
    public TableColumn endCol;

    @FXML
    public TableColumn custCol;

    @FXML
    public TableColumn userCol;

    @FXML
    public Label userSelectedLabel;

    @FXML
    public TextField titleText;

    @FXML
    public TextField descText;

    @FXML
    public TextField locText;

    @FXML
    public TextField apptIDText;

    @FXML
    public TextField typeText;

    @FXML
    public ComboBox<contact> contactBox;

    @FXML
    public ComboBox<customer> customerBox;

    @FXML
    public ComboBox<user> userBox;

    ObservableList<appointment> apptList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("date: " + java.time.LocalDateTime.now());
        LocalDateTime today = LocalDateTime.now();
        System.out.println("date 7 days later: " + java.time.LocalDateTime.now().plusDays(7));
        LocalDateTime lastDay = LocalDateTime.now().plusDays(7);

        appIDCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        locCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contactID"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        custCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        userCol.setCellValueFactory(new PropertyValueFactory<>("userID"));
        try {
            appointmentTable.setItems(appointmentDao.getAllAppt(apptList, today, lastDay));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void monthlyView() {
        //loads appointment table with data in month range
    }

    public void weeklyView() {
        //loads appointment table with data in week range
    }

    public void deleteAppt() {
        //deletes an appointment
    }

    public void createAppt(){
        //takes you to create appointment screen
    }

    public void updateAppt() {
        //allows you to save the changes to updated appointment
    }

    public void closeApplication() {
        System.exit(0);
    }

    public void goBack(){
        //brings you back to main customer screen
    }
}
