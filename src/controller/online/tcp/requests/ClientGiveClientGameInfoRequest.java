package controller.online.tcp.requests;

import controller.listeners.epsilonMovement.EpsilonMovement;
import controller.online.OnlineData;
import controller.online.tcp.ClientRequest;
import controller.online.tcp.ClientRequestType;

public class ClientGiveClientGameInfoRequest extends ClientRequest {

    public ClientGiveClientGameInfoRequest() {
        type = ClientRequestType.giveClientGameInfo;
    }

    @Override
    public void sendRequest() {
        OnlineData.getTCPMessager().sendMessage(type);
        OnlineData.getTCPMessager().sendMessage(EpsilonMovement.pressedKeys);
        OnlineData.getTCPMessager().sendMessage(EpsilonMovement.releasedKeys);
    }
}
