package controller.online;

import constants.ControllerConstants;
import utils.TCPMessager;
import view.Application;
import view.painting.menuPanels.MainFrame;

import javax.swing.*;
import java.io.IOException;
import java.net.Socket;

public class OnlineData {

    public static TCPMessager tcpMessager;

    public static void initTCPMessager() {
        try {
            Socket socket = new Socket(ControllerConstants.SERVER_IP ,ControllerConstants.SERVER_PORT);
            tcpMessager = new TCPMessager(socket);
            Application.startMainFrame();
            MainFrame.menuPanel.end();
            MainFrame.onlineChoicePanel.start();
        }
        catch (IOException e) {
            failedToConnect();
        }
    }

    private static void failedToConnect() {
        int recponce = JOptionPane.showConfirmDialog(
                null ,
                "do you want to try again?" ,
                "failed to connect to the server" ,
                0
        );
        if (recponce == 0) {
            initTCPMessager();
        }
        else {
            Application.startMainFrame();
        }
    }

    public static TCPMessager getTCPMessager() {
        return tcpMessager;
    }

    public static void setTCPMessager(TCPMessager tcpMessager) {
        OnlineData.tcpMessager = tcpMessager;
    }
}
