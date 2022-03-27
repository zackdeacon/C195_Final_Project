package Controller;

import Database.countryDao;
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

    @FXML
    public Label loggedIn;

    @FXML
    public TextField customerID;

    @FXML
    public ComboBox<country> countryBox;

    @FXML
    public ComboBox<division> divisionBox;


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

    public int countrySelected(){
        return countryBox.getSelectionModel().getSelectedItem().getCountryID();
    }

    public void depart() {
        System.exit(0);
    }

}
