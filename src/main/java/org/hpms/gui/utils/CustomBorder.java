package org.hpms.gui.utils;

import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.geom.Area;

public class CustomBorder extends AbstractBorder {

    private static final long serialVersionUID = 1L;
    private Insets i;

    public CustomBorder() {
        i = new Insets(10, 20, 10, 20);
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        super.paintBorder(c, g, x, y, width, height);

        Polygon bubble = new Polygon();
        bubble.addPoint(x + 10, y + 5);
        bubble.addPoint(x + width - 10, y + 5);
        bubble.addPoint(x + width - 10, y + height / 3);
        bubble.addPoint(x + width, y + height / 2);
        bubble.addPoint(x + width - 10, y + height * 2 / 3);
        bubble.addPoint(x + width - 10, y - 5 + height);
        bubble.addPoint(x + 10, y - 5 + height);

        Graphics2D g2d = (Graphics2D) g;
        //Area rect = new Area(new Rectangle(x, y, width, height));
        Shape clip = g2d.getClip();
        Area rect = new Area(clip);
        //rect.subtract(new Area(bubble));
        g2d.setClip(rect);
        g2d.setColor(c.getParent().getBackground());
        g2d.fillRect(0, 0, width, height);
        //g2d.setClip(null);
        g2d.setClip(clip);
        g2d.setColor(Color.BLACK);
        //g2d.draw(bubble);
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return i;
    }

    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
        return i;
    }
}