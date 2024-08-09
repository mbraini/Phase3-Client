package controller.online.tcp.serverMessages.messages;

import controller.online.OnlineData;
import controller.online.udp.Game;

public class ServerEndGameMessage {

    public void end() {

        Game game = OnlineData.getCurrentOnlineGame();
        game.end();
        OnlineData.setCurrentOnlineGame(null);
    }

}
