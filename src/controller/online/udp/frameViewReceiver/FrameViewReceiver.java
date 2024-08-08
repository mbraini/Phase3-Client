package controller.online.udp.frameViewReceiver;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import utils.Helper;
import view.painting.ViewData;
import view.painting.ViewRequest;
import view.painting.objectViews.FrameView;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;

public class FrameViewReceiver extends Thread{

    private int port;
    private DatagramSocket datagramSocket;
    private DatagramPacket datagramPacket;
    private Gson gson;
    private final Type type;
    private volatile ArrayList<JFrameView> lastFrameViews;
    private volatile boolean canReceive = true;

    public FrameViewReceiver() {
        gson = new Gson();
        type = new TypeToken<ArrayList<JFrameView>>(){}.getType();
        lastFrameViews = new ArrayList<>();
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
                ArrayList<JFrameView> jFrameViews = gson.fromJson(JObjects , type);
                updateFrameViews(jFrameViews);
                lastFrameViews = (ArrayList<JFrameView>) jFrameViews.clone();
            }
            catch (Exception e) {

            }
        }
    }

    private void updateFrameViews(ArrayList<JFrameView> jFrameViews) {

        ArrayList<FrameView> frameViews;
        synchronized (ViewData.getFrames()) {
            frameViews = (ArrayList<FrameView>) ViewData.getFrames().clone();
        }

        for (FrameView frameView : frameViews) {
            if (killed(jFrameViews ,frameView.getId())) {
                ViewRequest.removeFrameView(frameView.getId());
                continue;
            }
        }

        for (JFrameView jFrameView : jFrameViews) {
            if (created(jFrameViews ,jFrameView.getId())) {
                ViewRequest.addFrameView(
                        new FrameView(
                                jFrameView.getPosition(),
                                jFrameView.getDimension(),
                                jFrameView.getId()
                        )
                );
                continue;
            }

            FrameView frameView = ViewData.getFrame(jFrameView.getId());
            if (frameView == null)
                continue;
            frameView.setPosition(jFrameView.getPosition());
            frameView.setDimension(jFrameView.getDimension());
        }

    }

    private boolean killed(ArrayList<JFrameView> jFrameViews ,String id) {
        if (!contains(jFrameViews ,id) && contains(lastFrameViews ,id) || (!contains(jFrameViews ,id) && !contains(lastFrameViews ,id))) {
            return true;
        }
        return false;
    }

    private boolean created(ArrayList<JFrameView> jFrameViews ,String id) {
        if (contains(jFrameViews ,id) && !contains(lastFrameViews ,id)) {
            return true;
        }
        return false;
    }

    private boolean contains(ArrayList<JFrameView> jFrameViews ,String id) {
        for (JFrameView frame : jFrameViews) {
            if (frame.getId().equals(id))
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
        datagramPacket = new DatagramPacket(new byte[10000] ,10000);
    }

    public void setCanReceive(boolean canReceive) {
        this.canReceive = canReceive;
    }
}
