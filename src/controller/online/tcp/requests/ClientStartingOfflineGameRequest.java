package controller.online.tcp.requests;

import controller.online.OnlineData;
import controller.online.tcp.ClientRequest;
import controller.online.tcp.ClientRequestType;

public class ClientStartingOfflineGameRequest extends ClientRequest {

    public ClientStartingOfflineGameRequest() {
        type = ClientRequestType.startOfflineGame;
    }

    @Override
    public void sendRequest() {
        OnlineData.getTCPMessager().sendMessage(type);
    }



}
