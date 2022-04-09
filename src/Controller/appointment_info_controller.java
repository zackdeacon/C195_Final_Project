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
    public ComboBox<String> typeText;

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
    ObservableList<String> typeList = FXCollections.observableArrayList();
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
        typeList.add("Work");
        typeList.add("Pleasure");
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
        if(selectedAppointment.getType().equals("Work")){
            typeText.setValue("Work");
        } else if(selectedAppointment.getType().equals("Pleasure")){
            typeText.setValue("Pleasure");
        }


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
            typeText.setItems(typeList);


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

    public void deleteAppt(ActionEvent actionEvent) throws IOException {
        int appID = selectedAppointment.getAppointmentID();
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        Alert informAlert = new Alert(Alert.AlertType.INFORMATION);
        confirmAlert.setTitle("Delete Appointment");
        confirmAlert.setHeaderText("You are about to delete " + selectedAppointment.getTitle() + ". Are you sure?");
        confirmAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                informAlert.setTitle("Deleted");
                informAlert.setHeaderText(selectedAppointment.getTitle() + " has been deleted");
                informAlert.show();
                appointmentDao.deleteAppointment(appID);
            }
        });
        Parent root = FXMLLoader.load(getClass().getResource("/view/appointment_info.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1300, 950);
        stage.setTitle("Appointment Info Page");
        stage.setScene(scene);
        stage.show();
    }

    public void createAppt(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/create_appointment_form.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1150, 750);
        stage.setTitle("Create Appointment Page");
        stage.setScene(scene);
        stage.show();
    }

    public void updateAppt(ActionEvent actionEvent) throws IOException {
        int appID = Integer.parseInt(apptIDText.getText());
        String title = titleText.getText();
        String description = descText.getText();
        String location = locText.getText();
        String type = typeText.getSelectionModel().getSelectedItem();
        LocalDateTime start = LocalDateTime.of(startDate.getValue(), startCombo.getValue());
        LocalDateTime end = LocalDateTime.of(endDate.getValue(), endCombo.getValue());
        String userName = activeUser.getUserName();
        int custID = customerBox.getSelectionModel().getSelectedItem().getCustomer_ID();
        int userID = userBox.getSelectionModel().getSelectedItem().getUserID();
        int contactID = contactBox.getSelectionModel().getSelectedItem().getContactID();
        try{
            appointmentDao.updateAppointmentSQL(appID, title, description, location, type, start, end, userName, custID, userID, contactID);
        }catch (Exception e) {
            e.printStackTrace();
        }
        Parent root = FXMLLoader.load(getClass().getResource("/view/appointment_info.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1300, 950);
        stage.setTitle("Appointment Info Page");
        stage.setScene(scene);
        stage.show();
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
