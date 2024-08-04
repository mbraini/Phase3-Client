package controller.online.tcp.serverMessages.recponces;

import com.google.gson.Gson;
import controller.configs.Configs;
import controller.configs.helper.SkillTreeJsonHelper;
import controller.online.OnlineData;
import controller.online.tcp.ServerRecponce;
import controller.online.tcp.ServerRecponceType;
import controller.online.tcp.TCPThread;
import view.painting.menuPanels.MainFrame;

import javax.swing.*;

public class ServerLogInRecponce extends ServerRecponce {

    private Gson gson;

    public ServerLogInRecponce() {
        initGson();
    }

    private void initGson() {
        gson = new Gson();
    }

    @Override
    public void receiveRecponce() {
        String recponce = OnlineData.getTCPMessager().readMessage();
        ServerRecponceType serverRecponce = gson.fromJson(recponce , ServerRecponceType.class);
        if (serverRecponce.equals(ServerRecponceType.done)) {
            update();
            OnlineData.setTcpThread(new TCPThread());
            OnlineData.getTcpThread().start();
            MainFrame.logInPanel.end();
            MainFrame.menuPanel.start();
        }
        else {
            JOptionPane.showMessageDialog(null ,"you cant log in with this username");
        }
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
