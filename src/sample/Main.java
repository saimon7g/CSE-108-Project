package sample;

import fxmlController.LogIn;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setResizable(false);
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxmlController/LogIn.fxml"));
        Parent root = loader.load();

        // Loading the controller
        LogIn controller = loader.getController();
        controller.setStage(primaryStage);

        // Set the primary stage
        primaryStage.setTitle("LOG IN Page");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    public static void main(String[] args) throws Exception {
        launch(args);
    }
}