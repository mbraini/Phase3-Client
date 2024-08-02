package controller.online.tcp.requests.getTreasuryInfo;

import com.google.gson.Gson;
import controller.online.OnlineData;
import controller.online.tcp.ServerRecponce;
import view.painting.menuPanels.MainFrame;

public class ServerUpdateTreasuryRecponce extends ServerRecponce {

    private Gson gson;

    public ServerUpdateTreasuryRecponce() {
        initGson();
    }

    private void initGson() {
        gson = new Gson();
    }

    @Override
    public void receiveRecponce() {
        String JIsOwner = OnlineData.getTCPMessager().readMessage();
        boolean isOwner = gson.fromJson(JIsOwner ,Boolean.class);
        int xp = Integer.valueOf(OnlineData.getTCPMessager().readMessage());
        int palioxisCount = Integer.valueOf(OnlineData.getTCPMessager().readMessage());
        int adoinisCount = Integer.valueOf(OnlineData.getTCPMessager().readMessage());
        int gefjonCount = Integer.valueOf(OnlineData.getTCPMessager().readMessage());
        MainFrame.squadTreasuryPanel.update(isOwner ,xp ,palioxisCount ,adoinisCount ,gefjonCount);
    }
}
