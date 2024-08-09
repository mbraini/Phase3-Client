package view.painting.menuPanels.onlinePanels.squad;

import constants.CostConstants;
import constants.SizeConstants;
import controller.configs.Configs;
import controller.online.tcp.requests.ClientJoinSquadRequest;
import controller.online.tcp.requests.getAllSquadsRequest.ClientGetAllSquadsRequest;
import controller.online.tcp.requests.getAllSquadsRequest.GetAllSquadHelper;
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

public class NoSquadPanel extends PIG {

    private MyLabel youHaveNoSquad;
    private MyButton createNewSquad;
    private MyButton back;
    private JScrollPane jScrollPane;
    private JPanel container;
    private JPanel lastClicked;
    private HashMap<MyButton ,String> buttonSquadMap;
    private HashMap<JPanel ,MyButton> panelButtonMap;

    public NoSquadPanel() {
        this.setLayout(null);
        this.setBounds(0,0, SizeConstants.GAME_WIDTH, SizeConstants.GAME_HEIGHT);
        this.setBackground(Color.BLACK);
        this.setVisible(false);

        initNoSquad();
        initBack();
        initJoinRequest();
        initCreateNewSquad();
        initJScrollPane();
        initMaps();
        initAL();
    }

    private void initBack() {
        back = new MyButton(
                new Point(getWidth() / 5 ,getHeight() / 15 * 13),
                new Dimension(getWidth() / 5 ,getHeight() / 15),
                "back",
                this
        );
    }

    private void initMaps() {
        panelButtonMap = new HashMap<>();
        buttonSquadMap = new HashMap<>();
    }

    private void initAL() {
        createNewSquad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                end();
                MainFrame.createNewSquadPanel.start();
            }
        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                end();
                MainFrame.menuPanel.start();
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
        buttonSquadMap = new HashMap<>();

        GridLayout gridLayout = new GridLayout(squads.size() + 1 ,1 ,2 ,2);
        container.setLayout(gridLayout);
        setMainPanel();
        for (GetAllSquadHelper squad : squads) {
            MyPanel myPanel = new MyPanel(
                    new Point(),
                    new Dimension(),
                    container
            );
            myPanel.setOpaque(true);
            myPanel.setBackground(Color.BLACK);
            myPanel.setLayout(new GridLayout(1 ,3 ,2 ,2));
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
            myPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (lastClicked != null) {
                        panelButtonMap.get(lastClicked).setVisible(false);
                    }
                    panelButtonMap.get(myPanel).setVisible(true);
                    lastClicked = myPanel;
                }
            });
            JoinRequestButton joinRequestButton = new JoinRequestButton(
                    new Point(),
                    new Dimension(),
                    "join",
                    myPanel,
                    squad.getName()
            );
            buttonSquadMap.put(joinRequestButton ,squad.getName());
            joinRequestButton.setVisible(false);
            panelButtonMap.put(myPanel ,joinRequestButton);
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

    private class JoinRequestButton extends MyButton{
        public JoinRequestButton(Point position, Dimension size, String text, JPanel panel ,String squadName) {
            super(position, size, text, panel);
            setForeground(Color.GREEN);
            setBorder(BorderFactory.createLineBorder(Color.GREEN ,2));
            buttonSquadMap.put(this ,squadName);
            addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    MyButton jPanel = panelButtonMap.get(lastClicked);
                    new ClientJoinSquadRequest(buttonSquadMap.get(jPanel)).sendRequest();
                }
            });
        }
    }

}
