package jdraw.handles.States;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Patrick on 11.10.2015.
 */
public class North extends AbstractState {

    private int originX;
    private Point anchor;

    public North(Figure figure) {
        super(figure);
    }

    @Override
    public Point getAnchor() {
        Rectangle r = getOwner().getBounds();
        return new Point(r.x + r.width / 2, r.y);
    }

    @Override
    public Cursor getCursor() {
        return Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR);
    }

    @Override
    public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
        Rectangle r = getOwner().getBounds();
        anchor = new Point(r.x + r.width, r.y + r.height);
        originX = r.x;
    }

    @Override
    public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
        getOwner().setBounds(new Point(originX, y), anchor);
    }

    @Override
    public void stopInteraction(int x, int y, MouseEvent e, DrawView v) {
        originX = 0;
        anchor = null;
    }
}
