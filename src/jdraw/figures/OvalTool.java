/*
 * Copyright (c) 2000-2016 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package jdraw.figures;

import jdraw.framework.DrawContext;
import jdraw.framework.Figure;

import javax.swing.*;
import java.awt.*;

/**
 * This tool defines a mode for drawing rectangles.
 *
 * @author Christoph Denzler
 * @version 2.1, 27.09.07
 * @see Figure
 */
public class OvalTool extends AbstractDragDrawTool {

    public OvalTool(DrawContext context, String name, String icon) {
        super(context, name, icon);
    }

    @Override
    protected Figure createFigure(Point p) {
        return new Oval(p);
    }
}
