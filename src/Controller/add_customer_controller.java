package Controller;

import Database.countryDao;
import Database.divisionDao;
import Model.country;
import Model.division;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;
import static Controller.LoginController.activeUser;
import static Controller.customerInfoController.newID;

public class add_customer_controller implements Initializable {

    ObservableList<country> countryOptions = FXCollections.observableArrayList();
    ObservableList<division> divisionOptions = FXCollections.observableArrayList();

    @FXML
    public Label loggedIn;

    @FXML
    public TextField customerID;

    @FXML
    public ComboBox<country> countryBox;

    @FXML
    public ComboBox<division> divisionBox;

    @FXML
    public TextField textName;

    @FXML
    public TextField textAdd;

    @FXML
    public TextField textPost;

    @FXML
    public TextField textPhone;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loggedIn.setText(activeUser.getUserName());
        customerID.setText(String.valueOf(newID));
        try {
            countryBox.setItems(countryDao.getAllCountries(countryOptions));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void countrySelected(){
        int selectedCountry = countryBox.getSelectionModel().getSelectedItem().getCountryID();
        try {
            divisionBox.setItems(divisionDao.getAllDivision(divisionOptions, selectedCountry));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createNewUser() {
        int selectedCountryID = divisionBox.getSelectionModel().getSelectedItem().getCountryID();
        int selectedDivisionID = divisionBox.getSelectionModel().getSelectedItem().getDivisionID();
        int selectedCustomerID = Integer.parseInt(customerID.getText());
        String selectedName = textName.getText();
        String selectedAddress = textAdd.getText();
        String selectedPostal = textPost.getText();
        String selectedPhone = textPhone.getText();
        //TODO create SQL code to add user to database
        //TODO add restrictions to not overload combo box selection when you pick a country a second time
    }

    public void depart() {
        System.exit(0);
    }

}
