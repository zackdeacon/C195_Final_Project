package Database;

import Model.appointment;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class reportsDao {

    public static int getApptByType(String type) throws SQLException, Exception {
        int count =0;
        JDBC.getConnection();
        String sqlstmt = "SELECT * from appointments WHERE Type = '" + type + "';";
        Query.makeQuery(sqlstmt);
        ResultSet result = Query.getResult();
        while(result.next()) {
            count += 1;
        }
        return count;
    }

    public static ObservableList getApptByContact(int contact, ObservableList list) throws SQLException, Exception {
        int count =0;
        JDBC.getConnection();
        String sqlstmt = "SELECT * from appointments WHERE Contact_ID = '" + contact + "';";
        Query.makeQuery(sqlstmt);
        appointment appointmentResult;
        ResultSet result = Query.getResult();
        while(result.next()) {
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

}
