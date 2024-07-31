package controller.online.tcp.messages;

import com.google.gson.Gson;
import controller.online.OnlineData;

import javax.swing.*;

public class ServerOKMessage extends ServerMessage{


    public ServerOKMessage() {

    }

    @Override
    public void showMessage() {
        String message = OnlineData.getTCPMessager().readMessage();
        JOptionPane.showMessageDialog(null,message);
    }
}
