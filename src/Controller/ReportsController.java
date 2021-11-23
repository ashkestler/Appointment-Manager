package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ReportsController {




    public void onBackBtn(ActionEvent actionEvent) {
        AddCustomerController back = new AddCustomerController();
        back.returnToMainScreen(actionEvent);

    }

    public void onRunApp101(ActionEvent actionEvent) {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("/View/Report101.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onRunApp102(ActionEvent actionEvent) {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("/View/Report102.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onRunApp103(ActionEvent actionEvent) {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("/View/Report103.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void returnToReports(ActionEvent actionEvent) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/View/Reports.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
