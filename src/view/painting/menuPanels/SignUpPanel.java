package view.painting.menuPanels;

import constants.SizeConstants;
import view.online.OnlineData;
import view.online.tcp.ClientRequestType;
import view.online.tcp.ClientSignUpRequest;
import view.painting.objectViews.panels.MyButton;
import view.painting.objectViews.panels.MyLabel;
import view.painting.objectViews.panels.MyText;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpPanel extends PIG{

    private MyText username;
    private MyLabel usernameL;
    private MyButton signUp;

    public SignUpPanel() {
        this.setLayout(null);
        this.setBounds(0,0, SizeConstants.GAME_WIDTH, SizeConstants.GAME_HEIGHT);
        this.setBackground(Color.BLACK);
        this.setVisible(false);

        initSend();
        initUsername();
        initALs();
    }

    private void initALs() {
        signUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String chosenUsername = username.getText();
                if (chosenUsername.isEmpty())
                    return;
                new ClientSignUpRequest(chosenUsername).sendRequest();
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
        signUp = new MyButton(
                new Point(getWidth() / 5 * 2 ,getHeight() / 5 * 3),
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
