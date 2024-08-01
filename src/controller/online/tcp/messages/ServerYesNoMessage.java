package controller.online.tcp.messages;

import com.google.gson.Gson;
import constants.ControllerConstants;
import controller.online.OnlineData;
import utils.TCPMessager;

import javax.swing.*;
import java.io.IOException;
import java.net.Socket;

public class ServerYesNoMessage extends ServerMessage{

    private Gson gson;
    private TCPMessager messager;
    private int serverPort;

    public ServerYesNoMessage() {
        initGson();
        initPort();
        initMessager();
    }

    private void initMessager() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            Socket socket = new Socket(ControllerConstants.SERVER_IP ,serverPort);
            messager = new TCPMessager(socket);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void initPort() {
        serverPort = Integer.valueOf(OnlineData.getTCPMessager().readMessage());
    }

    private void initGson() {
        gson = new Gson();
    }

    @Override
    public void showMessage() {
        String message = messager.readMessage();
        int choice = JOptionPane.showConfirmDialog(null,message , "" ,0);
        if (choice == 0) {
            messager.sendMessage(ClientMessageRecponceType.yes);
        }
        else {
            messager.sendMessage(ClientMessageRecponceType.no);
        }
    }
}
