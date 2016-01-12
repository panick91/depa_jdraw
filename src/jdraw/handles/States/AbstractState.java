package jdraw.handles.States;

import jdraw.figures.AbstractRectangularFigure;
import jdraw.framework.DrawView;
import jdraw.framework.Figure;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Patrick on 11.10.2015.
 */
public abstract class AbstractState implements HandleState, Cloneable {

    private Figure owner;

    public AbstractState(Figure owner){
        this.owner = owner;
    }

    public Figure getOwner(){
        return owner;
    }

    public AbstractState clone(){
        try {
            AbstractState s = (AbstractState)super.clone();
            return s;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e.getMessage());
        }
    }
}
