package Controller;

import DBAccess.DBAppointments;
import DBAccess.DBCustomers;
import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.*;
import java.util.Optional;
import java.util.ResourceBundle;

public class EditAppointmentController implements Initializable {
    private Appointments selectedAppt;
    @FXML
    private TextField idField;
    @FXML
    private TextField titleField;
    @FXML
    private TextField descriptionField;
    @FXML
    private TextField locationField;
    @FXML
    private TextField typeField;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private ComboBox<LocalTime> startTimePicker;
    @FXML
    private ComboBox<LocalTime> endTimePicker;
    @FXML
    private ComboBox<Contacts> contactComboBox;
    @FXML
    private ComboBox<Customers> customerComboBox;
    @FXML
    private ComboBox<Users> userComboBox;

    /**
     * This method handles the Save button action. Verifies text fields are not empty and updates the
     * database.
     * @param actionEvent
     */
    public void onSaveBtn(ActionEvent actionEvent) {
        int apptId = Integer.parseInt(idField.getText());
        String title = titleField.getText();
        String desc = descriptionField.getText();
        String location = locationField.getText();
        String type = typeField.getText();
        LocalDate date = startDatePicker.getValue();
        LocalTime startTime = startTimePicker.getValue();
        LocalTime endTime = endTimePicker.getValue();
        LocalDateTime start = LocalDateTime.of(date, startTime);
        LocalDateTime end = LocalDateTime.of(date, endTime);
        int contactId = contactComboBox.getValue().getContactId();
        int userId = userComboBox.getValue().getUserId();
        int customerId = customerComboBox.getValue().getCustomerId();

        if (title.isEmpty()) {
            showAlert(1);
            return;
        }
        if (desc.isEmpty()) {
            showAlert(2);
            return;
        }
        if (location.isEmpty()) {
            showAlert(3);
            return;
        }
        if (type.isEmpty()) {
            showAlert(4);
            return;
        }
        if (endTime.isBefore(startTime.plusSeconds(1))) {
            showAlert(5);
            return;
        }

        if (DBAppointments.modApptOverlap(customerId, apptId, start, end)) {
            showAlert(6);
            return;
        }

        try {
            DBAppointments.editAppointment(apptId, title, desc, location, type, start, end, customerId, userId, contactId);
            returnToApptScreen(actionEvent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onCancelBtn(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Cancel");
        alert.setHeaderText("Confirm Cancel");
        alert.setContentText("Are you sure you want to cancel editing an appointment?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK){
            returnToApptScreen(actionEvent);
        }
    }

    /**
     * This method returns to the Appointment Summary screen.
     * @param event
     */
    private void returnToApptScreen(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("../View/AppointmentSummary.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays various alerts.
     * @param alertType
     */
    public void showAlert(int alertType) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        switch (alertType) {
            case 1:
                alert.setTitle("Error");
                alert.setHeaderText("Error Adding Appointment");
                alert.setContentText("Title field is required");
                alert.showAndWait();
                break;

            case 2:
                alert.setTitle("Error");
                alert.setHeaderText("Error Adding Appointment");
                alert.setContentText("Description field is required");
                alert.showAndWait();
                break;

            case 3:
                alert.setTitle("Error");
                alert.setHeaderText("Error Adding Appointment");
                alert.setContentText("Location field is required");
                alert.showAndWait();
                break;

            case 4:
                alert.setTitle("Error");
                alert.setHeaderText("Error Adding Appointment");
                alert.setContentText("Type field is required");
                alert.showAndWait();
                break;

            case 5:
                alert.setTitle("Error");
                alert.setHeaderText("Error Adding Appointment");
                alert.setContentText("End time must be after start time");
                alert.showAndWait();
                break;

            case 6:
                alert.setTitle("Error");
                alert.setHeaderText("Error Adding Appointment");
                alert.setContentText("The chosen time span overlaps with a previously scheduled appointment");
                alert.showAndWait();
                break;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectedAppt = ApptSummaryController.getApptToEdit();

        idField.setText(String.valueOf(selectedAppt.getApptId()));
        titleField.setText(selectedAppt.getTitle());
        descriptionField.setText(selectedAppt.getDesc());
        locationField.setText(selectedAppt.getLocation());
        typeField.setText(selectedAppt.getType());
        startDatePicker.setValue(selectedAppt.getStart().toLocalDate());

        LocalTime startEst = LocalTime.of(8, 0);
        ZonedDateTime zdt = LocalDateTime.of(LocalDate.now(), startEst).atZone(ZoneId.of("America/New_York"));
        ZonedDateTime startTime = zdt.withZoneSameInstant(ZoneId.systemDefault());
        LocalTime start = startTime.toLocalTime();

        LocalTime endEst = LocalTime.of(22, 0);
        ZonedDateTime zdt1 = LocalDateTime.of(LocalDate.now(), endEst).atZone(ZoneId.of("America/New_York"));
        ZonedDateTime endTime = zdt1.withZoneSameInstant(ZoneId.systemDefault());
        LocalTime end = endTime.toLocalTime();

        while (start.isBefore(end.plusSeconds(1))) {
            startTimePicker.getItems().add(start);
            endTimePicker.getItems().add(start);
            start = start.plusMinutes(15);
        }
        startTimePicker.setValue(selectedAppt.getStart().toLocalTime());
        endTimePicker.setValue(selectedAppt.getEnd().toLocalTime());

        contactComboBox.setItems(DBAppointments.getAllContacts());
        int contactToChoose = selectedAppt.getContactId();
        for (Contacts C : contactComboBox.getItems()) {
            if (contactToChoose == C.getContactId()) {
                contactComboBox.setValue(C);
                break;
            }
        }

        customerComboBox.setItems(DBCustomers.getAllCustomers());
        int custToChoose = selectedAppt.getCustId();
        for (Customers C : customerComboBox.getItems()) {
            if (custToChoose == C.getCustomerId()) {
                customerComboBox.setValue(C);
                break;
            }
        }

        userComboBox.setItems(DBAppointments.getAllUsers());
        int userToChoose = selectedAppt.getUserId();
        for (Users U : userComboBox.getItems()) {
            if (userToChoose == U.getUserId()) {
                userComboBox.setValue(U);
                break;
            }
        }
    }
}
