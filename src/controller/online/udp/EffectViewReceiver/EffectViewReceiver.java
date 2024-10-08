package controller.online.udp.EffectViewReceiver;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import utils.Helper;
import view.painting.ViewData;
import view.painting.ViewRequest;
import view.painting.objectViews.effectView.EffectView;

import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;

public class EffectViewReceiver extends Thread {

    private int port;
    private DatagramSocket datagramSocket;
    private DatagramPacket datagramPacket;
    private final Type type;
    private ArrayList<JEffect> lastEffects;
    private Gson gson;
    private volatile boolean canReceive = true;

    public EffectViewReceiver() {
        gson = new Gson();
        type = new TypeToken<ArrayList<JEffect>>(){}.getType();
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
                ArrayList<JEffect> jEffects = gson.fromJson(JObjects , type);
                updateEffectView(jEffects);
                lastEffects = jEffects;
            }
            catch (Exception e) {

            }

        }
    }

    private void updateEffectView(ArrayList<JEffect> jEffects) {

        ArrayList<EffectView> effectViews;
        synchronized (ViewData.getEffectViews()) {
            effectViews = (ArrayList<EffectView>) ViewData.getEffectViews().clone();
        }
        for (EffectView effectView : effectViews) {
            if (killed(jEffects ,effectView.getId())) {
                ViewRequest.removeEffectView(effectView.getId());
                continue;
            }
        }

        for (JEffect jEffect : jEffects) {
            if (created(jEffects ,jEffect.getId())) {
                ViewRequest.addEffectView(
                        jEffect.getEffectType(),
                        jEffect.getId(),
                        jEffect.getPolygon(),
                        jEffect.getCircle()
                );
                continue;
            }
            EffectView effectView = ViewData.getEffectView(jEffect.getId());
            if (effectView == null)
                continue;
            if (jEffect.getPolygon() != null) {
                effectView.setArea(jEffect.getPolygon());
            }
            else {
                effectView.setArea(jEffect.getCircle());
            }
            effectView.setTheta(jEffect.getTheta());
            effectView.setColor(
                    new Color(
                        jEffect.getR(),
                        jEffect.getG(),
                        jEffect.getB()
                    )
            );

        }

    }

    private boolean killed(ArrayList<JEffect> jEffects, String id) {
        if (!contains(jEffects ,id) && contains(lastEffects ,id) || !contains(jEffects ,id) && contains(lastEffects ,id))
            return true;
        return false;
    }

    private boolean created(ArrayList<JEffect> jEffects, String id) {
        if (contains(jEffects ,id) && !contains(lastEffects ,id))
            return true;
        return false;
    }

    private boolean contains(ArrayList<JEffect> jEffects ,String id) {
        for (JEffect effect : jEffects) {
            if (effect.getId().equals(id))
                return true;
        }
        return false;
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
        datagramPacket = new DatagramPacket(new byte[25000] ,25000);
    }

    public void setCanReceive(boolean canReceive) {
        this.canReceive = canReceive;
    }
}
