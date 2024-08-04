package controller.online.tcp.serverMessages.recponces;

import com.google.gson.Gson;
import controller.online.OnlineData;
import controller.online.tcp.ServerRecponce;
import controller.online.tcp.ServerRecponceType;
import view.painting.menuPanels.MainFrame;
import view.painting.menuPanels.PIG;

public class ServerHasSquadBattleRecponce extends ServerRecponce {

    private Gson gson;

    public ServerHasSquadBattleRecponce() {
        initGson();
    }

    private void initGson() {
        gson = new Gson();
    }


    @Override
    public void receiveRecponce() {
        String JRecponce = OnlineData.getTCPMessager().readMessage();
        ServerRecponceType recponce = gson.fromJson(JRecponce ,ServerRecponceType.class);
        if (recponce.equals(ServerRecponceType.yes)) {
            MainFrame.hasSquadPanel.end();
            MainFrame.hasBattleSquadPanel.start();
        }
        else {
            MainFrame.hasSquadPanel.end();
            MainFrame.noBattleSquadPanel.start();
        }
    }
}
