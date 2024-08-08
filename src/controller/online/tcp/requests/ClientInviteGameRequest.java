package controller.online.tcp.requests;

import controller.online.OnlineData;
import controller.online.tcp.ClientRequest;
import controller.online.tcp.ClientRequestType;
import controller.online.tcp.GameType;

public class ClientInviteGameRequest extends ClientRequest {

    private final GameType gameType;
    private final String invitedPlayer;

    public ClientInviteGameRequest(GameType gameType ,String invitedPlayer) {
        type = ClientRequestType.inviteGame;
        this.gameType = gameType;
        this.invitedPlayer = invitedPlayer;
    }

    @Override
    public void sendRequest() {
        OnlineData.getTCPMessager().sendMessage(type);
        OnlineData.getTCPMessager().sendMessage(gameType);
        OnlineData.getTCPMessager().sendMessage(invitedPlayer);
    }
}
