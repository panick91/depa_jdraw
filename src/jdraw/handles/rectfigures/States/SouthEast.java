package jdraw.handles.rectfigures.States;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Patrick on 11.10.2015.
 */
public class SouthEast extends State {

    public SouthEast(Figure handleOwner) {
        super(handleOwner);
    }

    public SouthEast(Figure handleOwner, Point root) {
        super(handleOwner, root);
    }

    @Override
    public Point getLocation() {
        Rectangle r = handleOwner.getBounds();
        return new Point(r.x + r.width, r.y + r.height);
    }

    @Override
    public Cursor getCursor() {
        return Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR);
    }

    @Override
    public State setState(int newX, int newY) {
        if (corner.x > newX && corner.y > newY) {
            return new NorthWest(handleOwner, corner);
        } else if (corner.x < newX && corner.y < newY) {
            return this;
        } else if (corner.x >= newX && corner.y < newY) {
            return new SouthWest(handleOwner, corner);
        } else {
            return new NorthEast(handleOwner, corner);
        }
    }

    @Override
    public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
        corner = handleOwner.getBounds().getLocation();
    }

    @Override
    public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
        handleOwner.setBounds(corner, new Point(x, y));
    }
}
