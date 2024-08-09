package controller.online.tcp.requests;

import controller.online.OnlineData;
import controller.online.tcp.ClientRequest;
import controller.online.tcp.ClientRequestType;

public class ClientEndingOfflineGameRequest extends ClientRequest {

    public ClientEndingOfflineGameRequest() {
        type = ClientRequestType.endOfflineGame;
    }

    @Override
    public void sendRequest() {
        OnlineData.getTCPMessager().sendMessage(type);
    }

}
