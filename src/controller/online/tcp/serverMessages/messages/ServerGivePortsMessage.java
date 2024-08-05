package controller.online.tcp.serverMessages.messages;

import controller.online.OnlineData;
import controller.online.gameInfoSender.GameInfoSender;

public class ServerGivePortsMessage {

    public ServerGivePortsMessage() {

        int port = Integer.valueOf(OnlineData.getTCPMessager().readMessage());
        new GameInfoSender(port).start();
    }

}
