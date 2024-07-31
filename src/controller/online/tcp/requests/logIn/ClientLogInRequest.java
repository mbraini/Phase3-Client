package controller.online.tcp.requests.logIn;

import com.google.gson.Gson;
import controller.configs.Configs;
import controller.configs.helper.SkillTreeJsonHelper;
import controller.online.OnlineData;
import controller.online.tcp.ClientRequest;
import controller.online.tcp.ClientRequestType;
import controller.online.tcp.ServerRecponceType;
import view.painting.menuPanels.MainFrame;

import javax.swing.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ClientLogInRequest extends ClientRequest {

    protected String username;
    private Gson gson;

    public ClientLogInRequest(String username) {

        this.username = username;
        type = ClientRequestType.logIn;
        initGson();

    }

    private void initGson() {
        gson = new Gson();
    }

    @Override
    public void sendRequest() {

        OnlineData.getTCPMessager().sendMessage(type);
        OnlineData.getTCPMessager().sendMessage(username);
        try {
            OnlineData.getTCPMessager().sendMessage(
                    InetAddress.getLocalHost().getHostAddress().trim()
            );
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        new ServerLogInRecponce().receiveRecponce();
    }

    private void update() {
        OnlineData.getTCPMessager().sendMessage(Configs.GameConfigs.XP);
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
