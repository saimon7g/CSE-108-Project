package fxmlController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import sample.Client;
import Util.Message;
import Util.NetworkUtil;
import sample.Player;

public class ShowPlayer {
    private Player player;
    private int state=0;
    private Client client;
    public void setClient(Client client) { this.client = client; }

    @FXML
    private Text name;

    @FXML
    private Text country;

    @FXML
    private Text age;

    @FXML
    private Text salary;

    @FXML
    private Text position;

    @FXML
    private Text height;

    @FXML
    private Text number;

    @FXML
    private Button transfer;

    @FXML
    private Text club;

    @FXML
    private Text price;

    @FXML
    private Text setPeice;

    @FXML
    private TextField pricebox;

    @FXML
    void transfer(ActionEvent event) {
        if(state==1){
            try{
                int i=Integer.parseInt(pricebox.getText());
                player.setPrice(i);
                NetworkUtil nu = client.getNetworkUtil();
                Message txt= new Message();
                txt.setInstruction("SaleRequest");
                txt.setPlayer(player);
                try{
                    nu.write(txt);
                }catch(Exception e){
                    System.out.println("Exception in writting to server , in ShowPlayer ,transfer sale " + e);
                }
            }catch(Exception e){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Invalid Input");
                alert.setHeaderText("Input Should be Integer");
                alert.setContentText("Enter a number ");
                alert.showAndWait();
                try{
                    client.getHomePage().sellPlayer(event);
                }catch(Exception ex){
                    System.out.println(" Exception in HomePage in double parsing"+ex );
                }
            }
        }
        else if(state==2){
           // client.getClub().removeFromBuyablePlayer(player);
            player.setClubName(client.getClientName());
            NetworkUtil nu = client.getNetworkUtil();
            Message txt= new Message();
            txt.setInstruction("BuyRequest");
            txt.setPlayer(this.player);
            //System.out.println(player.getClubName() + newPlayer.getClubName());
            try{
                nu.write(txt);
            }catch(Exception e){
                System.out.println("Exception in writting to server , in ShowPlayer ,transfer buy " + e);
            }
        }
    }

    public void setData( Player p){
        this.player=p;
        String s;
        name.setText("Name : " + p.getName());
        country.setText("Country : " + p.getCountry());
        s=""+p.getAge();
        age.setText("Age : " + s);
        s=""+p.getHeight();
        height.setText("Height : " + s);
        position.setText("Position : " + p.getPosition());
        s=""+p.getNumber();
        number.setText("Number : " + s);
        s=""+p.getSalary();
        salary.setText("Salary : " + s);
        club.setText("Club : " + p.getClubName());
        price.setText("Price : "+ p.getPrice());
        transfer.setVisible(false);
        pricebox.setVisible(false);
        setPeice.setVisible(false);
    }

    public void setDataforSale(Player p) {
        this.player=p;
        String s;
        name.setText("Name : " + p.getName());
        country.setText("Country : " + p.getCountry());
        s=""+p.getAge();
        age.setText("Age : " + s);
        s=""+p.getHeight();
        height.setText("Height : " + s);
        position.setText("Position : " + p.getPosition());
        s=""+p.getNumber();
        number.setText("Number : " + s);
        s=""+p.getSalary();
        salary.setText("Salary : " + s);
        club.setText("Club : " + p.getClubName());
        price.setText("Price : "+ p.getPrice());

        transfer.setText("Sale");
        transfer.setVisible(true);
        pricebox.setVisible(true);
        setPeice.setVisible(true);
        state=1;
    }

    public void setDataforBuy(Player p) {
        this.player=p;
        String s;
        name.setText("Name : " + p.getName());
        country.setText("Country : " + p.getCountry());
        s=""+p.getAge();
        age.setText("Age : " + s);
        s=""+p.getHeight();
        height.setText("Height : " + s);
        position.setText("Position : " + p.getPosition());
        s=""+p.getNumber();
        number.setText("Number : " + s);
        s=""+p.getSalary();
        salary.setText("Salary : " + s);
        club.setText("Club : " + p.getClubName());
        price.setText("Price : "+ p.getPrice());

        transfer.setText("Buy");
        if(p.getClubName().equalsIgnoreCase(client.getClientName())){
            transfer.setText("Cancel Sale");
        }
        transfer.setVisible(true);
        pricebox.setVisible(false);
        setPeice.setVisible(false);
        state=2;
    }
}
