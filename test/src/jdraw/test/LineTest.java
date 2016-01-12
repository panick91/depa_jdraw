package jdraw.test;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureListener;

public class LineTest extends FigureTest {

	@Before
	public void setUp() {
		f = new jdraw.figures.Line(0, 0, 20, 10);
	}

}
