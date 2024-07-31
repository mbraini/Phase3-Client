package controller.online.tcp.requests.getAllSquadsRequest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import controller.online.OnlineData;
import controller.online.tcp.ClientRequest;
import controller.online.tcp.ClientRequestType;
import view.painting.menuPanels.MainFrame;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ClientGetAllSquadsRequest extends ClientRequest {

    private Gson gson;

    public ClientGetAllSquadsRequest() {
        type = ClientRequestType.getAllSquads;
        initGson();
    }

    private void initGson() {
        gson = new Gson();
    }

    @Override
    public void sendRequest() {
        OnlineData.getTCPMessager().sendMessage(type);
    }
}
