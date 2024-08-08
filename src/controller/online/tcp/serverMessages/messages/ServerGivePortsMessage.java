package controller.online.tcp.serverMessages.messages;

import controller.online.OnlineData;
import controller.online.udp.Game;
import controller.online.udp.gameInfoSender.GameInfoSender;

public class ServerGivePortsMessage {

    public ServerGivePortsMessage() {

        int port = Integer.valueOf(OnlineData.getTCPMessager().readMessage());
        Game game = OnlineData.getCurrentOnlineGame();
        GameInfoSender gameInfoSender = new GameInfoSender(port);
        game.setGameInfoSender(gameInfoSender);
        gameInfoSender.start();
    }

}
