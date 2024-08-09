package controller.online.tcp;

import com.google.gson.Gson;
import controller.ModelRequestController;
import controller.PauseController;
import controller.online.OnlineData;
import controller.online.tcp.messages.ServerOKMessage;
import controller.online.tcp.messages.ServerYesNoMessage;
import controller.online.tcp.serverMessages.messages.ServerEndGameMessage;
import controller.online.tcp.serverMessages.messages.ServerGetPortsMessage;
import controller.online.tcp.serverMessages.messages.ServerGivePortsMessage;
import controller.online.tcp.serverMessages.messages.ServerUpdateXPMessage;
import controller.online.tcp.serverMessages.messages.giveStats.ServerGiveStatsMessage;
import controller.online.tcp.serverMessages.recponces.*;
import controller.online.tcp.serverMessages.recponces.squadHistory.ServerGetBattleSquadHistoryRecponce;
import view.Application;
import view.painting.menuPanels.MainFrame;

import javax.swing.*;

public class TCPThread extends Thread {

    private Gson gson;
    private ConnectionChecker connectionChecker;

    public TCPThread() {
        initGson();
    }

    private void initGson() {
        gson = new Gson();
    }


    @Override
    public void run() {
        connectionChecker = new ConnectionChecker(OnlineData.getTcpConnectionChecker());
        connectionChecker.start();
        try {
            while (!isInterrupted()) {
                String json = OnlineData.getTCPMessager().readMessage();
                ServerMessageType type = gson.fromJson(json ,ServerMessageType.class);
                switch (type) {
                    case hasSquadRecponce :
                        new ServerHasSquadRecponce().receiveRecponce();
                        break;
                    case updateXP:
                        new ServerUpdateXPMessage();
                        break;
                    case matchHistory:
                        new ServerSendMatchHistoryRecponce().receiveRecponce();
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
                    case battleHistory:
                        new ServerGetBattleSquadHistoryRecponce().receiveRecponce();
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
                    case endGame:
                        new ServerEndGameMessage().end();
                        break;
                    case giveStats:
                        new ServerGiveStatsMessage().receiveMessage();
                        break;
                    case waitingRoom:
                        Application.endMainFrame();
                        Application.startMainFrame();
                        MainFrame.menuPanel.end();
                        MainFrame.waitingRoomPanel.start();
                        break;
                    case waitingTime:
                        MainFrame.waitingRoomPanel.update(OnlineData.getTCPMessager().readMessage());
                        break;
                    case endWaitingTime:
                        Application.endMainFrame();
                        break;
                    case randomizeKeys:
                        ModelRequestController.randomizeKeys();
                        break;
                    case reorderKeys:
                        ModelRequestController.reorderKeys();
                        break;
                    case spawnAlly:
                        new ServerSpawnAllyRecponce().receiveRecponce();
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

    public void end() {
        connectionChecker.setDisconnecting(true);
        this.interrupt();
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        OnlineData.setTCPMessager(null);
    }

}
