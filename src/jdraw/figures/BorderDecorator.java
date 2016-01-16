package jdraw.figures;

import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by Patrick on 12.01.2016.
 */
public class BorderDecorator extends AbstractDecoratorFigure {

    private static final int BORDER_OFFSET = 5;

    public BorderDecorator(Figure f) {
        super(f);
    }

    public Rectangle getBounds() {
        Rectangle r = super.figure.getBounds();
        r.grow(BORDER_OFFSET, BORDER_OFFSET);
        return r;
    }

    @Override
    public void draw(Graphics g) {
        super.figure.draw(g);
        g.setColor(Color.WHITE);
        Rectangle r = getBounds();
        g.setColor(Color.white);
        g.drawLine(r.x, r.y, r.x, r.y + r.height);
        g.drawLine(r.x, r.y, r.x + r.width, r.y);
        g.setColor(Color.gray);
        g.drawLine(r.x + r.width, r.y, r.x + r.width, r.y + r.height);
        g.drawLine(r.x, r.y + r.height, r.x + r.width, r.y + r.height);
    }

    @Override
    public boolean contains(int x, int y) {
        return getBounds().contains(x, y);
    }

    @Override
    public List<FigureHandle> getHandles() {
        return super.getHandles();
    }
}
