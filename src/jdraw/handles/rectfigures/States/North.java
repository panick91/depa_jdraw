package jdraw.handles.rectfigures.States;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Patrick on 11.10.2015.
 */
public class North extends State {

    public North(Figure handleOwner) {
        super(handleOwner);
    }

    public North(Figure handleOwner, Point root) {
        super(handleOwner, root);
    }

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
    }

    @Override
    public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
        // XXX man könnte sich das wiederholte Abfragen der Bounds des owners sparen indem man sich neben corner auch noch r.x merkt im startInteraction.
        //     Gilt analog auch für die anderen Handles.
        Rectangle r = getOwner().getBounds();
        getOwner().setBounds(new Point(r.x, y), corner);
    }
}
