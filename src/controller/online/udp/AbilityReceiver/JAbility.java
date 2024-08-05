package controller.online.udp.AbilityReceiver;

import controller.enums.InGameAbilityType;
import controller.enums.SkillTreeAbilityType;
import utils.Vector;

import java.awt.*;

public class JAbility {
    private int coolDownTime;
    private int timePassed;
    private boolean isAvailable;
    private int theta;
    private Vector position;
    private SkillTreeAbilityType skillTreeAbilityType;
    private InGameAbilityType inGameAbilityType;

    public int getCoolDownTime() {
        return coolDownTime;
    }

    public void setCoolDownTime(int coolDownTime) {
        this.coolDownTime = coolDownTime;
    }

    public int getTimePassed() {
        return timePassed;
    }

    public void setTimePassed(int timePassed) {
        this.timePassed = timePassed;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public int getTheta() {
        return theta;
    }

    public void setTheta(int theta) {
        this.theta = theta;
    }

    public Vector getPosition() {
        return position;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }


    public SkillTreeAbilityType getSkillTreeAbilityType() {
        return skillTreeAbilityType;
    }

    public void setSkillTreeAbilityType(SkillTreeAbilityType skillTreeAbilityType) {
        this.skillTreeAbilityType = skillTreeAbilityType;
    }

    public InGameAbilityType getInGameAbilityType() {
        return inGameAbilityType;
    }

    public void setInGameAbilityType(InGameAbilityType inGameAbilityType) {
        this.inGameAbilityType = inGameAbilityType;
    }
}
