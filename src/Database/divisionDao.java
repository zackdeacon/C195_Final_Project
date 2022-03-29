package Database;

import Model.country;
import Model.division;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class divisionDao {

        public static ObservableList getAllDivision(ObservableList list, int ID) throws SQLException, Exception {
            JDBC.getConnection();

            String sqlStmt = "SELECT * from first_level_divisions WHERE Country_ID = '" + ID + "';";
            Query.makeQuery(sqlStmt);
            division divisionResult;
            ResultSet result = Query.getResult();
            while (result.next()) {
                int divisionID = result.getInt("division_ID");
                int countryID = result.getInt("country_ID");
                String divisionName = result.getString("Division");
                divisionResult = new division(divisionID, countryID, divisionName);
                //System.out.println(customerResult.getCustomer_Name());
                list.addAll(divisionResult);
            }

            return list;

        }

    public static ObservableList getTotalDivision(ObservableList list) throws SQLException, Exception {
        JDBC.getConnection();

        String sqlStmt = "SELECT * from first_level_divisions";
        Query.makeQuery(sqlStmt);
        division divisionResult;
        ResultSet result = Query.getResult();
        while (result.next()) {
            int divisionID = result.getInt("division_ID");
            int countryID = result.getInt("country_ID");
            String divisionName = result.getString("Division");
            divisionResult = new division(divisionID, countryID, divisionName);
            //System.out.println(customerResult.getCustomer_Name());
            list.addAll(divisionResult);
        }

        return list;

    }

    }

