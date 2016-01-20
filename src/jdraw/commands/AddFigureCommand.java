package jdraw.commands;

import jdraw.framework.DrawCommand;
import jdraw.framework.DrawModel;
import jdraw.framework.Figure;

/**
 * Created by Patrick on 16.01.2016.
 */
public class AddFigureCommand implements DrawCommand {

    private DrawModel model;
    private Figure figure;

    public AddFigureCommand(DrawModel model, Figure figure) {
        this.model = model;
        this.figure = figure;
    }

    @Override
    public void redo() {
        model.addFigure(figure);
    }

    @Override
    public void undo() {
        model.removeFigure(figure);
    }
}
