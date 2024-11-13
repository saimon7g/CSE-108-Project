package Util;

import sample.Player;

import java.io.Serializable;

public class Message implements Serializable {
    private String instruction;
    private String info;
    private Player player;

    public Message() {
        info="";
        player=null;
    }

    public String getInstruction() {
        return instruction;
    }
    public void setInstruction(String from) {
        this.instruction = from;
    }

    public Player getPlayer() {
        return player;
    }
    public void setPlayer(Player to) {
        this.player = to;
    }

    public void setInfo(String client) {
        this.info = client;
    }
    public String getInfo() {
        return info;
    }
}