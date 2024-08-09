package controller.online.tcp.serverMessages.recponces.squadHistory;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import controller.online.OnlineData;
import controller.online.tcp.ServerRecponce;
import view.painting.menuPanels.MainFrame;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ServerGetBattleSquadHistoryRecponce extends ServerRecponce {

    private Gson gson;

    public ServerGetBattleSquadHistoryRecponce() {
        initGson();
    }

    private void initGson() {
        gson = new Gson();
    }

    @Override
    public void receiveRecponce() {

        String JHistory = OnlineData.getTCPMessager().readMessage();
        Type type = new TypeToken<ArrayList<SquadBattleHistoryMember>>(){}.getType();
        ArrayList<SquadBattleHistoryMember> historyMembers = gson.fromJson(JHistory ,type);
        MainFrame.squadBattleHistory.update(historyMembers);
    }

}
