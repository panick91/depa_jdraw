package jdraw.handles.States;

import jdraw.figures.RectangularFigure;
import jdraw.framework.DrawView;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Patrick on 11.10.2015.
 */
public class South extends State {

    public South(RectangularFigure handleOwner) {
        super(handleOwner);
    }

    private int originWidth;

    @Override
    public Point getLocation() {
        Rectangle r = getOwner().getBounds();
        return new Point(r.x + r.width / 2, r.y + r.height);
    }

    @Override
    public Cursor getCursor() {
        return Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR);
    }

    @Override
    public State setState(int newX, int newY) {
        return this;
    }

    @Override
    public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
        Rectangle r = getOwner().getBounds();
        corner = r.getLocation();
        originWidth = r.width;
    }

    @Override
    public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
        getOwner().setBounds(corner, new Point(corner.x + originWidth, y));
    }
}
