package view.painting.menuPanels;

import constants.SizeConstants;
import controller.online.tcp.ClientLogInRequest;
import view.painting.objectViews.panels.MyButton;
import view.painting.objectViews.panels.MyLabel;
import view.painting.objectViews.panels.MyText;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogInPanel extends PIG{

    private MyText username;
    private MyLabel usernameL;
    private MyButton logIn;

    public LogInPanel() {
        this.setLayout(null);
        this.setBounds(0,0, SizeConstants.GAME_WIDTH, SizeConstants.GAME_HEIGHT);
        this.setBackground(Color.BLACK);
        this.setVisible(false);

        initSend();
        initUsername();
        initALs();
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
                new Dimension(getWidth() / 5 ,getHeight() / 5),
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
