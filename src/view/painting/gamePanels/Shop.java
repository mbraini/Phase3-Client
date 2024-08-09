package view.painting.gamePanels;


import constants.CostConstants;
import constants.ImageConstants;
import constants.SizeConstants;
import controller.Controller;
import controller.ViewRequestController;
import controller.configs.Configs;
import controller.enums.InGameAbilityType;
import controller.listeners.PanelKeyListener;
import controller.manager.GameState;
import controller.online.OnlineData;
import controller.online.tcp.ClientRequestType;
import controller.online.tcp.requests.ClientSpawnAllyRequest;
import utils.Helper;
import view.painting.ViewData;
import view.painting.menuPanels.PIG;
import view.painting.objectViews.panels.MyButton;
import view.painting.objectViews.panels.MyLabel;
import view.painting.objectViews.panels.MyPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Shop extends PIG {
    private boolean isOnline;
    private ShopFrame shopFrame;
    private MyButton back;
    private MyPanel banish;
    private MyPanel empower;
    private MyPanel heal;
    private MyPanel dismay;
    private MyPanel slumber;
    private MyPanel slaughter;
    private MyLabel xp;
    private MyLabel healL;
    private MyLabel banishL;
    private MyLabel empowerL;
    private MyLabel dismayL;
    private MyLabel slumberL;
    private MyLabel slaughterL;
    private MyLabel slaughterXPL;
    private MyLabel healXPL;
    private MyLabel banishXPL;
    private MyLabel empowerXPL;
    private MyLabel dismayXPL;
    private MyLabel slumberXPL;
    private MyButton spawnAlly;
    public static int pauseTimeLeft;
    private MyLabel pauseTimeL;

    public Shop(ShopFrame shopFrame ,boolean isOnline){
        this.setLayout(null);
        this.setBounds(0,0, SizeConstants.GAME_WIDTH, SizeConstants.GAME_HEIGHT);
        this.setBackground(Color.BLACK);
        this.shopFrame = shopFrame;
        initLabels();
        initContainers();
        initBanish();
        initEmpower();
        initHeal();
        initDismay();
        initSlumber();
        initSlaughter();
        initKeyListener();
        initPauseTimeL();
        this.isOnline = isOnline;
        if (isOnline)
            initSpawn();
        this.shopFrame.add(this);
        this.grabFocus();
        this.setFocusable(true);
        initBack();
        this.setVisible(true);
    }

    public static void setPauseTimeLeft(int pauseTimeLeft) {
        Shop.pauseTimeLeft = pauseTimeLeft;
    }

    public static int getPauseTimeLeft() {
        return pauseTimeLeft;
    }

    private void initPauseTimeL() {
        pauseTimeL = new MyLabel(
                new Point(getWidth() / 5 * 3 + 10 ,getHeight() / 16),
                new Dimension(getWidth() / 5 * 2 - 14 ,getHeight() / 16),
                "pause time left :" + pauseTimeLeft,
                this
        );
        pauseTimeL.setForeground(Color.WHITE);
        pauseTimeL.setBorder(BorderFactory.createLineBorder(Color.WHITE ,2));
    }

    private void initSpawn() {
        spawnAlly = new MyButton(
                new Point(healL.getX() ,getHeight() / 16),
                new Dimension(healL.getWidth() ,healL.getHeight()),
                "spawn an ally",
                this
        );
        spawnAlly.setForeground(Color.BLUE);
        spawnAlly.setBorder(BorderFactory.createLineBorder(Color.BLUE ,2));
        spawnAlly.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ClientSpawnAllyRequest().sendRequest();
            }
        });
    }

    private void initSlaughter() {
        slaughter.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (OnlineData.getCurrentOnlineGame() == null) {
                    ViewRequestController.inGameAbilityRequest(InGameAbilityType.slaughter);
                    updateXP();
                }
                else {
                    OnlineData.getTCPMessager().sendMessage(ClientRequestType.buyInGameAbility);
                    OnlineData.getTCPMessager().sendMessage(InGameAbilityType.slaughter);
                }
            }
        });
    }

    private void initSlumber() {
        slumber.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (OnlineData.getCurrentOnlineGame() == null) {
                    ViewRequestController.inGameAbilityRequest(InGameAbilityType.slumber);
                    updateXP();
                }
                else {
                    OnlineData.getTCPMessager().sendMessage(ClientRequestType.buyInGameAbility);
                    OnlineData.getTCPMessager().sendMessage(InGameAbilityType.slumber);
                }
            }
        });
    }

    private void initDismay() {
        dismay.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (OnlineData.getCurrentOnlineGame() == null) {
                    ViewRequestController.inGameAbilityRequest(InGameAbilityType.dismay);
                    updateXP();
                }
                else {
                    OnlineData.getTCPMessager().sendMessage(ClientRequestType.buyInGameAbility);
                    OnlineData.getTCPMessager().sendMessage(InGameAbilityType.dismay);
                }
            }
        });
    }

    private void initKeyListener() {
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                PanelKeyListener.typedKeys.add('p');
                if (e.getKeyChar() == 'p' && GameState.isPause()){
                    if (OnlineData.getCurrentOnlineGame() == null)
                        end();
                }
            }
        });
    }

    private void initLabels() {
        initXPL();
        initHealL();
        initBanishL();
        initEmpowerL();
        initDismayL();
        initSlumberL();
        initSlaughterL();
    }

    private void initSlaughterL() {
        slaughterL = new MyLabel(
                new Point(SizeConstants.GAME_WIDTH / 10 * 7 , SizeConstants.GAME_HEIGHT / 16 * 12),
                new Dimension(SizeConstants.GAME_WIDTH / 10 * 2 , SizeConstants.GAME_HEIGHT / 16),
                "slaughter",
                this
        );
    }

    private void initSlumberL() {
        slumberL = new MyLabel(
                new Point(SizeConstants.GAME_WIDTH / 10 * 4 , SizeConstants.GAME_HEIGHT / 16 * 12),
                new Dimension(SizeConstants.GAME_WIDTH / 10 * 2 , SizeConstants.GAME_HEIGHT / 16),
                "slumber",
                this
        );
    }

    private void initDismayL() {
        dismayL = new MyLabel(
                new Point(SizeConstants.GAME_WIDTH / 10 , SizeConstants.GAME_HEIGHT / 16 * 12),
                new Dimension(SizeConstants.GAME_WIDTH / 10 * 2 , SizeConstants.GAME_HEIGHT / 16),
                "dismay",
                this
        );
    }

    private void initXPL() {
        xp = new MyLabel(
                new Point(SizeConstants.GAME_WIDTH / 10 * 4 , SizeConstants.GAME_HEIGHT / 16),
                new Dimension(SizeConstants.GAME_WIDTH / 10 * 2 , SizeConstants.GAME_HEIGHT / 16),
                "XP :" + GameState.getXp(),
                this
        );
        xp.setBorder(BorderFactory.createLineBorder(Color.MAGENTA ,2));
        xp.setForeground(Color.MAGENTA);
    }

    private void initHealL() {
        healL = new MyLabel(
                new Point(SizeConstants.GAME_WIDTH / 10 ,(int) (SizeConstants.GAME_HEIGHT / 16 * 6.5)),
                new Dimension(SizeConstants.GAME_WIDTH / 10 * 2 , SizeConstants.GAME_HEIGHT / 16),
                "heal",
                this
        );
    }

    private void initEmpowerL() {
        empowerL = new MyLabel(
                new Point(SizeConstants.GAME_WIDTH / 10 * 4 ,(int) (SizeConstants.GAME_HEIGHT / 16 * 6.5)),
                new Dimension(SizeConstants.GAME_WIDTH / 10 * 2 , SizeConstants.GAME_HEIGHT / 16),
                "empower",
                this
        );
    }

    private void initBanishL() {
        banishL = new MyLabel(
                new Point(SizeConstants.GAME_WIDTH / 10 * 7 ,(int) (SizeConstants.GAME_HEIGHT / 16 * 6.5)),
                new Dimension(SizeConstants.GAME_WIDTH / 10 * 2 , SizeConstants.GAME_HEIGHT / 16),
                "banish",
                this
        );
    }

    private void initContainers() {
        banish = new MyPanel(
                new Point(getWidth() / 10 * 7 ,getHeight() / 16 * 3),
                new Dimension(getWidth() / 10 * 2 ,getHeight() / 16 * 3),
                this
        );
        banishXPL = new MyLabel(
                new Point(banishL.getX() ,banishL.getY() - getHeight() / 22),
                new Dimension(banishL.getWidth() ,banishL.getHeight()),
                CostConstants.BANISH_COST + "",
                this
        );
        banishXPL.setBorder(null);
        banishXPL.setForeground(Color.MAGENTA);
        empower = new MyPanel(
                new Point(getWidth() / 10 * 4 ,getHeight() / 16 * 3),
                new Dimension(getWidth() / 10 * 2 ,getHeight() / 16 * 3),
                this
        );
        empowerXPL = new MyLabel(
                new Point(empowerL.getX() ,empowerL.getY() - getHeight() / 22),
                new Dimension(empowerL.getWidth() ,empowerL.getHeight()),
                CostConstants.EMPOWER_COST + "",
                this
        );
        empowerXPL.setBorder(null);
        empowerXPL.setForeground(Color.MAGENTA);
        heal = new MyPanel(
                new Point(getWidth() / 10 ,getHeight() / 16 * 3),
                new Dimension(getWidth() / 10 * 2 ,getHeight() / 16 * 3),
                this
        );
        healXPL = new MyLabel(
                new Point(healL.getX() ,healL.getY() - getHeight() / 22),
                new Dimension(healL.getWidth() ,healL.getHeight()),
                CostConstants.HEAL_COST + "",
                this
        );
        healXPL.setBorder(null);
        healXPL.setForeground(Color.MAGENTA);
        dismay = new MyPanel(
                new Point(getWidth() / 10 ,(int)(getHeight() / 16 * 8.5)),
                new Dimension(getWidth() / 10 * 2 ,getHeight() / 16 * 3),
                this
        );
        dismayXPL = new MyLabel(
                new Point(dismayL.getX() ,dismayL.getY() - getHeight() / 22),
                new Dimension(dismayL.getWidth() ,dismayL.getHeight()),
                CostConstants.DISMAY_COST + "",
                this
        );
        dismayXPL.setBorder(null);
        dismayXPL.setForeground(Color.MAGENTA);
        slumber = new MyPanel(
                new Point(getWidth() / 10 * 4 ,(int)(getHeight() / 16 * 8.5)),
                new Dimension(getWidth() / 10 * 2 ,getHeight() / 16 * 3),
                this
        );
        slumberXPL = new MyLabel(
                new Point(slumberL.getX() ,slumberL.getY() - getHeight() / 22),
                new Dimension(slumberL.getWidth() ,slumberL.getHeight()),
                CostConstants.SLUMBER_COST + "",
                this
        );
        slumberXPL.setBorder(null);
        slumberXPL.setForeground(Color.MAGENTA);
        slaughter = new MyPanel(
                new Point(getWidth() / 10 * 7 ,(int)(getHeight() / 16 * 8.5)),
                new Dimension(getWidth() / 10 * 2 ,getHeight() / 16 * 3),
                this
        );
        slaughterXPL = new MyLabel(
                new Point(slaughterL.getX() ,slaughterL.getY() - getHeight() / 22),
                new Dimension(slaughterL.getWidth() ,slaughterL.getHeight()),
                CostConstants.SLAUGHTER_COST + "",
                this
        );
        slaughterXPL.setBorder(null);
        slaughterXPL.setForeground(Color.MAGENTA);
    }

    private void initHeal() {
        heal.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (OnlineData.getCurrentOnlineGame() == null) {
                    ViewRequestController.inGameAbilityRequest(InGameAbilityType.heal);
                    updateXP();
                }
                else {
                    OnlineData.getTCPMessager().sendMessage(ClientRequestType.buyInGameAbility);
                    OnlineData.getTCPMessager().sendMessage(InGameAbilityType.heal);
                }
            }
        });
    }

    private void initEmpower() {
        empower.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (OnlineData.getCurrentOnlineGame() == null) {
                    ViewRequestController.inGameAbilityRequest(InGameAbilityType.empower);
                    updateXP();
                }
                else {
                    OnlineData.getTCPMessager().sendMessage(ClientRequestType.buyInGameAbility);
                    OnlineData.getTCPMessager().sendMessage(InGameAbilityType.empower);
                }
            }
        });
    }

    private void initBanish() {
        banish.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (OnlineData.getCurrentOnlineGame() == null) {
                    ViewRequestController.inGameAbilityRequest(InGameAbilityType.banish);
                    updateXP();
                }
                else {
                    OnlineData.getTCPMessager().sendMessage(ClientRequestType.buyInGameAbility);
                    OnlineData.getTCPMessager().sendMessage(InGameAbilityType.banish);
                }
            }
        });
    }

    void initBack(){
        back = new MyButton(
                new Point(getWidth() / 10 * 4 ,getHeight() / 16 * 14),
                new Dimension(getWidth() / 10 * 2 ,getHeight() / 16),
                "Back",
                this
        );

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PanelKeyListener.typedKeys.add('p');
                if (OnlineData.getCurrentOnlineGame() == null)
                    end();
            }
        });
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        xp.setText(ViewData.getXp() + "");
        g.drawImage(ImageConstants.heal ,heal.getX() ,heal.getY() ,heal.getWidth() ,heal.getHeight() ,null);
        g.drawImage(ImageConstants.empower ,empower.getX() ,empower.getY() ,empower.getWidth() ,empower.getHeight() ,null);
        g.drawImage(ImageConstants.banish ,banish.getX() ,banish.getY() ,banish.getWidth() ,banish.getHeight() ,null);
        g.drawImage(ImageConstants.slumber ,slumber.getX() ,slumber.getY() ,slumber.getWidth() ,slumber.getHeight() ,null);
        g.drawImage(ImageConstants.slaughter ,slaughter.getX() ,slaughter.getY() ,slaughter.getWidth() ,slaughter.getHeight() ,null);
        g.drawImage(ImageConstants.dismay ,dismay.getX() ,dismay.getY() ,dismay.getWidth() ,dismay.getHeight() ,null);
        pauseTimeL.setText("pause time left :" + pauseTimeLeft);
    }

    @Override
    public void start() {
        setVisible(true);
        if (!isOnline) {
            pauseTimeL.setVisible(false);
        }
    }

    private void updateXP() {
        Helper.saveXP(GameState.getXp());
        xp.setText("xp: " + Configs.GameConfigs.XP);
    }

    @Override
    public void end() {
        Controller.resume();
        shopFrame.dispose();
    }
}
