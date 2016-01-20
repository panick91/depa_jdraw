package jdraw.commands;

import jdraw.framework.DrawCommand;
import jdraw.framework.Figure;

import java.awt.*;

/**
 * Created by Patrick on 16.01.2016.
 */
public class SetBoundsCommand implements DrawCommand {
    private Figure figure; // Figure on which setBoundswas appied
    private Point fromOrig, fromCorn, toOrig, toCorn;

    public SetBoundsCommand(Figure figure,
                            Point fromOrig, Point fromCorn, Point toOrig, Point toCorn) {
        this.figure = figure;
        this.fromOrig = fromOrig;
        this.fromCorn = fromCorn;
        this.toOrig = toOrig;
        this.toCorn = toCorn;
    }

    public void redo() {
        figure.setBounds(toOrig, toCorn);
    }

    public void undo() {
        figure.setBounds(fromOrig, fromCorn);
    }
}
