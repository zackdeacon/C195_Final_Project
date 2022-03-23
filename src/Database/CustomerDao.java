package Database;

import Model.customer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDao {

    public static customer getAllCustomer() throws SQLException, Exception {
        JDBC.getConnection();
        String sqlStmt = "SELECT * from customers JOIN first_level_divisions ON first_level_divisions.Division_ID = customers.Division_ID JOIN countries ON first_level_divisions.Country_ID = countries.Country_ID;";
        Query.makeQuery(sqlStmt);
        customer customerResult;
        ResultSet result = Query.getResult();
        while (result.next()) {
            int customerID = result.getInt("Customer_ID");
            String customerNAME = result.getString("Customer_Name");
            String Address = result.getString("Address");
            String PostalCode = result.getString("Postal_Code");
            String Phone = result.getString("Phone");
            int DivisionID = result.getInt("Division_ID");
            customerResult = new customer(customerID, customerNAME, Address, PostalCode, Phone, DivisionID);
            System.out.println(customerResult.getCustomer_Name());
            return customerResult;
        }

        return null;

    }

}
