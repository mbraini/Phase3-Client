package controller.online.udp.gameInfoSender;

import com.google.gson.Gson;
import constants.ControllerConstants;
import constants.RefreshRateConstants;
import controller.listeners.EpsilonAiming;
import controller.listeners.EpsilonCirculation;
import controller.listeners.PanelKeyListener;
import controller.listeners.epsilonMovement.EpsilonMovement;
import controller.online.OnlineData;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.ArrayList;

public class GameInfoSender extends Thread{

    private Gson gson;
    private DatagramSocket datagramSocket;
    private GameInfoSenderHelper helper;
    private final int port;
    private volatile DatagramPacket datagramPacket;
    private volatile boolean canSend = true;

    public GameInfoSender(int port) {
        this.port = port;
        gson = new Gson();
        try {
            datagramSocket = new DatagramSocket(OnlineData.getAvailablePort());
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {

        while (canSend) {
            try {
                Thread.sleep(RefreshRateConstants.GAME_INFO_SENDER_REFRESH_RATE);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            helper = new GameInfoSenderHelper();
            helper.setPressedKeys(EpsilonMovement.pressedKeys);
            helper.setReleasedKeys(EpsilonMovement.releasedKeys);
            helper.setLastAim(EpsilonAiming.lastTimeClicked);
            helper.setTypedKeys(PanelKeyListener.typedKeys);
            helper.setLastMousePosition(EpsilonCirculation.lastTimePosition);

            if (helper.getLastAim() == null && helper.getLastMousePosition() == null && helper.getPressedKeys().isEmpty()
            && helper.getReleasedKeys().isEmpty() && helper.getTypedKeys().isEmpty())
                continue;

            String Json = gson.toJson(helper);
            byte[] data = Json.getBytes();
            resetAll();
            datagramPacket = new DatagramPacket(
                    data ,
                    data.length ,
                    new InetSocketAddress(ControllerConstants.SERVER_IP, port)
            );
            for (int i = 0 ;i < ControllerConstants.DATA_SENDER_COUNTS ;i++) {
                try {
                    datagramSocket.send(datagramPacket);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

    private void resetAll() {
        EpsilonCirculation.lastTimePosition = null;
        EpsilonAiming.lastTimeClicked = null;
        PanelKeyListener.typedKeys = new ArrayList<>();
        EpsilonMovement.pressedKeys = new ArrayList<>();
        EpsilonMovement.releasedKeys = new ArrayList<>();
    }

    public void setCanSend(boolean canSend) {
        this.canSend = canSend;
    }

    public void end() {
        canSend = false;
    }
}
