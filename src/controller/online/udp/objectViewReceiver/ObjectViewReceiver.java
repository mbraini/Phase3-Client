package controller.online.udp.objectViewReceiver;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import controller.interfaces.SizeChanger;
import utils.Helper;
import view.painting.ViewData;
import view.painting.ViewRequest;
import view.painting.objectViews.ObjectView;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;

public class ObjectViewReceiver extends Thread{

    private int port;
    private Gson gson;
    private DatagramSocket datagramSocket;
    private DatagramPacket datagramPacket;
    private final Type type;
    private ArrayList<JView> lastJViews = new ArrayList<>();
    private volatile boolean canReceive = true;

    public ObjectViewReceiver() {
        gson = new Gson();
        type = new TypeToken<ArrayList<JView>>(){}.getType();
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
                ArrayList<JView> jViews = gson.fromJson(JObjects , type);
                updateObjectView(jViews);
                lastJViews = jViews;
            }
            catch (Exception e) {

            }
        }
    }

    private void updateObjectView(ArrayList<JView> jViews) {
        ArrayList<ObjectView> views;
        synchronized (ViewData.getViews()) {
            views = (ArrayList<ObjectView>) ViewData.getViews().clone();
        }
        for (ObjectView view : views) {
            if (killed(jViews ,view.getId())) {
                ViewRequest.removeObjectView(view.getId());
                continue;
            }
        }
        for (JView jView : jViews) {
            if (created(jViews ,jView.getId())) {
                ViewRequest.addObjectView(
                        jView.getPosition(),
                        jView.getModelType(),
                        jView.getSize(),
                        jView.getId()
                );
                continue;
            }

            ObjectView objectView = ViewData.getView(jView.getId());
            if (objectView == null)
                continue;

            objectView.setPosition(jView.getPosition());
            objectView.setTheta(jView.getTheta());
            objectView.setHovering(jView.isHovering());
            if (objectView instanceof SizeChanger) {
                ((SizeChanger) objectView).setSize(jView.getSize());
            }
        }

    }

    private boolean killed(ArrayList<JView> jViews, String id) {
        if (!contains(jViews ,id) && contains(lastJViews ,id) || (!contains(jViews ,id) && !contains(lastJViews ,id)))
            return true;
        return false;
    }

    private boolean created(ArrayList<JView> jViews, String id) {
        if (contains(jViews ,id) && !contains(lastJViews ,id)) {
            return true;
        }
        return false;
    }

    private boolean contains(ArrayList<JView> jViews, String id) {
        for (JView view : jViews) {
            if (view.getId().equals(id))
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
        datagramPacket = new DatagramPacket(new byte[20000] ,20000);
    }

    public void setCanReceive(boolean canReceive) {
        this.canReceive = canReceive;
    }
}
