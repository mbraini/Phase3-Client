package model.animations;

import model.logics.FrameHitCalculator;
import model.objectModel.frameModel.FrameModel;

public class GameStartAnimation extends TimerAnimation {
    private FrameHitCalculator frameHitCalculator;
    private FrameModel frame;
    public GameStartAnimation(FrameModel frame){
        this.frame = frame;
    }

    @Override
    public void StartAnimation(){
        frameHitCalculator = new FrameHitCalculator(frame ,-250 ,-250 ,-250 ,-250 ,1000);
        frameHitCalculator.frameHit();
    }
}