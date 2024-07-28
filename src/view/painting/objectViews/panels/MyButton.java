package view.painting.objectViews.panels;

import javax.swing.*;
import java.awt.*;

public class MyButton extends JButton{
    public MyButton(Point position ,Dimension size ,String text ,JPanel panel) {
        super();
        setBounds(position.x ,position.y,size.width ,size.height);
        setFont(new Font(null,Font.BOLD ,15));
        setText(text);
        setBackground(Color.WHITE);
        setOpaque(false);
        setForeground(Color.WHITE);

        setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        setFocusable(false);
        panel.add(this);
    }
    
}
