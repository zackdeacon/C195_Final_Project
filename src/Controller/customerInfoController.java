package Controller;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class customerInfoController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initialized");
    }

    public void exitProgram() {
        System.exit(0);
    }
}
