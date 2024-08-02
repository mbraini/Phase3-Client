package controller.online.tcp.requests.updateTreasuryShop;

import controller.online.OnlineData;
import controller.online.tcp.ClientRequest;
import controller.online.tcp.ClientRequestType;

public class ClientUpdateTreasuryShopRequest extends ClientRequest {

    public ClientUpdateTreasuryShopRequest() {
        type = ClientRequestType.updateTreasuryShop;
    }

    @Override
    public void sendRequest() {
        OnlineData.getTCPMessager().sendMessage(type);
    }


}
