package controller.online.tcp.serverMessages.recponces;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import controller.online.OnlineData;
import controller.online.tcp.ServerRecponce;
import controller.online.tcp.requests.getAllSquadsRequest.GetAllSquadHelper;
import view.painting.menuPanels.MainFrame;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ServerGetAllSquadsRecponce extends ServerRecponce {

    private Gson gson;

    public ServerGetAllSquadsRecponce() {
        initGson();
    }

    private void initGson() {
        gson = new Gson();
    }

    @Override
    public void receiveRecponce() {
        String json = OnlineData.getTCPMessager().readMessage();
        Type type = new TypeToken<ArrayList<GetAllSquadHelper>>(){}.getType();
        ArrayList<GetAllSquadHelper> squads = gson.fromJson(json ,type);

        MainFrame.noSquadPanel.updateSquads(squads);
    }
}
