package controller;

import constants.SoundPathConstants;
import controller.listeners.keyHelper.KeyHelper;
import controller.manager.GameState;
import model.ModelData;
import model.objectModel.ObjectModel;
import model.objectModel.PortalModel;
import model.objectModel.fighters.EpsilonModel;
import view.painting.gamePanels.PortalFrame;
import view.painting.gamePanels.PortalPanel;
import view.painting.soundEffects.Sound;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.ArrayList;

public class ModelRequestController {


    private static PortalModel portalModel;

    public static void randomizeKeys() {
        KeyHelper.randomize();
    }

    public static void reorderKeys() {
        KeyHelper.reorder();
    }

    public static void portalWindow() {
        Controller.pause();
        ObjectController.removeObject(portalModel);
        int totalPR = GameState.getAllPR();
        int PR = (int) (totalPR * GameState.getXpGained() * 10 / GameState.getHp());
        new PortalPanel(new PortalFrame() ,PR).start();
    }

    public synchronized static void killEveryThing() {
        ArrayList<ObjectModel> models;
        synchronized (ModelData.getModels()) {
            models = (ArrayList<ObjectModel>) ModelData.getModels().clone();
        }
        for (ObjectModel model : models) {
            if (!(model instanceof EpsilonModel))
                model.die();
        }
    }

    public static void setPortalModel(PortalModel portalModel) {
        ModelRequestController.portalModel = portalModel;
    }

    public static void playSound(String path) {
        Sound sound = null;
        try {
            sound = new Sound(path);
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        sound.play();
    }

}
