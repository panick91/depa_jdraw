package jdraw.handles;

import jdraw.figures.Line;
import jdraw.framework.DrawView;
import jdraw.framework.Figure;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Patrick on 11.10.2015.
 */
public class LineEndHandle extends LineHandle {

    private Point anchor;

    public LineEndHandle(Line owner) {
        super(owner);
    }

    @Override
    public Point getLocation() {
        return ((Line)getOwner()).getEndPoint();
    }

    @Override
    public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
        anchor = ((Line)getOwner()).getStartPoint();
    }

    @Override
    public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
        getOwner().setBounds(anchor, new Point(x, y));
    }

    @Override
    public void stopInteraction(int x, int y, MouseEvent e, DrawView v) {
        anchor = null;
    }

}
