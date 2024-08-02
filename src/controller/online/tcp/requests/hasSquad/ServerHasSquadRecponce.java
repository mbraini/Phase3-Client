package controller.online.tcp.requests.hasSquad;

import com.google.gson.Gson;
import controller.online.OnlineData;
import controller.online.tcp.ServerRecponce;
import controller.online.tcp.ServerRecponceType;
import view.painting.menuPanels.MainFrame;

public class ServerHasSquadRecponce extends ServerRecponce {

    private Gson gson;

    public ServerHasSquadRecponce() {
        initGson();
    }

    private void initGson() {
        gson = new Gson();
    }

    @Override
    public void receiveRecponce() {
        String answer = OnlineData.getTCPMessager().readMessage();
        ServerRecponceType recponce = gson.fromJson(answer , ServerRecponceType.class);
        if (recponce.equals(ServerRecponceType.yes)) {
            MainFrame.menuPanel.end();
            MainFrame.hasSquadPanel.start();
        }
        else {
            MainFrame.menuPanel.end();
            MainFrame.noSquadPanel.start();
        }
    }
}
