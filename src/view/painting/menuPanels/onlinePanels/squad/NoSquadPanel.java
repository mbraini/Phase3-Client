package view.painting.menuPanels.onlinePanels.squad;

import constants.SizeConstants;
import view.painting.menuPanels.PIG;

import java.awt.*;

public class NoSquadPanel extends PIG {

    public NoSquadPanel() {
        this.setLayout(null);
        this.setBounds(0,0, SizeConstants.GAME_WIDTH, SizeConstants.GAME_HEIGHT);
        this.setBackground(Color.BLACK);
        this.setVisible(false);


    }


    @Override
    public void start() {

    }

    @Override
    public void end() {

    }
}
