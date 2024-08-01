package controller.online.tcp.requests.killSquad;

import controller.online.OnlineData;
import controller.online.tcp.ClientRequest;
import controller.online.tcp.ClientRequestType;

public class ClientKillSquadRequest extends ClientRequest {

    private String squadName;

    public ClientKillSquadRequest(String squadName) {
        this.squadName = squadName;
    }

    @Override
    public void sendRequest() {
        OnlineData.getTCPMessager().sendMessage(ClientRequestType.killSquad);
        OnlineData.getTCPMessager().sendMessage(squadName);
    }
}
