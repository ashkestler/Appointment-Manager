package DBAccess;

import Model.Appointments;
import Model.Contacts;
import Model.Users;
import Utils.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import java.sql.*;
import java.time.LocalDateTime;

public class DBAppointments {
    public static ObservableList<Appointments> getAllAppts(int custId) {

        ObservableList<Appointments> apptList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * from appointments JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID AND appointments.Customer_ID = ?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, custId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int apptId = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String desc = rs.getString("Description");
                String location = rs.getString("Location");
                String contact = rs.getString("Contact_Name");
                String type = rs.getString("Type");
                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
                int customerId = rs.getInt("Customer_ID");
                int userId = rs.getInt("User_ID");
                int contactId = rs.getInt("Contact_ID");
                Appointments A = new Appointments(apptId, title, desc, location, type, start, end, customerId, userId, contact, contactId);
                apptList.add(A);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return apptList;
    }

    public static ObservableList<Contacts> getAllContacts() {

        ObservableList<Contacts> contactList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM Contacts";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int contactId = rs.getInt("Contact_ID");
                String name = rs.getString("Contact_Name");
                String email = rs.getString("Email");
                Contacts C = new Contacts(contactId, name, email);
                contactList.add(C);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return contactList;
    }

    public static ObservableList<Users> getAllUsers() {

        ObservableList<Users> userList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM Users";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int userId = rs.getInt("User_ID");
                String userName = rs.getString("User_Name");
                String password = rs.getString("Password");
                Users U = new Users(userId, userName, password);
                userList.add(U);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userList;
    }

    public static void addAppointment(String title, String desc, String location, String type,
                                      LocalDateTime start, LocalDateTime end, int custId,
                                      int userId, int contactId) {
        try {
            String sql = "INSERT INTO Appointments VALUES(NULL, ?, ?, ?, ?, ?, ?, NOW(), 'script', NOW(), 'script', ?, ?, ?)";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setString(1, title);
            ps.setString(2, desc);
            ps.setString(3, location);
            ps.setString(4, type);
            ps.setTimestamp(5, Timestamp.valueOf(start));
            ps.setTimestamp(6, Timestamp.valueOf(end));
            ps.setInt(7, custId);
            ps.setInt(8, userId);
            ps.setInt(9, contactId);

            ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void editAppointment(int apptId, String title, String desc, String location,
                                       String type, LocalDateTime start, LocalDateTime end,
                                       int customerId, int userId, int contactId) {
        try {
            String sql = "UPDATE Appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE appointment_id = ?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setString(1, title);
            ps.setString(2, desc);
            ps.setString(3, location);
            ps.setString(4, type);
            ps.setTimestamp(5, Timestamp.valueOf(start));
            ps.setTimestamp(6, Timestamp.valueOf(end));
            ps.setInt(7, customerId);
            ps.setInt(8, userId);
            ps.setInt(9, contactId);
            ps.setInt(10, apptId);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteAppointment(int apptId) {
        try {
            String sql = "DELETE FROM Appointments WHERE appointment_id = ?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setInt(1, apptId);
            ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
