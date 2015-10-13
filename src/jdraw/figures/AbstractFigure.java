package jdraw.figures;

import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureHandle;
import jdraw.framework.FigureListener;

import java.awt.*;
import java.awt.geom.RectangularShape;
import java.util.*;
import java.util.List;

/**
 * Created by Patrick on 03.10.2015.
 */
public abstract class AbstractFigure implements Figure {


    private List<FigureListener> listeners = new ArrayList<>();

    @Override
    public void addFigureListener(FigureListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeFigureListener(FigureListener listener) {
        listeners.remove(listener);
    }

    protected void notifyObservers() {
        for (FigureListener listener : new ArrayList<>(listeners)) {
            listener.figureChanged(new FigureEvent(this));
        }
    }

    @Override
    public Figure clone() {
        return null;
    }
}