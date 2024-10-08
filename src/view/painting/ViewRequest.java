package view.painting;

import constants.ImageConstants;
import controller.enums.EffectType;
import controller.enums.ModelType;
import controller.online.OnlineData;
import utils.Vector;
import utils.area.Area;
import utils.area.Circle;
import utils.area.Polygon;
import view.painting.ViewData;
import view.painting.gamePanels.ImaginaryPanel;
import view.painting.objectViews.*;
import view.painting.objectViews.basicEnemyView.SquarantineView;
import view.painting.objectViews.basicEnemyView.TrigorathView;
import view.painting.objectViews.bossView.BossAoeEffectView;
import view.painting.objectViews.bossView.HandView;
import view.painting.objectViews.bossView.HeadView;
import view.painting.objectViews.bossView.PunchView;
import view.painting.objectViews.effectView.EffectView;
import view.painting.objectViews.miniBossEnemyView.BarricadosView;
import view.painting.objectViews.miniBossEnemyView.BlackOrbLaserEffectView;
import view.painting.objectViews.miniBossEnemyView.OrbView;
import view.painting.objectViews.normalEnemyView.NecropickView;
import view.painting.objectViews.normalEnemyView.OmenoctView;
import view.painting.objectViews.normalEnemyView.WyrmView;
import view.painting.objectViews.normalEnemyView.archmireView.ArchmireEffectView;
import view.painting.objectViews.normalEnemyView.archmireView.ArchmireView;
import view.painting.objectViews.projectiles.*;

import java.awt.*;
import java.util.ArrayList;

public class ViewRequest {

    private static ArrayList<String> removeObjectViewReq;
    private static ArrayList<String> removeFrameViewReq;
    private static ArrayList<String> removeEffectViewReq;
    private static ArrayList<EffectView> addedEffectViews;
    private static ArrayList<ObjectView> addedObjectViews;
    private static ArrayList<FrameView> addedFrameViews;
    private static boolean endRequest;
    private static int epsilonColor = -1;

    public synchronized static void resetAll() {
        removeObjectViewReq = new ArrayList<>();
        removeFrameViewReq = new ArrayList<>();
        removeEffectViewReq = new ArrayList<>();
        addedEffectViews = new ArrayList<>();
        addedObjectViews = new ArrayList<>();
        addedFrameViews = new ArrayList<>();
        epsilonColor = -1;
    }

    public synchronized static void checkRequests(){
        if (endRequest) {
            endRequest = false;
            resetAll();
            ViewData.resetAll();
        }
        checkObjects();
        checkFrames();
        checkEffects();
    }

    private synchronized static void checkObjects() {
        for (int i = 0; i < addedObjectViews.size() ;i++){
            ViewData.addObject(addedObjectViews.get(i));
            addedObjectViews.remove(i);
            i--;
        }
        for (int i = 0;i < removeObjectViewReq.size() ;i++){
            ViewData.removeView(removeObjectViewReq.get(i));
            removeObjectViewReq.remove(i);
            i--;
        }
    }

    private synchronized static void checkFrames() {
        for (int i = 0 ;i < addedFrameViews.size() ;i++){
            ViewData.addFrame(addedFrameViews.get(i));
            ViewData.addImaginaryPanel(new ImaginaryPanel(addedFrameViews.get(i).getId()));
            addedFrameViews.remove(i);
            i--;
        }
        for (int i = 0 ;i < removeFrameViewReq.size() ;i++){
            ViewData.removeFrame(removeFrameViewReq.get(i));
            removeFrameViewReq.remove(i);
            i--;
        }
    }

    private synchronized static void checkEffects(){
        for (int i = 0 ;i < addedEffectViews.size() ;i++){
            ViewData.addEffect(addedEffectViews.get(i));
            addedEffectViews.remove(i);
            i--;
        }
        for (int i = 0 ;i < removeEffectViewReq.size() ;i++){
            ViewData.removeEffect(removeEffectViewReq.get(i));
            removeEffectViewReq.remove(i);
            i--;
        }
    }

    public synchronized static void addObjectView(ObjectView objectView){
        addedObjectViews.add(objectView);
    }

    public synchronized static void addFrameView(FrameView frameView){
        addedFrameViews.add(frameView);
    }

    public synchronized static void removeObjectView(String id){
        removeObjectViewReq.add(id);
    }

    public synchronized static void removeFrameView(String id){
        removeFrameViewReq.add(id);
    }

    public synchronized static void addEffectView(EffectView effectView){
        addedEffectViews.add(effectView);
    }

    public synchronized static void removeEffectView(String id){
        removeEffectViewReq.add(id);
    }

    public synchronized static void endRequest() {
        endRequest = true;
        checkRequests();
    }


    public static void addObjectView(Vector position, ModelType modelType, Dimension size , String id) {
        switch (modelType) {
            case epsilon :
                EpsilonView epsilonView = new EpsilonView(
                        position,
                        id
                );
                switch (giveEpsilonColor() % 4) {
                    case 0:
                        epsilonView.setImage(ImageConstants.whiteEpsilon);
                        break;
                    case 1:
                        epsilonView.setImage(ImageConstants.redEpsilon);
                        break;
                    case 2:
                        epsilonView.setImage(ImageConstants.blueEpsilon);
                        break;
                    case 3:
                        epsilonView.setImage(ImageConstants.yellowEpsilon);
                        break;
                }
                epsilonView.setSize(size);
                addObjectView(epsilonView);
                break;
            case trigorath:
                addObjectView(new TrigorathView(
                        position,
                        id
                ));
                break;
            case squarantine:
                addObjectView(new SquarantineView(
                        position,
                        id
                ));
                break;
            case wyrm:
                addObjectView(new WyrmView(position ,id));
                break;
            case omenoct:
                addObjectView(new OmenoctView(position ,id));
                break;
            case necropick:
                addObjectView(new NecropickView(position ,id));
                break;
            case archmire:
                addObjectView(new ArchmireView(position ,id));
                break;
            case barricadosTheFirst:
                addObjectView(new BarricadosView(position ,id));
                break;
            case barricadosTheSecond:
                addObjectView(new BarricadosView(position ,id));
                break;
            case orb:
                addObjectView(new OrbView(position ,id));
                break;
            case epsilonBullet:
                addObjectView(new EpsilonBulletView(position ,id));
                break;
            case cerberus:
                addObjectView(new CerberusView(position ,id));
                break;
            case epsilonProtector:
                addObjectView(new EpsilonProtectorView(position ,id));
                break;
            case slaughterBullet:
                addObjectView(new SlaughterBulletView(position, id));
                break;
            case hand:
                addObjectView(new HandView(position ,id));
                break;
            case head:
                addObjectView(new HeadView(position, id));
                break;
            case punch:
                addObjectView(new PunchView(position, id));
                break;
            case necropickBullet:
                addObjectView(new NecropickBulletView(position, id));
                break;
            case collective:
                addObjectView(new CollectiveView(position, id));
                break;
            case bossBullet:
                addObjectView(new BossBulletView(position, id));
                break;
            case wyrmBullet:
                addObjectView(new WyrmBulletView(position, id));
                break;
            case epsilonVertex:
                addObjectView(new EpsilonVertexView(position, id));
                break;
            case omenoctBullet:
                addObjectView(new OmenoctBulletView(position, id));
                break;
        }
    }

    public static void addEffectView(EffectType effectType, String id, Polygon polygon, Circle circle) {
        switch (effectType) {
            case archmireEffect :
                addEffectView(new ArchmireEffectView(
                        polygon,
                        id
                ));
                break;
            case BlackOrbEffect:
                addEffectView(new BlackOrbLaserEffectView(polygon ,id));
                break;
            case VomitEffect:
                addEffectView(new BossAoeEffectView(circle ,id));
                break;
        }
    }

    public static int giveEpsilonColor() {
        epsilonColor++;
        return epsilonColor;
    }

}
