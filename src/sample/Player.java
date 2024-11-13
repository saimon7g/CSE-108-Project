package  sample;
import java.io.Serializable;

public class Player implements Serializable {
    private String Name;
    private String Country;
    private int Age;
    private double Height;
    private String ClubName;
    private String Position;
    private int Number;
    private double Salary;
    private int price;


    public Player(Player player, String clientName) {
        this.Name= player.getName();
        this.Country=player.getCountry();
        this.Age=player.getAge();
        this.Height=player.getHeight();
        this.ClubName=clientName;
        this.Position=player.getPosition();
        this.Number=player.getNumber();
        this.Salary=player.getSalary();
        this.price=player.getPrice();
    }
    public Player(){
        price=0;
    }
    public void setName(String Name){ this.Name=Name;}
   public void setCountry(String Country){
        this.Country=Country;
   }
   public void setAge(int Age){
        this.Age=Age;
   }
   public void setHeight(double Height){
        this.Height=Height;
   }
   public void setClubName(String ClubName){
        this.ClubName=ClubName;
   }
   public void setPosition(String Position){
        this.Position=Position;
   }
   public void setNumber(int Number){
        this.Number=Number;
   }
   public void setSalary(double Salary){
        this.Salary=Salary;
   }
   public void setPrice(int price) { this.price = price; }

    public String getName(){
        return Name;
    }
    public String getCountry(){
        return Country;
    }
    public int getAge(){
        return Age;
    }
    public double getHeight(){
        return Height;
    }
    public String getClubName(){
        return ClubName;
    }
    public String getPosition(){
        return Position;
    }
    public int getNumber(){
        return Number;
    }
    public double getSalary(){
        return Salary;
    }
    public int getPrice() { return price; }

}
