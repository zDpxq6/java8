package ch08.ex06;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.Comparator;
import java.util.Objects;

public class Point2DComparator implements Comparator<Point2D> {

	@Override
	public int compare(Point2D o1, Point2D o2) {
		Objects.requireNonNull(o1, "a parameter is null");
		Objects.requireNonNull(o2, "a parameter is null");
		return Comparator.<Point2D, Double> comparing(e -> e.getX()).thenComparing(e -> e.getY()).compare(o1, o2);
	}

	public static void main(String[] args) {
		Comparator<Point2D> comp = new Point2DComparator();
		Point2D o1 = new Point(0, 1);
		Point2D o2 = new Point(0, 2);
		System.out.println(comp.compare(o1, o2));
	}
}