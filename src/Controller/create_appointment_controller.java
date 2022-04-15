package Controller;

import Database.CustomerDao;
import Database.appointmentDao;
import Model.appointment;
import Model.contact;
import Model.customer;
import Model.user;
import Utility.timezones;
import com.sun.prism.shader.AlphaOne_Color_AlphaTest_Loader;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

import static Controller.LoginController.activeUser;
import static Controller.LoginController.alertToDisplay;

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
    public ComboBox textType;

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
    ObservableList<String> typeList = FXCollections.observableArrayList();
    public static int newAppID;
    LocalTime beginOfDay = LocalTime.of(8,0);
    LocalTime endOfDay = LocalTime.of(22,0);
    LocalDateTime beginToEST = timezones.localToEST(LocalDate.now(), beginOfDay);
    LocalDateTime endToEST = timezones.localToEST(LocalDate.now(), endOfDay);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        activeUserLabel.setText(activeUser.getUserName());
        typeList.add("Work");
        typeList.add("Pleasure");
        LocalTime beginTime = beginToEST.toLocalTime();
        LocalTime endingTime = endToEST.toLocalTime();
        while(beginTime.isBefore(endingTime)){
            startTime.getItems().add(beginTime);
            beginTime = beginTime.plusHours(1);
        }
        textType.setItems(typeList);
        textType.setValue(typeList.get(0));
        try {
        contactBox.getItems().clear();
        contactBox.setItems(appointmentDao.getAllContacts(contList));
        contactBox.setValue(contList.get(0));
        userBox.getItems().clear();
        userBox.setItems(appointmentDao.getAllUser(userList));
        userBox.setValue(userList.get(0));
        custBox.getItems().clear();
        custBox.setItems(CustomerDao.getAllCustomer(custList));
        custBox.setValue(custList.get(0));
        appointmentDao.getAll(apptList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        startDate.setValue(LocalDate.now());
        endDate.setValue(LocalDate.now().plusDays(1));
        startTime.setValue(beginToEST.toLocalTime());
        newAppID = apptList.get(apptList.size()-1).getAppointmentID() + 1;
        textApptID.setText(String.valueOf(newAppID));
        setEndBox();
    }

    public void setEndBox() {
        endTime.getItems().clear();
        LocalTime selectedStart = startTime.getSelectionModel().getSelectedItem();
        LocalTime initialLoad = selectedStart.plusHours(1);
        selectedStart = selectedStart.plusHours(1);
        while(selectedStart.isBefore(endToEST.toLocalTime())){
            endTime.getItems().add(selectedStart);
            selectedStart = selectedStart.plusHours(1);
        }
        endTime.setValue(initialLoad);
    }


    public void createAppt(ActionEvent actionEvent) throws Exception {
        //TODO
        //Make sure pre loaded fields are refreshing correctly
        String title = textTitle.getText();
        String description = textDesc.getText();
        String location = textLoc.getText();
        String type = textType.getSelectionModel().getSelectedItem().toString();
        LocalDateTime start = LocalDateTime.of(startDate.getValue(), startTime.getValue());
        LocalDateTime end = LocalDateTime.of(endDate.getValue(), endTime.getValue());
        String user = activeUser.getUserName();
        int customerID = custBox.getSelectionModel().getSelectedItem().getCustomer_ID();
        int userID = userBox.getSelectionModel().getSelectedItem().getUserID();
        int contactID = contactBox.getSelectionModel().getSelectedItem().getContactID();
        boolean cleared = false;
        if(title.equals("") || description.equals("") || location.equals("")){
            alertToDisplay(8);
        } else {
            ObservableList<appointment> overlap = appointmentDao.getAllByCustID(customerID);
            for(int i =0; i < overlap.size(); i++){
                if( (start.isAfter(overlap.get(i).getStart())||start.isEqual(overlap.get(i).getStart())) && start.isBefore(overlap.get(i).getEnd())){
                    alertToDisplay(9);
                    cleared = false;
                    break;
                } else if(end.isAfter(overlap.get(i).getStart())&&(end.isBefore(overlap.get(i).getEnd())||end.isEqual(overlap.get(i).getEnd()))){
                    alertToDisplay(9);
                    cleared = false;
                    break;
                } else if((start.isBefore(overlap.get(i).getStart())||start.isEqual(overlap.get(i).getStart()))&&(end.isAfter(overlap.get(i).getEnd())||end.isEqual(overlap.get(i).getEnd()))){
                    alertToDisplay(9);
                    cleared = false;
                    break;
                }
                cleared = true;
            }
            if(cleared == true) {
                appointmentDao.insertAppointment(title, description, location, type, start, end, user, customerID, userID, contactID);

                Parent root = FXMLLoader.load(getClass().getResource("/view/appointment_info.fxml"));
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(root, 1300, 950);
                stage.setTitle("Appointment Info Page");
                stage.setScene(scene);
                stage.show();
            }
        }
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
