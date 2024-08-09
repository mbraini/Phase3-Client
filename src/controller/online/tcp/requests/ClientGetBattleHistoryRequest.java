package controller.online.tcp.requests;

import controller.online.OnlineData;
import controller.online.tcp.ClientRequest;
import controller.online.tcp.ClientRequestType;

public class ClientGetBattleHistoryRequest extends ClientRequest {

    public ClientGetBattleHistoryRequest() {
        type = ClientRequestType.battleHistory;
    }

    @Override
    public void sendRequest() {
        OnlineData.getTCPMessager().sendMessage(type);
    }
}
