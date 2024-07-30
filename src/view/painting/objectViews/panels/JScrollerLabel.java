package view.painting.objectViews.panels;

import javax.swing.*;
import java.awt.*;

public class JScrollerLabel extends JLabel {
    public JScrollerLabel(String text , Color color ,Container container) {
        super();
        setForeground(color);
        setOpaque(false);
        setText(text);
        setFont(new Font(null,Font.BOLD ,15));
        setHorizontalAlignment(JLabel.CENTER);
        setVerticalAlignment(JLabel.CENTER);
        container.add(this);
    }
}
