package controller.online.tcp.requests.getSquadInfo;

import controller.online.OnlineData;
import controller.online.tcp.ClientRequest;
import controller.online.tcp.ClientRequestType;

public class ClientGetSquadInfoRequest extends ClientRequest {


    public ClientGetSquadInfoRequest() {
        type = ClientRequestType.getSquadInfo;
    }

    @Override
    public void sendRequest() {
        OnlineData.getTCPMessager().sendMessage(type);
    }
}
