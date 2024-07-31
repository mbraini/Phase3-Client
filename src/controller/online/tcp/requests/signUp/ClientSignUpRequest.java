package controller.online.tcp.requests.signUp;

import com.google.gson.Gson;
import controller.online.OnlineData;
import controller.online.tcp.ClientRequest;
import controller.online.tcp.ClientRequestType;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ClientSignUpRequest extends ClientRequest {

    private String username;
    private Gson gson;

    public ClientSignUpRequest(String username) {
        this.username = username;
        type = ClientRequestType.signUp;
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
        new ServerSignUpRecponce().receiveRecponce();
    }
}
