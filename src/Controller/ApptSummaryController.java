package Controller;

import DBAccess.DBAppointments;
import DBAccess.DBCustomers;
import Model.Appointments;
import Model.Customers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ApptSummaryController implements Initializable {
    private Customers selectedCustomer;
    @FXML
    private TableView<Appointments> apptTable;
    @FXML
    private TableColumn IdCol;
    @FXML
    private TableColumn titleCol;
    @FXML
    private TableColumn descCol;
    @FXML
    private TableColumn locationCol;
    @FXML
    private TableColumn contactCol;
    @FXML
    private TableColumn typeCol;
    @FXML
    private TableColumn startCol;
    @FXML
    private TableColumn endCol;
    @FXML
    private TableColumn custIdCol;
    @FXML
    private TableColumn userIdCol;

    private static Appointments apptToEdit;

    public void onAddApptBtn(ActionEvent actionEvent) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("../View/AddAppointment.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onEditApptBtn(ActionEvent actionEvent) {
        apptToEdit = apptTable.getSelectionModel().getSelectedItem();
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("../View/EditAppointment.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onDeleteApptBtn(ActionEvent actionEvent) {
        apptToEdit = apptTable.getSelectionModel().getSelectedItem();
        int apptId = apptToEdit.getApptId();
        int custId = MainScreenController.getCustomerToEdit().getCustomerId();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Are you sure?");
        alert.setContentText("Do you want to delete this appointment?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            DBAppointments.deleteAppointment(apptId);

            apptTable.setItems(DBAppointments.getAllAppts(custId));
        }
    }

    public void onCancelBtn(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Return to main");
        alert.setHeaderText("Return to main screen");
        alert.setContentText("Are you sure you want to return to the main screen?");
        Optional<ButtonType> result = alert.showAndWait();
        // if OK button selection = true, returns to main screen
        if (result.get() == ButtonType.OK){
            returnToMainScreen(actionEvent);
        }
    }

    public static Appointments getApptToEdit() {
        return apptToEdit;
    }

    private void returnToMainScreen(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("../View/MainScreen.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectedCustomer = MainScreenController.getCustomerToEdit();
        IdCol.setCellValueFactory(new PropertyValueFactory<>("apptId"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descCol.setCellValueFactory(new PropertyValueFactory<>("desc"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contact"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        custIdCol.setCellValueFactory(new PropertyValueFactory<>("custId"));
        userIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
        apptTable.setItems(DBAppointments.getAllAppts(selectedCustomer.getCustomerId()));
    }
}
