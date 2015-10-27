package jdraw.handles.States;

import jdraw.figures.AbstractRectangularFigure;
import jdraw.framework.DrawView;
import jdraw.framework.Figure;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Patrick on 11.10.2015.
 */
public class South extends AbstractState {

    private int originWidth;
    private Point anchor;

    public South(Figure figure) {
        super(figure);
    }

    @Override
    public Point getAnchor() {
        Rectangle r = getOwner().getBounds();
        return new Point(r.x + r.width / 2, r.y + r.height);
    }

    @Override
    public Cursor getCursor() {
        return Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR);
    }

    @Override
    public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
        Rectangle r = getOwner().getBounds();
        anchor = r.getLocation();
        originWidth = r.width;
    }

    @Override
    public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
        getOwner().setBounds(anchor, new Point(anchor.x + originWidth, y));
    }

    @Override
    public void stopInteraction(int x, int y, MouseEvent e, DrawView v) {
        originWidth = 0;
        anchor = null;
    }
}
