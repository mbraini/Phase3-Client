package view.painting.menuPanels.onlinePanels.squad;

import constants.SizeConstants;
import controller.online.tcp.ClientCreateSquadRequest;
import controller.online.tcp.ClientLogInRequest;
import view.painting.menuPanels.MainFrame;
import view.painting.menuPanels.PIG;
import view.painting.objectViews.panels.MyButton;
import view.painting.objectViews.panels.MyLabel;
import view.painting.objectViews.panels.MyText;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateNewSquadPanel extends PIG {

    private MyText squadName;
    private MyLabel suqadL;
    private MyButton submit;
    private MyButton back;

    public CreateNewSquadPanel() {
        this.setLayout(null);
        this.setBounds(0,0, SizeConstants.GAME_WIDTH, SizeConstants.GAME_HEIGHT);
        this.setBackground(Color.BLACK);
        this.setVisible(false);

        initSubmit();
        initSquadName();
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
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String chosenSquadName = squadName.getText();
                if (chosenSquadName.isEmpty())
                    return;
                new ClientCreateSquadRequest(chosenSquadName).sendRequest();
            }
        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                end();
                MainFrame.noSquadPanel.start();
            }
        });
    }

    private void initSquadName() {
        squadName = new MyText(
                new Point(getWidth() / 5 * 3 ,getHeight() / 5),
                new Dimension(getWidth() / 5 ,getHeight() / 10),
                this
        );
        suqadL = new MyLabel(
                new Point(getWidth() / 5 ,getHeight() / 5),
                new Dimension(getWidth() / 5 ,getHeight() / 10),
                "squad's name: ",
                this
        );
    }

    private void initSubmit() {
        submit = new MyButton(
                new Point(getWidth() / 5 * 2 ,getHeight() / 5 * 3),
                new Dimension(getWidth() / 5 ,getHeight() / 10),
                "submit",
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
