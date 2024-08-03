package model.objectModel.fighters.normalEnemies.wyrmModel;

import constants.TimeConstants;
import controller.manager.GameState;

public class WyrmThread extends Thread{
    private WyrmModel wyrmModel;
    public WyrmThread(WyrmModel wyrmModel){
        this.wyrmModel = wyrmModel;
    }

    @Override
    public void run() {
        while (!GameState.isOver()) {
            try {
                Thread.sleep(TimeConstants.WYRM_SHOOTING_TIME);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (GameState.isPause() || GameState.isDizzy()){
                continue;
            }
            if (isInterrupted())
                return;
            shoot();
        }
    }

    private void shoot() {
        if (GameState.isOver())
            return;
        new WyrmShooter(wyrmModel).shoot();
    }

}
