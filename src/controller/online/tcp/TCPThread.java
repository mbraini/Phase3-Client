package controller.online.tcp;

import com.google.gson.Gson;
import controller.PauseController;
import controller.online.OnlineData;
import controller.online.tcp.messages.ServerOKMessage;
import controller.online.tcp.messages.ServerYesNoMessage;
import controller.online.tcp.serverMessages.messages.ServerGetPortsMessage;
import controller.online.tcp.serverMessages.messages.ServerGivePortsMessage;
import controller.online.tcp.serverMessages.recponces.ServerBuyCallRecponce;
import controller.online.tcp.serverMessages.recponces.ServerCreateSquadRecponce;
import controller.online.tcp.serverMessages.recponces.ServerDonateXPRecponce;
import controller.online.tcp.serverMessages.recponces.ServerGetAllSquadsRecponce;
import controller.online.tcp.serverMessages.recponces.ServerUpdateTreasuryRecponce;
import controller.online.tcp.serverMessages.recponces.ServerHasSquadRecponce;
import controller.online.tcp.serverMessages.recponces.ServerHasSquadBattleRecponce;
import controller.online.tcp.serverMessages.recponces.ServerJoinSquadRecponce;
import controller.online.tcp.serverMessages.recponces.ServerKickOutRecponce;
import controller.online.tcp.serverMessages.recponces.ServerKillSquadRecponce;
import controller.online.tcp.serverMessages.recponces.ServerLeaveSquadRecponce;
import controller.online.tcp.serverMessages.recponces.ServerUpdateBattleSquadRecponce;
import controller.online.tcp.serverMessages.recponces.ServerUpdateHasSquadRecponce;
import controller.online.tcp.serverMessages.recponces.ServerUpdateTreasuryShopRecponce;

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
                    case updateTreasury:
                        new ServerUpdateTreasuryRecponce().receiveRecponce();
                        break;
                    case donateXP:
                        new ServerDonateXPRecponce().receiveRecponce();
                        break;
                    case updateTreasuryShop:
                        new ServerUpdateTreasuryShopRecponce().receiveRecponce();
                        break;
                    case buyCall:
                        new ServerBuyCallRecponce().receiveRecponce();
                        break;
                    case getPorts:
                        new ServerGetPortsMessage();
                        break;
                    case givePorts:
                        new ServerGivePortsMessage();
                        break;
                    case gamePause:
                        PauseController.createPausePanel();
                        break;
                    case gameUnpause:
                        PauseController.endPausePanel();
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
