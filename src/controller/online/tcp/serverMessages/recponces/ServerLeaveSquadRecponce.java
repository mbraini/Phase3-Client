package controller.online.tcp.serverMessages.recponces;

import com.google.gson.Gson;
import controller.online.OnlineData;
import controller.online.tcp.ServerRecponce;
import controller.online.tcp.ServerRecponceType;

import javax.swing.*;

public class ServerLeaveSquadRecponce extends ServerRecponce {

    private Gson gson;

    public ServerLeaveSquadRecponce() {
        initGson();
    }

    private void initGson() {
        gson = new Gson();
    }

    @Override
    public void receiveRecponce() {
        String json = OnlineData.getTCPMessager().readMessage();
        ServerRecponceType serverRecponce = gson.fromJson(json , ServerRecponceType.class);
        if (serverRecponce.equals(ServerRecponceType.done)) {
            JOptionPane.showMessageDialog(null ,"you left the squad");
        }
    }
}
