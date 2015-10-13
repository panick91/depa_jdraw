package jdraw.figures;

import jdraw.framework.FigureHandle;
import jdraw.handles.rectfigures.RectFigureHandle;
import jdraw.handles.rectfigures.States.East;
import jdraw.handles.rectfigures.States.North;
import jdraw.handles.rectfigures.States.South;
import jdraw.handles.rectfigures.States.West;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Patrick on 03.10.2015.
 */
public class Circle extends AbstractFigure {

    private java.awt.geom.Ellipse2D.Double ellipse;

    public Circle(double x, double y, double width, double height) {
        ellipse = new Ellipse2D.Double(x, y, width, height);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval((int) ellipse.x, (int) ellipse.y,
                (int) ellipse.width, (int) ellipse.height);
        g.setColor(Color.BLACK);
        g.drawOval((int) ellipse.x, (int) ellipse.y,
                (int) ellipse.width, (int) ellipse.height);
    }

    @Override
    public void move(int dx, int dy) {
        if (dx != 0 || dy != 0) {
            ellipse.setFrame(ellipse.x + dx, ellipse.y + dy, ellipse.width, ellipse.height);
            notifyObservers();
        }
    }

    @Override
    public boolean contains(int x, int y) {
        return ellipse.contains(x, y);
    }

    @Override
    public void setBounds(Point origin, Point corner) {
        ellipse.setFrameFromDiagonal(origin, corner);
        notifyObservers();
    }

    @Override
    public Rectangle getBounds() {
        return ellipse.getBounds();
    }

    @Override
    public List<FigureHandle> getHandles() {
        List<FigureHandle> handles = new LinkedList<>();
        handles.add(new RectFigureHandle(new North(this)));
        handles.add(new RectFigureHandle(new East(this)));
        handles.add(new RectFigureHandle(new South(this)));
        handles.add(new RectFigureHandle(new West(this)));
        return handles;
    }
}
