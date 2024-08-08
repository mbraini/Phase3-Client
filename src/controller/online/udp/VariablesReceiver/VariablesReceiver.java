package controller.online.udp.VariablesReceiver;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import controller.online.udp.objectViewReceiver.JView;
import utils.Helper;
import view.painting.ViewData;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;

public class VariablesReceiver extends Thread{

    private int port;
    private DatagramSocket datagramSocket;
    private DatagramPacket datagramPacket;
    private Gson gson;
    private volatile boolean canReceive = true;


    public VariablesReceiver() {
        gson = new Gson();
    }

    @Override
    public void run() {
        while (canReceive) {

            try {
                datagramSocket.receive(datagramPacket);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                String JObjects = new String(Helper.getDataUntil(datagramPacket.getData() ,datagramPacket.getLength()));
                JVariables jVariables = gson.fromJson(JObjects , JVariables.class);
                updateVariables(jVariables);
            }
            catch (Exception e){

            }
        }
    }

    private void updateVariables(JVariables jVariables) {
        ViewData.setXp(jVariables.getXp());
        ViewData.setHp(jVariables.getHp());
        ViewData.setWave(jVariables.getWave());
        ViewData.setTime(jVariables.getTime());
        ViewData.setEpsilonFrame(ViewData.getFrame(jVariables.getFrameView().getId()));
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setUp() {
        try {
            datagramSocket = new DatagramSocket(port);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
        datagramPacket = new DatagramPacket(new byte[10000] ,10000);
    }

    public void setCanReceive(boolean canReceive) {
        this.canReceive = canReceive;
    }
}
