package jdraw.handles.States;

import jdraw.figures.RectangularFigure;
import jdraw.framework.DrawView;
import jdraw.framework.Figure;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Patrick on 11.10.2015.
 */
public abstract class State {

    protected RectangularFigure handleOwner;
    protected Point corner;

    public State(RectangularFigure handleOwner){
        this.handleOwner = handleOwner;
    }
    public State(RectangularFigure handleOwner, Point root){
        this.handleOwner = handleOwner;
        this.corner = root;
    }

    public Figure getOwner(){
        return handleOwner;
    }

    public abstract Point getLocation();
    public abstract Cursor getCursor();
    public abstract void startInteraction(int x, int y, MouseEvent e, DrawView v);
    public abstract void dragInteraction(int x, int y, MouseEvent e, DrawView v);
    public void stopInteraction(int x, int y, MouseEvent e, DrawView v) {
        corner = null;
    }
    public abstract State setState(int newX, int newY);
}
