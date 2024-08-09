package controller.online.tcp.serverMessages.recponces;

import com.google.gson.Gson;
import controller.configs.Configs;
import controller.configs.helper.SkillTreeJsonHelper;
import controller.online.OnlineData;
import controller.online.tcp.ClientRequestType;
import controller.online.tcp.ServerRecponce;
import controller.online.tcp.ServerRecponceType;
import controller.online.tcp.TCPThread;
import controller.online.tcp.requests.ClientSendMatchHistoryRequest;
import view.painting.menuPanels.MainFrame;

import javax.swing.*;

public class ServerLogInRecponce extends ServerRecponce {

    private Gson gson;

    public ServerLogInRecponce() {
        initGson();
    }

    private void initGson() {
        gson = new Gson();
    }

    @Override
    public void receiveRecponce() {
        String recponce = OnlineData.getTCPMessager().readMessage();
        ServerRecponceType serverRecponce = gson.fromJson(recponce , ServerRecponceType.class);
        if (serverRecponce.equals(ServerRecponceType.done)) {
            new ClientSendMatchHistoryRequest().sendRequest();
            OnlineData.setTcpThread(new TCPThread());
            OnlineData.getTcpThread().start();
            MainFrame.logInPanel.end();
            MainFrame.menuPanel.start();
        }
        else {
            JOptionPane.showMessageDialog(null ,"you cant log in with this username");
        }
    }


}
