package controller.online.tcp.requests.buyCall;

import com.google.gson.Gson;
import controller.online.OnlineData;
import controller.online.tcp.ServerRecponce;
import controller.online.tcp.ServerRecponceType;

import javax.swing.*;

public class ServerBuyCallRecponce extends ServerRecponce {

    private Gson gson;

    public ServerBuyCallRecponce() {
        initGson();
    }

    private void initGson() {
        gson = new Gson();
    }

    @Override
    public void receiveRecponce() {
        String callType = OnlineData.getTCPMessager().readMessage();
        String JRecponce = OnlineData.getTCPMessager().readMessage();
        ServerRecponceType recponce = gson.fromJson(JRecponce ,ServerRecponceType.class);
        if (recponce.equals(ServerRecponceType.done)) {
            JOptionPane.showMessageDialog(
                    null ,
                    "you have successfully bought " + callType + " for your squad"
            );
        }
        else {
            JOptionPane.showMessageDialog(
                    null ,
                    "you cant buy " + callType + " for your squad"
            );
        }
    }
}
