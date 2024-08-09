package view.painting.gamePanels;

import constants.SizeConstants;
import controller.online.tcp.serverMessages.messages.giveStats.StatsHelper;
import view.Application;
import view.painting.menuPanels.PIG;
import view.painting.objectViews.panels.JScrollerLabel;
import view.painting.objectViews.panels.MyButton;
import view.painting.objectViews.panels.MyPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OnlineEndGamePanel extends PIG {

    private OnlineEndGameFrame onlineEndGameFrame;
    private MyButton menu;
    private JScrollPane jScrollPane;
    private JPanel container;

    public OnlineEndGamePanel(OnlineEndGameFrame frame) {
        this.setLayout(null);
        this.setBounds(0,0, SizeConstants.GAME_WIDTH, SizeConstants.GAME_HEIGHT);
        this.setBackground(Color.BLACK);
        this.onlineEndGameFrame = frame;

        initJScrollPane();

        onlineEndGameFrame.add(this);
        this.setFocusable(true);
        this.grabFocus();
        initMenu();
        this.setVisible(true);
    }

    private void setMainPanel() {
        MyPanel myPanel = new MyPanel(
                new Point(),
                new Dimension(),
                container
        );
        myPanel.setLayout(new GridLayout(1 ,7 ,2 ,2));
        new JScrollerLabel(
                "player",
                Color.WHITE,
                myPanel
        );
        new JScrollerLabel(
                "xp gained",
                Color.MAGENTA,
                myPanel
        );
        new JScrollerLabel(
                "survival time",
                Color.WHITE,
                myPanel
        );
        new JScrollerLabel(
                "total shots",
                Color.BLUE,
                myPanel
        );
        new JScrollerLabel(
                "successful shots",
                Color.RED,
                myPanel
        );
        new JScrollerLabel(
                "most xp gained",
                Color.MAGENTA,
                myPanel
        );
        new JScrollerLabel(
                "most survival time",
                Color.WHITE,
                myPanel
        );
    }

    public void update(ArrayList<StatsHelper> stats) {
        container.removeAll();
        GridLayout gridLayout = new GridLayout(stats.size() + 1 ,1 ,2 ,2);
        container.setLayout(gridLayout);
        setMainPanel();

        for (StatsHelper statsHelper : stats) {
            MyPanel myPanel = new MyPanel(
                    new Point(),
                    new Dimension(),
                    container
            );
            myPanel.setOpaque(true);
            myPanel.setBackground(Color.BLACK);
            myPanel.setLayout(new GridLayout(1 ,7 ,2 ,2));
            new JScrollerLabel(
                    statsHelper.getUsername(),
                    Color.WHITE,
                    myPanel
            );
            new JScrollerLabel(
                    statsHelper.getXpGained() + "",
                    Color.MAGENTA,
                    myPanel
            );
            new JScrollerLabel(
                    statsHelper.getSurvivalTime() + "",
                    Color.WHITE,
                    myPanel
            );
            new JScrollerLabel(
                    statsHelper.getTotalBullets() + "",
                    Color.BLUE,
                    myPanel
            );
            new JScrollerLabel(
                    statsHelper.getSuccessfulBullets() + "",
                    Color.RED,
                    myPanel
            );
            new JScrollerLabel(
                    statsHelper.getMostXpGained() + "",
                    Color.MAGENTA,
                    myPanel
            );
            new JScrollerLabel(
                    statsHelper.getMostSurvivalTime() + "",
                    Color.WHITE,
                    myPanel
            );
        }
        revalidate();
        repaint();
    }

    private void initJScrollPane() {
        container = new JPanel();
        container.setLayout(null);
        container.setBounds(
                getWidth() / 5 * 3 ,
                getHeight() / 5 * 3,
                0,
                0
        );
        container.setBackground(Color.BLACK);
        jScrollPane = new JScrollPane(
                container,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED ,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
        );
        jScrollPane.setOpaque(false);
        jScrollPane.setBounds(
                getWidth() / 10 ,
                getHeight() / 10 ,
                getWidth() / 10 * 8 ,
                getHeight() / 10 * 6
        );
        jScrollPane.createVerticalScrollBar();
        jScrollPane.createHorizontalScrollBar();
        jScrollPane.getVerticalScrollBar().setOpaque(false);
        jScrollPane.getVerticalScrollBar().setForeground(Color.CYAN);
        jScrollPane.setForeground(Color.CYAN);
        this.add(jScrollPane);
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
                onlineEndGameFrame.dispose();
                Application.startMainFrame();
            }
        });
        this.add(menu);
    }

    @Override
    public void start() {
        setVisible(true);
    }

    @Override
    public void end() {
        setVisible(false);
        onlineEndGameFrame.dispose();
    }
}
