package controller.online.tcp;

import controller.online.tcp.messages.ClientMessageRecponceType;
import utils.TCPMessager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

public class ConnectionChecker extends Thread{

    private final TCPMessager connectionMessager;
    private volatile boolean disconnect;

    public ConnectionChecker(TCPMessager tcpMessager) {
        this.connectionMessager = tcpMessager;
    }

    @Override
    public void run() {
        try {
            while (true) {
                connectionMessager.readMessage();
                if (disconnect) {
                    connectionMessager.sendMessage(ClientMessageRecponceType.disconnecting);
                    connectionMessager.close();
                    return;
                }
                else {
                    connectionMessager.sendMessage(ClientMessageRecponceType.connected);
                }
            }
        }
        catch (Exception e) {
            end();
        }
    }

    private void end() {
        JOptionPane.showMessageDialog(null ,"you are disconnected!");
        System.exit(0);
    }

    public void setDisconnecting(boolean disconnect) {
        this.disconnect = disconnect;
    }
}
