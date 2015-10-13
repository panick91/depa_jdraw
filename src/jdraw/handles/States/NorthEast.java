package jdraw.handles.States;

import jdraw.figures.RectangularFigure;
import jdraw.framework.DrawView;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Patrick on 11.10.2015.
 */
public class NorthEast extends State {

    public NorthEast(RectangularFigure handleOwner) {
        super(handleOwner);
    }

    public NorthEast(RectangularFigure handleOwner, Point root) {
        super(handleOwner, root);
    }

    @Override
    public Point getLocation() {
        Rectangle r = handleOwner.getBounds();
        return new Point(r.x + r.width, r.y);
    }

    @Override
    public Cursor getCursor() {
        return Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR);
    }

    @Override
    public State setState(int newX, int newY) {
        if (corner.x > newX && corner.y > newY) {
            State s = new NorthWest(handleOwner, corner);
            handleOwner.exchangeStates(this, s);
            return s;
        } else if (corner.x < newX && corner.y < newY) {
            State s = new SouthEast(handleOwner, corner);
            handleOwner.exchangeStates(this, s);
            return s;
        } else if (corner.x >= newX && corner.y < newY) {
            State s = new SouthWest(handleOwner, corner);
            handleOwner.exchangeStates(this, s);
            return s;
        } else {
            return this;
        }
    }

    @Override
    public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
        Rectangle r = handleOwner.getBounds();
        corner = new Point(r.x, r.y + r.height);
    }

    @Override
    public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
        handleOwner.setBounds(new Point(corner.x, y), new Point(x, corner.y));
    }
}
