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

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.ResourceBundle;

public class LoginScreenController implements Initializable {
    @FXML
    private Button ExitBtn;
    @FXML
    private TextField usernameText, passwordText;
    @FXML
    private Label timeZoneLabel;
    private ResourceBundle resourceBundle;

    /**
     * This method handles the login process.
     * Checks if username and password exists in database and displays error message if login fails
     */
    public void onLoginBtn(ActionEvent actionEvent) {
        String userName = usernameText.getText();
        String password = passwordText.getText();
        if (DBCustomers.validateLogin(userName, password)) {
            try {
                Parent parent = FXMLLoader.load(getClass().getResource("/View/MainScreen.fxml"));
                Scene scene = new Scene(parent);
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            showAlert();
        }
        logLoginAttempt(DBCustomers.validateLogin(userName, password));
    }

    /**
     * This method exits the application.
     */
    public void onExitBtn(ActionEvent actionEvent) { System.exit(0); }

    /**
     * This method displays a translatable alert when login fails.
     */
    public void showAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(resourceBundle.getString("error"));
        alert.setHeaderText(resourceBundle.getString("loginfailed"));
        alert.setContentText(resourceBundle.getString("incorrect"));
        alert.showAndWait();
    }

    /**
     * This method logs every login attempt with a timestamp, the attempted username, and whether the attempt was successful.
     */
    private void logLoginAttempt(boolean success) {
        final DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        final String time = formatter.format(OffsetDateTime.now().truncatedTo(ChronoUnit.MILLIS));
        final String username = usernameText.getText();
        try {
            final FileWriter fw = new FileWriter("login_activity.txt", true);
            final BufferedWriter bw = new BufferedWriter(fw);
            bw.write("time: " + time + "\t");
            bw.write("username: " + username + "\t");
            bw.write("success: " + success + "\t");
            bw.newLine();
            bw.close();
        } catch (IOException ex) {
            System.out.println("Failed to log invalid login attempt:");
            System.out.println(ex.getMessage());
        }
    }


    /**
     * This method initializes the Login screen controller.
     * The lambda sets the action for the Exit button.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
        timeZoneLabel.setText(resourceBundle.getString("timezone") + " " + String.valueOf(ZoneId.systemDefault()));
        ExitBtn.setOnAction(e -> onExitBtn(e));
    }
}
