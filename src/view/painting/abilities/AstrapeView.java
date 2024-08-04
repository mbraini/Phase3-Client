package view.painting.abilities;

import constants.ImageConstants;
import constants.SizeConstants;
import utils.Vector;
import view.painting.ViewData;
import view.painting.objectViews.FrameView;

public class AstrapeView extends AbilityView {

    public AstrapeView(int coolDownTime, int timePassed, boolean isAvailable) {
        super(coolDownTime, timePassed, isAvailable);
        image = ImageConstants.astrape;
    }

    @Override
    public void setUp() {
        super.setUp();
        FrameView frame = ViewData.getEpsilonFrame();
        if (frame == null)
            return;
        position = new Vector(
                frame.getX() - SizeConstants.barD.width + SizeConstants.ABILITY_VIEW_DIMENSION.width / 2d + SizeConstants.SCREEN_SIZE.width,
                frame.getY() + ((4.5) / 11) * (frame.getHeight() - SizeConstants.barD.height) + SizeConstants.SCREEN_SIZE.height
                        - SizeConstants.barD.height
        );
    }
}
