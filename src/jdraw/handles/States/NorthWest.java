package jdraw.handles.States;

import jdraw.figures.RectangularFigure;
import jdraw.framework.DrawView;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Patrick on 11.10.2015.
 */
public class NorthWest extends State {

    public NorthWest(RectangularFigure handleOwner) {
        super(handleOwner);
    }

    public NorthWest(RectangularFigure handleOwner, Point root) {
        super(handleOwner, root);
    }

    @Override
    public Point getLocation() {
        return handleOwner.getBounds().getLocation();
    }

    @Override
    public Cursor getCursor() {
        return Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR);
    }

    @Override
    public State setState(int newX, int newY) {
        if (corner.x > newX && corner.y > newY) {
            return this;
        } else if (corner.x < newX && corner.y < newY) {
            State s = new SouthEast(handleOwner, corner);
            handleOwner.exchangeStates(this, s);
            return s;
        } else if (corner.x >= newX && corner.y < newY) {
            State s = new SouthWest(handleOwner, corner);
            handleOwner.exchangeStates(this, s);
            return s;
        } else {
            State s = new NorthEast(handleOwner, corner);
            handleOwner.exchangeStates(this, s);
            return s;
        }
    }

    @Override
    public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
        Rectangle r = handleOwner.getBounds();
        corner = new Point(r.x + r.width, r.y + r.height);
    }

    @Override
    public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
        handleOwner.setBounds(new Point(x, y), corner);
    }
}
