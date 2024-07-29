package controller.online.tcp;

import com.google.gson.Gson;
import controller.online.OnlineData;
import view.painting.menuPanels.MainFrame;

import javax.swing.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ClientLogInRequest extends ClientRequest{

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
        String recponce = OnlineData.getTCPMessager().readMessage();
        ServerMessageType serverRecponce = gson.fromJson(recponce , ServerMessageType.class);
        if (serverRecponce.equals(ServerMessageType.done)) {
            MainFrame.logInPanel.end();
            MainFrame.menuPanel.start();
        }
        else {
            JOptionPane.showMessageDialog(null ,"you cant log in with this username");
        }

    }
}
