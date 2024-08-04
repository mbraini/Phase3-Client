package controller.online.tcp.requests;

import com.google.gson.Gson;
import controller.online.OnlineData;
import controller.online.tcp.ClientRequest;
import controller.online.tcp.ClientRequestType;

public class ClientHasSquadBattleRequest extends ClientRequest {

    public ClientHasSquadBattleRequest() {
        type = ClientRequestType.hasSquadBattle;
    }

    @Override
    public void sendRequest() {
        OnlineData.getTCPMessager().sendMessage(type);
    }
}
