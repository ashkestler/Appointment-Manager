package Controller;

import DBAccess.DBAppointments;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Report101Controller implements Initializable {
    @FXML
    private ComboBox<String> typeCombo;
    @FXML
    private ComboBox<String> monthCombo;
    @FXML
    private Button countBtn;
    @FXML
    private Button backBtn;
    @FXML
    private Label resultLabel;

    /**
     * This method retrieves the type and month from the comboboxes and counts the number of the chosen
     * type of appointment in the chosen month.
     * @param actionEvent
     */
    public void onCountBtn(ActionEvent actionEvent) {
        String type = typeCombo.getValue();
        String month = monthCombo.getValue();
        int count = DBAppointments.runApp101(type, month);

        if (count == 1) {
            resultLabel.setText("There is " + count + " " + type +
                    " appointment in " + month);
        } else {
            resultLabel.setText("There are " + count + " " + type +
                    " appointments in " + month);
        }
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
        typeCombo.setItems(DBAppointments.getAllTypes());
        monthCombo.getItems().addAll("January", "February", "March", "April", "May", "June",
                "July",  "August", "September", "October", "November", "December");

    }
}
