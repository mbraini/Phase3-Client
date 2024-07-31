package controller.online.tcp.requests.createSquad;

import com.google.gson.Gson;
import constants.CostConstants;
import controller.configs.Configs;
import controller.online.OnlineData;
import controller.online.tcp.ServerRecponce;
import controller.online.tcp.ServerRecponceType;
import utils.Helper;
import view.painting.menuPanels.MainFrame;

import javax.swing.*;

public class ServerCreateSquadRecponce extends ServerRecponce {

    private Gson gson;

    public ServerCreateSquadRecponce() {
        initGson();
    }

    private void initGson() {
        gson = new Gson();
    }


    @Override
    public void receiveRecponce() {
        String json = OnlineData.getTCPMessager().readMessage();

        ServerRecponceType recponce = gson.fromJson(json , ServerRecponceType.class);
        if (recponce.equals(ServerRecponceType.done)) {
            Configs.GameConfigs.XP -= CostConstants.SQUAD_XP_COST;
            Helper.saveXP(Configs.GameConfigs.XP);
            MainFrame.createNewSquadPanel.end();
            MainFrame.hasSquadPanel.start();
        }
        else {
            JOptionPane.showMessageDialog(null ,"you cant create a squad with this name");
        }
    }
}
