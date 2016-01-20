package jdraw.figures;

import jdraw.framework.DrawContext;
import jdraw.framework.DrawTool;

/**
 * Created by Patrick on 20.01.2016.
 */
public class OvalToolFactory extends AbstractDrawToolFactory{
    @Override
    public DrawTool createTool(DrawContext c) {
        return new OvalTool(c, getName(), getIconName());
    }
}
