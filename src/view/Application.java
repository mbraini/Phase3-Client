package view;

import constants.ControllerConstants;
import constants.ImageConstants;
import constants.SoundPathConstants;
import controller.Controller;
import view.online.OnlineData;
import view.painting.soundEffects.Sound;
import view.painting.menuPanels.MainFrame;
import view.online.tcp.TCPClient;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class Application implements Runnable{
    private static MainFrame mainFrame;

    @Override
    public void run() {
        Controller.rumModel();
        runView();
    }


    private void runView() {
        getImages();
        getAudios();
        startFirstTime();
    }

    private void startFirstTime() {
        int response = JOptionPane.showConfirmDialog(null ,
                "do you want to play online?",
                "play online",
                0
        );
        if (response == 0) {
            OnlineData.initTCPMessager();
        }
        else {
            startMainFrame();
        }
    }

    private void getImages(){
        try {
            ImageConstants.epsilonImage = ImageIO.read(new File("src/view/painting/objectViews/images/epsilonImage.png"));
            ImageConstants.trigorathImage = ImageIO.read(new File("src/view/painting/objectViews/images/trigorathImage.png"));
            ImageConstants.squarantineImage = ImageIO.read(new File("src/view/painting/objectViews/images/squarantineImage.png"));
            ImageConstants.banish = ImageIO.read(new File("src/view/painting/objectViews/images/Banish.png"));
            ImageConstants.empower = ImageIO.read(new File("src/view/painting/objectViews/images/Empower.png"));
            ImageConstants.heal = ImageIO.read(new File("src/view/painting/objectViews/images/Heal.png"));
            ImageConstants.endGameImage = ImageIO.read(new File("src/view/painting/objectViews/images/GameOver.png"));
            ImageConstants.omenoct = ImageIO.read(new File("src/view/painting/objectViews/images/omenoct.png"));
            ImageConstants.orb = ImageIO.read(new File("src/view/painting/objectViews/images/orb.png"));
            ImageConstants.smiley = ImageIO.read(new File("src/view/painting/objectViews/images/smiley.png"));
            ImageConstants.hand = ImageIO.read(new File("src/view/painting/objectViews/images/hand.png"));
            ImageConstants.bossAoe = ImageIO.read(new File("src/view/painting/objectViews/images/bossAoe.png"));
            ImageConstants.punch = ImageIO.read(new File("src/view/painting/objectViews/images/punch.png"));
            ImageConstants.slumber = ImageIO.read(new File("src/view/painting/objectViews/images/slumber.png"));
            ImageConstants.archmire = ImageIO.read(new File("src/view/painting/objectViews/images/archmire.png"));
            ImageConstants.skeleton = ImageIO.read(new File("src/view/painting/objectViews/images/skeleton.png"));
            ImageConstants.barricados = ImageIO.read(new File("src/view/painting/objectViews/images/barricados.png"));
            ImageConstants.wyrm = ImageIO.read(new File("src/view/painting/objectViews/images/wyrm.png"));
            ImageConstants.portal = ImageIO.read(new File("src/view/painting/objectViews/images/portal.png"));
            ImageConstants.slaughter = ImageIO.read(new File("src/view/painting/objectViews/images/slaughter.png"));
            ImageConstants.dismay = ImageIO.read(new File("src/view/painting/objectViews/images/dismay.png"));
            ImageConstants.necropick = ImageIO.read(new File("src/view/painting/objectViews/images/necropick.png"));
            ImageConstants.ares = ImageIO.read(new File("src/view/painting/objectViews/images/ares.png"));
            ImageConstants.astrape = ImageIO.read(new File("src/view/painting/objectViews/images/astrape.png"));
            ImageConstants.cerberus = ImageIO.read(new File("src/view/painting/objectViews/images/cerberus.png"));
            ImageConstants.aceso = ImageIO.read(new File("src/view/painting/objectViews/images/aceso.png"));
            ImageConstants.melampus = ImageIO.read(new File("src/view/painting/objectViews/images/melampus.png"));
            ImageConstants.chiron = ImageIO.read(new File("src/view/painting/objectViews/images/chiron.png"));
            ImageConstants.athena = ImageIO.read(new File("src/view/painting/objectViews/images/athena.png"));
            ImageConstants.proteus = ImageIO.read(new File("src/view/painting/objectViews/images/proteus.png"));
            ImageConstants.empusa = ImageIO.read(new File("src/view/painting/objectViews/images/empusa.png"));
            ImageConstants.dolus = ImageIO.read(new File("src/view/painting/objectViews/images/dolus.png"));
        }
        catch (Exception e){
            System.out.println("File Not Found!");
        }
    }

    private void getAudios() {
        SoundPathConstants.backGroundSound = "src/view/painting/SoundEffects/Song.wav";
        SoundPathConstants.BulletFiredSound = "src/view/painting/SoundEffects/Bullet Fired.wav";
        SoundPathConstants.waveSpawnSound = "src/view/painting/SoundEffects/Wave Spawn.wav";
        SoundPathConstants.enemyOnDeathSound = "src/view/painting/SoundEffects/EnemyOnDeath.wav";
        SoundPathConstants.impactSound = "src/view/painting/SoundEffects/ImpactSound.wav";
        SoundPathConstants.endSound = "src/view/painting/SoundEffects/endSound.wav";
        SoundPathConstants.winSound = "src/view/painting/SoundEffects/winSound.wav";
        Sound sound = null;
        try {
            sound = new Sound(SoundPathConstants.backGroundSound);
            Sound.volumeUp();
            Sound.volumeDown();
            sound.loop();
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    public static void endMainFrame(){
        mainFrame.dispose();
    }

    public static void startMainFrame(){
        mainFrame = new MainFrame();
    }


}
