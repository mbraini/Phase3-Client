package view.painting.objectViews.bossView;

import constants.ImageConstants;
import constants.SizeConstants;
import utils.Vector;

import java.awt.*;

public class HeadView extends BossHelperView {

    public HeadView(Vector position , String id){
        this.position = position;
        this.id = id;
        this.image = ImageConstants.smiley;
        this.size = new Dimension(
                SizeConstants.HEAD_DIMENSION.width,
                SizeConstants.HEAD_DIMENSION.height
        );
    }

}
