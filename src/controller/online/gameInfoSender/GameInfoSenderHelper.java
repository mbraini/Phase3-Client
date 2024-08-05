package controller.online.gameInfoSender;

import utils.Vector;

import java.util.ArrayList;

public class GameInfoSenderHelper {

    private ArrayList<Integer> pressedKeys;
    private ArrayList<Integer> releasedKeys;
    private Vector lastAim;
    private Vector lastMousePosition;
    private ArrayList<Character> typedKeys;

    public ArrayList<Integer> getPressedKeys() {
        return pressedKeys;
    }

    public void setPressedKeys(ArrayList<Integer> pressedKeys) {
        this.pressedKeys = pressedKeys;
    }

    public ArrayList<Integer> getReleasedKeys() {
        return releasedKeys;
    }

    public void setReleasedKeys(ArrayList<Integer> releasedKeys) {
        this.releasedKeys = releasedKeys;
    }

    public Vector getLastAim() {
        return lastAim;
    }

    public void setLastAim(Vector lastAim) {
        this.lastAim = lastAim;
    }

    public Vector getLastMousePosition() {
        return lastMousePosition;
    }

    public void setLastMousePosition(Vector lastMousePosition) {
        this.lastMousePosition = lastMousePosition;
    }

    public ArrayList<Character> getTypedKeys() {
        return typedKeys;
    }

    public void setTypedKeys(ArrayList<Character> typedKeys) {
        this.typedKeys = typedKeys;
    }
}
