package view.painting.menuPanels.onlinePanels.squad;

import constants.RefreshRateConstants;
import constants.SizeConstants;
import controller.online.tcp.ClientState;
import controller.online.tcp.requests.ClientHasSquadBattleRequest;
import controller.online.tcp.requests.updateNoSquadPanel.GetSquadMembersJsonHelper;
import controller.online.tcp.requests.ClientHasSquadRequest;
import controller.online.tcp.requests.ClientKickOutRequest;
import controller.online.tcp.requests.ClientKillSquadRequest;
import controller.online.tcp.requests.ClientLeaveSquadRequest;
import controller.online.tcp.requests.updateNoSquadPanel.ClientUpdateHasSquadRequest;
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
    private MyButton kickOut;
    private MyButton killSquad;
    private MyButton battleSquad;
    private String ownerUsername;
    private JPanel lastClicked;
    private HashMap<JPanel ,MyButton> panelButtonMap;
    private HashMap<MyButton ,String> buttonMemberMap;
    private ArrayList<GetSquadMembersJsonHelper> members = new ArrayList<>();
    private GetSquadMembersJsonHelper thisPlayer = new GetSquadMembersJsonHelper();
    private final Timer updater;

    public HasSquadPanel() {
        this.setLayout(null);
        this.setBounds(0,0, SizeConstants.GAME_WIDTH, SizeConstants.GAME_HEIGHT);
        this.setBackground(Color.BLACK);
        this.setVisible(false);

        updater = new Timer(RefreshRateConstants.HAS_SQUAD_REFRESH_RATE, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ClientUpdateHasSquadRequest(members ,thisPlayer).sendRequest();
                jScrollPane.revalidate();
                jScrollPane.repaint();
            }
        });

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
                if (answer == 0) {
                    end();
                    MainFrame.menuPanel.start();
                    new ClientLeaveSquadRequest().sendRequest();
                }
            }
        });

        battleSquad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ClientHasSquadBattleRequest().sendRequest();
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
        battleSquad = new MyButton(
                new Point(getWidth() / 5 * 3 ,getHeight() / 20 * 18),
                new Dimension(getWidth() / 5 ,getHeight() / 20),
                "battle squad",
                this
        );
    }

    private void initBack() {
        back = new MyButton(
                new Point(getWidth() / 5 ,getHeight() / 20 * 18),
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
        updater.start();
        setVisible(true);
    }

    @Override
    public void end() {
        updater.stop();
        setVisible(false);
    }

    public void update(ArrayList<GetSquadMembersJsonHelper> otherMembers, GetSquadMembersJsonHelper thisPlayer
            , String squadName ,boolean clientIsOwner)
    {
        this.members = otherMembers;
        this.thisPlayer = thisPlayer;

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
        this.squadName.setText(squadName);
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
            killSquad = new MyButton(
                    new Point(),
                    new Dimension(),
                    "kill squad",
                    myPanel
            );
            killSquad.setForeground(Color.RED);
            killSquad.setBorder(BorderFactory.createLineBorder(Color.RED ,2));
            killSquad.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int choice = JOptionPane.showConfirmDialog(
                            null ,
                            "are you sure that you want to kill the squad?" ,
                            "" ,
                            0
                    );
                    if (choice == 0) {
                        end();
                        new ClientKillSquadRequest(squadName.getText()).sendRequest();
                        new ClientHasSquadRequest().sendRequest();
                    }
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
