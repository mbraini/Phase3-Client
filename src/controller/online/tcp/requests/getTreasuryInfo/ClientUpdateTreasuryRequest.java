package controller.online.tcp.requests.getTreasuryInfo;

import controller.online.OnlineData;
import controller.online.tcp.ClientRequest;
import controller.online.tcp.ClientRequestType;

public class ClientUpdateTreasuryRequest extends ClientRequest {

    public ClientUpdateTreasuryRequest() {
        type = ClientRequestType.updateTreasury;
    }

    @Override
    public void sendRequest() {
        OnlineData.getTCPMessager().sendMessage(type);
    }



}
