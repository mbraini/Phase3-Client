package view.painting.objectViews.bossView;

import constants.SizeConstants;
import controller.interfaces.SizeChanger;
import view.painting.objectViews.EnemyView;

import java.awt.*;

public abstract class BossHelperView extends EnemyView implements SizeChanger {
    protected Dimension size;

    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage(
                image ,
                (int) position.x - size.width / 2 + SizeConstants.SCREEN_SIZE.width,
                (int) position.y - size.height / 2  + SizeConstants.SCREEN_SIZE.height,
                size.width ,size.height ,
                null
        );
    }

    @Override
    public void setSize(Dimension size) {
        this.size = size;
    }

    @Override
    public Dimension getSize() {
        return size;
    }
}
