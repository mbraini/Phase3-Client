package view.painting.menuPanels.onlinePanels.squad;

import constants.SizeConstants;
import controller.online.tcp.requests.getAllSquadsRequest.GetAllSquadHelper;
import controller.online.tcp.serverMessages.recponces.squadHistory.SquadBattleHistoryMember;
import view.painting.menuPanels.MainFrame;
import view.painting.menuPanels.PIG;
import view.painting.objectViews.panels.JScrollerLabel;
import view.painting.objectViews.panels.MyButton;
import view.painting.objectViews.panels.MyLabel;
import view.painting.objectViews.panels.MyPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class SquadBattleHistory extends PIG {

    private MyLabel historyL;
    private MyButton back;
    private JScrollPane jScrollPane;
    private JPanel container;

    public SquadBattleHistory() {
        this.setLayout(null);
        this.setBounds(0,0, SizeConstants.GAME_WIDTH, SizeConstants.GAME_HEIGHT);
        this.setBackground(Color.BLACK);
        this.setVisible(false);

        initHistoryL();
        initJScrollPane();
        initBack();
        initALs();
    }

    private void initHistoryL() {
        historyL = new MyLabel(
                new Point(getWidth() / 3 ,getHeight() / 10),
                new Dimension(getWidth() / 3 ,getHeight() / 10),
                "squad battle history",
                this
        );
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
                getWidth() / 10 * 2 ,
                getHeight() / 10 * 3 ,
                getWidth() / 10 * 6 ,
                getHeight() / 10 * 4
        );
        jScrollPane.createVerticalScrollBar();
        jScrollPane.createHorizontalScrollBar();
        jScrollPane.getVerticalScrollBar().setOpaque(false);
        jScrollPane.getVerticalScrollBar().setForeground(Color.CYAN);
        jScrollPane.setForeground(Color.CYAN);
        this.add(jScrollPane);
    }

    private void initALs() {
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                end();
                MainFrame.hasSquadPanel.start();
            }
        });
    }

    private void initBack() {
        back = new MyButton(
                new Point(getWidth() / 7 * 3 ,getHeight() / 10 * 8),
                new Dimension(getWidth() / 7 ,getHeight() / 10),
                "back",
                this
        );
    }

    public void update(ArrayList<SquadBattleHistoryMember> historyMembers) {
        container.removeAll();
        GridLayout gridLayout = new GridLayout(historyMembers.size() + 1 ,1 ,2 ,2);
        container.setLayout(gridLayout);
        setMainPanel();

        for (SquadBattleHistoryMember historyMember : historyMembers) {
            MyPanel myPanel = new MyPanel(
                    new Point(),
                    new Dimension(),
                    container
            );
            myPanel.setOpaque(true);
            myPanel.setBackground(Color.BLACK);
            myPanel.setLayout(new GridLayout(1 ,3 ,2 ,2));
            new JScrollerLabel(
                    historyMember.getMySquadName(),
                    Color.WHITE,
                    myPanel
            );
            new JScrollerLabel(
                    historyMember.getEnemySquadName(),
                    Color.ORANGE,
                    myPanel
            );
            if (historyMember.isHasWon()) {
                new JScrollerLabel(
                        "won",
                        Color.GREEN,
                        myPanel
                );
            }
            else {
                new JScrollerLabel(
                        "lost",
                        Color.RED,
                        myPanel
                );
            }
        }
        revalidate();
        repaint();
    }

    private void setMainPanel() {
        MyPanel myPanel = new MyPanel(
                new Point(),
                new Dimension(),
                container
        );
        myPanel.setLayout(new GridLayout(1 ,3 ,2 ,2));
        new JScrollerLabel(
                "your squad",
                Color.WHITE,
                myPanel
        );
        new JScrollerLabel(
                "enemy squad",
                Color.WHITE,
                myPanel
        );
        new JScrollerLabel(
                "has won",
                Color.WHITE,
                myPanel
        );
    }

    @Override
    public void start() {
        setVisible(true);
    }

    @Override
    public void end() {
        setVisible(false);
    }
}
