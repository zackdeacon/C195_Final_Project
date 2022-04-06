package Controller;

import Database.CustomerDao;
import Database.appointmentDao;
import Model.appointment;
import Model.contact;
import Model.customer;
import Model.user;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

import static Controller.LoginController.activeUser;

public class create_appointment_controller implements Initializable {

    @FXML
    public Label activeUserLabel;

    @FXML
    public TextField textApptID;

    @FXML
    public TextField textTitle;

    @FXML
    public TextField textDesc;

    @FXML
    public TextField textLoc;

    @FXML
    public TextField textType;

    @FXML
    public DatePicker startDate;

    @FXML
    public DatePicker endDate;

    @FXML
    public ComboBox<contact> contactBox;

    @FXML
    public ComboBox<customer> custBox;

    @FXML
    public ComboBox<user> userBox;

    @FXML
    public ComboBox<LocalTime> startTime;

    @FXML
    public ComboBox<LocalTime> endTime;

    ObservableList<contact> contList = FXCollections.observableArrayList();
    ObservableList<customer> custList = FXCollections.observableArrayList();
    ObservableList<user> userList = FXCollections.observableArrayList();
    ObservableList<appointment> apptList = FXCollections.observableArrayList();
    public static int newAppID;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        activeUserLabel.setText(activeUser.getUserName());
        LocalTime begin = LocalTime.of(0,0);
        LocalTime endOfDay = LocalTime.of(23,0);
        while(begin.isBefore(endOfDay)){
            begin = begin.plusHours(1);
            startTime.getItems().add(begin);
            endTime.getItems().add(begin);
        }
        try {
        contactBox.getItems().clear();
        contactBox.setItems(appointmentDao.getAllContacts(contList));
        userBox.getItems().clear();
        userBox.setItems(appointmentDao.getAllUser(userList));
        custBox.getItems().clear();
        custBox.setItems(CustomerDao.getAllCustomer(custList));
        appointmentDao.getAll(apptList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        startTime.setValue(LocalTime.MIN);
        endTime.setValue(LocalTime.MIN);
        newAppID = apptList.get(apptList.size()-1).getAppointmentID() + 1;
        textApptID.setText(String.valueOf(newAppID));
    }


    public void createAppt(ActionEvent actionEvent) throws Exception {
        //TODO
        //Make sure pre loaded fields are refreshing correctly
        String title = textTitle.getText();
        String description = textDesc.getText();
        String location = textLoc.getText();
        String type = textType.getText();
        LocalDateTime start = LocalDateTime.of(startDate.getValue(), startTime.getValue());
        LocalDateTime end = LocalDateTime.of(endDate.getValue(), endTime.getValue());
        String user = activeUser.getUserName();
        int customerID = custBox.getSelectionModel().getSelectedItem().getCustomer_ID();
        int userID = userBox.getSelectionModel().getSelectedItem().getUserID();
        int contactID = contactBox.getSelectionModel().getSelectedItem().getContactID();
        appointmentDao.insertAppointment(title, description, location, type, start, end, user, customerID, userID, contactID);

        Parent root = FXMLLoader.load(getClass().getResource("/view/appointment_info.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1300, 950);
        stage.setTitle("Appointment Info Page");
        stage.setScene(scene);
        stage.show();
    }

    public void goBackButton(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/appointment_info.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1300, 950);
        stage.setTitle("Appointment Info Page");
        stage.setScene(scene);
        stage.show();
    }

    public void toExit() {
        System.exit(0);
    }
}
