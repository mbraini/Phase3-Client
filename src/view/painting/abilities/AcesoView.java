package view.painting.abilities;

import constants.ImageConstants;
import constants.SizeConstants;
import utils.Vector;
import view.painting.ViewData;

public class AcesoView extends AbilityView {

    public AcesoView(int coolDownTime, int timePassed, boolean isAvailable) {
        super(coolDownTime, timePassed, isAvailable);
        image = ImageConstants.aceso;
    }

    @Override
    public void setUp() {
        super.setUp();
        frame = ViewData.getEpsilonFrame();
        if (frame == null)
            return;
        position = new Vector(
                frame.getX() - SizeConstants.barD.width + SizeConstants.ABILITY_VIEW_DIMENSION.width / 2d + SizeConstants.SCREEN_SIZE.width,
                frame.getY() + ((8.5) / 11) * (frame.getHeight() - SizeConstants.barD.height) + SizeConstants.SCREEN_SIZE.height
                        - SizeConstants.barD.height
        );
    }

}
