package jdraw.handles.States;

import jdraw.figures.AbstractRectangularFigure;
import jdraw.framework.DrawView;
import jdraw.framework.Figure;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Patrick on 11.10.2015.
 */
public class SouthEast extends AbstractState {

    private Point anchor;

    public SouthEast(Figure figure) {
        super(figure);
    }
    @Override
    public Point getAnchor() {
        Rectangle r = getOwner().getBounds();
        return new Point(r.x + r.width, r.y + r.height);
    }

    @Override
    public Cursor getCursor() {
        return Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR);
    }

    @Override
    public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
        anchor = getOwner().getBounds().getLocation();
    }

    @Override
    public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {

        Rectangle r = getOwner().getBounds();
        getOwner().setBounds(new Point(r.x, r.y),
                new Point(x, y));

        if (x < r.x) {
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
