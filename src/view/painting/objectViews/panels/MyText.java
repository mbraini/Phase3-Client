package view.painting.objectViews.panels;

import javax.swing.*;
import java.awt.*;

public class MyText extends JTextField {

    public MyText(Point position , Dimension size , JPanel panel) {
        super();
        setBounds(
                position.x,
                position.y,
                size.width,
                size.height
        );
        setOpaque(false);
        setForeground(Color.CYAN);
        setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        setHorizontalAlignment(JLabel.CENTER);
        panel.add(this);
    }

}
