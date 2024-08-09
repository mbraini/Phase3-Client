package controller.online.tcp.requests;

import controller.online.OnlineData;
import controller.online.tcp.ClientRequest;
import controller.online.tcp.ClientRequestType;
import utils.Helper;

public class ClientSendMatchHistoryRequest extends ClientRequest {

    public ClientSendMatchHistoryRequest() {
        type = ClientRequestType.matchHistory;
    }

    @Override
    public void sendRequest() {
        OnlineData.getTCPMessager().sendMessage(type);
        StringBuilder gameHistories = Helper.readFile("src/controller/gameHistory/gameHistories.json");
        StringBuilder skillTreeHistory = Helper.readFile("src/controller/gameHistory/skillTreeBuys.json");
        OnlineData.getTCPMessager().sendMessage(gameHistories.toString());
        OnlineData.getTCPMessager().sendMessage(skillTreeHistory.toString());
        Helper.writeFile("src/controller/gameHistory/gameHistories.json" ,"[]");
        Helper.writeFile("src/controller/gameHistory/skillTreeBuys.json" ,"[]");
    }
}
