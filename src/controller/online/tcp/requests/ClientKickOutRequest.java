package controller.online.tcp.requests;

import controller.online.OnlineData;
import controller.online.tcp.ClientRequest;
import controller.online.tcp.ClientRequestType;

public class ClientKickOutRequest extends ClientRequest {

    private String username;
    public ClientKickOutRequest(String username) {
        this.username = username;
        type = ClientRequestType.kickOut;
    }

    @Override
    public void sendRequest() {
        OnlineData.getTCPMessager().sendMessage(type);
        OnlineData.getTCPMessager().sendMessage(username);
    }
}
