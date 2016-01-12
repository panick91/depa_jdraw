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

    protected void notifyObservers(FigureEvent event) {
        for (FigureListener listener : new ArrayList<>(listeners)) {
            listener.figureChanged(event);
        }
    }

    @Override
    public AbstractFigure clone() {
        try {
            AbstractFigure copy = (AbstractFigure) super.clone();
            copy.listeners = new LinkedList<>();
            return copy;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e.getMessage());
        }
    }
}
