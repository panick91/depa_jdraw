/*
 * Copyright (c) 2000-2016 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package jdraw.figures;

import jdraw.framework.FigureHandle;
import jdraw.handles.Handle;
import jdraw.handles.States.*;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents rectangles in JDraw.
 *
 * @author Christoph Denzler
 */
public class Rect extends AbstractRectangularFigure {

    private Handle NW = new Handle(new NorthWest(this));
    private Handle N  = new Handle(new North(this));
    private Handle NE = new Handle(new NorthEast(this));
    private Handle E  = new Handle(new East(this));
    private Handle SE = new Handle(new SouthEast(this));
    private Handle S  = new Handle(new South(this));
    private Handle SW = new Handle(new SouthWest(this));
    private Handle W  = new Handle(new West(this));

    public Rect(Point p) {
        super(p);
    }

    public Rect(int x, int y, int w, int h) {
        super(x, y, w, h);
    }

    /**
     * Draw the rectangle to the given graphics context.
     *
     * @param g the graphics context to use for drawing.
     */
    public void draw(Graphics g) {
        Rectangle r = getBounds();

        g.setColor(Color.WHITE);
        g.fillRect(r.x, r.y, r.width, r.height);
        g.setColor(Color.BLACK);
        g.drawRect(r.x, r.y, r.width, r.height);
    }

    @Override
    public List<FigureHandle> getHandles() {
        if (handles.size() == 0) {
            handles.add(NW);
            handles.add(N);
            handles.add(NE);
            handles.add(E);
            handles.add(SE);
            handles.add(S);
            handles.add(SW);
            handles.add(W);
        }
        return new LinkedList<>(handles);
    }

    @Override
    public void swapHorizontal() {
        HandleState NWstate = NW.getState();
        HandleState NEstate = NE.getState();
        HandleState SWstate = SW.getState();
        HandleState SEstate = SE.getState();
        HandleState WState = W.getState();
        HandleState EState = E.getState();
        NW.setState(NEstate);
        NE.setState(NWstate);
        SW.setState(SEstate);
        SE.setState(SWstate);
        W.setState(EState);
        E.setState(WState);
    }

    @Override
    public void swapVertical() {
        HandleState NWstate = NW.getState();
        HandleState NEstate = NE.getState();
        HandleState SWstate = SW.getState();
        HandleState SEstate = SE.getState();
        HandleState NState = N.getState();
        HandleState SState = S.getState();
        NW.setState(SWstate);
        NE.setState(SEstate);
        SW.setState(NWstate);
        SE.setState(NEstate);
        N.setState(SState);
        S.setState(NState);
    }


}
