package jdraw.handles;

import jdraw.framework.FigureHandle;

import java.awt.*;

/**
 * Created by Patrick on 13.10.2015.
 */
public abstract class Handle implements FigureHandle {

    protected final int handleSize = 6;

    @Override
    public final void draw(Graphics g) {
        Point loc = getLocation();
        g.setColor(Color.WHITE);
        g.fillRect(loc.x - handleSize / 2, loc.y - handleSize / 2, handleSize, handleSize);
        g.setColor(Color.BLACK);
        g.drawRect(loc.x - handleSize / 2, loc.y - handleSize / 2, handleSize, handleSize);
    }

    @Override
    public final boolean contains(int x, int y) {
        Point loc = getLocation();
        int r = handleSize / 2;

        return (x >= loc.x - r && x <= loc.x + r) &&
                (y >= loc.y - r && y <= loc.y + r);
    }
}
