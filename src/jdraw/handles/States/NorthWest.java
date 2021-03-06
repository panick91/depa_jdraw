package jdraw.handles.States;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Patrick on 11.10.2015.
 */
public class NorthWest extends AbstractState {

    public NorthWest(Figure figure) {
        super(figure);
    }

    @Override
    public Point getAnchor() {
        return getOwner().getBounds().getLocation();
    }

    @Override
    public Cursor getCursor() {
        return Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR);
    }

    @Override
    public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
    }

    @Override
    public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
        Rectangle r = getOwner().getBounds();
        getOwner().setBounds(new Point(x,y),
                new Point(r.x+r.width,r.y+r.height));

        if (x > r.x+r.width) {
            getOwner().swapHorizontal();
        }
        if (y > r.y+r.height) {
            getOwner().swapVertical();
        }
    }

    @Override
    public void stopInteraction(int x, int y, MouseEvent e, DrawView v) {

    }
}
