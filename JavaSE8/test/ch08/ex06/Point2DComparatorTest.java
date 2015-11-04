package ch08.ex06;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.Comparator;

import org.junit.Test;

public class Point2DComparatorTest {

	private static final Comparator<Point2D> TARGET = new Point2DComparator();

	@Test
	public void test1() {
		Point2D o1 = new Point(0, 0);
		Point2D o2 = new Point(0, 0);
		int actual = TARGET.compare(o1, o2);
		assertThat(actual, is(0));
	}

	@Test
	public void test2() {
		Point2D o1 = new Point(Integer.MIN_VALUE, Integer.MIN_VALUE);
		Point2D o2 = new Point(Integer.MIN_VALUE, Integer.MIN_VALUE);
		int actual = TARGET.compare(o1, o2);
		assertThat(actual, is(0));
	}

	@Test
	public void test3() {
		Point2D o1 = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);
		Point2D o2 = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);
		int actual = TARGET.compare(o1, o2);
		assertThat(actual, is(0));
	}

	@Test
	public void test4() {
		Point2D o1 = new Point(Integer.MIN_VALUE, 0);
		Point2D o2 = new Point(0, 0);
		int actual = TARGET.compare(o1, o2);
		assertThat(actual, is(-1));
	}

	@Test
	public void test5() {
		Point2D o1 = new Point(0, 0);
		Point2D o2 = new Point(Integer.MIN_VALUE, 0);
		int actual = TARGET.compare(o1, o2);
		assertThat(actual, is(1));
	}

	@Test
	public void test6() {
		Point2D o1 = new Point(0, Integer.MIN_VALUE);
		Point2D o2 = new Point(0, 0);
		int actual = TARGET.compare(o1, o2);
		assertThat(actual, is(-1));
	}

	@Test
	public void test7() {
		Point2D o1 = new Point(0, 0);
		Point2D o2 = new Point(0, Integer.MIN_VALUE);
		int actual = TARGET.compare(o1, o2);
		assertThat(actual, is(1));
	}

}