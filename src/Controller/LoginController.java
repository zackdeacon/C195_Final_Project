package Controller;

import javafx.fxml.Initializable;

import java.util.Locale;
import java.util.ResourceBundle;
import java.net.URL;
import java.time.*;

public class LoginController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initialized");
    }

    public static void main(String[] args) {
        Locale currentLocale = Locale.getDefault();
        currentLocale = Locale.FRANCE;
        System.out.println("I am currently " + currentLocale);
    }

}
