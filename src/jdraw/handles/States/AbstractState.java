package jdraw.handles.States;

import jdraw.figures.AbstractRectangularFigure;
import jdraw.framework.DrawView;
import jdraw.framework.Figure;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Patrick on 11.10.2015.
 */
public abstract class AbstractState implements HandleState {

    private Figure owner;

    public AbstractState(Figure owner){
        this.owner = owner;
    }

    public Figure getOwner(){
        return owner;
    }
}
