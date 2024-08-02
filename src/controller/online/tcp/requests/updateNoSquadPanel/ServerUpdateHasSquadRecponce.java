package controller.online.tcp.requests.updateNoSquadPanel;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import controller.online.OnlineData;
import controller.online.tcp.ServerRecponce;
import controller.online.tcp.ServerRecponceType;
import view.painting.menuPanels.MainFrame;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ServerUpdateHasSquadRecponce extends ServerRecponce {

    private Gson gson;

    public ServerUpdateHasSquadRecponce() {
        initGson();
    }

    private void initGson() {
        gson = new Gson();
    }

    @Override
    public void receiveRecponce() {
        String JRecponce = OnlineData.getTCPMessager().readMessage();
        ServerRecponceType recponce = gson.fromJson(JRecponce ,ServerRecponceType.class);
        if (recponce.equals(ServerRecponceType.yes)) {
            String JMembers = OnlineData.getTCPMessager().readMessage();
            String JThisPlayer = OnlineData.getTCPMessager().readMessage();
            String squadName = OnlineData.getTCPMessager().readMessage();
            String JIsOwner = OnlineData.getTCPMessager().readMessage();
            Type type = new TypeToken<ArrayList<GetSquadMembersJsonHelper>>(){}.getType();
            ArrayList<GetSquadMembersJsonHelper> members = gson.fromJson(JMembers ,type);
            GetSquadMembersJsonHelper thisPlayer = gson.fromJson(JThisPlayer ,GetSquadMembersJsonHelper.class);
            boolean isOwner = gson.fromJson(JIsOwner ,Boolean.class);
            MainFrame.hasSquadPanel.update(members ,thisPlayer ,squadName ,isOwner);
        }
    }
}
