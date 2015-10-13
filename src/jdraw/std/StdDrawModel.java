/*
 * Copyright (c) 2000-2016 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package jdraw.std;

import java.util.*;

import jdraw.framework.*;

/**
 * Provide a standard behavior for the drawing model. This class initially does not implement the methods
 * in a proper way.
 * It is part of the course assignments to do so.
 *
 * @author Patrick Nick
 */
public class StdDrawModel implements DrawModel, FigureListener {

    public LinkedList<Figure> figures = new LinkedList<>();
    public List<DrawModelListener> listeners = new ArrayList<>();

    @Override
    public void addFigure(Figure f) {
        if (figures.indexOf(f) == -1) {
            figures.add(f);
            f.addFigureListener(this);
            notifyListeners(f, DrawModelEvent.Type.FIGURE_ADDED);
        }
    }

    @Override
    public Iterable<Figure> getFigures() {
        return figures;
    }

    @Override
    public void removeFigure(Figure f) {
        if (figures.remove(f)) {
            f.removeFigureListener(this);
            notifyListeners(f, DrawModelEvent.Type.FIGURE_REMOVED);
        }
    }

    @Override
    public void addModelChangeListener(DrawModelListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeModelChangeListener(DrawModelListener listener) {
        listeners.remove(listener);
    }

    /**
     * The draw command handler. Initialized here with a dummy implementation.
     */
    // TODO initialize with your implementation of the undo/redo-assignment.
    private DrawCommandHandler handler = new EmptyDrawCommandHandler();

    /**
     * Retrieve the draw command handler in use.
     *
     * @return the draw command handler.
     */
    public DrawCommandHandler getDrawCommandHandler() {
        return handler;
    }

    @Override
    public void setFigureIndex(Figure f, int index) {
        if(index < 0 || index >= figures.size())
            throw new IndexOutOfBoundsException("Parameter index not withing boundaries of list");
        if(!figures.contains(f))
            throw new IllegalArgumentException("Figure does not exist in collection");

        if(figures.remove(f)){
            figures.add(index,f);
        }

        notifyListeners(f, DrawModelEvent.Type.DRAWING_CHANGED);
    }

    @Override
    public void removeAllFigures() {
        for(Figure f : figures){
            f.removeFigureListener(this);
        }
        figures.clear();
        notifyListeners(null, DrawModelEvent.Type.DRAWING_CLEARED);
    }

    private void notifyListeners(Figure f, DrawModelEvent.Type eventType) {
        for (DrawModelListener listener : listeners) {
            listener.modelChanged(new DrawModelEvent(this, f, eventType));
        }
    }

    @Override
    public void figureChanged(FigureEvent e) {
        notifyListeners(null, DrawModelEvent.Type.FIGURE_CHANGED);
    }

}
