package controller.online.tcp.requests.donateXP;

import controller.online.OnlineData;
import controller.online.tcp.ClientRequest;
import controller.online.tcp.ClientRequestType;

public class ClientDonateXPRequest extends ClientRequest {

    private String donatedAmount;

    public ClientDonateXPRequest(String donatedAmount) {
        this.donatedAmount = donatedAmount;
        type = ClientRequestType.donateXP;
    }

    @Override
    public void sendRequest() {
        OnlineData.getTCPMessager().sendMessage(type);
        OnlineData.getTCPMessager().sendMessage(donatedAmount);
    }
}
