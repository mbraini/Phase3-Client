package controller.online.tcp.requests;

import com.google.gson.Gson;
import controller.online.OnlineData;
import controller.online.tcp.ClientRequest;
import controller.online.tcp.ClientRequestType;
import controller.online.tcp.ServerRecponceType;
import view.painting.menuPanels.MainFrame;

public class ClientHasSquadRequest extends ClientRequest {

    private Gson gson;

    public ClientHasSquadRequest() {
        type = ClientRequestType.hasSquad;
        initGson();
    }

    private void initGson() {
        gson = new Gson();
    }

    @Override
    public void sendRequest() {
        OnlineData.getTCPMessager().sendMessage(type);
    }
}
