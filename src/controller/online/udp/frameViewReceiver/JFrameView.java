package controller.online.udp.frameViewReceiver;

import utils.Vector;

import java.awt.*;

public class JFrameView {

    private Vector position;
    private Dimension dimension;
    private String id;

    public Vector getPosition() {
        return position;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

