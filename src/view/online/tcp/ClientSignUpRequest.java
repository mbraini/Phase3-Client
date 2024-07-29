package view.online.tcp;

import com.google.gson.Gson;
import view.Application;
import view.online.OnlineData;
import view.painting.menuPanels.MainFrame;

import javax.swing.*;

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
        System.out.println(OnlineData.getTCPMessager().getSocket().getInetAddress().getHostAddress().trim());
        OnlineData.getTCPMessager().sendMessage(
                OnlineData.getTCPMessager().getSocket().getInetAddress().getHostAddress().trim()
        );
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
