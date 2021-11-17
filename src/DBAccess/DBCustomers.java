package DBAccess;

import Model.Customers;
import Utils.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.transform.Result;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBCustomers {
    public static ObservableList<Customers> getAllCustomers() {

        ObservableList<Customers> custList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM Customers JOIN first_level_divisions ON customers.Division_ID = first_level_divisions.Division_ID;";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int customerId = rs.getInt("Customer_ID");
                String customerName = rs.getString("Customer_Name");
                String address = rs.getString("Address");
                String postalCode = rs.getString("Postal_Code");
                String phone = rs.getString("Phone");
                int divisionId = rs.getInt("Division_ID");
                String division = rs.getString("Division");
                int countryId = rs.getInt("Country_ID");
                Customers C = new Customers(customerId, divisionId, customerName, address, postalCode, phone, division, countryId);
                custList.add(C);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return custList;
    }

    public static void addCustomer(String name, String address, String postalCode, String phone, int divisionId) {
        try {
            String sql = "INSERT INTO Customers VALUES(NULL, ?, ?, ?, ?, NOW(), 'script', NOW(), 'script', ?)";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setString(1, name);
            ps.setString(2, address);
            ps.setString(3, postalCode);
            ps.setString(4, phone);
            ps.setInt(5, divisionId);

            ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void editCustomer(int custId, String name, String address, String postalCode, String phone, int divisionId) {
        try {
            String sql = "UPDATE Customers SET customer_name = ?, address = ?, postal_code = ?, phone = ?, division_id = ? WHERE customer_id = ?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setString(1, name);
            ps.setString(2, address);
            ps.setString(3, postalCode);
            ps.setString(4, phone);
            ps.setInt(5, divisionId);
            ps.setInt(6, custId);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteCustomer(int customerId) {
        try {
            String sql = "DELETE FROM Customers WHERE customer_id = ?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setInt(1, customerId);
            ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static boolean validateLogin(String userName, String password) {
        try {
            String sql = "SELECT * FROM Users WHERE User_Name = ? AND Password = ?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setString(1, userName);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
