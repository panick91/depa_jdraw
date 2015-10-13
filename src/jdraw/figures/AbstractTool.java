package jdraw.figures;

import jdraw.framework.DrawContext;
import jdraw.framework.DrawTool;
import jdraw.framework.DrawView;

import java.awt.*;

/**
 * Created by Patrick on 13.10.2015.
 */
public abstract class AbstractTool implements DrawTool {

    /**
     * the image resource path.
     */
    protected static final String IMAGES = "/images/";

    /**
     * The context we use for drawing.
     */
    protected DrawContext context;

    /**
     * The context's view. This variable can be used as a shortcut, i.e.
     * instead of calling context.getView().
     */
    protected DrawView view;

    /**
     * Temporary variable.
     * During rectangle creation this variable refers to the point the
     * mouse was first pressed.
     */
    protected Point anchor = null;

    /**
     * Deactivates the current mode by resetting the cursor
     * and clearing the status bar.
     * @see jdraw.framework.DrawTool#deactivate()
     */
    public void deactivate() {
        this.context.showStatusText("");
    }

    @Override
    public Cursor getCursor() {
        return Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);
    }
}
