package view.painting;

import controller.PauseController;
import controller.enums.InGameAbilityType;
import controller.enums.SkillTreeAbilityType;
import controller.listeners.EpsilonAiming;
import controller.listeners.EpsilonCirculation;
import controller.listeners.epsilonMovement.EpsilonMovement;
import controller.online.udp.AbilityReceiver.JAbility;
import view.painting.abilities.*;
import view.painting.objectViews.FrameView;
import view.painting.objectViews.ObjectView;
import view.painting.gamePanels.ImaginaryPanel;
import view.painting.objectViews.effectView.EffectView;

import java.util.ArrayList;

public class ViewData {
    private static ArrayList<ImaginaryPanel> panels;
    private static ArrayList<FrameView> frames;
    private static ArrayList<ObjectView> views;
    private static ArrayList<EffectView> effectViews;
    private static ArrayList<AbilityView> abilityViews;
    private static double time;
    private static double hp;
    private static double xp;
    private static int wave;
    private static FrameView epsilonFrame;

    public static void resetAll() {
        panels = new ArrayList<>();
        if (frames != null) {
            for (FrameView frameView : frames) {
                frameView.dispose();
            }
        }
        frames = new ArrayList<>();
        views = new ArrayList<>();
        effectViews = new ArrayList<>();
        abilityViews = new ArrayList<>();
    }


    public static ArrayList<FrameView> getFrames() {
        return frames;
    }

    public static ArrayList<ObjectView> getViews() {
        return views;
    }

    public static void setFrames(ArrayList<FrameView> frames) {
        ViewData.frames = frames;
    }

    public static void setViews(ArrayList<ObjectView> views) {
        ViewData.views = views;
    }

    public static void addImaginaryPanel(ImaginaryPanel imaginaryPanel){
        frames.getLast().add(imaginaryPanel);

        imaginaryPanel.addMouseListener(new EpsilonAiming());
        imaginaryPanel.addKeyListener(new EpsilonMovement());
        imaginaryPanel.addMouseMotionListener(new EpsilonCirculation());
        imaginaryPanel.grabFocus();

        panels.add(imaginaryPanel);
    }

    public static ArrayList<ImaginaryPanel> getPanels() {
        return panels;
    }

    public static void setPanels(ArrayList<ImaginaryPanel> panels) {
        ViewData.panels = panels;
    }

    public static void addFrame(FrameView newFrame) {
        frames.add(newFrame);
    }

    public static void addObject(ObjectView objectView){
        views.add(objectView);
    }

    public static double getTime() {
        return time;
    }

    public static void setTime(double time) {
        ViewData.time = time;
    }

    public static double getHp() {
        return hp;
    }

    public static void setHp(double hp) {
        ViewData.hp = hp;
    }

    public static double getXp() {
        return xp;
    }

    public static void setXp(double xp) {
        ViewData.xp = xp;
    }

    public static int getWave() {
        return wave;
    }

    public static void setWave(int wave) {
        ViewData.wave = wave;
    }



    public static ArrayList<EffectView> getEffectViews() {
        return effectViews;
    }

    public static void setEffectViews(ArrayList<EffectView> effectViews) {
        ViewData.effectViews = effectViews;
    }

    public synchronized static void removeView(String id) {
        for (ObjectView view : views){
            if (view.getId().equals(id)) {
                views.remove(view);
                return;
            }
        }
    }

    public synchronized static void removeFrame(String id) {
        for (int i = 0; i < frames.size(); i++){
            if (frames.get(i).getId().equals(id)){
                frames.get(i).dispose();
                frames.remove(i);
                panels.remove(i);
                return;
            }
        }
    }

    public synchronized static void removeEffect(String id){
        for (int i = 0 ;i < effectViews.size() ;i++){
            if (effectViews.get(i).getId().equals(id)){
                effectViews.remove(i);
                return;
            }
        }
    }

    public static void addEffect(EffectView effectView) {
        effectViews.add(effectView);
    }

    public static ArrayList<AbilityView> getAbilityViews() {
        return abilityViews;
    }

    public static void setAbilityViews(ArrayList<AbilityView> abilityViews) {
        ViewData.abilityViews = abilityViews;
    }

    public static FrameView getEpsilonFrame() {
        return epsilonFrame;
    }

    public static void setEpsilonFrame(FrameView frameView) {
        ViewData.epsilonFrame = frameView;
    }

    public static void addAbilityWithType(int coolDown, int timePassed, boolean isAvailable, SkillTreeAbilityType type) {
        switch (type) {
            case ares :
                ViewData.addAbility(
                        new AresView(coolDown,timePassed,isAvailable)
                );
                break;
            case astrape:
                ViewData.addAbility(
                        new AstrapeView(coolDown,timePassed,isAvailable)
                );
                break;
            case cerberus:
                ViewData.addAbility(
                        new CerberusView(coolDown,timePassed,isAvailable)
                );
                break;
            case aceso:
                ViewData.addAbility(
                        new AcesoView(coolDown,timePassed,isAvailable)
                );
                break;
            case melampus:
                ViewData.addAbility(
                        new MelampusView(coolDown,timePassed,isAvailable)
                );
                break;
            case chiron:
                ViewData.addAbility(
                        new ChironView(coolDown,timePassed,isAvailable)
                );
                break;
            case athena:
                ViewData.addAbility(
                        new AthenaView(coolDown,timePassed,isAvailable)
                );
                break;
            case proteus:
                ViewData.addAbility(
                    new ProteusView(coolDown,timePassed,isAvailable)
                );
                break;
            case empusa:
                ViewData.addAbility(
                        new EmpusaView(coolDown,timePassed,isAvailable)
                );
                break;
            case dolus:
                ViewData.addAbility(
                        new DolusView(coolDown,timePassed,isAvailable)
                );
                break;
        }
    }

    public static void addAbilityWithType(int coolDown, int timePassed, boolean isAvailable, InGameAbilityType type) {
        if (type == InGameAbilityType.slaughter) {
            addAbility(new SlaughterView(coolDown,timePassed,isAvailable));
        }
        else {
            addAbility(new SlumberView(coolDown,timePassed,isAvailable));
        }
    }

    private static void addAbility(AbilityView abilityView) {
        abilityViews.add(abilityView);
    }

    public synchronized static FrameView getFrame(String id) {
        synchronized (frames) {
            for (FrameView frameView : frames) {
                if (frameView.getId().equals(id))
                    return frameView;
            }
        }
        return null;
    }

    public static ObjectView getView(String id) {
        synchronized (views) {
            for (ObjectView view : views) {
                if (view.getId().equals(id))
                    return view;
            }
        }
        return null;
    }

    public static EffectView getEffectView(String id) {
        synchronized (effectViews) {
            for (EffectView effectView : effectViews) {
                if (effectView.getId().equals(id))
                    return effectView;
            }
        }
        return null;
    }

    public static void initAbilities() {
        for (SkillTreeAbilityType skillTreeAbilityType : SkillTreeAbilityType.values()) {
            addAbilityWithType(
                    0 ,0 ,true ,skillTreeAbilityType
            );
        }
        addAbilityWithType(
                0 ,0 ,true ,InGameAbilityType.slaughter
        );
        addAbilityWithType(
                0 ,0 ,true ,InGameAbilityType.slumber
        );
    }

    public static AbilityView getAbility(JAbility jAbility) {
        synchronized (abilityViews) {
            for (AbilityView abilityView : abilityViews) {
                if (jAbility.getSkillTreeAbilityType() != null) {
                    if (jAbility.getSkillTreeAbilityType().equals(SkillTreeAbilityType.ares) && abilityView instanceof AresView)
                        return abilityView;
                    if (jAbility.getSkillTreeAbilityType().equals(SkillTreeAbilityType.astrape) && abilityView instanceof AstrapeView)
                        return abilityView;
                    if (jAbility.getSkillTreeAbilityType().equals(SkillTreeAbilityType.cerberus) && abilityView instanceof CerberusView)
                        return abilityView;
                    if (jAbility.getSkillTreeAbilityType().equals(SkillTreeAbilityType.melampus) && abilityView instanceof MelampusView)
                        return abilityView;
                    if (jAbility.getSkillTreeAbilityType().equals(SkillTreeAbilityType.aceso) && abilityView instanceof AcesoView)
                        return abilityView;
                    if (jAbility.getSkillTreeAbilityType().equals(SkillTreeAbilityType.athena) && abilityView instanceof AthenaView)
                        return abilityView;
                    if (jAbility.getSkillTreeAbilityType().equals(SkillTreeAbilityType.chiron) && abilityView instanceof ChironView)
                        return abilityView;
                    if (jAbility.getSkillTreeAbilityType().equals(SkillTreeAbilityType.dolus) && abilityView instanceof DolusView)
                        return abilityView;
                    if (jAbility.getSkillTreeAbilityType().equals(SkillTreeAbilityType.empusa) && abilityView instanceof EmpusaView)
                        return abilityView;
                    if (jAbility.getSkillTreeAbilityType().equals(SkillTreeAbilityType.proteus) && abilityView instanceof ProteusView)
                        return abilityView;
                }
                else {
                    if (jAbility.getInGameAbilityType().equals(InGameAbilityType.slaughter) && abilityView instanceof SlaughterView)
                        return abilityView;
                    if (jAbility.getInGameAbilityType().equals(InGameAbilityType.slumber) && abilityView instanceof SlumberView)
                        return abilityView;
                }
            }
            return null;
        }
    }
}
