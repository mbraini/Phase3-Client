package view.painting.menuPanels;

import constants.SizeConstants;
import view.painting.objectViews.panels.MyButton;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OnlineChoicePanel extends PIG{

    private MyButton logIn;
    private MyButton signUp;

    public OnlineChoicePanel(){
        this.setLayout(null);
        this.setBounds(0,0, SizeConstants.GAME_WIDTH, SizeConstants.GAME_HEIGHT);
        this.setBackground(Color.BLACK);
        this.setVisible(false);

        initButtons();
        initAls();
    }

    private void initAls() {
        logIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                end();
                MainFrame.logInPanel.start();
            }
        });
        signUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                end();
                MainFrame.signUpPanel.start();
            }
        });
    }

    private void initButtons() {
        logIn = new MyButton(
                new Point(getWidth() / 5 ,getHeight() / 5 * 2),
                new Dimension(getWidth() / 5 ,getHeight() / 5),
                "logIn",
                this
        );
        signUp = new MyButton(
                new Point(getWidth() / 5 * 3 ,getHeight() / 5 * 2),
                new Dimension(getWidth() / 5 ,getHeight() / 5),
                "signUp",
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
