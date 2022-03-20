package Database;

import Model.user;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    public static user getUser(String userName) throws SQLException, Exception {
        JDBC.makeConnection();
            String sqlStmt = "SELECT * FROM users WHERE User_Name = '" + userName + "'";
            Query.makeQuery(sqlStmt);
            user userResult;
            ResultSet result = Query.getResult();
            while(result.next()) {
                int userID = result.getInt("User_ID");
                String userNAME = result.getString("User_Name");
                String password = result.getString("Password");
                userResult = new user(userID, userNAME, password);
                return userResult;
            }
            JDBC.closeConnection();
            return null;

    }

}
