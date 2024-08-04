package controller.online.tcp.requests;

import controller.online.OnlineData;
import controller.online.tcp.ClientRequest;
import controller.online.tcp.ClientRequestType;

public class ClientLeaveSquadRequest extends ClientRequest {

    public ClientLeaveSquadRequest() {
        type = ClientRequestType.leaveSquad;
    }

    @Override
    public void sendRequest() {
        OnlineData.getTCPMessager().sendMessage(type);
    }
}
