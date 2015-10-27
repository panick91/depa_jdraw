package jdraw.handles.States;

import jdraw.figures.AbstractRectangularFigure;
import jdraw.framework.DrawView;
import jdraw.framework.Figure;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Patrick on 11.10.2015.
 */
public interface HandleState {

    Point getAnchor();
    Figure getOwner();
    Cursor getCursor();
    void startInteraction(int x, int y, MouseEvent e, DrawView v);
    void dragInteraction(int x, int y, MouseEvent e, DrawView v);
    void stopInteraction(int x, int y, MouseEvent e, DrawView v);

}
