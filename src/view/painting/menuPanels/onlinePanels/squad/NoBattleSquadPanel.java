package view.painting.menuPanels.onlinePanels.squad;

import constants.SizeConstants;
import controller.online.tcp.requests.getTreasuryInfo.ClientUpdateTreasuryRequest;
import view.painting.menuPanels.MainFrame;
import view.painting.menuPanels.PIG;
import view.painting.objectViews.panels.MyButton;
import view.painting.objectViews.panels.MyLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NoBattleSquadPanel extends PIG {
    private MyLabel battleL;
    private MyButton back;
    private MyButton treasury;

    public NoBattleSquadPanel() {
        this.setLayout(null);
        this.setBounds(0,0, SizeConstants.GAME_WIDTH, SizeConstants.GAME_HEIGHT);
        this.setBackground(Color.BLACK);
        this.setVisible(false);

        initBattleL();
        initBack();
        initDonate();
        initAls();
    }

    private void initAls() {
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                end();
                MainFrame.hasSquadPanel.start();
            }
        });

        treasury.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                end();
                MainFrame.squadTreasuryPanel.setHasSquadBattle(false);
                MainFrame.squadTreasuryPanel.start();
            }
        });
    }

    private void initDonate() {
        treasury = new MyButton(
                new Point(getWidth() / 5 ,getHeight() / 7 * 5),
                new Dimension(getWidth() / 5 ,getHeight() / 7),
                "treasury",
                this
        );
        treasury.setBorder(BorderFactory.createLineBorder(Color.MAGENTA ,2));
        treasury.setForeground(Color.MAGENTA);
    }

    private void initBack() {
        back = new MyButton(
                new Point(getWidth() / 5 * 3 ,getHeight() / 7 * 5),
                new Dimension(getWidth() / 5 ,getHeight() / 7),
                "back",
                this
        );
    }

    private void initBattleL() {
        battleL = new MyLabel(
                new Point(getWidth() / 3 ,getHeight() / 7),
                new Dimension(getWidth() / 3 ,getHeight() / 7),
                "battle squad!",
                this
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
