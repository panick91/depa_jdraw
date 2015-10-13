package jdraw.figures;

import jdraw.handles.RectFigureHandle;
import jdraw.handles.States.State;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Patrick on 13.10.2015.
 */
public abstract class RectangularFigure extends AbstractFigure {
    protected List<RectFigureHandle> handles = new LinkedList<>();

    /**
     * Searches for the RectFigureHandle which is currently in state2
     * and sets it so state1.
     * @param state1 New state (role) to set
     * @param state2 State which needs to removed of handle
     */
    public void exchangeStates(State state1, State state2) {
        List<RectFigureHandle> hs = new ArrayList<>(handles);

        RectFigureHandle handleToExchange = null;
        for (RectFigureHandle h : hs) {
            State s = h.getState();
            if (s != null && s.getClass().equals(state2.getClass())) {
                handleToExchange = h;
                break;
            }
        }

        if(handleToExchange != null){
            handleToExchange.setState(state1);
        }
    }
}
