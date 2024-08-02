package controller.online.tcp.requests.updateBattleSquadPanel;

import com.google.gson.Gson;
import controller.online.OnlineData;
import controller.online.tcp.ClientRequest;
import controller.online.tcp.ClientRequestType;
import controller.online.tcp.requests.updateNoSquadPanel.GetSquadMembersJsonHelper;

import java.util.ArrayList;

public class ClientUpdateBattleSquadRequest extends ClientRequest {

    private ArrayList<GetBattleSquadMemberHelper> mySquadMembers;
    private ArrayList<GetBattleSquadMemberHelper> enemySquadMembers;
    private GetBattleSquadMemberHelper thisPlayer;
    private String enemySquadName;
    private Gson gson;

    public ClientUpdateBattleSquadRequest(String enemySquadName , ArrayList<GetBattleSquadMemberHelper> mySquadMembers
    ,GetBattleSquadMemberHelper thisPlayer ,ArrayList<GetBattleSquadMemberHelper> enemySquadMembers)
    {
        type = ClientRequestType.updateBattleSquad;
        this.enemySquadName = enemySquadName;
        this.enemySquadMembers = enemySquadMembers;
        this.thisPlayer = thisPlayer;
        this.mySquadMembers = mySquadMembers;
        initGson();
    }

    private void initGson() {
        gson = new Gson();
    }


    @Override
    public void sendRequest() {
        OnlineData.getTCPMessager().sendMessage(type);
        OnlineData.getTCPMessager().sendMessage(gson.toJson(enemySquadName));
        OnlineData.getTCPMessager().sendMessage(gson.toJson(mySquadMembers));
        OnlineData.getTCPMessager().sendMessage(gson.toJson(thisPlayer));
        OnlineData.getTCPMessager().sendMessage(gson.toJson(enemySquadMembers));
    }
}
