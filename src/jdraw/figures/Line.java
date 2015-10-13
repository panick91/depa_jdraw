/*
 * Copyright (c) 2000-2016 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package jdraw.figures;

import jdraw.framework.FigureHandle;
import jdraw.handles.linefigures.LineEndHandle;
import jdraw.handles.linefigures.LineStartHandle;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents rectangles in JDraw.
 *
 * @author Christoph Denzler
 */
public class Line extends AbstractFigure {
    /**
     * Use the java.awt.Rectangle in order to save/reuse code.
     */
    private Line2D.Double line;
    private int HIT_BOX_SIZE = 6;

    /**
     * Create a new line of the given dimension.
     *
     * @param x the x-coordinate of the upper left corner of the line
     * @param y the y-coordinate of the upper left corner of the line
     * @param w the line's width
     * @param h the line's height
     */
    public Line(int x, int y, int w, int h) {
        line = new Line2D.Double(x, y, w, h);
    }

    /**
     * Draw the line to the given graphics context.
     *
     * @param g the graphics context to use for drawing.
     */
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawLine((int) line.x1, (int) line.y1,
                (int) line.x2, (int) line.y2);
    }

    @Override
    public void setBounds(Point origin, Point corner) {
        line.setLine(origin, corner);
        notifyObservers();
    }

    @Override
    public void move(int dx, int dy) {
        if (dx != 0 || dy != 0) {
            line.setLine((int) line.x1 + dx
                    , (int) line.y1 + dy
                    , (int) line.x2 + dx
                    , (int) line.y2 + dy);
            notifyObservers();
        }
    }

    @Override
    public boolean contains(int x, int y) {
        return line.intersects(x - HIT_BOX_SIZE / 2, y - HIT_BOX_SIZE / 2, HIT_BOX_SIZE, HIT_BOX_SIZE);
    }

    @Override
    public Rectangle getBounds() {
        return line.getBounds();
    }

    public Point getStartPoint(){
        return new Point((int)line.getX1(), (int)line.getY1());
    }

    public Point getEndPoint(){
        return new Point((int)line.getX2(), (int)line.getY2());
    }

    @Override
    public List<FigureHandle> getHandles() {
        List<FigureHandle> handles = new LinkedList<>();
        handles.add(new LineStartHandle(this));
        handles.add(new LineEndHandle(this));
        return handles;
    }

}
