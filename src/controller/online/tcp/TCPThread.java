package controller.online.tcp;

import com.google.gson.Gson;
import controller.online.OnlineData;
import controller.online.tcp.messages.ServerOKMessage;
import controller.online.tcp.messages.ServerYesNoMessage;
import controller.online.tcp.requests.createSquad.ServerCreateSquadRecponce;
import controller.online.tcp.requests.getAllSquadsRequest.ServerGetAllSquadsRecponce;
import controller.online.tcp.requests.hasSquad.ServerHasSquadRecponce;
import controller.online.tcp.requests.hasSquadBattle.ServerHasSquadBattleRecponce;
import controller.online.tcp.requests.joinSquad.ServerJoinSquadRecponce;
import controller.online.tcp.requests.kickOutRequest.ServerKickOutRecponce;
import controller.online.tcp.requests.killSquad.ServerKillSquadRecponce;
import controller.online.tcp.requests.leaveSquad.ServerLeaveSquadRecponce;
import controller.online.tcp.requests.updateBattleSquadPanel.ServerUpdateBattleSquadRecponce;
import controller.online.tcp.requests.updateNoSquadPanel.ServerUpdateHasSquadRecponce;

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
        new ConnectionChecker(OnlineData.getTcpConnectionChecker()).start();
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
                    case leaveSquad:
                        new ServerLeaveSquadRecponce().receiveRecponce();
                        break;
                    case kickOut:
                        new ServerKickOutRecponce().receiveRecponce();
                        break;
                    case killSquad:
                        new ServerKillSquadRecponce().receiveRecponce();
                        break;
                    case updateHasSquad:
                        new ServerUpdateHasSquadRecponce().receiveRecponce();
                        break;
                    case hasSquadBattle:
                        new ServerHasSquadBattleRecponce().receiveRecponce();
                        break;
                    case updateBattleSquad:
                        new ServerUpdateBattleSquadRecponce().receiveRecponce();
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
