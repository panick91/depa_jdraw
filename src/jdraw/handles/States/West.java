package jdraw.handles.States;

import jdraw.figures.AbstractRectangularFigure;
import jdraw.framework.DrawView;
import jdraw.framework.Figure;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Patrick on 11.10.2015.
 */
public class West extends AbstractState {

    private int originY;
    private Point anchor;

    public West(Figure figure) {
        super(figure);
    }

    @Override
    public Point getAnchor() {
        Rectangle r = getOwner().getBounds();
        return new Point(r.x, r.y + r.height / 2);
    }

    @Override
    public Cursor getCursor() {
        return Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR);
    }

    @Override
    public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
        Rectangle r = getOwner().getBounds();
        anchor = new Point(r.x + r.width, r.y + r.height);
        originY = r.y;
    }

    @Override
    public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
        getOwner().setBounds(new Point(x, originY), anchor);
    }

    @Override
    public void stopInteraction(int x, int y, MouseEvent e, DrawView v) {
        originY = 0;
        anchor = null;
    }
}
