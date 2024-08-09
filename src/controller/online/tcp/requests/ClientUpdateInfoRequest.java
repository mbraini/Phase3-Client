package controller.online.tcp.requests;

import com.google.gson.Gson;
import controller.configs.Configs;
import controller.configs.helper.SkillTreeJsonHelper;
import controller.manager.GameState;
import controller.online.OnlineData;
import controller.online.tcp.ClientRequest;
import controller.online.tcp.ClientRequestType;

public class ClientUpdateInfoRequest extends ClientRequest {

    private Gson gson;

    public ClientUpdateInfoRequest() {
        type = ClientRequestType.updateInfo;
        gson = new Gson();
    }

    @Override
    public void sendRequest() {
        OnlineData.getTCPMessager().sendMessage(type);
        update();
    }

    private void update() {
        OnlineData.getTCPMessager().sendMessage(GameState.getXp());
        SkillTreeJsonHelper helper = new SkillTreeJsonHelper();
        helper.ares = Configs.SkillTreeConfigs.aresBought;
        helper.astrape = Configs.SkillTreeConfigs.astrapeBought;
        helper.cerberus = Configs.SkillTreeConfigs.cerberusBought;
        helper.melampus = Configs.SkillTreeConfigs.melampusBought;
        helper.chiron = Configs.SkillTreeConfigs.chironBought;
        helper.athena = Configs.SkillTreeConfigs.athenaBought;
        helper.proteus = Configs.SkillTreeConfigs.proteusBought;
        helper.empusa = Configs.SkillTreeConfigs.empusaBought;
        helper.dolus = Configs.SkillTreeConfigs.dolusBought;
        helper.aceso = Configs.SkillTreeConfigs.acesoBought;

        OnlineData.getTCPMessager().sendMessage(gson.toJson(helper));
    }

}
