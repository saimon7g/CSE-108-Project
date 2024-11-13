package  sample;


import Util.NetworkUtil;
import fxmlController.HomePage;
import fxmlController.LogIn;

public class Client {
    private String clientName;
    private LogIn login;
    private NetworkUtil networkUtil;
    private Club club;
    private HomePage homePage;
    private int state;

    public int getState() { return state; }
    public void setState(int state) { this.state = state; }
    public HomePage getHomePage() { return homePage; }
    public void setHomePage(HomePage homePage) { this.homePage = homePage; }
    public Club getClub() {
        return club;
    }
    public String getClientName() {
        return clientName;
    }
    public NetworkUtil getNetworkUtil() {
        return networkUtil;
    }
    public LogIn getLogin() {
        return login;
    }

    public Client(String serverAddress, int serverPort, String clientName, LogIn logIn, String password) {
        try {
            state=1;
            this.login=logIn;
            this.clientName=clientName;
            club= new Club(this,clientName);
            networkUtil = new NetworkUtil(serverAddress, serverPort);
            networkUtil.write(clientName);
            networkUtil.write(password);
            new ReadThreadClient(networkUtil, this);
        } catch (Exception e) {
            System.out.println(" Exception in ClientSocket" + e);
        }
    }
}