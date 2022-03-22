package Controller;

import Database.UserDao;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class create_user_controller implements Initializable {

    @FXML
    public TextField userNameText;

    @FXML
    public TextField passwordText;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initialized");
    }

    public void makeUser() throws Exception {
        UserDao.createUser(userNameText.getText(), passwordText.getText());

    }

    public void exitProgram() {
        System.exit(0);
    }
}
