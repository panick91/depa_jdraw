package jdraw.test;

import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureListener;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class RectangleTest extends FigureTest {


	@Before
	public void setUp() {
		super.f = new jdraw.figures.Rect(0, 0, 20, 10);
	}

}
