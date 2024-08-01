package controller.online.tcp.requests.getSquadInfo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import controller.online.OnlineData;
import controller.online.tcp.ServerRecponce;
import view.painting.menuPanels.MainFrame;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ServerGetSquadInfoRecponce extends ServerRecponce {

    private Gson gson;

    public ServerGetSquadInfoRecponce() {
        initGson();
    }

    private void initGson() {
        gson = new Gson();
    }

    @Override
    public void receiveRecponce() {
        String json = OnlineData.getTCPMessager().readMessage();
        Type type = new TypeToken<ArrayList<GetSquadMembersJsonHelper>>(){}.getType();
        ArrayList<GetSquadMembersJsonHelper> helpers = gson.fromJson(json , type);

        json = OnlineData.getTCPMessager().readMessage();
        GetSquadMembersJsonHelper thisPlayer = gson.fromJson(json , GetSquadMembersJsonHelper.class);

        String squadName = OnlineData.getTCPMessager().readMessage();

        String JIsOwner = OnlineData.getTCPMessager().readMessage();
        Boolean isOwner = gson.fromJson(JIsOwner , Boolean.class);

        MainFrame.hasSquadPanel.update(helpers ,thisPlayer ,squadName ,isOwner);
    }
}
