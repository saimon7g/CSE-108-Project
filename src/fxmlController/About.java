package fxmlController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.Client;

public class About {
    private Client client;
    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
        Image image1 = new Image(getClass().getResourceAsStream("/Images/AllrightsReserved.png"));
        iamge.setImage(image1);
        Image image2 = new Image(getClass().getResourceAsStream("/Images/eplLogo2.png"));
        imagelogo.setImage(image2);
    }
    public void setClient(Client client) {
        this.client=client;
    }


    @FXML
    private ImageView iamge;

    @FXML
    private ImageView imagelogo;

    @FXML
    void goBack(ActionEvent event) {
        client.setState(2);
        client.getHomePage().homePressed(event);

    }
}
