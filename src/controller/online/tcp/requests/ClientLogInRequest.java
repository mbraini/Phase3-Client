package controller.online.tcp.requests;

import com.google.gson.Gson;
import controller.configs.Configs;
import controller.configs.helper.SkillTreeJsonHelper;
import controller.online.OnlineData;
import controller.online.tcp.ClientRequest;
import controller.online.tcp.ClientRequestType;
import controller.online.tcp.serverMessages.recponces.ServerLogInRecponce;

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
}
