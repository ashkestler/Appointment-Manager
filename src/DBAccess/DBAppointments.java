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

    /**
     * This method queries the database and returns all appointments.
     * @return apptList
     */
    public static ObservableList<Appointments> getAllAppts() {

        ObservableList<Appointments> apptList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * from appointments JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
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

    /**
     * This method queries the database and returns all appointments in the current month.
     * @return weeklyApptList
     */
    public static ObservableList<Appointments> getWeeklyAppts() {

        ObservableList<Appointments> weeklyApptList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * from appointments JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID AND yearweek(Start) = yearweek(NOW())";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
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
                weeklyApptList.add(A);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return weeklyApptList;
    }

    /**
     * This method queries the database and returns all appointments in the current month.
     * @return monthlyApptList
     */
    public static ObservableList<Appointments> getMonthlyAppts() {

        ObservableList<Appointments> monthlyApptList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * from appointments JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID AND month(Start) = month(NOW())";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
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
                monthlyApptList.add(A);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return monthlyApptList;
    }

    /**
     * This method queries the database and returns all appointments for a given contact.
     * @param contactId
     * @return apptList
     */
    public static ObservableList<Appointments> getAllApptsByContact(int contactId) {

        ObservableList<Appointments> apptList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * from appointments WHERE contact_id = ?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, contactId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int apptId = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String desc = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
                int customerId = rs.getInt("Customer_ID");
                Appointments A = new Appointments(apptId, title, desc, location, type, start, end, customerId);
                apptList.add(A);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return apptList;
    }

    /**
     * This method returns a list of appointments by customer. The lambda filters all appointments by those which match
     * a given customer ID.
     * @param custId
     * @return cList
     */
    public static ObservableList<Appointments> getAllApptsByCustomer(int custId) {

        ObservableList<Appointments> allList = getAllAppts();
        ObservableList<Appointments> cList = allList.filtered(a -> {
            if (custId == a.getCustId()) {
                return true;
            }
            return false;
        });
        return cList;
    }

    /**
     * This method queries the database and returns a list of all contacts.
     * @return contactList
     */
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

    /**
     * This method queries the database and returns a list of all users.
     * @return userList
     */
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

    /**
     * This method queries the database and returns a list of all types.
     * @return typeList
     */
    public static ObservableList<String> getAllTypes() {

        ObservableList<String> typeList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT distinct Type FROM Appointments";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String type = rs.getString("Type");
                typeList.add(type);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return typeList;
    }

    /**
     * This method runs the first report.
     * @param type
     * @param month
     * @return count
     */
    public static int runApp101(String type, String month) {
        int count = 0;
        try {
            String sql = "SELECT count(*) FROM appointments WHERE type = ? AND monthname(Start) = ?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setString(1, type);
            ps.setString(2, month);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt("count(*)");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }

    /**
     * This method adds a new appointment to the database.
     * @param title
     * @param desc
     * @param location
     * @param type
     * @param start
     * @param end
     * @param custId
     * @param userId
     * @param contactId
     */
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

    /**
     * This method updates an appointment in the database.
     * @param apptId
     * @param title
     * @param desc
     * @param location
     * @param type
     * @param start
     * @param end
     * @param customerId
     * @param userId
     * @param contactId
     */
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

    /**
     * This method deletes an appointment from the database.
     * @param apptId
     */
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

    /**
     * This method checks if customer has an overlapping appointment with the one currently being scheduled.
     * @param custId
     * @param aStart
     * @param aEnd
     * @return
     */
    public static boolean apptOverlap(int custId, LocalDateTime aStart, LocalDateTime aEnd) {
        try {
            String sql = "SELECT * FROM Appointments WHERE customer_id = ?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setInt(1, custId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                LocalDateTime bStart = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime bEnd = rs.getTimestamp("End").toLocalDateTime();

                if ((aStart.isAfter(bStart) || aStart.isEqual(bStart)) && aStart.isBefore(bEnd)) {
                    return true;
                }

                if (aEnd.isAfter(bStart) && (aEnd.isBefore(bEnd) || aEnd.isEqual(bEnd))) {
                    return true;
                }

                if ((aStart.isBefore(bStart) || aStart.isEqual(bStart)) &&
                        (aEnd.isAfter(bEnd) || aEnd.isEqual(bEnd))) {
                    return true;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    /**
     * This method checks whether there is an appointment overlap for the Edit Appointment screen.
     * @param custId
     * @param apptId
     * @param aStart
     * @param aEnd
     * @return
     */
    public static boolean modApptOverlap(int custId, int apptId, LocalDateTime aStart, LocalDateTime aEnd) {
        try {
            String sql = "SELECT * FROM Appointments WHERE customer_id = ? AND appointment_id != ?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setInt(1, custId);
            ps.setInt(2, apptId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                LocalDateTime bStart = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime bEnd = rs.getTimestamp("End").toLocalDateTime();

                if ((aStart.isAfter(bStart) || aStart.isEqual(bStart)) && aStart.isBefore(bEnd)) {
                    return true;
                }

                if (aEnd.isAfter(bStart) && (aEnd.isBefore(bEnd) || aEnd.isEqual(bEnd))) {
                    return true;
                }

                if ((aStart.isBefore(bStart) || aStart.isEqual(bStart)) &&
                        (aEnd.isAfter(bEnd) || aEnd.isEqual(bEnd))) {
                    return true;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    /**
     * This method checks if there is an appointment upcoming within 15 minutes.
     * @return result
     */
    public static String checkAppointmentIn15Minutes() {
        try {
            LocalDateTime currentTime = LocalDateTime.now();
            LocalDateTime endTime = currentTime.plusMinutes(15).plusSeconds(1);

            String sql = "SELECT * FROM appointments WHERE start BETWEEN ? AND ?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setTimestamp(1, Timestamp.valueOf(currentTime));
            ps.setTimestamp(2, Timestamp.valueOf(endTime));

            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                String result = String.format("Appointment ID %o is starting at %tc",
                        rs.getInt("Appointment_ID"), rs.getTimestamp("Start"));
                return result;
            }

            return null;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        return null;
        }
    }
}
