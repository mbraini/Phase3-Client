package controller.online.tcp.requests;

import controller.online.OnlineData;
import controller.online.tcp.ClientRequest;
import controller.online.tcp.ClientRequestType;

public class ClientSpawnAllyRequest extends ClientRequest {

    public ClientSpawnAllyRequest() {
        type = ClientRequestType.spawnAlly;
    }

    @Override
    public void sendRequest() {
        OnlineData.getTCPMessager().sendMessage(type);
    }
}
