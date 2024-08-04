package controller.online.tcp.serverMessages.recponces;

import com.google.gson.Gson;
import controller.online.OnlineData;
import controller.online.tcp.ServerRecponce;
import view.painting.menuPanels.MainFrame;

public class ServerUpdateTreasuryShopRecponce extends ServerRecponce {

    private Gson gson;

    public ServerUpdateTreasuryShopRecponce() {
        initGson();
    }

    private void initGson() {
        gson = new Gson();
    }

    @Override
    public void receiveRecponce() {
        int xp = Integer.valueOf(OnlineData.getTCPMessager().readMessage());
        int palioxisXP = Integer.valueOf(OnlineData.getTCPMessager().readMessage());
        MainFrame.squadTreasuryShopPanel.update(xp ,palioxisXP);
    }
}
