package controller.online.tcp.serverMessages.recponces;

import com.google.gson.Gson;
import controller.configs.helper.GameConfigsJsonHelper;
import controller.online.OnlineData;
import controller.online.tcp.ServerRecponce;
import controller.online.tcp.ServerRecponceType;
import controller.online.udp.VariablesReceiver.JVariables;
import utils.Helper;

import javax.swing.*;

public class ServerBuyCallRecponce extends ServerRecponce {

    private Gson gson;

    public ServerBuyCallRecponce() {
        initGson();
    }

    private void initGson() {
        gson = new Gson();
    }

    @Override
    public void receiveRecponce() {
        String callType = OnlineData.getTCPMessager().readMessage();
        int xp = Integer.valueOf(OnlineData.getTCPMessager().readMessage());
        String JRecponce = OnlineData.getTCPMessager().readMessage();
        ServerRecponceType recponce = gson.fromJson(JRecponce ,ServerRecponceType.class);
        if (recponce.equals(ServerRecponceType.done)) {
            JOptionPane.showMessageDialog(
                    null ,
                    "you have successfully bought " + callType + " for your squad"
            );
        }
        else {
            JOptionPane.showMessageDialog(
                    null ,
                    "you cant buy " + callType + " for your squad"
            );
        }
        saveXP(xp);
    }

    private void saveXP(int xp) {
        StringBuilder stringBuilder = Helper.readFile("src/controller/configs/gameConfigs.json");
        GameConfigsJsonHelper configs = gson.fromJson(stringBuilder.toString() ,GameConfigsJsonHelper.class);
        configs.XP = configs.XP - xp;
        Helper.writeFile("src/controller/configs/gameConfigs.json" ,gson.toJson(configs));
    }

}
