package model.inGameAbilities;

import constants.CostConstants;
import constants.RefreshRateConstants;
import constants.TimeConstants;
import controller.enums.InGameAbilityType;
import controller.manager.loading.SkippedByJson;
import controller.manager.GameState;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Slumber extends InGameAbility{

    private int timePassed;
    @SkippedByJson
    private Timer timer;

    public Slumber(){
        type = InGameAbilityType.slumber;
        xpCost = CostConstants.SLUMBER_COST;
        initTimer();
    }

    private void initTimer() {
        timer = new Timer(RefreshRateConstants.IN_GAME_ABILITY_TIMER_REFRESH_RATE, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (GameState.isPause())
                    return;
                timePassed += RefreshRateConstants.IN_GAME_ABILITY_TIMER_REFRESH_RATE;
                if (timePassed >= TimeConstants.SLUMBER_DURATION){
                    isAvailable = true;
                    isActive = false;
                    timePassed = 0;
                    GameState.setDizzy(false);
                    InGameAbilityHandler.permitAll();
                    timer.stop();
                }
            }
        });
    }


    @Override
    public void performAbility() {
        isActive = true;
        isAvailable = false;
        GameState.setDizzy(true);
        InGameAbilityHandler.disableAll();
        timer.start();
    }

    @Override
    public void setUp() {
        initTimer();
        if (timePassed <= TimeConstants.SLUMBER_DURATION && isActive) {
            GameState.setDizzy(true);
            timer.start();
        }
    }

    public int getTimePassed() {
        return timePassed;
    }
}
