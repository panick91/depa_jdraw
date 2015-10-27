package jdraw.handles.States;

import jdraw.figures.AbstractRectangularFigure;
import jdraw.framework.DrawView;
import jdraw.framework.Figure;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Patrick on 11.10.2015.
 */
public class SouthWest extends AbstractState {

    private Point anchor;

    public SouthWest(Figure figure) {
        super(figure);
    }

    @Override
    public Point getAnchor() {
        Rectangle r = getOwner().getBounds();
        return new Point(r.x, r.y + r.height);
    }

    @Override
    public Cursor getCursor() {
        return Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR);
    }

    @Override
    public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
        Rectangle r = getOwner().getBounds();
        anchor = new Point(r.x + r.width, r.y);
    }

    @Override
    public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {

        Rectangle r = getOwner().getBounds();
        getOwner().setBounds(new Point(x, r.y),
                new Point(r.x + r.width, y));

        if (x > r.x + r.width) {
            getOwner().swapHorizontal();
        }
        if (y < r.y) {
            getOwner().swapVertical();
        }
    }

    @Override
    public void stopInteraction(int x, int y, MouseEvent e, DrawView v) {
        anchor = null;
    }
}
