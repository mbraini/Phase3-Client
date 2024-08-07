package view.painting.menuPanels.onlinePanels.squad;

import constants.SizeConstants;
import controller.online.tcp.requests.ClientDonateXPRequest;
import controller.online.tcp.requests.ClientUpdateTreasuryRequest;
import view.painting.menuPanels.MainFrame;
import view.painting.menuPanels.PIG;
import view.painting.objectViews.panels.MyButton;
import view.painting.objectViews.panels.MyLabel;
import view.painting.objectViews.panels.MyText;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SquadTreasuryPanel extends PIG {

    private int xp;
    private MyLabel xpL;
    private MyLabel donateL;
    private MyText xpText;
    private MyButton donate;
    private int palioxisCount;
    private int adonisCount;
    private int gefjonCount;
    private MyLabel palioxisCountL;
    private MyLabel adonisCountL;
    private MyLabel gefjonCountL;
    private MyButton treasuryShop;
    private MyButton back;
    private boolean hasSquadBattle;

    public SquadTreasuryPanel() {
        this.setLayout(null);
        this.setBounds(0,0, SizeConstants.GAME_WIDTH, SizeConstants.GAME_HEIGHT);
        this.setBackground(Color.BLACK);
        this.setVisible(false);

        initXP();
        initDonatePart();
        initCallCountPart();
        initCallShopPart();
        initBack();
        initALs();
    }

    private void initShop() {

    }

    private void initALs() {
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                end();
                if (hasSquadBattle)
                    MainFrame.hasBattleSquadPanel.start();
                else
                    MainFrame.noBattleSquadPanel.start();
            }
        });

        treasuryShop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                end();
                MainFrame.squadTreasuryShopPanel.start();
            }
        });

        xpText.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
                    xpText.setEditable(true);
                } else {
                    xpText.setEditable(false);
                    xpText.setText("");
                }
            }
        });

        donate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String donatedXP = xpText.getText();
                if (!donatedXP.isEmpty()) {
                    new ClientDonateXPRequest(donatedXP).sendRequest();
                }
            }
        });
    }

    private void initBack() {
        back = new MyButton(
                new Point(getWidth() / 5 * 2 ,getHeight() / 11 * 9),
                new Dimension(getWidth() / 5 ,getHeight() / 11),
                "back",
                this
        );
    }

    private void initCallShopPart() {
        treasuryShop = new MyButton(
                new Point(getWidth() / 5 * 2 ,getHeight() / 11 * 7),
                new Dimension(getWidth() / 5 ,getHeight() / 11),
                "treasury shop",
                this
        );
    }

    private void initCallCountPart() {
        palioxisCountL = new MyLabel(
                new Point(getWidth() / 28 ,getHeight() / 11 * 5),
                new Dimension(getWidth() / 7 * 2 ,getHeight() / 11),
                "palioxis: " + "X" + palioxisCount,
                this
        );
        adonisCountL = new MyLabel(
                new Point(getWidth() / 28 * 10 ,getHeight() / 11 * 5),
                new Dimension(getWidth() / 7 * 2 ,getHeight() / 11),
                "palioxis: " + "X" + adonisCount,
                this
        );
        gefjonCountL = new MyLabel(
                new Point(getWidth() / 28 * 19 ,getHeight() / 11 * 5),
                new Dimension(getWidth() / 7 * 2 ,getHeight() / 11),
                "palioxis: " + "X" + gefjonCount,
                this
        );
    }

    private void initXP() {
        xpL = new MyLabel(
                new Point(getWidth() / 5 * 2 ,getHeight() / 11),
                new Dimension(getWidth() / 5 ,getHeight() / 11),
                "squad's xp: " + xp,
                this
        );
        xpL.setForeground(Color.MAGENTA);
        xpL.setBorder(BorderFactory.createLineBorder(Color.MAGENTA ,2));
    }

    private void initDonatePart() {
        donateL = new MyLabel(
                new Point(getWidth() / 14 ,getHeight() / 11 * 3),
                new Dimension(getWidth() / 7 * 2 ,getHeight() / 11),
                "donate xpL here",
                this
        );
        xpText = new MyText(
                new Point(getWidth() / 7 * 3 ,getWidth() / 11 * 3),
                new Dimension(getWidth() / 7 ,getHeight() / 11),
                this
        );
        donate = new MyButton(
                new Point(getWidth() / 7 * 5 ,getHeight() / 11 * 3),
                new Dimension(getWidth() / 7 ,getHeight() / 11),
                "donate",
                this
        );
        donate.setBorder(BorderFactory.createLineBorder(Color.MAGENTA ,2));
        donate.setForeground(Color.MAGENTA);
    }

    @Override
    public void start() {
        new ClientUpdateTreasuryRequest().sendRequest();
        setVisible(true);
    }

    @Override
    public void end() {
        setVisible(false);
    }

    public boolean isHasSquadBattle() {
        return hasSquadBattle;
    }

    public void setHasSquadBattle(boolean hasSquadBattle) {
        this.hasSquadBattle = hasSquadBattle;
    }

    public void update(boolean isOwner ,int xp ,int palioxisCount ,int adonisCount ,int gefjonCount) {
        if (isOwner)
            treasuryShop.setVisible(true);
        else
            treasuryShop.setVisible(false);
        xpL.setText("squad's xp: " + xp);
        palioxisCountL.setText("palioxis: " + "X" + palioxisCount);
        adonisCountL.setText("adonis: " + "X" + adonisCount);
        gefjonCountL.setText("gefjon: " + "X" + gefjonCount);
        revalidate();
        repaint();
    }

}
