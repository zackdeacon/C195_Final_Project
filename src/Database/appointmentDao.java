package Database;

import Model.appointment;
import Model.contact;
import Model.customer;
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
            Date start = result.getDate("Start");
            Date end = result.getDate("End");
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

        }
        return list;
    }

}
