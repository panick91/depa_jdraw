/*
 * Copyright (c) 2000-2016 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package jdraw.figures;

import jdraw.framework.DrawContext;
import jdraw.framework.DrawTool;
import jdraw.framework.DrawView;
import jdraw.framework.Figure;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;

/**
 * This tool defines a mode for drawing rectangles.
 *
 * @author Christoph Denzler
 * @version 2.1, 27.09.07
 * @see Figure
 */
public class CircleTool extends AbstractTool {


    /**
     * Temporary variable. During rectangle creation (during a
     * mouse down - mouse drag - mouse up cycle) this variable refers
     * to the new rectangle that is inserted.
     */
    private Circle newCircle = null;


    /**
     * Create a new rectangle tool for the given context.
     *
     * @param context a context to use this tool in.
     */
    public CircleTool(DrawContext context) {
        this.context = context;
        this.view = context.getView();
    }

    /**
     * Activates the Rectangle Mode. There will be a
     * specific menu added to the menu bar that provides settings for
     * Rectangle attributes
     */
    public void activate() {
        this.context.showStatusText("Circle Mode");
    }

    /**
     * Initializes a new Rectangle object by setting an anchor
     * point where the mouse was pressed. A new Rectangle is then
     * added to the model.
     *
     * @param x x-coordinate of mouse
     * @param y y-coordinate of mouse
     * @param e event containing additional information about which keys were pressed.
     * @see DrawTool#mouseDown(int, int, MouseEvent)
     */
    public void mouseDown(int x, int y, MouseEvent e) {
        if (newCircle != null) {
            throw new IllegalStateException();
        }
        anchor = new Point(x, y);
        newCircle = new Circle(x, y, 0, 0);
        view.getModel().addFigure(newCircle);
    }

    /**
     * During a mouse drag, the Rectangle will be resized according to the mouse
     * position. The status bar shows the current size.
     *
     * @param x x-coordinate of mouse
     * @param y y-coordinate of mouse
     * @param e event containing additional information about which keys were
     *          pressed.
     * @see DrawTool#mouseDrag(int, int, MouseEvent)
     */
    public void mouseDrag(int x, int y, MouseEvent e) {
        if (e.isShiftDown()) {
            // case top left or bottom right
            if ((x < anchor.x && y < anchor.y) || (x > anchor.x && y > anchor.y)) {
                if (Math.abs((x - anchor.x)) > Math.abs((y - anchor.y))) {
                    newCircle.setBounds(anchor, new Point(anchor.x + (x - anchor.x), anchor.y + (x - anchor.x)));
                } else {
                    newCircle.setBounds(anchor, new Point(anchor.x + (y - anchor.y), anchor.y + (y - anchor.y)));
                }
            }
            // case bottom left
            else if (x < anchor.x) {
                if (Math.abs((x - anchor.x)) > Math.abs((y - anchor.y))) {
                    newCircle.setBounds(anchor, new Point(anchor.x + (x - anchor.x), anchor.y - (x - anchor.x)));
                } else {
                    newCircle.setBounds(anchor, new Point(anchor.x - (y - anchor.y), anchor.y + (y - anchor.y)));
                }
            }
            // case top right
            else {
                if (Math.abs((x - anchor.x)) > Math.abs((y - anchor.y))) {
                    newCircle.setBounds(anchor, new Point(anchor.x + (x - anchor.x), anchor.y - (x - anchor.x)));
                } else {
                    newCircle.setBounds(anchor, new Point(anchor.x - (y - anchor.y), anchor.y + (y - anchor.y)));
                }
            }
        } else {
            newCircle.setBounds(anchor, new Point(x, y));
        }

        java.awt.Rectangle r = newCircle.getBounds();
        this.context.showStatusText("w: " + r.width + ", h: " + r.height);
    }

    /**
     * When the user releases the mouse, the Rectangle object is updated
     * according to the color and fill status settings.
     *
     * @param x x-coordinate of mouse
     * @param y y-coordinate of mouse
     * @param e event containing additional information about which keys were
     *          pressed.
     * @see DrawTool#mouseUp(int, int, MouseEvent)
     */

    public void mouseUp(int x, int y, MouseEvent e) {
        newCircle = null;
        anchor = null;
        this.context.showStatusText("Circle Mode");
    }

    @Override
    public Icon getIcon() {
        return new ImageIcon(getClass().getResource(IMAGES + "oval.png"));
    }

    @Override
    public String getName() {
        return "Circle";
    }
}
