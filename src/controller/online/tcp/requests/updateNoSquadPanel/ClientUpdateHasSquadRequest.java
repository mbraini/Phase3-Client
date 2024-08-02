package controller.online.tcp.requests.updateNoSquadPanel;

import com.google.gson.Gson;
import controller.online.OnlineData;
import controller.online.tcp.ClientRequest;
import controller.online.tcp.ClientRequestType;

import java.util.ArrayList;

public class ClientUpdateHasSquadRequest extends ClientRequest {

    private ArrayList<GetSquadMembersJsonHelper> members;
    private GetSquadMembersJsonHelper thisPlayer;
    private Gson gson;

    public ClientUpdateHasSquadRequest(ArrayList<GetSquadMembersJsonHelper> members ,GetSquadMembersJsonHelper thisPlayer) {
        this.members = members;
        this.thisPlayer = thisPlayer;
        type = ClientRequestType.updateHasSquad;
        initGson();
    }

    private void initGson() {
        gson = new Gson();
    }

    @Override
    public void sendRequest() {
        OnlineData.getTCPMessager().sendMessage(type);
        String JMembers = gson.toJson(members);
        String JThisPlayer = gson.toJson(thisPlayer);
        OnlineData.getTCPMessager().sendMessage(JMembers);
        OnlineData.getTCPMessager().sendMessage(JThisPlayer);
    }
}
