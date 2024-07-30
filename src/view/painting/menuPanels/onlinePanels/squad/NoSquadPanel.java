package view.painting.menuPanels.onlinePanels.squad;

import constants.CostConstants;
import constants.SizeConstants;
import controller.configs.Configs;
import controller.online.tcp.getAllSquadsRequest.ClientGetAllSquadsRequest;
import controller.online.tcp.getAllSquadsRequest.GetAllSquadHelper;
import view.painting.menuPanels.PIG;
import view.painting.objectViews.panels.MyButton;
import view.painting.objectViews.panels.MyLabel;

import javax.swing.*;
import java.awt.*;
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
                "you don't have any squad right now!",
                this
        );
    }

    private void initJScrollPane() {
        container = new JPanel();
        container.setBounds(
                getWidth() / 5 * 3 ,
                getHeight() / 5 * 3,
                0,
                0
        );
        container.setLayout(new GridLayout(1 ,4 ,10 ,50));
        container.setOpaque(false);
        container.setBackground(Color.RED);
        jScrollPane = new JScrollPane(
                container,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED ,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
        );
        jScrollPane.setBounds(
                getWidth() / 5 ,
                getHeight() / 15 * 3 ,
                getWidth() / 5 * 3 ,
                getHeight() / 5 * 3
        );
        jScrollPane.setBackground(Color.BLACK);
        jScrollPane.setOpaque(false);
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
        for (GetAllSquadHelper squad : squads) {
            new MyLabel(
                    new Point(),
                    new Dimension(),
                    squad.getName(),
                    container
            );
            new MyLabel(
                    new Point(),
                    new Dimension(),
                    squad.getMemberCount(),
                    container
            );
        }
    }
}
