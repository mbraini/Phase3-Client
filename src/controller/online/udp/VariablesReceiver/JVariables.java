package controller.online.udp.VariablesReceiver;

import utils.Vector;

import java.awt.*;

public class JVariables {

    int time;
    int hp;
    int xp;
    int wave;
    JVariables.FrameView frameView;

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getWave() {
        return wave;
    }

    public void setWave(int wave) {
        this.wave = wave;
    }

    public FrameView getFrameView() {
        return frameView;
    }

    public void setFrameView(FrameView frameView) {
        this.frameView = frameView;
    }

    public static class FrameView {

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

}
