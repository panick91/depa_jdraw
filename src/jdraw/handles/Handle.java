package jdraw.handles;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;
import jdraw.handles.States.HandleState;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Patrick on 13.10.2015.
 */
public class Handle implements FigureHandle {

    private final int handleSize = 6;
    private HandleState state;

    public Handle(HandleState state) {
        this.state = state;
    }

    public HandleState getState() {
        return state;
    }

    public void setState(HandleState state) {
        this.state = state;
    }

    @Override
    public Figure getOwner() {
        return state.getOwner();
    }

    @Override
    public Point getLocation() {
        return state.getAnchor();
    }

    @Override
    public Cursor getCursor() {
        return state.getCursor();
    }

    @Override
    public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
        state.startInteraction(x, y, e, v);
    }

    @Override
    public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
        state.dragInteraction(x, y, e, v);
    }

    @Override
    public void stopInteraction(int x, int y, MouseEvent e, DrawView v) {
        state.stopInteraction(x, y, e, v);
    }

    @Override
    public final void draw(Graphics g) {
        Point loc = getLocation();
        g.setColor(Color.WHITE);
        g.fillRect(loc.x - handleSize / 2, loc.y - handleSize / 2, handleSize, handleSize);
        g.setColor(Color.BLACK);
        g.drawRect(loc.x - handleSize / 2, loc.y - handleSize / 2, handleSize, handleSize);
    }

    @Override
    public final boolean contains(int x, int y) {
        Point loc = getLocation();
        int r = handleSize / 2;

        return (x >= loc.x - r && x <= loc.x + r) &&
                (y >= loc.y - r && y <= loc.y + r);
    }
}
