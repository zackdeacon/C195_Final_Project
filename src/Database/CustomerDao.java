package Database;

import Model.customer;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDao {

    public static ObservableList getAllCustomer(ObservableList list) throws SQLException, Exception {
        JDBC.getConnection();

        String sqlStmt = "SELECT * from customers JOIN first_level_divisions ON first_level_divisions.Division_ID = customers.Division_ID JOIN countries ON first_level_divisions.Country_ID = countries.Country_ID;";
        Query.makeQuery(sqlStmt);
        customer customerResult;
        ResultSet result = Query.getResult();
        while (result.next()) {
            int customer_ID = result.getInt("Customer_ID");
            String customer_Name = result.getString("Customer_Name");
            String Address = result.getString("Address");
            String postal_Code = result.getString("Postal_Code");
            String phone = result.getString("Phone");
            int division_ID = result.getInt("Division_ID");
            customerResult = new customer(customer_ID, customer_Name, Address, postal_Code, phone, division_ID);
            //System.out.println(customerResult.getCustomer_Name());
            list.addAll(customerResult);
        }

        return list;

    }

}
