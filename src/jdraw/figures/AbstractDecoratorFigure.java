package jdraw.figures;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;
import jdraw.framework.FigureListener;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Patrick on 12.01.2016.
 */
public abstract class AbstractDecoratorFigure implements Figure {

    protected Figure figure;

    public AbstractDecoratorFigure(Figure f) {
        figure = f;
    }

    @Override
    public void draw(Graphics g) {
        figure.draw(g);
    }

    @Override
    public void move(int dx, int dy) {
        figure.move(dx, dy);
    }

    @Override
    public boolean contains(int x, int y) {
        return figure.contains(x, y);
    }

    @Override
    public void setBounds(Point origin, Point corner) {
        figure.setBounds(origin, corner);
    }

    @Override
    public Rectangle getBounds() {
        return figure.getBounds();
    }

    @Override
    public List<FigureHandle> getHandles() {
        List<FigureHandle> handles = new LinkedList<>();
        for(FigureHandle h: figure.getHandles()) {
            handles.add(new HandleDecorator(h));
        }
        return Collections.unmodifiableList(handles);
    }

    @Override
    public void addFigureListener(FigureListener listener) {
        figure.addFigureListener(listener);
    }

    @Override
    public void removeFigureListener(FigureListener listener) {
        figure.removeFigureListener(listener);
    }

    @Override
    public Figure clone() {
        try {
            AbstractDecoratorFigure f = (AbstractDecoratorFigure)super.clone();
            f.figure = f.figure.clone();
            return f;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }

    @Override
    public void swapHorizontal() {
        figure.swapHorizontal();
    }

    @Override
    public void swapVertical() {
        figure.swapHorizontal();
    }

    private final class HandleDecorator implements FigureHandle {

        private FigureHandle handle;

        public HandleDecorator(FigureHandle fh) {
            handle = fh;
        }

        @Override
        public Figure getOwner() {
            return AbstractDecoratorFigure.this;
        }

        @Override
        public Point getLocation() {
            return handle.getLocation();
        }

        @Override
        public void draw(Graphics g) {
            handle.draw(g);
        }

        @Override
        public Cursor getCursor() {
            return handle.getCursor();
        }

        @Override
        public boolean contains(int x, int y) {
            return handle.contains(x, y);
        }

        @Override
        public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
            handle.startInteraction(x, y, e, v);
        }

        @Override
        public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
            handle.dragInteraction(x, y, e, v);
        }

        @Override
        public void stopInteraction(int x, int y, MouseEvent e, DrawView v) {
            handle.stopInteraction(x, y, e, v);
        }
    }

}
