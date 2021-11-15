package Controller;

import DBAccess.DBCountries;
import DBAccess.DBCustomers;
import DBAccess.DBDivisions;
import Model.Countries;
import Model.Divisions;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddCustomerController implements Initializable {
    public TextField custNameText;
    public TextField addressText;
    public TextField postalCodeText;
    public TextField phoneText;
    public ComboBox<Countries> countryComboBox;
    public ComboBox<Divisions> divisionComboBox;
    public Button saveBtn;
    public Button cancelBtn;

    public void onSaveBtn(ActionEvent actionEvent) {
        String name = custNameText.getText();
        String address = addressText.getText();
        String postalCode = postalCodeText.getText();
        String phone = phoneText.getText();
        int divisionId = divisionComboBox.getValue().getDivisionId();

        if (name.isEmpty()) {
            showAlert(1);
            return;
        }
        if (address.isEmpty()) {
            showAlert(2);
            return;
        }
        if (postalCode.isEmpty()) {
            showAlert(3);
            return;
        }
        if (phone.isEmpty()) {
            showAlert(4);
            return;
        }
        if (divisionComboBox.getSelectionModel().isEmpty()) {
            showAlert(5);
            return;
        }

        try {
            DBCustomers.addCustomer(name, address, postalCode, phone, divisionId);
            returnToMainScreen(actionEvent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onCancelBtn(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Cancel");
        alert.setHeaderText("Confirm Cancel");
        alert.setContentText("Are you sure you want to cancel adding a new customer?");
        Optional<ButtonType> result = alert.showAndWait();
        // if OK button selection = true, returns to main screen
        if (result.get() == ButtonType.OK){
            returnToMainScreen(actionEvent);
        }
    }

    public void cbxSelect(ActionEvent actionEvent) {
        Countries C = countryComboBox.getValue();
        divisionComboBox.setItems(DBDivisions.getStates(C.getCountryId()));
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

    public void showAlert(int alertType) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        switch (alertType) {
            case 1:
                alert.setTitle("Error");
                alert.setHeaderText("Error Adding Customer");
                alert.setContentText("Name field is required");
                alert.showAndWait();
                break;

            case 2:
                alert.setTitle("Error");
                alert.setHeaderText("Error Adding Customer");
                alert.setContentText("Address field is required");
                alert.showAndWait();
                break;

            case 3:
                alert.setTitle("Error");
                alert.setHeaderText("Error Adding Customer");
                alert.setContentText("Postal code field is required");
                alert.showAndWait();
                break;

            case 4:
                alert.setTitle("Error");
                alert.setHeaderText("Error Adding Customer");
                alert.setContentText("Phone field is required");
                alert.showAndWait();
                break;

            case 5:
                alert.setTitle("Error");
                alert.setHeaderText("Error Adding Customer");
                alert.setContentText("Division field is required");
                alert.showAndWait();
                break;

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        countryComboBox.setItems(DBCountries.getAllCountries());
        }
}


