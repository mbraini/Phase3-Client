package view.painting.abilities;

import constants.ImageConstants;
import constants.SizeConstants;
import utils.Vector;
import view.painting.ViewData;
import view.painting.objectViews.FrameView;

public class ChironView extends AbilityView {
    public ChironView(int coolDownTime, int timePassed, boolean isAvailable) {
        super(coolDownTime, timePassed, isAvailable);
        image = ImageConstants.chiron;
    }

    @Override
    public void setUp() {
        super.setUp();
        FrameView frame = ViewData.getEpsilonFrame();
        if (frame == null)
            return;
        position = new Vector(
                frame.getX() + ((4.5) / 11) * (frame.getWidth() - SizeConstants.barD.width) + SizeConstants.SCREEN_SIZE.width,
                frame.getY() + frame.getHeight() - SizeConstants.ABILITY_VIEW_DIMENSION.height / 2d +
                        SizeConstants.SCREEN_SIZE.height - SizeConstants.barD.height * 1.43
        );
    }

}
