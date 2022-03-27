package Database;

import Model.country;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class countryDao {

    public static ObservableList getAllCountries(ObservableList list) throws SQLException, Exception {
        JDBC.getConnection();

        String sqlStmt = "SELECT * from countries;";
        Query.makeQuery(sqlStmt);
        country countryResult;
        ResultSet result = Query.getResult();
        while (result.next()) {
            int countryID = result.getInt("Country_ID");
            String countryName = result.getString("Country");
            countryResult = new country(countryID, countryName);
            //System.out.println(customerResult.getCustomer_Name());
            list.addAll(countryResult);
        }

        return list;

    }
}
