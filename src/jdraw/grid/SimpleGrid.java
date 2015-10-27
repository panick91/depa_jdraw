package jdraw.grid;

import jdraw.framework.PointConstrainer;

import java.awt.*;

/**
 * Created by Patrick on 13.10.2015.
 */
public class SimpleGrid implements PointConstrainer{
    @Override
    public Point constrainPoint(Point p) {
//        System.out.println("SimpleGrid:constrainPoint: " + p);

        int x = (int)(Math.rint((double) p.x / 20) * 20);
        int y = (int)(Math.rint((double) p.y / 20) * 20);

        Point newP = new Point(x,y);

//        System.out.println("SimpleGrid:constrainPoint: " + newP);

        return newP;
    }

    @Override
    public int getStepX(boolean right) {
//        System.out.println("step x: right = " + right);
        return 20;
    }

    @Override
    public int getStepY(boolean down) {
//        System.out.println("step y: down = " + down);
        return 20;
    }

    @Override
    public void activate() {
//        System.out.println("SimpleGrid:activate()");
    }

    @Override
    public void deactivate() {
//        System.out.println("SimpleGrid:deactivate()");
    }

    @Override
    public void mouseDown() {
//        System.out.println("SimpleGrid:mouseDown()");
    }

    @Override
    public void mouseUp() {
//        System.out.println("SimpleGrid:mouseUp()");
    }
}
