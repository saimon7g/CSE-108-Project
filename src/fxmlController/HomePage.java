package fxmlController;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.*;

import java.util.List;
import java.util.Locale;


public class HomePage {
    private Client client;
    private Stage stage;
    private int state;

    public void setStage(Stage stage) {
        this.stage = stage;
        stage.setResizable(false);
    }
    public void setClient(Client client) {
        this.client=client;
    }
    public Client getClient() {
        return client;
    }

    public void setClubNameLabel(String clubName) {
        clubNameLabel.setText(clubName.toUpperCase(Locale.ROOT));
        welcomeText.setText( " Welcome To " + clubName.toUpperCase(Locale.ROOT) + " Football Club" );
    }



    @FXML
    private Label clubNameLabel;

    @FXML
    private TextField box1;

    @FXML
    private Text text1;

    @FXML
    private TextField box2;

    @FXML
    private Text text2;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Button searchButton;

    @FXML
    private Pane pane2;

    @FXML
    private Text salaryText;

    @FXML
    private ListView<String> list;

    @FXML
    private Text welcomeText;

    @FXML
    private GridPane grid;

    @FXML
    void SearchbyName(ActionEvent event) {
        state=1;
        text1.setText(" Enter a Player's Name");
        text1.setVisible(true);
        box1.clear();
        box1.setVisible(true);
        text2.setVisible(false);
        box2.setVisible(false);
        searchButton.setVisible(true);
        pane2.setVisible(false);
        scrollPane.setVisible(false);
    }

    @FXML
    void SearchbyCountry(ActionEvent event) {
        state=2;
        text1.setText(" Enter a Country Name");
        text1.setVisible(true);
        box1.clear();
        box1.setVisible(true);
        text2.setVisible(false);
        box2.setVisible(false);
        searchButton.setVisible(true);
        pane2.setVisible(false);
        scrollPane.setVisible(false);
    }

    @FXML
    void SearchbyPosition(ActionEvent event) {
        state=3;
        text1.setText(" Enter a Position");
        text1.setVisible(true);
        box1.clear();;
        box1.setVisible(true);
        text2.setVisible(false);
        box2.setVisible(false);
        searchButton.setVisible(true);
        pane2.setVisible(false);
        scrollPane.setVisible(false);
    }

    @FXML
    void SearchbySalaryRange(ActionEvent event) {
        state=4;
        text1.setText(" Enter Lower Limit");
        text2.setText(" Enter Highest Limit");
        text1.setVisible(true);
        box1.clear();
        box2.clear();
        box1.setVisible(true);
        text2.setVisible(true);
        box2.setVisible(true);
        searchButton.setVisible(true);
        pane2.setVisible(false);
        scrollPane.setVisible(false);
    }

    @FXML
    void PlayerWithMaxAge(ActionEvent event) {
        text1.setVisible(false);
        box1.setVisible(false);
        text2.setVisible(false);
        box2.setVisible(false);
        searchButton.setVisible(false);
        welcomeText.setVisible(false);
        scrollPane.setVisible(true);
        pane2.setVisible(false);
        List<Player> result=client.getClub().ClubPlayerWithMaxAge();
        showplayer(result);
    }

    @FXML
    void PlayerWithMaxheight(ActionEvent event) {
        text1.setVisible(false);
        box1.setVisible(false);
        text2.setVisible(false);
        box2.setVisible(false);
        pane2.setVisible(false);
        searchButton.setVisible(false);
        welcomeText.setVisible(false);
        scrollPane.setVisible(true);
        List<Player> result=client.getClub().ClubPlayerWithMaxHeight();
        showplayer(result);
    }

    @FXML
    void PlayerWithMaxSalary(ActionEvent event) {
        text1.setVisible(false);
        box1.setVisible(false);
        text2.setVisible(false);
        box2.setVisible(false);
        pane2.setVisible(false);
        searchButton.setVisible(false);
        welcomeText.setVisible(false);
        scrollPane.setVisible(true);
        List<Player> result=client.getClub().ClubPlayerWithMaxSalary();
        showplayer(result);
    }

    @FXML
    void CountrywisePlayer(ActionEvent event) {
        text1.setVisible(false);
        box1.setVisible(false);
        text2.setVisible(false);
        box2.setVisible(false);
        searchButton.setVisible(false);
        salaryText.setVisible(false);
        welcomeText.setVisible(false);
        scrollPane.setVisible(false);
        pane2.setVisible(true);
        list.setVisible(true);
        ObservableList<String> result = client.getClub().CountryWisePlayerCount();
        list.setItems(result);
    }

    @FXML
    public void AllPlayersOfClub(ActionEvent actionEvent) {
        text1.setVisible(false);
        box1.setVisible(false);
        text2.setVisible(false);
        box2.setVisible(false);
        pane2.setVisible(false);
        searchButton.setVisible(false);
        welcomeText.setVisible(false);
        scrollPane.setVisible(true);
        List<Player> result=client.getClub().getAllClubPlayer();
        showplayer(result);
    }

    @FXML
    void TotalClubSalary(ActionEvent event) {
        text1.setVisible(false);
        box1.setVisible(false);
        text2.setVisible(false);
        box2.setVisible(false);
        searchButton.setVisible(false);
        scrollPane.setVisible(false);
        welcomeText.setVisible(false);
        pane2.setVisible(true);
        list.setVisible(false);
        salaryText.setVisible(true);
        salaryText.setText(client.getClub().ClubTotalSalary());
    }


    @FXML
    void buyPlayer(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("BuyPlayer.fxml"));
            Parent root = loader.load();
            client.setState(3);

            // Loading the controller
            BuyPlayer controller = loader.getController();
            controller.setStage(stage);
            controller.setClient(client);
            controller.getBuyablePlayerList();

            // Set the primary stage
            stage.setTitle("Transfer Buy Page");
            stage.setScene(new Scene(root));
            stage.show();
        }catch( Exception e){
            System.out.println("Exception in buy player in Homepage" + e);
        }
    }

    @FXML
    void sellPlayer(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("SellPlayer.fxml"));
            Parent root = loader.load();
            client.setState(4);

            // Loading the controller
            SellPlayer controller = loader.getController();
            controller.setStage(stage);
            controller.setClient(client);
            controller.getCurrentPlayerList();

            // Set the primary stage
            stage.setTitle("Transfer Sale Page");
            stage.setScene(new Scene(root));
            stage.show();
        }catch( Exception e){
            System.out.println("Exception in sell player in Homepage" + e);
        }

    }

    @FXML
    void homePressed(ActionEvent event) {
       try{
           client.getLogin().showHomePage();
       }catch( Exception e){
           System.out.println("Exception in homepressed in Homaepage" + e );
       }
    }

    @FXML
    void about(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("About.fxml"));
            Parent root = loader.load();

            // Loading the controller
            About controller = loader.getController();
            controller.setStage(stage);
            controller.setClient(client);

            // Set the primary stage
            stage.setTitle("Transfer Sale Page");
            stage.setScene(new Scene(root));
            stage.show();
        }catch( Exception e){
            System.out.println("Exception in sell player in Homepage" + e);
        }
    }

    @FXML
    void logOut(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("LogIn.fxml"));
            Parent root = loader.load();

            // Loading the controller
            LogIn controller = loader.getController();
            controller.setStage(stage);

            // Set the primary stage
            stage.setTitle("Log In Page");
            stage.setScene(new Scene(root));
            stage.show();
        }catch ( Exception e){
            System.out.println(e);
        }
    }

    @FXML
    public void SearchPressed(ActionEvent actionEvent) {
        scrollPane.setVisible(true);
        welcomeText.setVisible(false);
        pane2.setVisible(false);
        if(state==1){
            List<Player> result=client.getClub().SearchPlayerByName(box1.getText());
            showplayer(result);
        }
        else if( state==2){
            List<Player> result=client.getClub().SearchPlayerByCountry(box1.getText());
            showplayer(result);
        }
        else if( state==3){
            List<Player> result=client.getClub().SearchPlayerByPosition(box1.getText());
            showplayer(result);
        }
        else if( state==4){
            String st1=box1.getText();
            String st2=box2.getText();
            double low=0;
            double high=0;
            try{
                grid.getChildren().clear();
                low=Double.parseDouble(st1);
                high=Double.parseDouble(st2);
                List<Player> result=client.getClub().SearchPlayerBySalary(low,high);
                showplayer(result);
            }catch(Exception e){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Invalid Input");
                alert.setHeaderText("Input Should be Integer or Decimal");
                alert.setContentText("Enter a number ");
                alert.showAndWait();
                try{
                    client.getLogin().showHomePage();
                }catch(Exception ex){
                    System.out.println(" Exception in HomePage in double parsing"+ex );
                }
            }


        }
    }

    private void showplayer(List<Player> playerlist) {
        grid.getChildren().clear();
        if(playerlist.isEmpty()){
            showAlertNoPlayer();
        }

       else{
        int column = 1, row = 1;
        try {
            column = 0;
            for (Player p : playerlist) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("ShowPlayer.fxml"));
                AnchorPane anchorpane = loader.load();

                ShowPlayer controller = loader.getController();
                controller.setClient(client);
                controller.setData(p);

                grid.add(anchorpane, column, row++);

                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_COMPUTED_SIZE);

                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_COMPUTED_SIZE);

                GridPane.setMargin(anchorpane, new Insets(10));
            }
        } catch (Exception e) {
            System.out.println(" Exception in showing player loop" + e);
        }
    }
    }

    private void showAlertNoPlayer() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("No Result");
        alert.setContentText("No result for this Search");
        alert.showAndWait();
    }

    public void sellPlayercalledbyyThread() {

        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("SellPlayer.fxml"));
            Parent root = loader.load();
            client.setState(4);

            // Loading the controller
            SellPlayer controller = loader.getController();
            controller.setStage(stage);
            controller.setClient(client);
            controller.getCurrentPlayerList();

            // Set the primary stage
            stage.setTitle("Transfer Sale Page");
            stage.setScene(new Scene(root));
            stage.show();
        }catch( Exception e){
            System.out.println("Exception in sell player in Homepage" + e);
        }

    }

    public void buyPlayercalledbyThread() {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("BuyPlayer.fxml"));
            Parent root = loader.load();
            client.setState(3);

            // Loading the controller
            BuyPlayer controller = loader.getController();
            controller.setStage(stage);
            controller.setClient(client);
            controller.getBuyablePlayerList();

            // Set the primary stage
            stage.setTitle("Transfer Buy Page");
            stage.setScene(new Scene(root));
            stage.show();
        }catch( Exception e){
            System.out.println("Exception in buy player in Homepage" + e);
        }
    }
}