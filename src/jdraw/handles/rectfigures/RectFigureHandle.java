package jdraw.handles.rectfigures;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;
import jdraw.handles.Handle;
import jdraw.handles.rectfigures.States.State;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Patrick on 11.10.2015.
 */
public class RectFigureHandle extends Handle {

    protected State state;

    public RectFigureHandle(State state) {
        this.state = state;
    }

    @Override
    public Figure getOwner() {
        return state.getOwner();
    }

    @Override
    public Point getLocation() {
        return state.getLocation();
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
        state = state.setState(x, y);
        state.dragInteraction(x, y, e, v);
    }

    @Override
    public void stopInteraction(int x, int y, MouseEvent e, DrawView v) {
        state.stopInteraction(x, y, e, v);
    }

}
