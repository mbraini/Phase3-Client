package controller.online.tcp.serverMessages.recponces;

import com.google.gson.Gson;
import controller.online.OnlineData;
import controller.online.tcp.ServerRecponce;
import controller.online.tcp.ServerRecponceType;

import javax.swing.*;

public class ServerDonateXPRecponce extends ServerRecponce {

    private Gson gson;

    public ServerDonateXPRecponce() {
        initGson();
    }

    private void initGson() {
        gson = new Gson();
    }

    @Override
    public void receiveRecponce() {
        String JRecponce = OnlineData.getTCPMessager().readMessage();
        ServerRecponceType recponce = gson.fromJson(JRecponce ,ServerRecponceType.class);
        if (recponce.equals(ServerRecponceType.done)) {
            JOptionPane.showMessageDialog(null ,"you have successfully donated to your squad");
        }
        else {
            JOptionPane.showMessageDialog(null ,"you cant donate this amount of xp to your squad");
        }
    }
}
