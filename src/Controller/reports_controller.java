package Controller;

import Database.CustomerDao;
import Database.appointmentDao;
import Database.countryDao;
import Database.reportsDao;
import Model.appointment;
import Model.contact;
import Model.country;
import Model.customer;
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

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.ResourceBundle;

public class reports_controller implements Initializable {

    @FXML
    public ComboBox<String> typeBox;

    @FXML
    public ComboBox<Month> monthBox;

    @FXML
    public ComboBox<contact> contactBox;

    @FXML
    public ComboBox<country> countryBox;

    @FXML
    public Label typeLabel;

    @FXML
    public Label typeNum;

    @FXML
    public Label monthLabel;

    @FXML
    public Label monthNum;

    @FXML
    public TableView tableSched;

    @FXML
    public TableColumn apptID;

    @FXML
    public TableColumn Title;

    @FXML
    public TableColumn Type;

    @FXML
    public TableColumn Desc;

    @FXML
    public TableColumn start;


    @FXML
    public TableColumn end;

    @FXML
    public TableColumn custID;

    @FXML
    public Label customerLabel;

    @FXML
    public Label customerNum;

    @FXML
    public Label contactSchedLabel;

    ObservableList<contact> contList = FXCollections.observableArrayList();
    ObservableList<country> countryOptions = FXCollections.observableArrayList();
    ObservableList<customer> customerList = FXCollections.observableArrayList();
    ObservableList<String> typeList = FXCollections.observableArrayList();
    ObservableList<appointment> apptList = FXCollections.observableArrayList();
    ObservableList<Month> monthList = FXCollections.observableArrayList();
    ObservableList<appointment> apptByContactList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        typeList.add("Work");
        typeList.add("Pleasure");
        monthList.add(Month.JANUARY);
        monthList.add(Month.FEBRUARY);
        monthList.add(Month.MARCH);
        monthList.add(Month.APRIL);
        monthList.add(Month.MAY);
        monthList.add(Month.JUNE);
        monthList.add(Month.JULY);
        monthList.add(Month.AUGUST);
        monthList.add(Month.SEPTEMBER);
        monthList.add(Month.OCTOBER);
        monthList.add(Month.NOVEMBER);
        monthList.add(Month.DECEMBER);
        try{
            contactBox.getItems().clear();
            contactBox.setItems(appointmentDao.getAllContacts(contList));
            countryBox.getItems().clear();
            countryBox.setItems(countryDao.getAllCountries(countryOptions));
            typeBox.setItems(typeList);
            typeBox.setValue("Work");
            monthBox.setItems(monthList);
            monthBox.setValue(monthList.get(0));
            contactBox.setValue(contList.get(0));
            countryBox.setValue(countryOptions.get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void appointmentByType(ActionEvent actionEvent) throws Exception {
        String type = typeBox.getSelectionModel().getSelectedItem();
        ObservableList<appointment> apptType = reportsDao.getApptByType(type);
        int count = 0;
        Month selectedMonth = monthBox.getSelectionModel().getSelectedItem();
        for(appointment a : apptType) {
            Month month = a.getStartDate().getMonth();
            if(month == selectedMonth) {
                count+=1;
            }
        }
        typeLabel.setText("Total " + type + " Appointments in the month of: " + selectedMonth);
        typeNum.setText(String.valueOf(count));
    }
    

    public void schedByContact() throws Exception {
        apptID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        Title.setCellValueFactory(new PropertyValueFactory<>("Title"));
        Type.setCellValueFactory(new PropertyValueFactory<>("Type"));
        Desc.setCellValueFactory(new PropertyValueFactory<>("Description"));
        start.setCellValueFactory(new PropertyValueFactory<>("Start"));
        end.setCellValueFactory(new PropertyValueFactory<>("End"));
        custID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        int contactID = contactBox.getSelectionModel().getSelectedItem().getContactID();
        String ContactName = contactBox.getSelectionModel().getSelectedItem().getName();
        apptByContactList.clear();
        tableSched.setItems(reportsDao.getApptByContact(contactID, apptByContactList));

        contactSchedLabel.setText("See " + ContactName + "'s schedule below:");
    }

    public void customerByCountry() throws Exception {
        String country = countryBox.getSelectionModel().getSelectedItem().getCountryName();
        int countryID = countryBox.getSelectionModel().getSelectedItem().getCountryID();
        customerList.clear();
        CustomerDao.getAllCustomer(customerList);
        int count = 0;
        for(customer c : customerList){
            if(c.getCountryID() == countryID){
                count +=1;
            }
        }
        customerLabel.setText("The number of Customers in " + country + " are:");
        customerNum.setText(String.valueOf(count));
    }

    public void goBack(ActionEvent actionEvent) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/view/customerInfo.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1150, 750);
        stage.setTitle("Customer Landing Page");
        stage.setScene(scene);
        stage.show();
    }

    public void close() {
        System.exit(0);
    }



}
