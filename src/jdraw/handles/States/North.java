package jdraw.handles.States;

import jdraw.figures.RectangularFigure;
import jdraw.framework.DrawView;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Patrick on 11.10.2015.
 */
public class North extends State {

    public North(RectangularFigure handleOwner) {
        super(handleOwner);
    }

    private int originX;

    @Override
    public Point getLocation() {
        Rectangle r = getOwner().getBounds();
        return new Point(r.x + r.width / 2, r.y);
    }

    @Override
    public Cursor getCursor() {
        return Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR);
    }

    @Override
    public State setState(int newX, int newY) {
        return this;
    }

    @Override
    public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
        Rectangle r = getOwner().getBounds();
        corner = new Point(r.x + r.width, r.y + r.height);
        originX = r.x;
    }

    @Override
    public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
        getOwner().setBounds(new Point(originX, y), corner);
    }
}
