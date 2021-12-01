package Controller;

import DBAccess.DBAppointments;
import Model.Customers;
import DBAccess.DBCustomers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainScreenController implements Initializable {
    @FXML
    private TableView<Customers> customerTable;
    @FXML
    private TableColumn nameCol, addressCol, postalCodeCol, phoneCol, divisionIdCol;

    private Alert appointmentAlert = new Alert(Alert.AlertType.INFORMATION);

    private static Customers customerToEdit;
    private static Customers selectedCustomer;

    /**
     * This method launches the Add Customer screen.
     */
    public void onAddCustomerBtn(ActionEvent actionEvent) {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("/View/AddCustomer.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * This method launches the Edit Customer screen and passes the selected customer.
     */
    public void onEditCustomerBtn(ActionEvent actionEvent) {
        customerToEdit = customerTable.getSelectionModel().getSelectedItem();
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("/View/EditCustomer.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method deletes the selected customer from the database.
     * Displays alert if no customer is selected for deletion
     */
    public void onDeleteCustomerBtn(ActionEvent actionEvent) {
        selectedCustomer = customerTable.getSelectionModel().getSelectedItem();

        if (selectedCustomer == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("You must select a customer to delete.");
            alert.showAndWait();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Are you sure?");
            alert.setContentText("Do you want to delete this customer?");
            Optional<ButtonType> result = alert.showAndWait();

            int customerId = selectedCustomer.getCustomerId();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                DBCustomers.deleteCustomer(customerId);

                customerTable.setItems(DBCustomers.getAllCustomers());
            }
        }
    }

    /**
     * This method launches the View Appointments screen.
     */
    public void onViewAppts(ActionEvent actionEvent) {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("/View/AppointmentSummary.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method launches the View Reports screen.
     */
    public void onViewReports(ActionEvent actionEvent) {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("/View/Reports.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method displays a custom alert if there is an appointment happening
     * within the next 15 minutes or not.
     */
    public void checkForAppointments() {
        String result = DBAppointments.checkAppointmentIn15Minutes();
        if(result != null) {
            appointmentAlert.setTitle("Upcoming appointment!");
            appointmentAlert.setHeaderText("An appointment will be arriving soon.");
            appointmentAlert.setContentText(result);
            appointmentAlert.show();
        } else {
            appointmentAlert.setTitle("No upcoming appointments");
            appointmentAlert.setHeaderText("There are no appointments within the next 15 minutes.");
            appointmentAlert.show();
        }
    }

    /**
     * This method returns the customer selected in the table.
     */
    public static Customers getCustomerToEdit() {
        return customerToEdit;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        postalCodeCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        divisionIdCol.setCellValueFactory(new PropertyValueFactory<>("division"));
        customerTable.setItems(DBCustomers.getAllCustomers());

        checkForAppointments();
    }
}
