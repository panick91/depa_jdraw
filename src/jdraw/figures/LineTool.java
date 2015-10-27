/*
 * Copyright (c) 2000-2016 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package jdraw.figures;

import jdraw.framework.DrawContext;
import jdraw.framework.DrawTool;
import jdraw.framework.Figure;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * This tool defines a mode for drawing lines.
 *
 * @see Figure
 *
 * @author  Christoph Denzler
 * @version 2.1, 27.09.07
 */
public class LineTool extends AbstractDrawTool {

	private DrawContext context;
	private Point anchor;
	private Line newLine;

	/**
	 * Create a new line tool for the given context.
	 * @param context a context to use this tool in.
	 */
	public LineTool(DrawContext context, String name, String icon) {
		super(name, icon);
		this.context = context;
	}

	/**
	 * Activates the Rectangle Mode. There will be a
	 * specific menu added to the menu bar that provides settings for
	 * Rectangle attributes
	 */
	public void activate() {
		this.context.showStatusText("Line Mode");
	}

	/**
	 * Initializes a new Rectangle object by setting an anchor
	 * point where the mouse was pressed. A new Rectangle is then
	 * added to the model.
	 * @param x x-coordinate of mouse
	 * @param y y-coordinate of mouse
	 * @param e event containing additional information about which keys were pressed.
	 *
	 * @see DrawTool#mouseDown(int, int, MouseEvent)
	 */
	public void mouseDown(int x, int y, MouseEvent e) {
		if (newLine != null) {
			throw new IllegalStateException();
		}
		anchor = new Point(x, y);
		newLine = new Line(x, y, x, y);
		context.getModel().addFigure(newLine);
	}

	/**
	 * During a mouse drag, the Rectangle will be resized according to the mouse
	 * position. The status bar shows the current size.
	 *
	 * @param x   x-coordinate of mouse
	 * @param y   y-coordinate of mouse
	 * @param e   event containing additional information about which keys were
	 *            pressed.
	 *
	 * @see DrawTool#mouseDrag(int, int, MouseEvent)
	 */
	public void mouseDrag(int x, int y, MouseEvent e) {
		newLine.setBounds(anchor, new Point(x, y));
		java.awt.Rectangle r = newLine.getBounds();
		this.context.showStatusText("w: " + r.width + ", h: " + r.height);
	}

	/**
	 * When the user releases the mouse, the Rectangle object is updated
	 * according to the color and fill status settings.
	 *
	 * @param x   x-coordinate of mouse
	 * @param y   y-coordinate of mouse
	 * @param e   event containing additional information about which keys were
	 *            pressed.
	 *
	 * @see DrawTool#mouseUp(int, int, MouseEvent)
	 */
	public void mouseUp(int x, int y, MouseEvent e) {
		newLine = null;
		anchor = null;
		this.context.showStatusText("Rectangle Mode");
	}


	@Override
	public String getName() {
		return "Line";
	}
}
