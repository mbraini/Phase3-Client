package controller.online.tcp.requests.getSquadMembers;

import com.google.gson.Gson;
import controller.online.OnlineData;
import controller.online.tcp.ClientRequest;
import controller.online.tcp.ClientRequestType;

public class ClientGetSquadMembersRequest extends ClientRequest {


    public ClientGetSquadMembersRequest() {
        type = ClientRequestType.getSquadMembers;
    }

    @Override
    public void sendRequest() {
        OnlineData.getTCPMessager().sendMessage(type);
    }
}
