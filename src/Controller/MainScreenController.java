package Controller;

import Model.Customers;
import DBAccess.DBCustomers;
import javafx.event.ActionEvent;
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

    public TableView<Customers> customerTable;
    public TableColumn nameCol, addressCol, postalCodeCol, phoneCol, divisionIdCol;
    public Button addCustomerBtn, editCustomerBtn, deleteCustomerBtn;

    private static Customers customerToEdit;

    public void onAddCustomerBtn(ActionEvent actionEvent) {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("../View/AddCustomer.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onEditCustomerBtn(ActionEvent actionEvent) {
        customerToEdit = customerTable.getSelectionModel().getSelectedItem();
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("../View/EditCustomer.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onDeleteCustomerBtn(ActionEvent actionEvent) {
        Customers selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
        int customerId = selectedCustomer.getCustomerId();

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

            if (result.isPresent() && result.get() == ButtonType.OK) {
                DBCustomers.deleteCustomer(customerId);

                customerTable.setItems(DBCustomers.getAllCustomers());
            }
        }
    }

    public void onViewAppts(ActionEvent actionEvent) {
        customerToEdit = customerTable.getSelectionModel().getSelectedItem();
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("../View/AppointmentSummary.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

    }
}
