package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Utils.JDBC;

import java.util.Locale;
import java.util.ResourceBundle;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Locale locale = new Locale("fr");
        ResourceBundle bundle = ResourceBundle.getBundle("App", locale);
        Parent root = FXMLLoader.load(getClass().getResource("/View/LoginScreen.fxml"), bundle);
        primaryStage.setTitle("Appointment Scheduler");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        JDBC.makeConnection();
        launch(args);

        JDBC.closeConnection();
    }
}
