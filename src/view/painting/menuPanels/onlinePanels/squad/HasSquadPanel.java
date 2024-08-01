package view.painting.menuPanels.onlinePanels.squad;

import constants.SizeConstants;
import controller.online.tcp.ClientState;
import controller.online.tcp.requests.getSquadMembers.GetSquadMembersJsonHelper;
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

public class HasSquadPanel extends PIG {

    private MyLabel squadName;
    private JScrollPane jScrollPane;
    private JPanel container;
    private MyButton leaveSquad;
    private MyButton back;
    private MyButton battleSquad;

    public HasSquadPanel() {
        this.setLayout(null);
        this.setBounds(0,0, SizeConstants.GAME_WIDTH, SizeConstants.GAME_HEIGHT);
        this.setBackground(Color.BLACK);
        this.setVisible(false);

        initSquadName();
        initJScrollPane();
        initLeaveSquad();
        initBack();
        initBattleSquad();
        initAls();
    }

    private void initAls() {
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                end();
                MainFrame.menuPanel.start();
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
                new Point(getWidth() / 5 ,getHeight() / 20 * 18),
                new Dimension(getWidth() / 5 ,getHeight() / 20),
                "battle squad",
                this
        );
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

    public void update(ArrayList<GetSquadMembersJsonHelper> otherMembers, GetSquadMembersJsonHelper thisPlayer) {
        container.removeAll();
        GridLayout gridLayout = new GridLayout(otherMembers.size() + 2 ,1 ,2 ,2);
        container.setLayout(gridLayout);
        setMainInfo();
        addMember(thisPlayer);
        for (GetSquadMembersJsonHelper otherMember : otherMembers) {
            addMember(otherMember);
        }
        revalidate();
        repaint();
    }

    private void setMainInfo() {
        MyPanel myPanel = new MyPanel(
                new Point(),
                new Dimension(),
                container
        );
        myPanel.setLayout(new GridLayout(1 ,3 ,2 ,2));
        new JScrollerLabel("username" ,Color.WHITE ,myPanel);
        new JScrollerLabel("xp" ,Color.WHITE ,myPanel);
        new JScrollerLabel("state" ,Color.WHITE ,myPanel);
    }

    private void addMember(GetSquadMembersJsonHelper player) {
        MyPanel myPanel = new MyPanel(
                new Point(),
                new Dimension(),
                container
        );
        myPanel.setLayout(new GridLayout(1 ,3 ,2 ,2));
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
    }
}
