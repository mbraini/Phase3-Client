package controller.online.tcp.serverMessages.messages.giveStats;

import com.google.gson.Gson;
import controller.online.OnlineData;
import view.painting.gamePanels.EndGameFrame;
import view.painting.gamePanels.EndGamePanel;

public class ServerGiveStatsMessage {

    private Gson gson;

    public ServerGiveStatsMessage() {
        gson = new Gson();
    }

    public void receiveMessage() {

        String JMessage = OnlineData.getTCPMessager().readMessage();
        StatsHelper helper = gson.fromJson(JMessage ,StatsHelper.class);

        EndGameFrame endGameFrame = new EndGameFrame();
        EndGamePanel endGamePanel = new EndGamePanel(
                endGameFrame,
                helper.getXpGained(),
                helper.getSurvivalTime(),
                helper.getTotalBullets(),
                helper.getSuccessfulBullets(),
                helper.getMostXpGained(),
                helper.getMostSurvivalTime()
        );

        endGamePanel.start();
        endGamePanel.revalidate();
        endGamePanel.repaint();

    }

}
