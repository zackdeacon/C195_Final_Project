package Controller;

import Database.CustomerDao;
import Database.appointmentDao;
import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.ResourceBundle;

import static Controller.LoginController.activeUser;

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

    @FXML
    public ComboBox<LocalTime> startCombo;

    @FXML
    public ComboBox<LocalTime> endCombo;

    @FXML
    public RadioButton monthlyRadio;

    @FXML
    public RadioButton weeklyRadio;

    @FXML
    public DatePicker startDate;

    @FXML
    public DatePicker endDate;


    ObservableList<appointment> apptList = FXCollections.observableArrayList();
    ObservableList<contact> contList = FXCollections.observableArrayList();
    ObservableList<customer> custList = FXCollections.observableArrayList();
    ObservableList<user> userList = FXCollections.observableArrayList();
    LocalDateTime today = LocalDateTime.now();
    LocalDateTime lastDayWeek = LocalDateTime.now().plusDays(7);
    LocalDateTime lastDayMonth = LocalDateTime.now().plusDays(30);
    appointment selectedAppointment;
    int custID;
    int contID;
    int userID;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userSelectedLabel.setText(activeUser.getUserName());
        weeklyRadio.setSelected(true);
        weeklyView();
    }


    public void monthlyView() {
        weeklyRadio.setSelected(false);
        monthlyRadio.setSelected(true);
        appointmentTable.getItems().clear();
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
            appointmentTable.setItems(appointmentDao.getAllAppt(apptList, today, lastDayMonth));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void weeklyView() {
        monthlyRadio.setSelected(false);
        weeklyRadio.setSelected(true);
        appointmentTable.getItems().clear();
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
            appointmentTable.setItems(appointmentDao.getAllAppt(apptList, today, lastDayWeek));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setUpdateAppointment() {
        selectedAppointment = (appointment) appointmentTable.getSelectionModel().getSelectedItem();
        custID = selectedAppointment.getCustomerID();
        contID = selectedAppointment.getContactID();
        userID = selectedAppointment.getUserID();
        apptIDText.setText(String.valueOf(selectedAppointment.getAppointmentID()));
        titleText.setText(selectedAppointment.getTitle());
        descText.setText(selectedAppointment.getDescription());
        locText.setText(selectedAppointment.getLocation());
        typeText.setText(selectedAppointment.getType());


        LocalTime begin = LocalTime.of(0,0);
        LocalTime endOfDay = LocalTime.of(23,0);


        while(begin.isBefore(endOfDay)){
            begin = begin.plusHours(1);
            startCombo.getItems().add(begin);
            endCombo.getItems().add(begin);
        }

        try{
            startDate.setValue(selectedAppointment.getStartDate());
            endDate.setValue(selectedAppointment.getEndDate());
            startCombo.setValue(selectedAppointment.getStartTime());
            endCombo.setValue(selectedAppointment.getEndTime());
            contactBox.getItems().clear();
            contactBox.setItems(appointmentDao.getAllContacts(contList));
            userBox.getItems().clear();
            userBox.setItems(appointmentDao.getAllUser(userList));
            customerBox.getItems().clear();
            customerBox.setItems(CustomerDao.getAllCustomer(custList));
            for(contact c : contactBox.getItems()){
                if(selectedAppointment.getContactID() == c.getContactID()){
                    contactBox.setValue(c);
                    break;
                }
            }
            for(user u : userBox.getItems()){
                if(selectedAppointment.getUserID() == u.getUserID()){
                    userBox.setValue(u);
                    break;
                }
            }
            for(customer cu : customerBox.getItems()){
                if(selectedAppointment.getCustomerID() == cu.getCustomer_ID()){
                    customerBox.setValue(cu);
                    break;
                }
            }

        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteAppt() {
        //TODO
        //create SQL string to correctly delete appt from database
        //same with customers
    }

    public void createAppt(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/create_appointment_form.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1150, 750);
        stage.setTitle("Create Appointment Page");
        stage.setScene(scene);
        stage.show();
    }

    public void updateAppt() {
        //TODO
        //Create SQL string to correctly update the database
        //collect correct data typed info from the forms
    }

    public void closeApplication() {
        System.exit(0);
    }

    public void goBack(ActionEvent actionEvent) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/view/customerInfo.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1150, 750);
        stage.setTitle("Customer Landing Page");
        stage.setScene(scene);
        stage.show();
    }
}
