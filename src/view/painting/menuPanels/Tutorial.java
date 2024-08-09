package view.painting.menuPanels;


import constants.SizeConstants;
import view.painting.objectViews.panels.MyButton;
import view.painting.objectViews.panels.MyLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tutorial extends PIG {

     MyLabel shoot;
     MyLabel shootGuide;
     MyLabel movement;
     MyLabel movementGuide1;
     MyLabel movementGuide2;
     MyLabel movementGuide3;
     MyLabel movementGuide4;
     MyLabel shop;
     MyLabel shopGuide;
     private MyButton back;
     public Tutorial(){
         this.setLayout(null);
         this.setBounds(0,0, SizeConstants.GAME_WIDTH, SizeConstants.GAME_HEIGHT);
         this.setBackground(Color.BLACK);
         this.setVisible(false);

         initShot();
         initMovement();
         initAbilities();
         initShop();
         initBack();
         initBackAL();
     }

    private void initShop() {
        shop = new MyLabel(
                new Point(SizeConstants.GAME_WIDTH / 25 , SizeConstants.GAME_HEIGHT / 15),
                new Dimension(SizeConstants.GAME_WIDTH / 5 , SizeConstants.GAME_HEIGHT / 15),
                "Shop",
                this
        );

        shopGuide = new MyLabel(
                new Point(SizeConstants.GAME_WIDTH / 25 * 2 + SizeConstants.GAME_WIDTH / 5, SizeConstants.GAME_HEIGHT / 15),
                new Dimension(SizeConstants.GAME_WIDTH / 5 , SizeConstants.GAME_HEIGHT / 15),
                "P_KEY",
                this
        );
    }

    private void initBackAL() {
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                end();
                MainFrame.menuPanel.start();
            }
        });
    }

    private void initBack() {
        back = new MyButton(
                new Point(SizeConstants.GAME_WIDTH / 3 , SizeConstants.GAME_HEIGHT / 15 * 13),
                new Dimension(SizeConstants.GAME_WIDTH / 3 , SizeConstants.GAME_HEIGHT / 15),
                "back",
                this
        );
    }

    private void initAbilities() {

    }

    private void initMovement() {
        movement = new MyLabel(
                new Point(SizeConstants.GAME_WIDTH / 7 , SizeConstants.GAME_HEIGHT / 15 * 4),
                new Dimension(SizeConstants.GAME_WIDTH / 7 , SizeConstants.GAME_HEIGHT / 15),
                "Movement",
                this
        );
        movementGuide1 = new MyLabel(
                new Point(SizeConstants.GAME_WIDTH / 7 * 3 , SizeConstants.GAME_HEIGHT / 15 * 3),
                new Dimension(SizeConstants.GAME_WIDTH / 7 , SizeConstants.GAME_HEIGHT / 15),
                "UP_KEY",
                this
        );

        movementGuide2 = new MyLabel(
                new Point(SizeConstants.GAME_WIDTH / 7 * 5 , SizeConstants.GAME_HEIGHT / 15 * 3),
                new Dimension(SizeConstants.GAME_WIDTH / 7 , SizeConstants.GAME_HEIGHT / 15),
                "DOWN_KEY",
                this
        );

        movementGuide3 = new MyLabel(
                new Point(SizeConstants.GAME_WIDTH / 7 * 3 , SizeConstants.GAME_HEIGHT / 15 * 5),
                new Dimension(SizeConstants.GAME_WIDTH / 7 , SizeConstants.GAME_HEIGHT / 15),
                "LEFT_KEY",
                this
        );

        movementGuide4 = new MyLabel(
                new Point(SizeConstants.GAME_WIDTH / 7 * 5 , SizeConstants.GAME_HEIGHT / 15 * 5),
                new Dimension(SizeConstants.GAME_WIDTH / 7 , SizeConstants.GAME_HEIGHT / 15),
                "RIGHT_KEY",
                this
        );
    }

    private void initShot() {
        shoot = new MyLabel(
                new Point(SizeConstants.GAME_WIDTH / 25 * 3 + SizeConstants.GAME_WIDTH / 5 * 2 , SizeConstants.GAME_HEIGHT / 15),
                new Dimension(SizeConstants.GAME_WIDTH / 5 , SizeConstants.GAME_HEIGHT / 15),
                "Shoot",
                this
        );

        shootGuide = new MyLabel(
                new Point(SizeConstants.GAME_WIDTH / 25 * 4 + SizeConstants.GAME_WIDTH / 5 * 3 , SizeConstants.GAME_HEIGHT / 15),
                new Dimension(SizeConstants.GAME_WIDTH / 5 + SizeConstants.GAME_WIDTH / 30 , SizeConstants.GAME_HEIGHT / 15),
                "Left_Mouse_Button",
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
