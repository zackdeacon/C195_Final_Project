package Controller;

import Model.contact;
import Model.country;
import Model.customer;
import Model.user;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class appointment_info_controller implements Initializable {

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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
