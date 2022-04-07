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

    public static boolean insertAppointment(String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, String user, int custID, int userID, int contactID) throws SQLException, Exception {
        JDBC.getConnection();
        try {
            String sqlStmt = "INSERT INTO appointments (Title, Description, Location, Type, Start, End, Create_Date, Created_By, Last_Update, " +
                    "Last_Updated_By, Customer_ID, User_ID, Contact_ID) VALUES('" + title + "', '" + description + "', " +
                    "'" + location + "', '" + type + "', '" + start + "','" + end + "', sysdate(), '" + user + "', sysdate(), '" + user + "', '" + custID + "','" + userID + "','" + contactID + "' );";
            Query.makeQuery(sqlStmt);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void updateAppointmentSQL(int appID, String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, String userName, int custID, int userID, int contactID) {
        JDBC.getConnection();
        String sqlStmt = "UPDATE appointments SET Title = '" + title + "', Description = '" + description + "', Location = '" + location + "', " +
                "Type = '" + type + "', Start = '" + Timestamp.valueOf(start) + "', End = '" + Timestamp.valueOf(end) + "', " +
                "Last_Update = sysdate(), Last_Updated_By = '" + userName + "', Customer_ID = '" + custID + "', User_ID = '" + userID + "', Contact_ID = '" + contactID + "' WHERE Appointment_ID = '" + appID + "';";
        Query.makeQuery(sqlStmt);
    }



}
