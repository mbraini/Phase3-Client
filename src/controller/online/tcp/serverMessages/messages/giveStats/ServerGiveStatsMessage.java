package controller.online.tcp.serverMessages.messages.giveStats;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import controller.online.OnlineData;
import view.painting.gamePanels.EndGameFrame;
import view.painting.gamePanels.EndGamePanel;
import view.painting.gamePanels.OnlineEndGameFrame;
import view.painting.gamePanels.OnlineEndGamePanel;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ServerGiveStatsMessage {

    private Gson gson;

    public ServerGiveStatsMessage() {
        gson = new Gson();
    }

    public void receiveMessage() {

        String JMessage = OnlineData.getTCPMessager().readMessage();
        Type type = new TypeToken<ArrayList<StatsHelper>>(){}.getType();
        ArrayList<StatsHelper> helpers = gson.fromJson(JMessage ,type);

        OnlineEndGameFrame endGameFrame = new OnlineEndGameFrame();
        OnlineEndGamePanel endGamePanel = new OnlineEndGamePanel(endGameFrame);
        endGamePanel.start();
        endGamePanel.update(helpers);

    }

}
