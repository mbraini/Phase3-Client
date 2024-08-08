package view.painting.menuPanels.onlinePanels;

import constants.SizeConstants;
import view.painting.menuPanels.PIG;
import view.painting.objectViews.panels.MyLabel;

import java.awt.*;

public class WaitingRoomPanel extends PIG {

    private MyLabel matchL;
    private MyLabel time;

    public WaitingRoomPanel() {
        this.setLayout(null);
        this.setBounds(0,0, SizeConstants.GAME_WIDTH, SizeConstants.GAME_HEIGHT);
        this.setBackground(Color.BLACK);
        this.setVisible(false);

        initMatchL();
        initTime();
    }

    private void initMatchL() {
        matchL = new MyLabel(
                new Point(getWidth() / 5 * 2 ,getHeight() / 5),
                new Dimension(getWidth() / 5 ,getHeight() / 5),
                "game starts at",
                this
        );
    }

    private void initTime() {
        time = new MyLabel(
                new Point(getWidth() / 5 * 2 ,getHeight() / 5 * 3),
                new Dimension(getWidth() / 5 ,getHeight() / 5),
                "15",
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

    public void update(String time) {

        this.time.setText(time);

    }

}
