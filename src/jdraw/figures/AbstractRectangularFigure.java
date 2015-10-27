package jdraw.figures;

import jdraw.framework.FigureEvent;
import jdraw.framework.FigureHandle;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Patrick on 13.10.2015.
 */
public abstract class AbstractRectangularFigure extends AbstractFigure {

    private Rectangle rectangle = null;
    protected List<FigureHandle> handles = new LinkedList<>();

    protected AbstractRectangularFigure(Point origin) {
        rectangle = new Rectangle(origin);
    }

    protected AbstractRectangularFigure(int x, int y, int w, int h) {
        rectangle = new Rectangle(x, y, w, h);
    }

    @Override
    public void setBounds(Point origin, Point corner) {
        Rectangle original = new Rectangle(rectangle);
        rectangle.setFrameFromDiagonal(origin, corner);
        if (!original.equals(rectangle))
            notifyObservers(new FigureEvent(this));
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(rectangle);
    }

    @Override
    public void move(int dx, int dy) {
        if (dx != 0 || dy != 0) {
            rectangle.translate(dx, dy);
            notifyObservers(new FigureEvent(this));
        }
    }

    @Override
    public boolean contains(int x, int y) {
        return rectangle.contains(x, y);
    }

}
