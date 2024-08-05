package controller.online;

import constants.ControllerConstants;
import controller.online.tcp.TCPThread;
import controller.online.udp.Game;
import utils.TCPMessager;
import view.Application;
import view.painting.menuPanels.MainFrame;

import javax.swing.*;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class OnlineData {

    public static TCPMessager tcpMessager;
    public static TCPThread tcpThread;
    public static TCPMessager tcpConnectionChecker;
    public static TCPMessager tcpGameConnection;
    public static Game currentOnlineGame;
    public static int availablePort = 11000;

    public static void initTCPMessager() {
        try {
            Socket socket = new Socket(ControllerConstants.SERVER_IP ,ControllerConstants.SERVER_PORT);
            tcpMessager = new TCPMessager(socket);
            Thread.sleep(1000);
            int serverConnectionPort = Integer.valueOf(tcpMessager.readMessage());
            Socket connectionSocket = new Socket(ControllerConstants.SERVER_IP ,serverConnectionPort);
            tcpConnectionChecker = new TCPMessager(connectionSocket);
            Application.startMainFrame();
            MainFrame.menuPanel.end();
            MainFrame.onlineChoicePanel.start();
        }
        catch (IOException e) {
            failedToConnect();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
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

    public static TCPThread getTcpThread() {
        return tcpThread;
    }

    public static void setTcpThread(TCPThread tcpThread) {
        OnlineData.tcpThread = tcpThread;
    }

    public static TCPMessager getTcpConnectionChecker() {
        return tcpConnectionChecker;
    }

    public static void setTcpConnectionChecker(TCPMessager tcpConnectionChecker) {
        OnlineData.tcpConnectionChecker = tcpConnectionChecker;
    }

    public static int getAvailablePort() {
        availablePort++;
        return availablePort;
    }

    public static Game getCurrentOnlineGame() {
        return currentOnlineGame;
    }

    public static void setCurrentOnlineGame(Game currentOnlineGame) {
        OnlineData.currentOnlineGame = currentOnlineGame;
    }



}
