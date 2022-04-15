package Controller;

import Database.UserDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import static Controller.LoginController.alertToDisplay;

public class create_user_controller implements Initializable {

    @FXML
    public TextField userNameText;

    @FXML
    public TextField passwordText;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initialized");
    }

    public void makeUser(ActionEvent actionEvent) throws Exception {
        if(userNameText.getText().equals("") || passwordText.getText().equals("")){
            alertToDisplay(8);
        } else {
            UserDao.createUser(userNameText.getText(), passwordText.getText());

            Parent root = FXMLLoader.load(getClass().getResource("/view/log-in_form.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 600, 400);
            stage.setTitle("Log-in Page");
            stage.setScene(scene);
            stage.show();
        }
    }

    public void exitProgram() {
        System.exit(0);
    }
}
