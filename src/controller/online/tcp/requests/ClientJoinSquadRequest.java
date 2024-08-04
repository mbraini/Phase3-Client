package controller.online.tcp.requests;

import com.google.gson.Gson;
import controller.online.OnlineData;
import controller.online.tcp.ClientRequest;
import controller.online.tcp.ClientRequestType;
import controller.online.tcp.ServerRecponceType;

import javax.swing.*;

public class ClientJoinSquadRequest extends ClientRequest {

    private final String squadName;
    private Gson gson;

    public ClientJoinSquadRequest(String squadName){
        this.squadName = squadName;
        type = ClientRequestType.joinSquad;
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
