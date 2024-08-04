package controller.online.tcp.requests;

import controller.online.CallType;
import controller.online.OnlineData;
import controller.online.tcp.ClientRequest;
import controller.online.tcp.ClientRequestType;

public class ClientBuyCallRequest extends ClientRequest {

    private CallType callType;

    public ClientBuyCallRequest(CallType callType) {
        this.callType = callType;
        type = ClientRequestType.buyCall;
    }

    @Override
    public void sendRequest() {
        OnlineData.getTCPMessager().sendMessage(type);
        OnlineData.getTCPMessager().sendMessage(callType);
    }
}
