package jdraw.figures;

import jdraw.framework.FigureHandle;
import jdraw.handles.Handle;
import jdraw.handles.States.*;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Patrick on 03.10.2015.
 */
public class Oval extends AbstractRectangularFigure {

    private Handle N = new Handle(new North(this));
    private Handle E = new Handle(new East(this));
    private Handle S = new Handle(new South(this));
    private Handle W = new Handle(new West(this));

    public Oval(Point p) {
        super(p);
    }

    public Oval(int x, int y, int w, int h) {
        super(x, y, w, h);
    }

    @Override
    public void draw(Graphics g) {
        Rectangle r = getBounds();

        g.setColor(Color.WHITE);
        g.fillOval(r.x, r.y, r.width, r.height);
        g.setColor(Color.BLACK);
        g.drawOval(r.x, r.y, r.width, r.height);
    }

    @Override
    public List<FigureHandle> getHandles() {
        List<FigureHandle> handles = new LinkedList<>();
        if (N == null) N = new Handle(new North(this));
        handles.add(N);
        if (E == null) E = new Handle(new East(this));
        handles.add(E);
        if (S == null) S = new Handle(new South(this));
        handles.add(S);
        if (W == null) W = new Handle(new West(this));
        handles.add(W);
        return handles;
    }

    @Override
    public void swapHorizontal() {
        HandleState WState = W.getState();
        HandleState EState = E.getState();
        W.setState(EState);
        E.setState(WState);
    }

    @Override
    public void swapVertical() {
        HandleState NState = N.getState();
        HandleState SState = S.getState();
        N.setState(SState);
        S.setState(NState);
    }

    @Override
    public Oval clone() {
        Oval o = (Oval) super.clone();
        o.N = null;
        o.E = null;
        o.S = null;
        o.W = null;
        return o;
    }
}
