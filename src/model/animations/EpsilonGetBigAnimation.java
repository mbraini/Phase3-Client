package model.animations;

import constants.SizeConstants;
import constants.SoundPathConstants;
import controller.Controller;
import controller.ModelRequestController;
import controller.manager.GameState;
import model.ModelData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EpsilonGetBigAnimation extends ThreadAnimation {

    private int delay = 10;

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (ModelData.getEpsilon().getSize().width >= ModelData.getEpsilonFrame().getSize().width
                    || ModelData.getEpsilon().getSize().height >= ModelData.getEpsilonFrame().getSize().height)
            {
                Controller.endGame(true);
                ModelRequestController.playSound(SoundPathConstants.endSound);
                return;
            }
            ModelData.getEpsilon().setSize(
                    new Dimension(
                            ModelData.getEpsilon().getSize().width + SizeConstants.EPSILON_DIMENSION.width / 10,
                            ModelData.getEpsilon().getSize().height + SizeConstants.EPSILON_DIMENSION.height / 10
                    )
            );

        }
    }

    @Override
    public void StartAnimation() {
        GameState.setIsInAnimation(true);
        start();
    }
}
