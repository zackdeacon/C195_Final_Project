package Controller;

import Database.UserDao;
import Model.user;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.util.Locale;
import java.util.ResourceBundle;
import java.net.URL;
import java.time.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginController implements Initializable{
    @FXML
    public Label locationAlertLabel;

    @FXML
    public Label welcomeLabel;

    @FXML
    public Label userNameLabel;

    @FXML
    public Label passwordLabel;

    @FXML
    public Label locationLabel;

    @FXML
    public Button loginButton;

    @FXML
    public Button exitButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initialized");
        setLanguage();
        System.out.println(userCheck().getUserName());
    }

    public void setLanguage() {
        Locale.setDefault(new Locale("fr", "FR"));
        try{
            ResourceBundle rb = ResourceBundle.getBundle("translator", Locale.getDefault());
        welcomeLabel.setText(rb.getString("Welcome!") );
        userNameLabel.setText(rb.getString("username"));
        passwordLabel.setText(rb.getString("password"));
        locationLabel.setText(rb.getString("Location"));
        loginButton.setText(rb.getString("Log-in"));
        exitButton.setText(rb.getString("Exit"));
        locationAlertLabel.setText(Locale.getDefault().getDisplayCountry());

    } catch (Exception e){
        System.out.println("English!");
        locationAlertLabel.setText(Locale.getDefault().getDisplayCountry());
    }
   }

   public void loginAttempt() {
        System.out.println("log-in attempted!");
   }

    public void exitProgram() {
        System.exit(0);
    }

    public user userCheck() {
        try{
           return UserDao.getUser("test");
        } catch(Exception ex){
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
