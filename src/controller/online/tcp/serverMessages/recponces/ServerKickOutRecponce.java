package controller.online.tcp.serverMessages.recponces;

import com.google.gson.Gson;
import controller.online.OnlineData;
import controller.online.tcp.ServerRecponce;
import controller.online.tcp.ServerRecponceType;

import javax.swing.*;

public class ServerKickOutRecponce extends ServerRecponce {

    private Gson gson;

    public ServerKickOutRecponce() {
        initGson();
    }

    private void initGson() {
        gson = new Gson();
    }

    @Override
    public void receiveRecponce() {
        String kickedOutUsername = OnlineData.getTCPMessager().readMessage();
        String JRecponce = OnlineData.getTCPMessager().readMessage();
        ServerRecponceType recponce = gson.fromJson(JRecponce , ServerRecponceType.class);
        if (recponce.equals(ServerRecponceType.done))
            JOptionPane.showMessageDialog(
                    null ,
                    "the player '" + kickedOutUsername + "' has been successfully kicked out from the squad"
            );
    }
}
