package jdraw.handles;

import jdraw.figures.Line;
import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;

import java.awt.*;

/**
 * Created by Patrick on 27.10.2015.
 */
public abstract class LineHandle implements FigureHandle {

    private final int handleSize = 6;
    private Line owner;

    public LineHandle(Line owner) {
        this.owner = owner;
    }

    @Override
    public Figure getOwner() {
        return owner;
    }

    @Override
    public final void draw(Graphics g) {
        Point loc = getLocation();
        g.setColor(Color.WHITE);
        g.fillRect(loc.x - handleSize / 2, loc.y - handleSize / 2, handleSize, handleSize);
        g.setColor(Color.BLACK);
        g.drawRect(loc.x - handleSize / 2, loc.y - handleSize / 2, handleSize, handleSize);
    }

    @Override
    public Cursor getCursor() {
        return Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR);
    }

    @Override
    public boolean contains(int x, int y) {
        Point loc = getLocation();
        int r = handleSize / 2;

        return (x >= loc.x - r && x <= loc.x + r) &&
                (y >= loc.y - r && y <= loc.y + r);
    }



}
