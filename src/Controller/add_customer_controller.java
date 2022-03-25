package Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;
import static Controller.LoginController.activeUser;
import static Controller.customerInfoController.newID;

public class add_customer_controller implements Initializable {

    @FXML
    public Label loggedIn;

    @FXML
    public TextField customerID;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loggedIn.setText(activeUser.getUserName());
        customerID.setText(String.valueOf(newID));
    }

    public void depart() {
        System.exit(0);
    }

}
