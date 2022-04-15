package Controller;

import Database.CustomerDao;
import Database.appointmentDao;
import Database.countryDao;
import Database.divisionDao;
import Model.appointment;
import Model.country;
import Model.customer;
import Model.division;
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
import java.time.LocalTime;
import java.util.ResourceBundle;

import static Controller.LoginController.alertToDisplay;
import static Controller.LoginController.activeUser;

public class customerInfoController implements Initializable {

    ObservableList<country> countryOptions = FXCollections.observableArrayList();
    ObservableList<division> divisionOptions = FXCollections.observableArrayList();

    @FXML
    public TextField textName;

    @FXML
    public TextField textID;

    @FXML
    public TextField textAddress;

    @FXML
    public TextField textPhone;

    @FXML
    public TextField textPostalCode;

    @FXML
    public TableView customerTable;

    @FXML
    public TableColumn customerName;

    @FXML
    public TableColumn customerID;

    @FXML
    public TableColumn customerAddress;

    @FXML
    public TableColumn customerPostalCode;

    @FXML
    public TableColumn customerPhone;

    @FXML
    public TableColumn customerDivision;

    @FXML
    public TableColumn customerCountry;

    @FXML
    public Label userLabel;

    @FXML
    public Label apptWarningLabel;

    @FXML
    public ComboBox<country> updateCountry;

    @FXML
    public ComboBox<division> updateDivision;

    public static int newID;

    ObservableList<customer> customerList = FXCollections.observableArrayList();
    ObservableList<appointment> appointmentList = FXCollections.observableArrayList();
    customer selectedCustomer;
    int selectedID;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        appointmentList.clear();
        userLabel.setText(activeUser.getUserName());
        try {
            customerName.setCellValueFactory(new PropertyValueFactory<>("customer_Name"));
            customerID.setCellValueFactory(new PropertyValueFactory<>("customer_ID"));
            customerAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
            customerPostalCode.setCellValueFactory(new PropertyValueFactory<>("postal_Code"));
            customerPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
            customerDivision.setCellValueFactory(new PropertyValueFactory<>("division"));
            customerCountry.setCellValueFactory(new PropertyValueFactory<>("country"));
            customerTable.setItems(CustomerDao.getAllCustomer(customerList));
            newID = customerList.get(customerList.size()-1).getCustomer_ID() + 1;
            appointmentDao.getAll(appointmentList);
            int count = 0;
            LocalTime range = LocalTime.now().plusMinutes(15);

            while(count < appointmentList.size()){
               // System.out.println("appt: " + appointmentList.get(count).getStart().toLocalTime());
                if(appointmentList.get(count).getStart().toLocalTime().isAfter(LocalTime.now()) && appointmentList.get(count).getStart().toLocalTime().isBefore(range)){
                    alertToDisplay(7);
                    apptWarningLabel.setText("Apppointment ID: " + appointmentList.get(count).getAppointmentID() + " is beginning at " + appointmentList.get(count).getStart().toLocalTime() + " on " + appointmentList.get(count).getStartDate());
                }
                count += 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void updateCustomer() {
       selectedCustomer = (customer) customerTable.getSelectionModel().getSelectedItem();
           textName.setText(selectedCustomer.getCustomer_Name());
           textAddress.setText(selectedCustomer.getAddress());
           textPostalCode.setText(selectedCustomer.getPostal_Code());
           textPhone.setText(selectedCustomer.getPhone());
           selectedID = selectedCustomer.getCustomer_ID();
           textID.setText(String.valueOf(selectedID));
           try{
               updateCountry.getItems().clear();
               updateCountry.setItems(countryDao.getAllCountries(countryOptions));
               updateDivision.getItems().clear();
               updateDivision.setItems(divisionDao.getAllDivision(divisionOptions, selectedCustomer.getCountryID()));
               for(country s : updateCountry.getItems()){
                   if(selectedCustomer.getCountryID() == s.getCountryID()){
                       updateCountry.setValue(s);
                       break;
                   }
               }
               for(division d : updateDivision.getItems()){
                   if(selectedCustomer.getDivisionID() == d.getDivisionID()){
                       updateDivision.setValue(d);
                       break;
                   }
               }
           } catch (Exception e) {
               e.printStackTrace();
           }
    }

    public void selectDivisionByCountry(ActionEvent actionEvent) throws IOException {
       int updatedCountry = updateCountry.getSelectionModel().getSelectedItem().getCountryID();
        updateDivision.getItems().clear();
        try {
            updateDivision.setItems(divisionDao.getAllDivision(divisionOptions, updatedCountry));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void completeUpdatedCustomer() {
        if(selectedCustomer == null){
            alertToDisplay(5);
        } else if(updateDivision.getSelectionModel().getSelectedItem() == null){
            alertToDisplay(6);
        } else {
            CustomerDao.updateCustomerSQL(selectedID, textName.getText(), textAddress.getText(), textPostalCode.getText(), textPhone.getText(), activeUser.getUserName(), updateDivision.getSelectionModel().getSelectedItem().getDivisionID());
            try {
                customerTable.getItems().clear();
                customerName.setCellValueFactory(new PropertyValueFactory<>("customer_Name"));
                customerID.setCellValueFactory(new PropertyValueFactory<>("customer_ID"));
                customerAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
                customerPostalCode.setCellValueFactory(new PropertyValueFactory<>("postal_Code"));
                customerPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
                customerDivision.setCellValueFactory(new PropertyValueFactory<>("division"));
                customerCountry.setCellValueFactory(new PropertyValueFactory<>("country"));
                customerTable.setItems(CustomerDao.getAllCustomer(customerList));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteCustomer(ActionEvent actionEvent) throws IOException {
        int custID = selectedCustomer.getCustomer_ID();
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        Alert informAlert = new Alert(Alert.AlertType.INFORMATION);
        confirmAlert.setTitle("Delete Customer");
        confirmAlert.setHeaderText("You are about to delete " + selectedCustomer.getCustomer_Name() + ". Are you sure?");
        confirmAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                informAlert.setTitle("Deleted");
                informAlert.setHeaderText(selectedCustomer.getCustomer_Name() + " has been deleted");
                informAlert.show();
                CustomerDao.deleteCustomer(custID);
            }
        });
        Parent root = FXMLLoader.load(getClass().getResource("/view/customerInfo.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1150, 750);
        stage.setTitle("Customer Landing Page");
        stage.setScene(scene);
        stage.show();
    }

    public void toReports(ActionEvent actionEvent) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/view/reports.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1200, 1000);
        stage.setTitle("Reports Page");
        stage.setScene(scene);
        stage.show();
    }

    public void goToAddCustomer(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/add_customer_form.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 900, 500);
        stage.setTitle("Add Customer Page");
        stage.setScene(scene);
        stage.show();
    }

    public void goToAppointments(ActionEvent actionEvent) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/view/appointment_info.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1225, 700);
        stage.setTitle("Appointments Page");
        stage.setScene(scene);
        stage.show();
    }

    public void exitProgram() {
        System.exit(0);
    }
}
