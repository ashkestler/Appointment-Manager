package Controller;

import DBAccess.DBAppointments;
import DBAccess.DBCustomers;
import Model.Appointments;
import Model.Customers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class Report103Controller implements Initializable {
    @FXML
    private TableView<Appointments> apptTableByCust;
    @FXML
    private ComboBox<Customers> customerCombo;
    @FXML
    private TableColumn IdCol;
    @FXML
    private TableColumn titleCol;
    @FXML
    private TableColumn descCol;
    @FXML
    private TableColumn locationCol;
    @FXML
    private TableColumn typeCol;
    @FXML
    private TableColumn startCol;
    @FXML
    private TableColumn endCol;

    /**
     * This method gets the customer from the combobox and populates the table with the appointments
     * for the chosen customer.
     * @param actionEvent
     */
    public void chooseCustomer(ActionEvent actionEvent) {
        Customers chosenCustomer = customerCombo.getValue();
        apptTableByCust.setItems(DBAppointments.getAllApptsByCustomer(chosenCustomer.getCustomerId()));
    }

    /**
     * This method returns to the Reports screen when Back button is pressed.
     * @param actionEvent
     */
    public void onBackBtn(ActionEvent actionEvent) {
        ReportsController backBtn = new ReportsController();
        backBtn.returnToReports(actionEvent);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customerCombo.setItems(DBCustomers.getAllCustomers());

        IdCol.setCellValueFactory(new PropertyValueFactory<>("apptId"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descCol.setCellValueFactory(new PropertyValueFactory<>("desc"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("end"));
    }
}
