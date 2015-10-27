/*
 * Copyright (c) 2000-2016 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package jdraw.figures;

import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import jdraw.framework.*;

/**
 * This tool defines a mode for drawing rectangles.
 *
 * @see jdraw.framework.Figure
 *
 * @author  Christoph Denzler
 * @version 2.1, 27.09.07
 */
public class RectTool extends AbstractDragDrawTool {

	public RectTool(DrawContext context, String name, String icon) {
		super(context, name, icon);
	}

	@Override
	protected Figure createFigure(Point p) {
		return new Rect(p);
	}
}
