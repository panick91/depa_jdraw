package jdraw.handles.rectfigures.States;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Patrick on 11.10.2015.
 */
public class SouthWest extends State {

    public SouthWest(Figure handleOwner) {
        super(handleOwner);
    }

    public SouthWest(Figure handleOwner, Point root) {
        super(handleOwner, root);
    }

    @Override
    public Point getLocation() {
        Rectangle r = handleOwner.getBounds();
        return new Point(r.x, r.y + r.height);
    }

    @Override
    public Cursor getCursor() {
        return Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR);
    }

    @Override
    public State setState(int newX, int newY) {
        if (corner.x > newX && corner.y > newY) {
            return new NorthWest(handleOwner, corner);
        } else if (corner.x < newX && corner.y < newY) {
            return new SouthEast(handleOwner, corner);
        } else if (corner.x >= newX && corner.y < newY) {
            return this;
        } else {
            return new NorthEast(handleOwner, corner);
        }
    }

    @Override
    public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
        Rectangle r = handleOwner.getBounds();
        corner = new Point(r.x + r.width, r.y);
    }

    @Override
    public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
        handleOwner.setBounds(new Point(x, corner.y), new Point(corner.x, y));
    }
}
