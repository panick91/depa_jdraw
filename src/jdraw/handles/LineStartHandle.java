package jdraw.handles;

import jdraw.figures.Line;
import jdraw.framework.DrawView;
import jdraw.framework.Figure;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Patrick on 11.10.2015.
 */
public class LineStartHandle extends LineHandle {

    private Point anchor;


    public LineStartHandle(Line owner) {
        super(owner);
    }

    @Override
    public Point getLocation() {
        return ((Line)getOwner()).getStartPoint();
    }

    @Override
    public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
        anchor = ((Line)getOwner()).getEndPoint();
    }

    @Override
    public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
        getOwner().setBounds(new Point(x, y), anchor);
    }

    @Override
    public void stopInteraction(int x, int y, MouseEvent e, DrawView v) {
        anchor = null;
    }

}
