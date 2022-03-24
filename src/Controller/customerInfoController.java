package Controller;

import Database.CustomerDao;
import Model.customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

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

    ObservableList<customer> customerList = FXCollections.observableArrayList();
    customer selectedCustomer;
    int selectedID;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initialized");
        userLabel.setText(activeUser.getUserName());
        try {
            customerName.setCellValueFactory(new PropertyValueFactory<>("customer_Name"));
            customerID.setCellValueFactory(new PropertyValueFactory<>("customer_ID"));
            customerAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
            customerPostalCode.setCellValueFactory(new PropertyValueFactory<>("postal_Code"));
            customerPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
            customerDivision.setCellValueFactory(new PropertyValueFactory<>("division"));
            customerCountry.setCellValueFactory(new PropertyValueFactory<>("country"));
            customerTable.setItems(CustomerDao.getAllCustomer(customerList));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void updateCustomer() {
       selectedCustomer = (customer) customerTable.getSelectionModel().getSelectedItem();
           textName.setText(selectedCustomer.getCustomer_Name());
           textAddress.setText(selectedCustomer.getAddress());
           textPostalCode.setText(selectedCustomer.getPostal_Code());
           textPhone.setText(selectedCustomer.getPhone());
           selectedID = selectedCustomer.getCustomer_ID();
    }

    public void completeUpdatedCustomer() {
        if(selectedCustomer == null){
            alertToDisplay(5);
        } else {
            CustomerDao.updateCustomerSQL(selectedID, textName.getText(), textAddress.getText(), textPostalCode.getText(), textPhone.getText(), activeUser.getUserName());
            try {
                customerTable.getItems().clear();
                customerName.setCellValueFactory(new PropertyValueFactory<>("customer_Name"));
                customerID.setCellValueFactory(new PropertyValueFactory<>("customer_ID"));
                customerAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
                customerPostalCode.setCellValueFactory(new PropertyValueFactory<>("postal_Code"));
                customerPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
                customerDivision.setCellValueFactory(new PropertyValueFactory<>("division"));
                customerCountry.setCellValueFactory(new PropertyValueFactory<>("country"));
                customerTable.setItems(CustomerDao.getAllCustomer(customerList));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void exitProgram() {
        System.exit(0);
    }
}
