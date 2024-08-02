package view.painting.menuPanels.onlinePanels.squad;

import constants.RefreshRateConstants;
import constants.SizeConstants;
import controller.online.tcp.ClientState;
import controller.online.tcp.requests.getTreasuryInfo.ClientUpdateTreasuryRequest;
import controller.online.tcp.requests.updateBattleSquadPanel.ClientUpdateBattleSquadRequest;
import controller.online.tcp.requests.updateBattleSquadPanel.GetBattleSquadMemberHelper;
import view.Application;
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
import java.util.ArrayList;

public class HasBattleSquadPanel extends PIG {

    private MyLabel battleL;
    private MyLabel myTeamL;
    private MyLabel enemyTeamL;
    private MyButton back;
    private MyButton treasury;
    private JScrollPane mySquad;
    private JScrollPane enemySquad;
    private JPanel mySquadContainer;
    private JPanel enemySquadContainer;
    private String enemySquadName = "";
    private ArrayList<GetBattleSquadMemberHelper> mySquadMembers = new ArrayList<>();
    private ArrayList<GetBattleSquadMemberHelper> enemySquadMembers = new ArrayList<>();
    private GetBattleSquadMemberHelper me = new GetBattleSquadMemberHelper();
    private final Timer updater;
    private final int wUnit;
    private final int hUnit;
    private final int hHelperUnit;

    public HasBattleSquadPanel() {
        this.setLayout(null);
        this.setBounds(0,0, SizeConstants.BATTLE_SQUAD_PANEL_WIDTH, SizeConstants.BATTLE_SQUAD_PANEL_HEIGHT);
        this.setBackground(Color.BLACK);
        this.setVisible(false);
        wUnit = getWidth() / 8;
        hUnit = getHeight() / 8;
        hHelperUnit = getHeight() * 5 / 64;
        updater = new Timer(RefreshRateConstants.SQUAD_BATTLE_UPDATE_REFRESH_RATE, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ClientUpdateBattleSquadRequest(
                        enemySquadName,
                        mySquadMembers,
                        me,
                        enemySquadMembers
                ).sendRequest();
            }
        });

        initBattleL();
        initTeamLs();
        initJScrollPanes();
        initBack();
        initDonate();
        initAls();
    }

    private void initAls() {
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                end();
                MainFrame.squadTreasuryPanel.setHasSquadBattle(true);
                MainFrame.hasSquadPanel.start();
            }
        });

        treasury.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                end();
                MainFrame.squadTreasuryPanel.setHasSquadBattle(true);
                MainFrame.squadTreasuryPanel.start();
            }
        });
    }

    private void initDonate() {
        treasury = new MyButton(
                new Point((int) (wUnit * 1.5) ,hHelperUnit * 6 + hUnit * 3),
                new Dimension(wUnit ,hHelperUnit),
                "treasury",
                this
        );
        treasury.setBorder(BorderFactory.createLineBorder(Color.MAGENTA ,2));
        treasury.setForeground(Color.MAGENTA);
    }

    private void initBack() {
        back = new MyButton(
                new Point((int) (wUnit * 5.5) ,hHelperUnit * 6 + hUnit * 3),
                new Dimension(wUnit ,hHelperUnit),
                "back",
                this
        );
    }

    private void initJScrollPanes() {
        mySquadContainer = new JPanel();
        mySquadContainer.setLayout(null);
        mySquadContainer.setBackground(Color.BLACK);
        mySquad = new JScrollPane(
                mySquadContainer,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED ,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
        );
        mySquad.setOpaque(false);
        mySquad.setBounds(
                (int) (wUnit * 0.5),
                 hHelperUnit * 5,
                wUnit * 3 ,
                hUnit * 3
        );
        mySquad.createVerticalScrollBar();
        mySquad.createHorizontalScrollBar();
        mySquad.getVerticalScrollBar().setOpaque(false);
        mySquad.getVerticalScrollBar().setForeground(Color.CYAN);
        mySquad.setForeground(Color.CYAN);
        this.add(mySquad);

        /////////////////////////////////

        enemySquadContainer = new JPanel();
        enemySquadContainer.setLayout(null);
        enemySquadContainer.setBackground(Color.BLACK);
        enemySquad = new JScrollPane(
                enemySquadContainer,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED ,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
        );
        enemySquad.setOpaque(false);
        enemySquad.setBounds(
                (int) (wUnit * 4.5),
                hHelperUnit * 5,
                wUnit * 3 ,
                hUnit * 3
        );
        enemySquad.createVerticalScrollBar();
        enemySquad.createHorizontalScrollBar();
        enemySquad.getVerticalScrollBar().setOpaque(false);
        enemySquad.getVerticalScrollBar().setForeground(Color.CYAN);
        enemySquad.setForeground(Color.CYAN);
        this.add(enemySquad);
    }

    private void initTeamLs() {
        myTeamL = new MyLabel(
                new Point((int) (wUnit * 1.5),hHelperUnit * 3),
                new Dimension(wUnit ,hHelperUnit),
                "your squad",
                this
        );
        enemyTeamL = new MyLabel(
                new Point((int) (wUnit * 5.5),hHelperUnit * 3),
                new Dimension(wUnit ,hHelperUnit),
                "enemy squad",
                this
        );
    }

    private void initBattleL() {
        battleL = new MyLabel(
                new Point(wUnit * 3 ,hHelperUnit),
                new Dimension(wUnit * 2 ,hHelperUnit),
                "battle squad!",
                this
        );
    }

    @Override
    public void start() {
        updater.start();
        Application.mainFrame.setSize(
                SizeConstants.BATTLE_SQUAD_PANEL_WIDTH + SizeConstants.barD.width,
                SizeConstants.BATTLE_SQUAD_PANEL_HEIGHT + SizeConstants.barD.height
        );
        Application.mainFrame.setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void end() {
        updater.stop();
        Application.mainFrame.setSize(
                SizeConstants.GAME_WIDTH + SizeConstants.barD.width,
                SizeConstants.GAME_HEIGHT + SizeConstants.barD.height
        );
        Application.mainFrame.setLocationRelativeTo(null);
        setVisible(false);
    }

    public void update(String enemySquadName, ArrayList<GetBattleSquadMemberHelper> mySquadMembers,
                       GetBattleSquadMemberHelper me, ArrayList<GetBattleSquadMemberHelper> enemySquadMembers)
    {
        this.enemySquadName = enemySquadName;
        this.mySquadMembers = mySquadMembers;
        this.me = me;
        this.enemySquadMembers = enemySquadMembers;

        enemyTeamL.setText(enemySquadName);
        updateMySquad();
        updateEnemySquad();
        revalidate();
        repaint();
    }

    private void updateEnemySquad() {
        enemySquadContainer.removeAll();
        GridLayout gridLayout = new GridLayout(enemySquadMembers.size() + 1, 1, 2, 2);
        enemySquadContainer.setLayout(gridLayout);
        setMainInfo(enemySquadContainer);
        for (GetBattleSquadMemberHelper otherMember : enemySquadMembers) {
            addMember(otherMember ,enemySquadContainer ,false);
        }
    }

    private void updateMySquad() {
        mySquadContainer.removeAll();
        GridLayout gridLayout = new GridLayout(mySquadMembers.size() + 2, 1, 2, 2);
        mySquadContainer.setLayout(gridLayout);
        setMainInfo(mySquadContainer);
        addMember(me ,mySquadContainer ,true);
        for (GetBattleSquadMemberHelper otherMember : mySquadMembers) {
            addMember(otherMember ,mySquadContainer ,true);
        }
    }

    private void setMainInfo(JPanel container) {
        MyPanel myPanel = new MyPanel(
                new Point(),
                new Dimension(),
                container
        );
        myPanel.setLayout(new GridLayout(1 ,4 ,2 ,2));
        new JScrollerLabel("username" ,Color.WHITE ,myPanel);
        new JScrollerLabel("xp" ,Color.WHITE ,myPanel);
        new JScrollerLabel("state" ,Color.WHITE ,myPanel);
        new JScrollerLabel("battle" ,Color.WHITE ,myPanel);
    }

    private void addMember(GetBattleSquadMemberHelper player ,JPanel container ,boolean isMySquad) {
        MyPanel myPanel = new MyPanel(
                new Point(),
                new Dimension(),
                container
        );
        myPanel.setLayout(new GridLayout(1 ,4 ,2 ,2));
        new JScrollerLabel(player.getUsername() ,Color.WHITE ,myPanel);
        new JScrollerLabel(player.getXp() + "" ,Color.MAGENTA ,myPanel);
        if (player.getClientState().equals(ClientState.online)) {
            new JScrollerLabel(player.getClientState().toString() ,Color.GREEN ,myPanel);
        }
        else if (player.getClientState().equals(ClientState.busy)) {
            new JScrollerLabel(player.getClientState().toString() ,Color.ORANGE ,myPanel);
        }
        else {
            new JScrollerLabel(player.getClientState().toString() ,Color.RED ,myPanel);
        }
        MyButton battleButton;
        if (isMySquad) {
            battleButton = new MyButton(
                    new Point(),
                    new Dimension(),
                    "colosseum",
                    myPanel
            );
            if (!player.getClientState().equals(ClientState.online) || player.playedColosseum())
                battleButton.setEnabled(false);
        }
        else {
            battleButton = new MyButton(
                    new Point(),
                    new Dimension(),
                    "monomachia",
                    myPanel
            );
            if (!player.getClientState().equals(ClientState.online) || player.playedMonomachia())
                battleButton.setEnabled(false);
        }
    }

}
