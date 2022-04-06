package Database;

import Model.appointment;
import Model.contact;
import Model.customer;
import Model.user;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

public class appointmentDao {

    public static ObservableList getAllAppt(ObservableList list, LocalDateTime selectedStart, LocalDateTime selectedEnd) throws SQLException, Exception {
        JDBC.getConnection();
        String sqlStmt = "SELECT * FROM appointments WHERE Start > '"+ Timestamp.valueOf(selectedStart) +"' AND End < '"+ Timestamp.valueOf(selectedEnd) +"';";
        Query.makeQuery(sqlStmt);
        appointment appointmentResult;
        ResultSet result = Query.getResult();
        while(result.next()){
            int appointmentID = result.getInt("Appointment_ID");
            String title = result.getString("Title");
            String description = result.getString("Description");
            String location = result.getString("Location");
            int contactID = result.getInt("Contact_ID");
            String type = result.getString("Type");
            LocalDateTime start = result.getTimestamp("Start").toLocalDateTime();
            LocalDateTime end = result.getTimestamp("End").toLocalDateTime();
            int customerID = result.getInt("Customer_ID");
            int userID = result.getInt("User_ID");
            appointmentResult = new appointment(appointmentID, title, description, location, type, start, end, contactID, customerID, userID);
            list.addAll(appointmentResult);
        }
        return list;
    }

    public static ObservableList getAllContacts(ObservableList list) throws SQLException, Exception {
        JDBC.getConnection();
        String sqlStmt = "SELECT * FROM contacts;";
        Query.makeQuery(sqlStmt);
        contact contactResult;
        ResultSet result = Query.getResult();
        while(result.next()){
            int contactID = result.getInt("Contact_ID");
            String name = result.getString("Contact_Name");
            String email = result.getString("Email");
            contactResult = new contact(contactID, name, email);
            list.addAll(contactResult);
        }
        return list;
    }

    public static ObservableList getAllUser(ObservableList list) throws SQLException, Exception {
        JDBC.getConnection();
        String sqlStmt = "SELECT * FROM users;";
        Query.makeQuery(sqlStmt);
        user userResult;
        ResultSet result = Query.getResult();
        while(result.next()){
            int userID = result.getInt("User_ID");
            String name = result.getString("User_Name");
            String password = result.getString("Password");
            userResult = new user(userID, name, password);
            list.addAll(userResult);
        }
        return list;
    }

    public static ObservableList getAll(ObservableList list) throws SQLException, Exception {

        JDBC.getConnection();
        String sqlstmt = "SELECT * FROM appointments;";
        Query.makeQuery(sqlstmt);
        appointment appointmentResult;
        ResultSet result = Query.getResult();
        while(result.next()){
            int appointmentID = result.getInt("Appointment_ID");
            String title = result.getString("Title");
            String description = result.getString("Description");
            String location = result.getString("Location");
            int contactID = result.getInt("Contact_ID");
            String type = result.getString("Type");
            LocalDateTime start = result.getTimestamp("Start").toLocalDateTime();
            LocalDateTime end = result.getTimestamp("End").toLocalDateTime();
            int customerID = result.getInt("Customer_ID");
            int userID = result.getInt("User_ID");
            appointmentResult = new appointment(appointmentID, title, description, location, type, start, end, contactID, customerID, userID);
            list.addAll(appointmentResult);
        }
        return list;
    }

//    public static ObservableList getAllCustomer(ObservableList list) throws SQLException, Exception {
//        JDBC.getConnection();
//        String sqlStmt = "SELECT * FROM customers;";
//        Query.makeQuery(sqlStmt);
//        customer customerResult;
//        ResultSet result = Query.getResult();
//        while(result.next()){
//            int customerID = result.getInt("Customer_ID");
//            String name = result.getString("Customer_Name");
//            String address = result.getString("Address");
//            String address = result.getString("Address");
//            customerResult = new customer();
//            list.addAll(customerResult);
//        }
//        return list;
//    }

}
