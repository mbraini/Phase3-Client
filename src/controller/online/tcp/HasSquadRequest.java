package controller.online.tcp;

import com.google.gson.Gson;
import controller.online.OnlineData;
import view.painting.menuPanels.MainFrame;

public class HasSquadRequest extends ClientRequest{

    private Gson gson;

    public HasSquadRequest() {
        type = ClientRequestType.hasSquad;
        initGson();
    }

    private void initGson() {
        gson = new Gson();
    }

    @Override
    public void sendRequest() {
        OnlineData.getTCPMessager().sendMessage(type);
        String answer = OnlineData.getTCPMessager().readMessage();
        ServerMessageType recponce = gson.fromJson(answer , ServerMessageType.class);
        if (recponce.equals(ServerMessageType.yes)) {
            MainFrame.menuPanel.end();
            MainFrame.hasSquadPanel.start();
        }
        else {
            MainFrame.menuPanel.end();
            MainFrame.noSquadPanel.start();
        }
    }
}
