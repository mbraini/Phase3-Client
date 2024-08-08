package view.painting.gamePanels;


import constants.ImageConstants;
import constants.SizeConstants;
import view.Application;
import view.painting.menuPanels.PIG;
import view.painting.objectViews.panels.MyButton;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndGamePanel extends PIG {
    private final int xpGained;
    private final int totalShots;
    private final int successfulShots;
    private int enemyKilled;
    private final int survivalTime;
    private int mostXpGained;
    private int mostSurvivalTime;
    private MyButton menu;
    private final EndGameFrame endGameFrame;
    private boolean onlineGame;
    public EndGamePanel(EndGameFrame endGameFrame ,int xpGained ,int enemyKilled ,int totalShots ,int successfulShots ,int survivalTime){
        this.endGameFrame = endGameFrame;
        this.survivalTime = survivalTime;
        this.successfulShots = successfulShots;
        this.enemyKilled = enemyKilled;
        this.xpGained = xpGained;
        this.totalShots = totalShots;
        setUp();
    }

    public EndGamePanel(EndGameFrame endGameFrame , int xpGained , int survivalTime, int totalShots ,
                        int successfulShots ,int mostXpGained ,int mostSurvivalTime)
    {

        this.endGameFrame = endGameFrame;
        this.xpGained = xpGained;
        this.survivalTime = survivalTime;
        this.totalShots = totalShots;
        this.successfulShots = successfulShots;
        this.mostXpGained = mostXpGained;
        this.mostSurvivalTime = mostSurvivalTime;
        onlineGame = true;

        setUp();
    }

    private void setUp() {
        this.setLayout(null);
        this.setBounds(0,0, SizeConstants.GAME_WIDTH, SizeConstants.GAME_HEIGHT);
        this.setBackground(Color.BLACK);
        endGameFrame.add(this);
        this.setFocusable(true);
        this.grabFocus();
        initMenu();
        this.setVisible(true);
    }

    private void initMenu() {
        menu = new MyButton(
                new Point(getWidth() / 5 * 2 ,getHeight() / 14 * 12),
                new Dimension(getWidth() / 5 ,getHeight() / 14),
                "menu",
                this
        );
        menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                endGameFrame.dispose();
                Application.startMainFrame();
            }
        });
        this.add(menu);
    }

    @Override
    public void start() {
        this.setVisible(true);
    }

    @Override
    public void end() {
        this.setVisible(false);
        endGameFrame.dispose();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(ImageConstants.endGameImage ,getWidth() / 3 ,getHeight() / 6 ,getWidth() / 3 ,getHeight() / 3 ,null);
        g.setFont(new Font(null,Font.BOLD ,15));
        if (!onlineGame) {
            g.setColor(Color.MAGENTA);
            g.drawString("xp earned: " + xpGained, getWidth() / 7 * 4, getHeight() / 14 * 8);
            g.setColor(Color.RED);
            g.drawString("successfulShots: " + successfulShots, getWidth() / 7 * 2, getHeight() / 14 * 8);
            g.drawString("enemyKilled: " + enemyKilled, getWidth() / 7 * 3, getHeight() / 14 * 10);
            g.setColor(Color.WHITE);
            g.drawString("survival time: " + survivalTime, getWidth() / 7, getHeight() / 14 * 10);
            g.setColor(Color.BLUE);
            g.drawString("totalShots: " + totalShots, getWidth() / 7 * 5, getHeight() / 14 * 10);
        }
        else {
            g.setColor(Color.MAGENTA);
            g.drawString("xp earned: " + xpGained, getWidth() / 7 * 3, getHeight() / 14 * 8);
            g.setColor(Color.RED);
            g.drawString("successfulShots: " + successfulShots, getWidth() / 7 * 5, getHeight() / 14 * 10);
            g.setColor(Color.WHITE);
            g.drawString("most survival time record: " + mostSurvivalTime, 10, getHeight() / 14 * 10 );
            g.drawString("most xp earned record: " + mostXpGained, 10, getHeight() / 14 * 8 );
            g.drawString("survival time: " + survivalTime, getWidth() / 7 * 3, getHeight() / 14 * 10);
            g.setColor(Color.BLUE);
            g.drawString("totalShots: " + totalShots, getWidth() / 7 * 5, getHeight() / 14 * 8);
        }
    }
}
