package controller.online.udp.objectViewReceiver;

import controller.enums.ModelType;
import utils.Vector;

import java.awt.*;

public class JView {

        private Vector position;
        private double theta;
        private boolean hovering;
        private Dimension size;
        private ModelType modelType;
        private String id;

        public Vector getPosition() {
                return position;
        }

        public void setPosition(Vector position) {
                this.position = position;
        }

        public double getTheta() {
                return theta;
        }

        public void setTheta(double theta) {
                this.theta = theta;
        }

        public boolean isHovering() {
                return hovering;
        }

        public void setHovering(boolean hovering) {
                this.hovering = hovering;
        }

        public Dimension getSize() {
                return size;
        }

        public void setSize(Dimension size) {
                this.size = size;
        }

        public ModelType getModelType() {
                return modelType;
        }

        public void setModelType(ModelType modelType) {
                this.modelType = modelType;
        }

        public String getId() {
                return id;
        }

        public void setId(String id) {
                this.id = id;
        }
}