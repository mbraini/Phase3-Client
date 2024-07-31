package controller.online.tcp.requests.createSquad;

import com.google.gson.Gson;
import constants.CostConstants;
import controller.configs.Configs;
import controller.online.OnlineData;
import controller.online.tcp.ClientRequest;
import controller.online.tcp.ClientRequestType;
import controller.online.tcp.ServerRecponceType;
import utils.Helper;
import view.painting.menuPanels.MainFrame;

import javax.swing.*;

public class ClientCreateSquadRequest extends ClientRequest {

    private String squadName;
    private Gson gson;

    public ClientCreateSquadRequest(String squadName) {
        this.squadName = squadName;
        type = ClientRequestType.createSquad;
        initGson();
    }

    private void initGson() {
        gson = new Gson();
    }

    @Override
    public void sendRequest() {

        OnlineData.getTCPMessager().sendMessage(type);
        OnlineData.getTCPMessager().sendMessage(squadName);
    }
}
