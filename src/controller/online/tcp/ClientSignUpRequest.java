package controller.online.tcp;

import com.google.gson.Gson;
import controller.online.OnlineData;
import view.painting.menuPanels.MainFrame;

import javax.swing.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ClientSignUpRequest extends ClientRequest{

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
        String recponce = OnlineData.getTCPMessager().readMessage();
        ServerMessageType serverRecponce = gson.fromJson(recponce , ServerMessageType.class);
        if (serverRecponce.equals(ServerMessageType.done)) {
            MainFrame.signUpPanel.end();
            MainFrame.menuPanel.start();
        }
        else {
            JOptionPane.showMessageDialog(null ,"you cant sign up with this username");
        }



    }
}
