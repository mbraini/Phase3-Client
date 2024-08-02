package view.painting.menuPanels.onlinePanels.squad;

import constants.CostConstants;
import constants.SizeConstants;
import view.painting.menuPanels.MainFrame;
import view.painting.menuPanels.PIG;
import view.painting.objectViews.panels.MyButton;
import view.painting.objectViews.panels.MyLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SquadTreasuryShopPanel extends PIG {

    private int xp;
    private MyLabel xpL;
    private MyButton palioxisBuy;
    private  MyButton adonisBuy;
    private MyButton gefjonBuy;
    private MyButton palioxisXPL;
    private  MyButton adonisXPL;
    private MyButton gefjonXPL;
    private MyLabel treasuryShop;
    private int palioxisXP;
    private final int adonisXP = CostConstants.ADONIS_XP_COST;
    private final int gefjonXP = CostConstants.GEFJON_XP_COST;
    private MyButton back;

    public SquadTreasuryShopPanel() {
        this.setLayout(null);
        this.setBounds(0,0, SizeConstants.GAME_WIDTH, SizeConstants.GAME_HEIGHT);
        this.setBackground(Color.BLACK);
        this.setVisible(false);

        initTreasuryShop();
        initShopPart();
        initXPS();
        initBack();
        initALs();
    }

    private void initALs() {
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                end();
                MainFrame.squadTreasuryPanel.start();
            }
        });
    }

    private void initTreasuryShop() {
        treasuryShop = new MyLabel(
                new Point(getWidth() / 5 ,getHeight() / 11),
                new Dimension(getWidth() / 5 ,getHeight() / 11),
                "treasury shop",
                this
        );
        xpL = new MyLabel(
                new Point(getWidth() / 5 * 3 ,getHeight() / 11),
                new Dimension(getWidth() / 5 ,getHeight() / 11),
                "squad's xp: " + xp,
                this
        );
        xpL.setForeground(Color.MAGENTA);
        xpL.setBorder(BorderFactory.createLineBorder(Color.MAGENTA ,2));
    }

    private void initBack() {
        back = new MyButton(
                new Point(getWidth() / 5 * 2 ,getHeight() / 11 * 9),
                new Dimension(getWidth() / 5 ,getHeight() / 11),
                "back",
                this
        );
    }

    private void initXPS() {
        palioxisXPL = new MyButton(
                new Point(getWidth() / 28 ,getHeight() / 11 * 6),
                new Dimension(getWidth() / 7 * 2 ,getHeight() / 11),
                "" + palioxisXP,
                this
        );

        adonisXPL = new MyButton(
                new Point(getWidth() / 28 * 10 ,getHeight() / 11 * 6),
                new Dimension(getWidth() / 7 * 2 ,getHeight() / 11),
                "" + adonisXP,
                this
        );

        gefjonXPL = new MyButton(
                new Point(getWidth() / 28 * 19,getHeight() / 11 * 6),
                new Dimension(getWidth() / 7 * 2 ,getHeight() / 11),
                "" + gefjonXP,
                this
        );

        palioxisXPL.setBorder(null);
        palioxisXPL.setForeground(Color.MAGENTA);
        adonisXPL.setBorder(null);
        adonisXPL.setForeground(Color.MAGENTA);
        gefjonXPL.setBorder(null);
        gefjonXPL.setForeground(Color.MAGENTA);
    }

    private void initShopPart() {
        palioxisBuy = new MyButton(
                new Point(getWidth() / 28 ,getHeight() / 11 * 5),
                new Dimension(getWidth() / 7 * 2 ,getHeight() / 11),
                "buy palioxis",
                this
        );

        adonisBuy = new MyButton(
                new Point(getWidth() / 28 * 10 ,getHeight() / 11 * 5),
                new Dimension(getWidth() / 7 * 2 ,getHeight() / 11),
                "buy adonis",
                this
        );

        gefjonBuy = new MyButton(
                new Point(getWidth() / 28 * 19,getHeight() / 11 * 5),
                new Dimension(getWidth() / 7 * 2 ,getHeight() / 11),
                "buy gefjon",
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
