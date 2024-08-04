package controller.online.tcp.serverMessages.recponces;

import com.google.gson.Gson;
import controller.online.OnlineData;
import controller.online.tcp.ServerMessageType;
import controller.online.tcp.ServerRecponce;
import controller.online.tcp.ServerRecponceType;

import javax.swing.*;

public class ServerKillSquadRecponce extends ServerRecponce {

    private Gson gson;

    public ServerKillSquadRecponce() {
        initGson();
    }

    private void initGson() {
        gson = new Gson();
    }

    @Override
    public void receiveRecponce() {
        String squadName = OnlineData.getTCPMessager().readMessage();
        String JRecponce = OnlineData.getTCPMessager().readMessage();
        ServerRecponceType recponce = gson.fromJson(JRecponce , ServerRecponceType.class);
        if (recponce.equals(ServerRecponceType.done)) {
            JOptionPane.showMessageDialog(
                    null ,
                    "the squad '" + squadName + "' has been successfully removed" ,
                    "",
                    0
            );
        }
    }
}
