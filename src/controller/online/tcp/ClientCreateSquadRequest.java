package controller.online.tcp;

import com.google.gson.Gson;
import constants.CostConstants;
import controller.configs.Configs;
import controller.online.OnlineData;
import utils.Helper;
import view.painting.menuPanels.MainFrame;
import view.painting.menuPanels.PIG;

import javax.swing.*;

public class ClientCreateSquadRequest extends ClientRequest{

    private String squadName;
    private Gson gson;

    public ClientCreateSquadRequest(String squadName) {
        this.squadName = squadName;
        type = ClientRequestType.createSquad;
        initGson();
    }

    private void initGson() {
        gson = new Gson();
    }

    @Override
    public void sendRequest() {

        OnlineData.getTCPMessager().sendMessage(type);
        OnlineData.getTCPMessager().sendMessage(squadName);
        String json = OnlineData.getTCPMessager().readMessage();

        ServerMessageType recponce = gson.fromJson(json , ServerMessageType.class);
        if (recponce.equals(ServerMessageType.done)) {
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
