package Server;

import Util.Message;
import Util.NetworkUtil;
import sample.Player;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

public class ReadThreadServer implements Runnable {
    private Thread thr;
    private NetworkUtil networkUtil;
    private Server server;
    private String clientName;


    public ReadThreadServer(NetworkUtil networkUtil, Server server, String clientName) {
        this.networkUtil = networkUtil;
        this.server=server;
        this.clientName=clientName;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
            Message txt1 = new Message();
            txt1.setInstruction("SuccessfullLogIn");
            txt1.setPlayer(null);
            networkUtil.write(txt1);
            List<Player> AllPlayerList = server.getAllPlayerList();
            for(Player player : AllPlayerList){
                if(clientName.equalsIgnoreCase(player.getClubName())) {
                    Message txt = new Message();
                    txt.setInstruction("ClubLogIn");
                    txt.setPlayer(player);
                    networkUtil.write(txt);
                }
            }
            List<Player> SalablePlayerList = server.getSalablePlayerList();
            for(Player player : SalablePlayerList){
                Message txt = new Message();
                txt.setInstruction("SaleListUpdated");
                txt.setInfo("Add");
                txt.setPlayer(player);
                networkUtil.write(txt);
            }
            while (true) {
                Object o = networkUtil.read();
                if (o instanceof Message) {
                    Message obj = (Message) o;
                    String instruction = obj.getInstruction();
                    String info=obj.getInfo();
                    Player player=obj.getPlayer();
                    if(instruction.equalsIgnoreCase("SaleRequest")){
                        server.addToSellList(player);
                        Collection<NetworkUtil> allClient= server.getClientMap().values();
                        for( NetworkUtil nu : allClient ){
                            Message txt= new Message();
                            txt.setInstruction("SaleListUpdated");
                            txt.setInfo("Add");
                            txt.setPlayer(player);
                            try{
                                nu.write(txt);
                            }catch(Exception e){
                                System.out.println(" Exception in ReadThreadServer writting to client server for Sale Request " +e);
                            }
                        }
                    }
                    else if(instruction.equalsIgnoreCase("BuyRequest")){
                        server.removeFromSellList(player);
                        Collection<NetworkUtil> allClient= server.getClientMap().values();
                        for( NetworkUtil nu : allClient ){
                            Message txt= new Message();
                            txt.setInstruction("SaleListUpdated");
                            txt.setInfo("Remove");
                            txt.setPlayer(player);
                            try{
                                nu.write(txt);
                            }catch(Exception e){
                                System.out.println(" Exception in ReadThreadServer writting to client server for Buy Request " +" e");
                            }
                        }
                    }
                }
                else{
//debug
                    System.out.println(" Not a instance of message ");
                }
            }
        } catch (Exception e) {
            System.out.println(" In Read Thread Server , Run " + e);
        } finally {
            try {
                networkUtil.closeConnection();
            } catch (IOException e) {
                System.out.println(" Exception in read Thread Server" + e );
            }
        }
    }
}