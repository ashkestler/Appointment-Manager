package Controller;

import DBAccess.DBCustomers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;

public class LoginScreenController implements Initializable {
    @FXML
    private TextField usernameText, passwordText;
    @FXML
    private Label timeZoneLabel;
    private ResourceBundle resourceBundle;


    public void onLoginBtn(ActionEvent actionEvent) {
        String userName = usernameText.getText();
        String password = passwordText.getText();
        if (DBCustomers.validateLogin(userName, password)) {
            try {
                Parent parent = FXMLLoader.load(getClass().getResource("../View/MainScreen.fxml"));
                Scene scene = new Scene(parent);
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            showAlert(1);
        }
    }

    public void onExitBtn(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void showAlert(int alertType) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        switch (alertType) {
            case 1:
                alert.setTitle(resourceBundle.getString("error"));
                alert.setHeaderText("Login Failed");
                alert.setContentText("Username / password is incorrect");
                alert.showAndWait();
                break;
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
        timeZoneLabel.setText(resourceBundle.getString("timezone") + " " + String.valueOf(ZoneId.systemDefault()));
    }
}
