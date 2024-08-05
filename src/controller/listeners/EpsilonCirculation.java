package controller.listeners;


import constants.SizeConstants;
import controller.manager.GameState;
import controller.online.OnlineData;
import model.ModelData;
import model.objectModel.fighters.EpsilonModel;
import utils.Math;
import utils.Vector;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class EpsilonCirculation extends MouseMotionAdapter {
    private EpsilonModel epsilon;

    public static Vector lastTimePosition = new Vector();

    public EpsilonCirculation(){
        if (OnlineData.getCurrentOnlineGame() == null)
            this.epsilon = ModelData.getEpsilon();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Vector mousePosition = new Vector(e.getX(), e.getY());
        mousePosition = Math.VectorAdd(
                mousePosition,
                new Vector(
                        -SizeConstants.SCREEN_SIZE.width,
                        -SizeConstants.SCREEN_SIZE.height
                )
        );
        lastTimePosition = mousePosition.clone();
        if (GameState.isInAnimation())
            return;
        if (OnlineData.getCurrentOnlineGame() == null) {
            Vector epsilonPosition = epsilon.getPosition();
            if (mousePosition.Equals(epsilonPosition))
                return;
            Vector direction = Math.VectorAdd(Math.ScalarInVector(-1, epsilonPosition), mousePosition);
            double cosTheta = Math.DotProduct(direction, new Vector(1, 0)) / (Math.VectorSize(direction));
            double theta = java.lang.Math.acos(cosTheta);
            if (direction.getY() <= 0) {
                theta = -theta;
            }
            epsilon.Rotate(-theta);
        }
        else {

        }
    }
}
