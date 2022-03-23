package Controller;

import Database.UserDao;
import Model.user;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.net.URL;
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

    @FXML
    public TextField userNameText;

    @FXML
    public TextField passwordText;

    static user activeUser;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initialized");
        setLanguage();
    }

    public void setLanguage() {
      // Locale.setDefault(new Locale("fr", "FR"));
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
        locationAlertLabel.setText(Locale.getDefault().getDisplayCountry());
    }
   }

   public void loginAttempt(ActionEvent actionEvent) throws IOException {
        String enteredName = userNameText.getText().toLowerCase();
        String enteredPassword = passwordText.getText();
       try{
          activeUser = UserDao.getUser(enteredName);
           if(activeUser == null && Locale.getDefault().getDisplayCountry().equals("France")) {
               alertToDisplay(1);
           } else if(activeUser == null && Locale.getDefault().getDisplayCountry().equals("United States")){
               alertToDisplay(2);
           }
            if(activeUser.getPassword().equals(enteredPassword)){
                System.out.println("You're in!");
                Parent root = FXMLLoader.load(getClass().getResource("/view/customerInfo.fxml"));
                Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(root, 1150, 750);
                stage.setTitle("Customer Landing Page");
                stage.setScene(scene);
                stage.show();
            } else {
                if(Locale.getDefault().getDisplayCountry().equals("France")) {
                    alertToDisplay(3);
                } else if(Locale.getDefault().getDisplayCountry().equals("United States")){
                    alertToDisplay(4);
                }
            }
       } catch(Exception ex){
           Logger.getLogger(customerInfoController.class.getName()).log(Level.SEVERE, null, ex);
       }
   }

    public void exitProgram() {
        System.exit(0);
    }

    public void createUser(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/create_user_form.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 600, 600);
        stage.setTitle("Customer Landing Page");
        stage.setScene(scene);
        stage.show();
    }

    public static void alertToDisplay(int alertNum){
        try{
            ResourceBundle rb = ResourceBundle.getBundle("translator", Locale.getDefault());

        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);

        switch(alertNum){
            case 1:
                errorAlert.setTitle(rb.getString("E"));
                errorAlert.setHeaderText(rb.getString("Fail"));
                errorAlert.setContentText(rb.getString("Error"));
                errorAlert.showAndWait();
                break;
            case 2:
                errorAlert.setTitle(rb.getString("head"));
                errorAlert.setHeaderText(rb.getString("LI"));
                errorAlert.setContentText(rb.getString("body"));
                errorAlert.showAndWait();
                break;
            case 3:
                errorAlert.setTitle(rb.getString("E"));
                errorAlert.setHeaderText(rb.getString("Fail"));
                errorAlert.setContentText(rb.getString("wrongPass"));
                errorAlert.showAndWait();
                break;
            case 4:
                errorAlert.setTitle(rb.getString("head"));
                errorAlert.setHeaderText(rb.getString("LI"));
                errorAlert.setContentText(rb.getString("password"));
                errorAlert.showAndWait();
                break;
        }
        } catch(Exception e){
            System.out.println("wasteful");
        }
    }

}
