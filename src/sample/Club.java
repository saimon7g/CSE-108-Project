package  sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Club {
    private String Name;
    private int PlayerCount;
    private List <Player> clubPlayer;
    private List <Player> BuyablePlayers;
    private Client client;

    public void setName(String Name){
        this.Name=Name;
    }
    public String getName(){
        return Name;
    }
    public int getPlayerCount(){return PlayerCount;}
    public List<Player> getAllClubPlayer() {
        return clubPlayer;
    }
    public List<Player> getBuyablePlayers() {
        return BuyablePlayers;
    }

    Club(Client client, String Name) {
        this.client=client;
        this.Name=Name;
        PlayerCount = 0;
        clubPlayer = new ArrayList<>();
        BuyablePlayers= new ArrayList<>();
    }

    public List<Player> SearchPlayerByName(String Name){
        List<Player> data= new ArrayList<>();
        for(Player p: clubPlayer ){
            if(p.getName().equalsIgnoreCase(Name)){
                data.add(p);
            }
        }
        return data;
    }
    public List<Player> SearchPlayerByCountry(String countryName){
        List<Player> data= new ArrayList<>();
        for (Player p :clubPlayer){
            if (p.getCountry().equalsIgnoreCase(countryName)){
                data.add(p);
            }
        }
        return data;
    }
    public List<Player> SearchPlayerByPosition(String position){
        List<Player> data= new ArrayList<>();
        for(Player p:clubPlayer){
            if(p.getPosition().equalsIgnoreCase(position)){
                data.add(p);
            }
        }
        return data;
    }
    public List<Player> SearchPlayerBySalary( Double low, Double high){
        List<Player> data= new ArrayList<>();
        for( Player p: clubPlayer){
            if(p.getSalary()>= low && p.getSalary()<= high){
                data.add(p);
            }
        }
        return data;
    }
    public List<Player> ClubPlayerWithMaxAge(){
        List<Player> data= new ArrayList<>();
        int MaxAge=0;
        for(Player p: clubPlayer){
            if(p.getAge()>=MaxAge){
                MaxAge=p.getAge();
            }
        }
        for(Player p: clubPlayer){
            if(p.getAge()==MaxAge){
               data.add(p);
            }
        }
        return data;
    }

    public List<Player> ClubPlayerWithMaxHeight(){

        List<Player> data= new ArrayList<>();
        double MaxHeight=0;
        for(Player p: clubPlayer){
            if(p.getHeight()>=MaxHeight){
                MaxHeight=p.getHeight();
            }
        }
        for(Player p:clubPlayer){
            if(p.getHeight()==MaxHeight){
                data.add(p);
            }
        }
        return data;
    }

    public List<Player> ClubPlayerWithMaxSalary(){
        List<Player> data= new ArrayList<>();
        double MaxSalary=0;
        for(Player p: clubPlayer){
            if(p.getSalary()>=MaxSalary){
                MaxSalary=p.getSalary();
            }
        }
        for(Player p: clubPlayer){
            if(p.getSalary()==MaxSalary){
                data.add(p);
            }
        }
        return data;
    }

    public String ClubTotalSalary(){
        double TotalSalary=0;
        for(Player p: clubPlayer){
            TotalSalary+=p.getSalary();
        }
        TotalSalary*=52;
        String s= "Total salary = " + String.format("%.2f",TotalSalary);
        return s;
    }

    public ObservableList<String> CountryWisePlayerCount(){
        ObservableList<String > result= FXCollections.observableArrayList();
        List<String > AllCountry= new ArrayList<>();

        for( Player p: clubPlayer){
            boolean isAvailabe=false;
            for( String s : AllCountry){
                if(p.getCountry().equalsIgnoreCase(s)){
                    isAvailabe=true;
                    break;
                }
            }
            if(isAvailabe==false){
               AllCountry.add(p.getCountry());
            }
        }
        for(String s: AllCountry){
            int count=0;
            for( Player p: clubPlayer){
                if(p.getCountry().equalsIgnoreCase(s)){
                    count++;
                }
            }
            String st = s + " , " +"Number of Player is "+ count;
            result.add(st);
        }
        return result;
    }

    public void addPlayer(Player player) {
        clubPlayer.add(player);
    }
    public void removeFromBuyablePlayer(Player player) {
        for(int i=0;i<BuyablePlayers.size();i++){
            if(BuyablePlayers.get(i).getName().equalsIgnoreCase(player.getName())){
                BuyablePlayers.remove(i);
                break;
            }
        }
        if(player.getClubName().equalsIgnoreCase(Name)){
            addPlayer(player);
        }
       // System.out.println("Removed "+ player.getName() + player.getClubName() + Name);
    }
    public void addBuyablePlayers(Player BuyablePlayersfromserver){

      //Clearing comment: own Player will not be shown in own buy list;
        if(BuyablePlayersfromserver.getClubName().equalsIgnoreCase(Name)){
            for(int i=0;i<clubPlayer.size();i++){
                if(clubPlayer.get(i).getName().equalsIgnoreCase(BuyablePlayersfromserver.getName())){
                    clubPlayer.remove(i);
                    break;
                }
            }
        }
  //      else{
            BuyablePlayers.add(BuyablePlayersfromserver);
  //    }
    }
}