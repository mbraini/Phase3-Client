package controller.online.udp;

import controller.online.udp.AbilityReceiver.AbilityViewReceiver;
import controller.online.udp.EffectViewReceiver.EffectViewReceiver;
import controller.online.udp.VariablesReceiver.VariablesReceiver;
import controller.online.udp.frameViewReceiver.FrameViewReceiver;
import controller.online.udp.objectViewReceiver.ObjectViewReceiver;

public class GameInfoReceiver {

    public FrameViewReceiver frameViewReceiver;
    public EffectViewReceiver effectViewReceiver;
    public AbilityViewReceiver abilityViewReceiver;
    public ObjectViewReceiver objectViewReceiver;
    public VariablesReceiver variablesReceiver;
    public int framePort;
    public int effectPort;
    public int abilityPort;
    public int objectPort;
    public int variablesPort;


    public GameInfoReceiver() {
        frameViewReceiver = new FrameViewReceiver();
        effectViewReceiver = new EffectViewReceiver();
        abilityViewReceiver = new AbilityViewReceiver();
        objectViewReceiver = new ObjectViewReceiver();
        variablesReceiver = new VariablesReceiver();
    }

    private void threadStarter() {
        frameViewReceiver.start();
        effectViewReceiver.start();
        abilityViewReceiver.start();
        objectViewReceiver.start();
        variablesReceiver.start();
    }

    public void endAll() {
        ///todo
    }


    public int getFramePort() {
        return framePort;
    }

    public void setFramePort(int framePort) {
        this.framePort = framePort;
    }

    public int getEffectPort() {
        return effectPort;
    }

    public void setEffectPort(int effectPort) {
        this.effectPort = effectPort;
    }

    public int getAbilityPort() {
        return abilityPort;
    }

    public void setAbilityPort(int abilityPort) {
        this.abilityPort = abilityPort;
    }

    public int getObjectPort() {
        return objectPort;
    }

    public void setObjectPort(int objectPort) {
        this.objectPort = objectPort;
    }

    public int getVariablesPort() {
        return variablesPort;
    }

    public void setVariablesPort(int variablesPort) {
        this.variablesPort = variablesPort;
    }

    public void start() {
        setPorts();
        setUp();
        threadStarter();
    }

    private void setUp() {
        frameViewReceiver.setUp();
        effectViewReceiver.setUp();
        objectViewReceiver.setUp();
        variablesReceiver.setUp();
        abilityViewReceiver.setUp();
    }

    private void setPorts() {
        frameViewReceiver.setPort(framePort);
        effectViewReceiver.setPort(effectPort);
        objectViewReceiver.setPort(objectPort);
        variablesReceiver.setPort(variablesPort);
        abilityViewReceiver.setPort(abilityPort);
    }
}
