package fxmlController;

import fxmlController.HomePage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.Client;

public class LogIn {
    private Client client;
    private Stage stage;
    public Client getClient() {
        return client;
    }
    public void setStage(Stage stage) {
        this.stage = stage;
        Image image = new Image(getClass().getResourceAsStream("/Images/PremierLeagueLogo.jpg"));
        imageView.setImage(image);
    }

    @FXML
    private ImageView imageView;

    @FXML
    private TextField LogInBox;

    @FXML
    private PasswordField passWord;

    @FXML
    void reset(ActionEvent event) {
        LogInBox.clear();
        passWord.clear();
    }

    @FXML
    public void LogInAttempt(ActionEvent event) {
        String clubName=LogInBox.getText();
        String password=passWord.getText();
        String serverAddress = "127.0.0.1";
        int serverPort = 7777;
        client = new Client(serverAddress, serverPort,clubName,this, password);
    }

    public void showHomePage() throws Exception{
         try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("HomePage.fxml"));
            Parent root = loader.load();

            // Loading the controller
            HomePage controller = loader.getController();
            controller.setStage(stage);
            controller.setClient(client);
            controller.setClubNameLabel(client.getClub().getName());
            client.setHomePage(controller);

            // Set the primary stage
            stage.setTitle("HomePage");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            System.out.println("Exception in login class , Successfull LogIn method" + e);
        }
    }
    public void showAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Incorrect Credentials");
        alert.setHeaderText("Incorrect Credentials");
        alert.setContentText("The username and password you provided is not correct.");
        alert.showAndWait();
    }
}
