package fxmlController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import sample.Client;
import sample.Player;

import java.util.ArrayList;
import java.util.List;

public class BuyPlayer {
    private Client client;
    private Stage stage;
    private List<Player> BuyablePlayerlist;

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public void setClient(Client client) {
        this.client=client;
    }

    @FXML
    private GridPane grid;

    @FXML
    void GoBack(ActionEvent event) {
        client.setState(2);
        client.getHomePage().homePressed(event);
    }

    public void getBuyablePlayerList(){
        this.BuyablePlayerlist= new ArrayList<>();
        BuyablePlayerlist = client.getClub().getBuyablePlayers();
        grid.getChildren().clear();
        int column=1,row=1;
        try{
            column=0;
            for(Player p: BuyablePlayerlist){

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("ShowPlayer.fxml"));
                AnchorPane anchorpane=loader.load();

                ShowPlayer controller = loader.getController();
                controller.setClient(client);
                controller.setDataforBuy(p);

                grid.add(anchorpane,column,row++);

                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_COMPUTED_SIZE);

                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_COMPUTED_SIZE);

                GridPane.setMargin(anchorpane,new Insets(10));
            }
        }catch(Exception e){
            System.out.println(" Exception in showing player loop for buy " + e);
        }
    }
}