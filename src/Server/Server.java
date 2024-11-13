package Server;

import Util.Message;
import Util.NetworkUtil;
import sample.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class Server{
    private ServerSocket serverSocket;
    private HashMap<Integer, NetworkUtil> clientMap;
    private List<Player> AllPlayerList;
    private  List<Player> SalablePlayerList;
    private List<String> ClubList;
    private HashMap<String, String> PasswordMap;
    private Integer Id;

    public HashMap<Integer, NetworkUtil> getClientMap (){
        return clientMap;
    }
    public List<Player> getAllPlayerList() { return AllPlayerList; }
    public List<Player> getSalablePlayerList() { return SalablePlayerList; }


    Server() {
        AllPlayerList=  new ArrayList<>();
        SalablePlayerList = new ArrayList<>();
        ClubList= new ArrayList<>();
        clientMap = new HashMap<>();
        PasswordMap=new HashMap<>();
        PasswordMap.put("Arsenal","arsenal");
        PasswordMap.put("Chelsea","chelsea");
        PasswordMap.put("Liverpool","liverpool");
        PasswordMap.put("Manchester United","manu");
        PasswordMap.put("Manchester City","mancity");
        Id=0;

        try{
            AllPlayerList = FileOperation.readFromFile();
        } catch (Exception e){
            System.out.println("Exception in Server in Constructor in  reading file " + e);
        }
        try {
            serverSocket = new ServerSocket(7777);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                serve(clientSocket);
            }
        } catch (Exception e) {
            System.out.println("Server starts: " + e);
        }
    }


    public void serve(Socket clientSocket) throws IOException, ClassNotFoundException {
        NetworkUtil networkUtil = new NetworkUtil(clientSocket);
        String clientName = (String) networkUtil.read();
        String password=(String)networkUtil.read();

        boolean flag = false;
        if(PasswordMap.containsKey(clientName)){
            flag=PasswordMap.get(clientName).equals(password);
        }

//        for( String s : ClubList){
//            if(s.equalsIgnoreCase(clientName)){
//                clientName=s;
//                flag=true;
//                break;
//            }
//        }

        if(flag){
            clientMap.put(Id++, networkUtil);
            new ReadThreadServer(networkUtil,this,clientName);
        }

        else{
            Message txt1 = new Message();
            txt1.setInstruction("UnSuccessfullLogIn");
            txt1.setPlayer(null);
            networkUtil.write(txt1);
//debug
            System.out.println("Wrong Club Name ");
        }
    }

//    private void ClubListCreate() {
//        for(Player p: AllPlayerList){
//            String club=p.getClubName();
//            boolean flag=false;
//            for(String s: ClubList){
//                if(s.equalsIgnoreCase(club)){
//                    flag=true;
//                    break;
//                }
//            }
//            if(flag==false){
//                ClubList.add(club);
//            }
//        }
//    }

    public void addToSellList (Player player){
        SalablePlayerList.add(player);
        for(int i=0;i<AllPlayerList.size();i++){
            if(AllPlayerList.get(i).getName().equalsIgnoreCase(player.getName())){
                AllPlayerList.remove(i);
                break;
            }
        }
//DEBUEG
//       for(Player p: SalablePlayerList){
//        System.out.println(p.getName()+p.getClubName());
//        }


    }

    public void removeFromSellList(Player player) {
        for(int i=0;i<SalablePlayerList.size();i++){
            if(SalablePlayerList.get(i).getName().equalsIgnoreCase(player.getName())){
                SalablePlayerList.remove(i);
//DEBUEG
 //               System.out.println(" removed from sale list by server");
                break;
            }
        }
        AllPlayerList.add(player);
    }

    public static void main(String args[]) {
        Server server = new Server();
    }
}
