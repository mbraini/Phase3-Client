package controller.online.tcp.serverMessages.recponces;

import com.google.gson.Gson;
import controller.online.OnlineData;
import controller.online.tcp.ServerRecponce;
import controller.online.tcp.ServerRecponceType;

import javax.swing.*;

public class ServerSendMatchHistoryRecponce extends ServerRecponce {

    private Gson gson;

    public ServerSendMatchHistoryRecponce() {
        gson = new Gson();
    }

    @Override
    public void receiveRecponce() {
        String JRecponce = OnlineData.getTCPMessager().readMessage();
        ServerRecponceType serverRecponceType = gson.fromJson(JRecponce ,ServerRecponceType.class);
        if (serverRecponceType.equals(ServerRecponceType.error)) {
            JOptionPane.showMessageDialog(null ,"we have concluded that you are cheating!");
            System.exit(0);
        }
    }
}
