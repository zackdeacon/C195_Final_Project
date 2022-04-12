package Utility;

import javafx.fxml.Initializable;

import java.net.URL;
import java.time.ZoneId;
import java.util.ResourceBundle;
import java.util.TimeZone;

public class timezones implements Initializable {

    ZoneId UTC = ZoneId.of("UTC");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(UTC);
    }


}
