package controller.online.tcp.serverMessages.recponces;

import com.google.gson.Gson;
import controller.online.OnlineData;
import controller.online.tcp.ServerRecponce;
import controller.online.tcp.ServerRecponceType;

import javax.swing.*;

public class ServerSpawnAllyRecponce extends ServerRecponce {

    private Gson gson;

    public ServerSpawnAllyRecponce() {
        gson = new Gson();
    }

    @Override
    public void receiveRecponce() {
        String JRecponce = OnlineData.getTCPMessager().readMessage();
        ServerRecponceType recponce = gson.fromJson(JRecponce ,ServerRecponceType.class);
        if (recponce.equals(ServerRecponceType.done)) {
            JOptionPane.showMessageDialog(null ,"we have sent your call for your allies!");
        }
        else {
            JOptionPane.showMessageDialog(null ,"you cant spawn an ally!");
        }
    }
}
