package jdraw.figures;

import jdraw.framework.DrawToolFactory;

/**
 * Created by Patrick on 20.01.2016.
 */
public abstract class AbstractDrawToolFactory
        implements DrawToolFactory {
    private String name; // name of the tool
    private String icon; // name of the icon
    @Override
    public void setName(String name) { this.name = name; }
    @Override
    public String getName() { return name; }
    @Override
    public void setIconName(String name) { this.icon= name; }
    @Override
    public String getIconName() { return icon; }
}
