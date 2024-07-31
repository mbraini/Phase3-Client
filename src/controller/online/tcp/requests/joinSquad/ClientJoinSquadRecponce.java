package controller.online.tcp.requests.joinSquad;

import com.google.gson.Gson;
import controller.online.OnlineData;
import controller.online.tcp.ServerRecponce;
import controller.online.tcp.ServerRecponceType;

import javax.swing.*;

public class ClientJoinSquadRecponce extends ServerRecponce {

    private Gson gson;

    public ClientJoinSquadRecponce() {
        initGson();
    }

    private void initGson() {
        gson = new Gson();
    }

    @Override
    public void receiveRecponce() {
        String json = OnlineData.getTCPMessager().readMessage();
        ServerRecponceType recponce = gson.fromJson(json , ServerRecponceType.class);
        if (recponce.equals(ServerRecponceType.error))
            JOptionPane.showMessageDialog(null ,"you have already sent a join request!");
        else
            JOptionPane.showMessageDialog(null ,"you'r join request will be sent to the owner");
    }
}
