package controller.listeners;


import constants.SizeConstants;
import constants.SoundPathConstants;
import constants.TimeConstants;
import controller.ViewRequestController;
import controller.manager.GameState;
import controller.online.OnlineData;
import model.ModelData;
import model.objectModel.fighters.EpsilonModel;
import utils.Math;
import utils.Vector;
import view.painting.soundEffects.Sound;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class EpsilonAiming extends MouseAdapter {

    double timer;
    public static int AIM_CODE = 1;
    public EpsilonAiming(){
        timer = 0;
    }

    public volatile static Vector lastTimeClicked = new Vector();
    public volatile static Vector lastTimeClickedOnPanel = new Vector();

    @Override
    public void mousePressed(MouseEvent e) {
        if (GameState.isInAnimation())
            return;
        if (e.getButton() != AIM_CODE) {
            return;
        }
        if (GameState.isPause())
            return;
        if (OnlineData.getCurrentOnlineGame() == null) {
            if (GameState.getTime() - timer < TimeConstants.EPSILON_SHOOTIN_TIME)
                return;
            timer = GameState.getTime();
            EpsilonModel epsilon = ModelData.getEpsilon();
            Vector clickedPoint = new Vector(
                    e.getX() - SizeConstants.SCREEN_SIZE.width,
                    e.getY() - SizeConstants.SCREEN_SIZE.height
            );
            lastTimeClicked = clickedPoint.clone();
            if (clickedPoint.Equals(epsilon.getPosition()))
                return;
            if (!ViewRequestController.shootRequest(clickedPoint))
                return;

            try {
                Sound sound = new Sound(SoundPathConstants.BulletFiredSound);
                sound.play();
            } catch (UnsupportedAudioFileException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (LineUnavailableException ex) {
                throw new RuntimeException(ex);
            }
        }
        else {
            Vector newClick = new Vector(
                    e.getX() - SizeConstants.SCREEN_SIZE.width,
                    e.getY() - SizeConstants.SCREEN_SIZE.height
            );
            if (newClick.Equals(lastTimeClickedOnPanel)) {
                lastTimeClicked = Math.VectorAdd(
                        newClick,
                        new Vector(0.0001 ,0.001)
                );
                lastTimeClickedOnPanel = lastTimeClicked;
            }
            else {
                lastTimeClicked = newClick;
                lastTimeClickedOnPanel = newClick;
            }
        }
    }
}
