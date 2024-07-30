package view.painting.menuPanels.onlinePanels.squad;

import constants.CostConstants;
import constants.SizeConstants;
import controller.configs.Configs;
import controller.online.tcp.getAllSquadsRequest.ClientGetAllSquadsRequest;
import controller.online.tcp.getAllSquadsRequest.GetAllSquadHelper;
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

public class NoSquadPanel extends PIG {

    private MyLabel youHaveNoSquad;
    private MyButton joinRequest;
    private MyButton createNewSquad;
    private JScrollPane jScrollPane;
    private JPanel container;

    public NoSquadPanel() {
        this.setLayout(null);
        this.setBounds(0,0, SizeConstants.GAME_WIDTH, SizeConstants.GAME_HEIGHT);
        this.setBackground(Color.BLACK);
        this.setVisible(false);

        initNoSquad();
        initJoinRequest();
        initCreateNewSquad();
        initJScrollPane();
        initAL();
    }

    private void initAL() {
        createNewSquad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                end();
                MainFrame.createNewSquadPanel.start();
            }
        });
    }

    private void initCreateNewSquad() {
        createNewSquad = new MyButton(
                new Point(getWidth() / 5 * 3 ,getHeight() / 15 * 13),
                new Dimension(getWidth() / 5 ,getHeight() / 15),
                "create new squad",
                this
        );
    }

    private void initJoinRequest() {
        joinRequest = new MyButton(
                new Point(getWidth() / 5 ,getHeight() / 15 * 13),
                new Dimension(getWidth() / 5 ,getHeight() / 15),
                "join request",
                this
        );
    }

    private void initNoSquad() {
        youHaveNoSquad = new MyLabel(
                new Point(getWidth() / 4 ,getHeight() / 15),
                new Dimension(getWidth() / 2 ,getHeight() / 15),
                "you don't have a squad right now!",
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
//        container.setLayout(new GridLayout(5 ,2 ,10 ,50));
        container.setBackground(Color.BLACK);
        jScrollPane = new JScrollPane(
                container,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED ,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
        );
        jScrollPane.setOpaque(false);
        jScrollPane.setBounds(
                getWidth() / 5 ,
                getHeight() / 15 * 3 ,
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
        joinRequest.setEnabled(false);
        if (Configs.GameConfigs.XP <= CostConstants.SQUAD_XP_COST) {
            createNewSquad.setEnabled(false);
        }
        else {
            createNewSquad.setEnabled(true);
        }
        new ClientGetAllSquadsRequest().sendRequest();
    }

    @Override
    public void end() {
        setVisible(false);
    }

    public void updateSquads(ArrayList<GetAllSquadHelper> squads) {
        container.removeAll();
        GridLayout gridLayout = new GridLayout(squads.size() + 1 ,1 ,2 ,2);
        setMainPanel();
        container.setLayout(gridLayout);
        for (GetAllSquadHelper squad : squads) {
            MyPanel myPanel = new MyPanel(
                    new Point(),
                    new Dimension(),
                    container
            );
            myPanel.setLayout(new GridLayout(1 ,2 ,2 ,2));
            new JScrollerLabel(
                    squad.getName(),
                    Color.PINK,
                    myPanel
            );
            new JScrollerLabel(
                    squad.getMemberCount() + "",
                    Color.GREEN,
                    myPanel
            );
        }
    }

    private void setMainPanel() {
        MyPanel myPanel = new MyPanel(
                new Point(),
                new Dimension(),
                container
        );
        myPanel.setLayout(new GridLayout(1 ,2 ,2 ,2));
        new JScrollerLabel(
                "squad's name ",
                Color.WHITE,
                myPanel
        );
        new JScrollerLabel(
                "squad's member count ",
                Color.WHITE,
                myPanel
        );
    }
}
