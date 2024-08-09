package view.painting.objectViews;


import constants.ImageConstants;
import constants.SizeConstants;
import controller.interfaces.SizeChanger;
import utils.Vector;

import java.awt.*;

public class EpsilonView extends ObjectView implements SizeChanger {
    private Dimension size;
    public EpsilonView(Vector position , String id) {
        this.position = position;
        this.id = id;
        image = ImageConstants.whiteEpsilon;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.rotate(
                -theta ,
                position.getX() + SizeConstants.SCREEN_SIZE.width ,
                position.getY() + SizeConstants.SCREEN_SIZE.height
        );
        g2d.drawImage(
                image,
                (int) position.getX() - size.width / 2 + SizeConstants.SCREEN_SIZE.width ,
                (int) position.getY() - size.height / 2 + SizeConstants.SCREEN_SIZE.height,
                size.width ,
                size.height ,
                null
        );
        g2d.rotate(
                theta ,
                position.getX() + SizeConstants.SCREEN_SIZE.width ,
                position.getY() + SizeConstants.SCREEN_SIZE.height
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
