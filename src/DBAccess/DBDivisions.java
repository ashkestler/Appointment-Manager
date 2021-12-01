package DBAccess;

import Model.Divisions;
import Utils.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBDivisions {

    /**
     * This method queries the database and returns a list of all divisions.
     * @param countryId
     * @return stateList
     */
    public static ObservableList<Divisions> getStates(int countryId) {

        ObservableList<Divisions> stateList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM first_level_divisions WHERE Country_ID = ?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, countryId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int divisionId = rs.getInt("Division_ID");
                String division = rs.getString("Division");
                int countryIdx = rs.getInt("Country_ID");
                Divisions D = new Divisions(divisionId, division, countryIdx);
                stateList.add(D);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return stateList;
    }
}
