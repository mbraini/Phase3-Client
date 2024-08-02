package controller.online.tcp;

import controller.online.tcp.messages.ClientMessageRecponceType;
import utils.TCPMessager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

public class ConnectionChecker extends Thread{

    private final TCPMessager connectionMessager;
    private volatile boolean received;
//    private final Timer timer;

    public ConnectionChecker(TCPMessager tcpMessager) {
        this.connectionMessager = tcpMessager;
//        timer = new Timer(7000, new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (!received) {
//                    end();
//                }
//            }
//        });
    }

    @Override
    public void run() {
        try {
            while (true) {
                received = false;
//                timer.start();
                connectionMessager.readMessage();

                received = true;
//                timer.stop();

                connectionMessager.sendMessage(ClientMessageRecponceType.connected);
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
}
