package Controller;

import DBAccess.DBAppointments;
import DBAccess.DBCustomers;
import Model.Contacts;
import Model.Countries;
import Model.Customers;
import Model.Users;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddAppointmentController implements Initializable {
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


    public void onSaveBtn(ActionEvent actionEvent) {
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

        try {
            DBAppointments.addAppointment(title, desc, location, type, start, end, customerId, userId, contactId);
            returnToApptScreen(actionEvent);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void onCancelBtn(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Cancel");
        alert.setHeaderText("Confirm Cancel");
        alert.setContentText("Are you sure you want to cancel adding an appointment?");
        Optional<ButtonType> result = alert.showAndWait();
        // if OK button selection = true, returns to main screen
        if (result.get() == ButtonType.OK){
            returnToApptScreen(actionEvent);
        }
    }

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
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Customers selectedCustomer = MainScreenController.getCustomerToEdit();

        LocalTime start = LocalTime.of(8, 0);
        LocalTime end = LocalTime.of(22, 0);

        while (start.isBefore(end.plusSeconds(1))) {
            startTimePicker.getItems().add(start);
            endTimePicker.getItems().add(start);
            start = start.plusMinutes(15);
        }

        contactComboBox.setItems(DBAppointments.getAllContacts());
        customerComboBox.setItems(DBCustomers.getAllCustomers());
        int custToChoose = selectedCustomer.getCustomerId();
        for (Customers C : customerComboBox.getItems()) {
            if (custToChoose == C.getCustomerId()) {
                customerComboBox.setValue(C);
                break;
            }
        }
        userComboBox.setItems(DBAppointments.getAllUsers());

    }
}
