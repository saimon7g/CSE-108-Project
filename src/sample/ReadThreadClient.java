package  sample;

import Util.Message;
import Util.NetworkUtil;
import javafx.application.Platform;

import java.io.IOException;

public class ReadThreadClient implements Runnable {
    private Client client;
    private Thread thr;
    private NetworkUtil networkUtil;

    public ReadThreadClient(NetworkUtil networkUtil, Client client) {
        this.networkUtil = networkUtil;
        this.client=client;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
            while (true) {
                Object o = networkUtil.read();
                if (o instanceof Message) {
                    Message obj = (Message) o;
                    String instruction=obj.getInstruction();
                    String info=obj.getInfo();
                    Player player=obj.getPlayer();
                     if(instruction.equalsIgnoreCase("SuccessfullLogIn")){
                         //System.out.println(" Successful log in from read thread");
                         Platform.runLater(new Runnable() {
                             @Override
                             public void run() {
                                         try{
                                             client.setState(2);
                                             client.getLogin().showHomePage();
                                         }catch (Exception e){
                                             System.out.println("In ReadThread Client in runlater"+e);
                                         }
                             }
                         });
                    }
                    else if(instruction.equalsIgnoreCase("UnSuccessfullLogIn")){


                         System.out.println(" UnSuccessful log in from read thread");
                         Platform.runLater(new Runnable() {
                             @Override
                             public void run() {
                                 try{
                                     client.getLogin().showAlert();
                                 }catch (Exception e){
                                     System.out.println("In ReadThread Client in runlater"+e);
                                 }
                             }
                         });



                         //generate alert

                    }
                    else if(instruction.equalsIgnoreCase("ClublogIn")){
                        // System.out.println(" ,In Read Thread client " + player.getName());
                         client.getClub().addPlayer(player);
                    }
                    else if(instruction.equalsIgnoreCase("SaleListUpdated")){
                        if(info.equalsIgnoreCase("Add")){
                            client.getClub().addBuyablePlayers(player);
                        }
                        else if(info.equalsIgnoreCase("Remove")){
                            //System.out.println(" remove request received " + player.getName());
                            client.getClub().removeFromBuyablePlayer(player);
                        }

                        if(client.getState()==3){
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    try{
                                        client.getHomePage().buyPlayercalledbyThread();
                                    }catch (Exception e){
                                        System.out.println("In ReadThread Client in runlater state 3"+e);
                                    }
                                }
                            });
                        }
                        else if(client.getState()==4){
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    try{
                                        client.getHomePage().sellPlayercalledbyyThread();
                                    }catch (Exception e){
                                        System.out.println("In ReadThread Client in runlater"+e);
                                    }
                                }
                            });


                         }


                        //liveupdate


                     }
                }
                else {
//debug
                    System.out.println("Not instance of message");
                }

            }
        } catch (Exception e) {
            System.out.println(" In ReadThreadClient in run "+e);
        } finally {
            try {
                networkUtil.closeConnection();
            } catch (IOException e) {
                System.out.println(" Exception in read Thread Client");
                e.printStackTrace();
            }
        }
    }
}
