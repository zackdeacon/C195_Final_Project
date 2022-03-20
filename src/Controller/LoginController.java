package Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.util.Locale;
import java.util.ResourceBundle;
import java.net.URL;
import java.time.*;

public class LoginController implements Initializable{
    @FXML
    public Label locationAlertLabel;

    @FXML
    public Label welcomeLabel;

    @FXML
    public Label userNameLabel;

    @FXML
    public Label passwordLabel;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initialized");
        setLanguage();
    }

    public void setLanguage() {
       Locale.setDefault(new Locale("fr"));
        try{
            ResourceBundle rb = ResourceBundle.getBundle("translator", Locale.getDefault());
        welcomeLabel.setText(rb.getString("Welcome!") );
        userNameLabel.setText(rb.getString("username"));
        passwordLabel.setText(rb.getString("password"));
        locationAlertLabel.setText("France");
    } catch (Exception e){
        System.out.println("English!");
    }
   }

}
