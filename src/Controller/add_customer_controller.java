package Controller;

import Database.CustomerDao;
import Database.countryDao;
import Database.divisionDao;
import Model.country;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
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
        divisionBox.getItems().clear();
        int selectedCountry = countryBox.getSelectionModel().getSelectedItem().getCountryID();
        try {
            divisionBox.setItems(divisionDao.getAllDivision(divisionOptions, selectedCountry));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createNewUser(ActionEvent actionEvent) throws IOException {
        int selectedCountryID = divisionBox.getSelectionModel().getSelectedItem().getCountryID();
        int selectedDivisionID = divisionBox.getSelectionModel().getSelectedItem().getDivisionID();
        int selectedCustomerID = Integer.parseInt(customerID.getText());
        String selectedName = textName.getText();
        String selectedAddress = textAdd.getText();
        String selectedPostal = textPost.getText();
        String selectedPhone = textPhone.getText();

        CustomerDao.createCustomerSQL(selectedCustomerID,selectedDivisionID,selectedName,selectedAddress,selectedPostal,selectedPhone,activeUser.getUserName());

        Parent root = FXMLLoader.load(getClass().getResource("/view/customerInfo.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1150, 750);
        stage.setTitle("Customer Landing Page");
        stage.setScene(scene);
        stage.show();
        //TODO add restrictions to not overload combo box selection when you pick a country a second time
    }

    public void goBack(ActionEvent actionEvent)throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/view/customerInfo.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1150, 750);
        stage.setTitle("Customer Landing Page");
        stage.setScene(scene);
        stage.show();
    }

    public void depart() {
        System.exit(0);
    }

}
