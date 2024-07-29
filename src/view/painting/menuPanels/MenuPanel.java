package view.painting.menuPanels;

import constants.SizeConstants;
import controller.Controller;
import view.Application;
import controller.online.OnlineData;
import view.painting.objectViews.panels.MyButton;
import view.painting.objectViews.panels.MyLabel;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class MenuPanel extends PIG {
    private MyButton exit;
    private MyButton start;
    private MyButton settings;
    private MyButton tutorial;
    private MyButton skillTree;
    private MyButton squad;
    private MyLabel live;
    public MenuPanel(){
        this.setLayout(null);
        this.setBounds(0,0, SizeConstants.GAME_WIDTH, SizeConstants.GAME_HEIGHT);
        this.setBackground(Color.BLACK);

        initExit();
        initSettings();
        initStart();
        initSkillTree();
        initTutorial();
        initSquad();
        initLive();
        initAL();
    }

    private void initLive() {
        live = new MyLabel(
                new Point(getWidth() - 100 ,0),
                new Dimension(100 ,50),
                "",
                this
        );
    }

    private void initSquad() {
        squad = new MyButton(
                new Point(SizeConstants.GAME_WIDTH / 3 , SizeConstants.GAME_HEIGHT / 13 * 3),
                new Dimension(SizeConstants.GAME_WIDTH / 3, SizeConstants.GAME_HEIGHT / 11),
                "squad",
                this
        );
    }

    private void initAL() {
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (OnlineData.getTCPMessager() != null) {
                    try {
                        OnlineData.getTCPMessager().getSocket().close();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                System.exit(0);
            }
        });

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Robot robot = new Robot();
                    robot.keyPress(KeyEvent.VK_WINDOWS);
                    robot.keyPress(KeyEvent.VK_D);
                    robot.keyRelease(KeyEvent.VK_WINDOWS);
                    robot.keyRelease(KeyEvent.VK_D);
                } catch (AWTException ex) {
                    throw new RuntimeException(ex);
                }
                Application.endMainFrame();
                Controller.startGame();
            }
        });
        settings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                end();
                MainFrame.settingsPanel.start();
            }
        });
        skillTree.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                end();
                MainFrame.skillTreePanel.start();
            }
        });

        tutorial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                end();
                MainFrame.tutorial.start();
            }
        });
        squad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (live.getText().equals("offline")) {
                    Application.endMainFrame();
                    OnlineData.initTCPMessager();
                }
            }
        });
    }

    private void initExit(){
        exit = new MyButton(
                new Point(SizeConstants.GAME_WIDTH / 3 , SizeConstants.GAME_HEIGHT / 13 * 11),
                new Dimension(SizeConstants.GAME_WIDTH / 3, SizeConstants.GAME_HEIGHT / 11),
                "exit",
                this
        );
    }

    private void initStart(){
        start = new MyButton(
                new Point(SizeConstants.GAME_WIDTH / 3 , SizeConstants.GAME_HEIGHT / 13),
                new Dimension(SizeConstants.GAME_WIDTH / 3, SizeConstants.GAME_HEIGHT / 11),
                "start",
                this
        );
    }

    private void initSettings(){
        settings = new MyButton(
                new Point(SizeConstants.GAME_WIDTH / 3 , SizeConstants.GAME_HEIGHT / 13 * 9),
                new Dimension(SizeConstants.GAME_WIDTH / 3, SizeConstants.GAME_HEIGHT / 11),
                "settings",
                this
        );
    }

    private void initTutorial(){
        tutorial = new MyButton(
                new Point(SizeConstants.GAME_WIDTH / 3 , SizeConstants.GAME_HEIGHT / 13 * 7),
                new Dimension(SizeConstants.GAME_WIDTH / 3, SizeConstants.GAME_HEIGHT / 11),
                "tutorial",
                this
        );
    }

    private void initSkillTree(){
        skillTree = new MyButton(
                new Point(SizeConstants.GAME_WIDTH / 3 , SizeConstants.GAME_HEIGHT / 13 * 5),
                new Dimension(SizeConstants.GAME_WIDTH / 3, SizeConstants.GAME_HEIGHT / 11),
                "skillTree",
                this
        );
    }

    @Override
    public void start() {
        this.setVisible(true);
        if (OnlineData.getTCPMessager() == null) {
            squad.setForeground(Color.RED);
            live.setText("offline");
        }
        else {
            squad.setForeground(Color.CYAN);
            live.setText("live");
        }
    }

    @Override
    public void end() {
        this.setVisible(false);
    }
}
