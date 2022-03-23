package Controller;

import Database.CustomerDao;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import static Controller.LoginController.alertToDisplay;
import static Controller.LoginController.activeUser;

public class customerInfoController implements Initializable {

    @FXML
    public TextField textName;

    @FXML
    public TextField textAddress;

    @FXML
    public TextField textPhone;

    @FXML
    public TextField textPostalCode;

    @FXML
    public TableView customerTable;

    @FXML
    public TableColumn customerName;

    @FXML
    public TableColumn customerID;

    @FXML
    public TableColumn customerAddress;

    @FXML
    public TableColumn customerPostalCode;

    @FXML
    public TableColumn customerPhone;

    @FXML
    public TableColumn customerDivision;

    @FXML
    public TableColumn customerCountry;

    @FXML
    public Label userLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initialized");
        userLabel.setText(activeUser.getUserName());
        try {
            CustomerDao.getAllCustomer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exitProgram() {
        System.exit(0);
    }
}
