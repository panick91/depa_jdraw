package jdraw.figures;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;
import jdraw.handles.Handle;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

/**
 * Created by Patrick on 12.01.2016.
 */
public class BundleDecorator extends AbstractDecoratorFigure {

    public BundleDecorator(Figure f) {
        super(f);
    }

    @Override
    public java.util.List<FigureHandle> getHandles() {
        List<FigureHandle> handles = new ArrayList<FigureHandle>() {};

        List<FigureHandle> originals = super.figure.getHandles();

        for(FigureHandle h : originals){
            handles.add(new HandleDecorator(h));
        }

        return handles;
    }

    private final class HandleDecorator implements FigureHandle {

        private FigureHandle handle;

        public HandleDecorator(FigureHandle fh) {
            handle = fh;
        }

        @Override
        public Figure getOwner() {
            return BundleDecorator.this;
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
//            handle.startInteraction(x, y, e, v);
        }

        @Override
        public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
//            handle.dragInteraction(x, y, e, v);
        }

        @Override
        public void stopInteraction(int x, int y, MouseEvent e, DrawView v) {
//            handle.stopInteraction(x, y, e, v);
        }
    }
}
