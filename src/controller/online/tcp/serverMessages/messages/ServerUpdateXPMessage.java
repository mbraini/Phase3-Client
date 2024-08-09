package controller.online.tcp.serverMessages.messages;

import com.google.gson.Gson;
import controller.configs.helper.GameConfigsJsonHelper;
import controller.manager.GameState;
import controller.online.OnlineData;
import utils.Helper;

public class ServerUpdateXPMessage {

    private Gson gson;

    public ServerUpdateXPMessage() {
        gson = new Gson();
        updateXP();
    }

    private void updateXP() {
        int xp = Integer.valueOf(OnlineData.getTCPMessager().readMessage());
        StringBuilder stringBuilder = Helper.readFile("src/controller/configs/gameConfigs.json");
        GameConfigsJsonHelper configs = gson.fromJson(stringBuilder.toString() ,GameConfigsJsonHelper.class);
        GameState.setXp(xp);
        configs.XP = xp;
        Helper.writeFile("src/controller/configs/gameConfigs.json" ,gson.toJson(configs));
    }
}
