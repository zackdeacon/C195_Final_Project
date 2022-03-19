package Controller;

import javafx.fxml.Initializable;

import java.util.Locale;
import java.util.ResourceBundle;
import java.net.URL;
import java.time.*;

public class LoginController {

//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        System.out.println("Initialized");
//    }

    public static void main(String[] args) {

        ResourceBundle rb = ResourceBundle.getBundle("src/translator", Locale.getDefault());
        Locale currentLocale = Locale.getDefault();
        //currentLocale = Locale.FRANCE;
        //Locale.setDefault(Locale.FRANCE);
        System.out.println(rb.getString("Welcome!") + " " + rb.getString("Please") + " " +
                rb.getString("log-in") + " " + rb.getString("to") + " " + rb.getString("continue") );
    }

}
