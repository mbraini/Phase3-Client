package controller.online.udp.AbilityReceiver;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import controller.online.udp.VariablesReceiver.JVariables;
import utils.Helper;
import view.painting.ViewData;
import view.painting.abilities.AbilityView;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;

public class AbilityViewReceiver extends Thread{

    private int port;
    private DatagramSocket datagramSocket;
    private DatagramPacket datagramPacket;
    private final Type type;
    private Gson gson;
    private volatile boolean canReceive = true;

    public AbilityViewReceiver() {
        gson = new Gson();
        type = new TypeToken<ArrayList<JAbility>>(){}.getType();
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
                ArrayList<JAbility> jAbilities = gson.fromJson(JObjects , type);
                updateAbilities(jAbilities);
            }
            catch (Exception e){

            }

        }

    }

    private void updateAbilities(ArrayList<JAbility> jAbilities) {

        for (JAbility jAbility : jAbilities) {
            if (jAbility.getInGameAbilityType() != null) {
                updateInGameAbility(jAbility);
                continue;
            }
            if (jAbility.getSkillTreeAbilityType() != null) {
                updateSkillTreeAbility(jAbility);
            }
        }

    }

    private void updateSkillTreeAbility(JAbility jAbility) {
        AbilityView abilityView = ViewData.getAbility(jAbility);
        abilityView.setAvailable(jAbility.isAvailable());
        abilityView.setTimePassed(jAbility.getTimePassed());
        abilityView.setCoolDownTime(jAbility.getCoolDownTime());
    }

    private void updateInGameAbility(JAbility jAbility) {
        AbilityView abilityView = ViewData.getAbility(jAbility);
        abilityView.setAvailable(jAbility.isAvailable());
        abilityView.setTimePassed(jAbility.getTimePassed());
        abilityView.setCoolDownTime(jAbility.getCoolDownTime());
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
