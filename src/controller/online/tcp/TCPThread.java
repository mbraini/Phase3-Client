package controller.online.tcp;

import com.google.gson.Gson;
import controller.online.OnlineData;
import controller.online.tcp.messages.ServerOKMessage;
import controller.online.tcp.messages.ServerYesNoMessage;
import controller.online.tcp.requests.createSquad.ServerCreateSquadRecponce;
import controller.online.tcp.requests.getAllSquadsRequest.ServerGetAllSquadsRecponce;
import controller.online.tcp.requests.getSquadMembers.ServerGetSquadMembersRecponce;
import controller.online.tcp.requests.hasSquad.ServerHasSquadRecponce;
import controller.online.tcp.requests.joinSquad.ServerJoinSquadRecponce;
import controller.online.tcp.requests.leaveSquad.ServerLeaveSquadRecponce;

import javax.swing.*;

public class TCPThread extends Thread {

    private Gson gson;

    public TCPThread() {
        initGson();
    }

    private void initGson() {
        gson = new Gson();
    }


    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("TCP THREAD!");
                String json = OnlineData.getTCPMessager().readMessage();
                ServerMessageType type = gson.fromJson(json ,ServerMessageType.class);
                switch (type) {
                    case hasSquadRecponce :
                        new ServerHasSquadRecponce().receiveRecponce();
                        break;
                    case getAllSquadsRecponce:
                        new ServerGetAllSquadsRecponce().receiveRecponce();
                        break;
                    case createSquadRecponce:
                        new ServerCreateSquadRecponce().receiveRecponce();
                        break;
                    case joinSquadRecponce:
                        new ServerJoinSquadRecponce().receiveRecponce();
                        break;
                    case getSquadMembers:
                        new ServerGetSquadMembersRecponce().receiveRecponce();
                        break;
                    case leaveSquad:
                        new ServerLeaveSquadRecponce().receiveRecponce();
                        break;
                    case yes_no_message:
                        new ServerYesNoMessage().showMessage();
                        break;
                    case ok_message:
                        new ServerOKMessage().showMessage();
                        break;
                }
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "you have disconnected!");
        }

    }
}
