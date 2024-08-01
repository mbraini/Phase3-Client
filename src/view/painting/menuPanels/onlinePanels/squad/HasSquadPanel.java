package view.painting.menuPanels.onlinePanels.squad;

import constants.SizeConstants;
import controller.online.tcp.ClientState;
import controller.online.tcp.requests.getSquadMembers.GetSquadMembersJsonHelper;
import controller.online.tcp.requests.kickOutRequest.ClientKickOutRequest;
import controller.online.tcp.requests.leaveSquad.ClientLeaveSquadRequest;
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
import java.util.HashMap;

public class HasSquadPanel extends PIG {

    private MyLabel squadName;
    private JScrollPane jScrollPane;
    private JPanel container;
    private MyButton leaveSquad;
    private MyButton back;
    private MyButton killSquad;
    private MyButton kickOut;
    private MyButton removeSquad;
    private String ownerUsername;
    private JPanel lastClicked;
    private HashMap<JPanel ,MyButton> panelButtonMap;
    private HashMap<MyButton ,String> buttonMemberMap;

    public HasSquadPanel() {
        this.setLayout(null);
        this.setBounds(0,0, SizeConstants.GAME_WIDTH, SizeConstants.GAME_HEIGHT);
        this.setBackground(Color.BLACK);
        this.setVisible(false);

        initSquadName();
        initJScrollPane();
        initLeaveSquad();
        initBack();
        initKickOut();
        initKillSquad();
        initBattleSquad();
        initMaps();
        initAls();
    }

    private void initMaps() {
        panelButtonMap = new HashMap<>();
        buttonMemberMap = new HashMap<>();
    }

    private void initKillSquad() {
        killSquad = new MyButton(
                new Point(),
                new Dimension(),
                "kill squad",
                this
        );
    }

    private void initKickOut() {
        kickOut = new MyButton(
                new Point(),
                new Dimension(),
                "kickOut",
                this
        );
    }

    private void initAls() {
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                end();
                MainFrame.menuPanel.start();
            }
        });

        leaveSquad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int answer = JOptionPane.showConfirmDialog(
                        null ,
                        "are you sure that you want to leave the squad?",
                        "",
                        0
                );
                if (answer == 0)
                    new ClientLeaveSquadRequest().sendRequest();
            }
        });

    }

    private void initSquadName() {
        squadName = new MyLabel(
                new Point(getWidth() / 5 * 2 ,getHeight() / 20),
                new Dimension(getWidth() / 5 ,getHeight() / 20),
                "squad name",
                this
        );
    }

    private void initBattleSquad() {
        killSquad = new MyButton(
                new Point(getWidth() / 5 ,getHeight() / 20 * 18),
                new Dimension(getWidth() / 5 ,getHeight() / 20),
                "battle squad",
                this
        );
        killSquad.setVisible(false);
    }

    private void initBack() {
        back = new MyButton(
                new Point(getWidth() / 5 * 3 ,getHeight() / 20 * 18),
                new Dimension(getWidth() / 5 ,getHeight() / 20),
                "back",
                this
        );
    }

    private void initLeaveSquad() {

        leaveSquad = new MyButton(
                new Point(getWidth() / 5 * 2 ,getHeight() / 20 * 16),
                new Dimension(getWidth() / 5 ,getHeight() / 20),
                "leaveSquad",
                this
        );
        leaveSquad.setForeground(new Color(255 ,0 ,0));

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
                getWidth() / 5 ,
                getHeight() / 20 * 3 ,
                getWidth() / 5 * 3 ,
                getHeight() / 5 * 3
        );
        jScrollPane.createVerticalScrollBar();
        jScrollPane.createHorizontalScrollBar();
        jScrollPane.getVerticalScrollBar().setOpaque(false);
        jScrollPane.getVerticalScrollBar().setForeground(Color.CYAN);
        jScrollPane.setForeground(Color.CYAN);
        this.add(jScrollPane);
    }

    @Override
    public void start() {
        setVisible(true);
    }

    @Override
    public void end() {
        setVisible(false);
    }

    public void update(ArrayList<GetSquadMembersJsonHelper> otherMembers, GetSquadMembersJsonHelper thisPlayer
            ,boolean clientIsOwner)
    {
        container.removeAll();
        GridLayout gridLayout = new GridLayout(otherMembers.size() + 2, 1, 2, 2);
        container.setLayout(gridLayout);
        setMainInfo(clientIsOwner);
        addMember(thisPlayer , clientIsOwner);
        if (clientIsOwner)
            ownerUsername = thisPlayer.getUsername();
        for (GetSquadMembersJsonHelper otherMember : otherMembers) {
            addMember(otherMember , clientIsOwner);
        }
        if (clientIsOwner) {
            kickOut.setVisible(true);
            killSquad.setVisible(true);
        }
        else {
            kickOut.setVisible(false);
            killSquad.setVisible(false);
        }
        revalidate();
        repaint();
    }

    private void setMainInfo(boolean isOwner) {
        MyPanel myPanel = new MyPanel(
                new Point(),
                new Dimension(),
                container
        );
        myPanel.setLayout(new GridLayout(1 ,4 ,2 ,2));
        new JScrollerLabel("username" ,Color.WHITE ,myPanel);
        new JScrollerLabel("xp" ,Color.WHITE ,myPanel);
        new JScrollerLabel("state" ,Color.WHITE ,myPanel);
        if (isOwner) {
            removeSquad = new MyButton(
                    new Point(),
                    new Dimension(),
                    "kill squad",
                    myPanel
            );
            removeSquad.setForeground(Color.RED);
            removeSquad.setBorder(BorderFactory.createLineBorder(Color.RED ,2));
            removeSquad.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ////todo
                }
            });
        }
    }

    private void addMember(GetSquadMembersJsonHelper player ,boolean clientIsOwner) {
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
        if (clientIsOwner) {
            KickOutRequestButton kick = new KickOutRequestButton(
                    new Point(),
                    new Dimension(),
                    "kickout",
                    myPanel,
                    player.getUsername()
            );
            kick.setVisible(false);
            panelButtonMap.put(myPanel, kick);
            myPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (lastClicked != null) {
                        panelButtonMap.get(lastClicked).setVisible(false);
                    }
                    if (buttonMemberMap.get(panelButtonMap.get(myPanel)).equals(ownerUsername))
                        return;
                    panelButtonMap.get(myPanel).setVisible(true);
                    lastClicked = myPanel;
                }
            });
        }
    }
    private class KickOutRequestButton extends MyButton{
        public KickOutRequestButton(Point position, Dimension size, String text, JPanel panel ,String memberName) {
            super(position, size, text, panel);
            setForeground(Color.RED);
            setBorder(BorderFactory.createLineBorder(Color.RED ,2));
            buttonMemberMap.put(this ,memberName);
            addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    MyButton button = panelButtonMap.get(lastClicked);
                    new ClientKickOutRequest(buttonMemberMap.get(button)).sendRequest();
                }
            });
        }
    }

}
