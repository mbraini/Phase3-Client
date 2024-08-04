package controller.online.tcp.serverMessages.recponces;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import controller.online.OnlineData;
import controller.online.tcp.ServerRecponce;
import controller.online.tcp.ServerRecponceType;
import controller.online.tcp.requests.updateBattleSquadPanel.GetBattleSquadMemberHelper;
import controller.online.tcp.requests.updateNoSquadPanel.GetSquadMembersJsonHelper;
import view.painting.menuPanels.MainFrame;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ServerUpdateBattleSquadRecponce extends ServerRecponce {

    private Gson gson;

    public ServerUpdateBattleSquadRecponce() {
        initGson();
    }

    private void initGson() {
        gson = new Gson();
    }

    @Override
    public void receiveRecponce() {

        String JRecponce = OnlineData.getTCPMessager().readMessage();
        ServerRecponceType recponce = gson.fromJson(JRecponce ,ServerRecponceType.class);
        if (recponce.equals(ServerRecponceType.yes)) {
            String enemySquadName = OnlineData.getTCPMessager().readMessage();
            String JMySquadMembers = OnlineData.getTCPMessager().readMessage();
            String JMe = OnlineData.getTCPMessager().readMessage();
            String JEnemySquadMembers = OnlineData.getTCPMessager().readMessage();
            Type type = new TypeToken<ArrayList<GetBattleSquadMemberHelper>>(){}.getType();
            ArrayList<GetBattleSquadMemberHelper> mySquadMembers = gson.fromJson(JMySquadMembers ,type);
            GetBattleSquadMemberHelper me = gson.fromJson(JMe ,GetBattleSquadMemberHelper.class);
            ArrayList<GetBattleSquadMemberHelper> enemySquadMembers = gson.fromJson(JEnemySquadMembers ,type);
            MainFrame.hasBattleSquadPanel.update(
                    enemySquadName,
                    mySquadMembers,
                    me,
                    enemySquadMembers
            );
        }
    }
}
