package jdraw.handles;

import jdraw.figures.Line;
import jdraw.framework.DrawView;
import jdraw.framework.Figure;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Patrick on 11.10.2015.
 */
public class LineEndHandle extends Handle {

    protected Line owner;
    protected Point anchor;


    public LineEndHandle(Line owner) {
        this.owner = owner;
    }

    @Override
    public Figure getOwner() {
        return owner;
    }

    @Override
    public Point getLocation() {
        return owner.getEndPoint();
    }

    @Override
    public Cursor getCursor() {
        return Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR);
    }


    @Override
    public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
        anchor = owner.getStartPoint();
    }

    @Override
    public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
        owner.setBounds(anchor, new Point(x, y));
    }

    @Override
    public void stopInteraction(int x, int y, MouseEvent e, DrawView v) {
        anchor = null;
    }

}
