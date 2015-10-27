/*
 * Copyright (c) 2000-2016 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package jdraw.figures;

import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureGroup;
import jdraw.framework.FigureHandle;
import jdraw.handles.Handle;
import jdraw.handles.States.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents rectangles in JDraw.
 *
 * @author Christoph Denzler
 */
public class Group extends AbstractFigure implements FigureGroup {

    private Handle NW = new Handle(new NorthWest(this));
    private Handle NE = new Handle(new East(this));
    private Handle SW = new Handle(new South(this));
    private Handle SE = new Handle(new West(this));

    private List<Figure> parts;

    public Group(List<Figure> parts) {
        this.parts = parts;
    }

    /**
     * Draw the rectangle to the given graphics context.
     *
     * @param g the graphics context to use for drawing.
     */
    public void draw(Graphics g) {
        for (Figure f : new ArrayList<>(parts)) {
            f.draw(g);
        }
    }

    @Override
    public void setBounds(Point origin, Point corner) {
//        rectangle.setFrameFromDiagonal(origin, corner);
        notifyObservers(new FigureEvent(this));

    }

    @Override
    public void move(int dx, int dy) {
        for (Figure f : new ArrayList<>(parts)) {
            f.move(dx, dy);
        }
    }

    @Override
    public boolean contains(int x, int y) {
        for(Figure f : new ArrayList<>(parts)){
            if(f.contains(x,y)) return true;
        }
        return false;
    }

    @Override
    public Rectangle getBounds() {
        Rectangle rect = new Rectangle(parts.get(0).getBounds());

        for(Figure f : new ArrayList<>(parts)){
            rect.add(f.getBounds());
        }

        return rect;
    }

    @Override
    public List<FigureHandle> getHandles() {
        List<FigureHandle> handles = new LinkedList<>();
        handles.add(NW);
        handles.add(NE);
        handles.add(SW);
        handles.add(SE);
        return handles;
    }

    @Override
    public void swapHorizontal() {
        HandleState NEState = NE.getState();
        HandleState NWState = NW.getState();
        HandleState SEState = SE.getState();
        HandleState SWState = SW.getState();
        NE.setState(NWState);
        NW.setState(NEState);
        SE.setState(SWState);
        SW.setState(SEState);
    }

    @Override
    public void swapVertical() {
        HandleState NEState = NE.getState();
        HandleState NWState = NW.getState();
        HandleState SEState = SE.getState();
        HandleState SWState = SW.getState();
        NE.setState(SEState);
        NW.setState(SWState);
        SE.setState(NEState);
        SW.setState(NWState);
    }


    @Override
    public Iterable<Figure> getFigureParts() {
        return parts;
    }
}
