/*
 * Copyright (c) 2000-2016 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package jdraw.figures;

import jdraw.framework.*;
import jdraw.handles.Handle;
import jdraw.handles.States.NorthEast;
import jdraw.handles.States.NorthWest;
import jdraw.handles.States.SouthEast;
import jdraw.handles.States.SouthWest;

import java.awt.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents rectangles in JDraw.
 *
 * @author Christoph Denzler
 */
public class Group extends AbstractFigure implements FigureGroup {

    private Handle NW = new Handle(new NorthWest(this));
    private Handle NE = new Handle(new NorthEast(this));
    private Handle SW = new Handle(new SouthWest(this));
    private Handle SE = new Handle(new SouthEast(this));

    private List<Figure> parts;

    public Group(List<Figure> parts, DrawModel model) {
        if (parts == null || parts.size() <= 1)
            throw new IllegalArgumentException();

        this.parts = new LinkedList<>();
        for (Figure f : model.getFigures()) {
            if (parts.contains(f)) {
                this.parts.add(f);
            }
        }
    }

    /**
     * Draw the rectangle to the given graphics context.
     *
     * @param g the graphics context to use for drawing.
     */
    public void draw(Graphics g) {
        for (Figure f : parts) {
            f.draw(g);
        }
    }

    @Override
    public void setBounds(Point origin, Point corner) {
//        rectangle.setFrameFromDiagonal(origin, corner);
//        notifyObservers(new FigureEvent(this));
    }

    @Override
    public void move(int dx, int dy) {
        if (dx != 0 || dy != 0) {
            for (Figure f : parts) {
                f.move(dx, dy);
                super.notifyObservers(new FigureEvent(this));
            }
        }
    }

    @Override
    public boolean contains(int x, int y) {
        for (Figure f : parts) {
            if (f.contains(x, y)) return true;
        }
        return false;
    }

    @Override
    public Rectangle getBounds() {
        Rectangle rect = new Rectangle(parts.get(0).getBounds());

        for (Figure f : parts) {
            rect.add(f.getBounds());
        }

        return rect;
    }

    @Override
    public List<FigureHandle> getHandles() {
        List<FigureHandle> handles = new LinkedList<>();
        if(NW == null) NW = new Handle(new NorthWest(this));
        handles.add(NW);
        if(NE == null) NE = new Handle(new NorthEast(this));
        handles.add(NE);
        if(SE == null) SE = new Handle(new SouthEast(this));
        handles.add(SE);
        if(SW == null) SW = new Handle(new SouthWest(this));
        handles.add(SW);
        return handles;
    }

    @Override
    public void swapHorizontal() {
    }

    @Override
    public void swapVertical() {
    }


    @Override
    public Iterable<Figure> getFigureParts() {
        return Collections.unmodifiableList(parts);
    }

    @Override
    public Group clone() {
        Group g = (Group) super.clone();
        g.NW = null;
        g.NE = null;
        g.SW = null;
        g.SE = null;
        g.parts = new LinkedList<>();
        for(Figure f : this.parts){
            g.parts.add(f.clone());
        }
        return g;
    }
}
