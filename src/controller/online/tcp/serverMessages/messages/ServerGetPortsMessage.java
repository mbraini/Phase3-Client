package controller.online.tcp.serverMessages.messages;

import controller.online.OnlineData;
import controller.online.tcp.ClientRequestType;
import controller.online.udp.Game;
import controller.online.udp.GameInfoReceiver;

public class ServerGetPortsMessage {

    public ServerGetPortsMessage() {
        Game game = new Game();
        OnlineData.setCurrentOnlineGame(game);

        GameInfoReceiver gameInfoReceiver = game.getGameInfoReceiver();
        gameInfoReceiver.setAbilityPort(OnlineData.getAvailablePort());
        gameInfoReceiver.setEffectPort(OnlineData.getAvailablePort());
        gameInfoReceiver.setFramePort(OnlineData.getAvailablePort());
        gameInfoReceiver.setObjectPort(OnlineData.getAvailablePort());
        gameInfoReceiver.setVariablesPort(OnlineData.getAvailablePort());
        gameInfoReceiver.start();

        OnlineData.getTCPMessager().sendMessage(ClientRequestType.givePorts);
        OnlineData.getTCPMessager().sendMessage(gameInfoReceiver.abilityPort);
        OnlineData.getTCPMessager().sendMessage(gameInfoReceiver.effectPort);
        OnlineData.getTCPMessager().sendMessage(gameInfoReceiver.framePort);
        OnlineData.getTCPMessager().sendMessage(gameInfoReceiver.objectPort);
        OnlineData.getTCPMessager().sendMessage(gameInfoReceiver.variablesPort);

    }

}
