package view.painting.menuPanels.onlinePanels;

import constants.SizeConstants;
import controller.online.tcp.ClientLogInRequest;
import view.painting.menuPanels.MainFrame;
import view.painting.menuPanels.PIG;
import view.painting.objectViews.panels.MyButton;
import view.painting.objectViews.panels.MyLabel;
import view.painting.objectViews.panels.MyText;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogInPanel extends PIG {

    private MyText username;
    private MyLabel usernameL;
    private MyButton logIn;
    private MyButton back;

    public LogInPanel() {
        this.setLayout(null);
        this.setBounds(0,0, SizeConstants.GAME_WIDTH, SizeConstants.GAME_HEIGHT);
        this.setBackground(Color.BLACK);
        this.setVisible(false);

        initSend();
        initUsername();
        initBack();
        initALs();
    }

    private void initBack() {
        back = new MyButton(
                new Point(getWidth() / 5 * 2 ,getHeight() / 5 * 4),
                new Dimension(getWidth() / 5 ,getHeight() / 10),
                "back",
                this
        );
    }

    private void initALs() {
        logIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String chosenUsername = username.getText();
                if (chosenUsername.isEmpty())
                    return;
                new ClientLogInRequest(chosenUsername).sendRequest();
            }
        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                end();
                MainFrame.onlineChoicePanel.start();
            }
        });
    }

    private void initUsername() {
        username = new MyText(
                new Point(getWidth() / 5 * 3 ,getHeight() / 5),
                new Dimension(getWidth() / 5 ,getHeight() / 10),
                this
        );
        usernameL = new MyLabel(
                new Point(getWidth() / 5 ,getHeight() / 5),
                new Dimension(getWidth() / 5 ,getHeight() / 10),
                "username: ",
                this
        );
    }

    private void initSend() {
        logIn = new MyButton(
                new Point(getWidth() / 5 * 2 ,getHeight() / 5 * 3),
                new Dimension(getWidth() / 5 ,getHeight() / 10),
                "logIn",
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
