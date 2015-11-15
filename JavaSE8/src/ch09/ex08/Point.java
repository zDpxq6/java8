package ch09.ex08;

import java.math.BigInteger;

public class Point {
	private int x;
	private int y;

	public int compareTo(Point other) {
		int diff = compareInt(this.x, other.x);
		return diff != 0 ? diff : compareInt(this.y, other.y);
	}

	private static final int RADIX = 10;

	private static BigInteger createBigInteger(int intValue) {
		return new BigInteger(Integer.toString(intValue), RADIX);
	}

	private int compareInt(int aByInt, int bByInt) {
		BigInteger a = createBigInteger(aByInt);
		BigInteger b = createBigInteger(bByInt);
		BigInteger diff = null;
		if (a.compareTo(b) == 0) {
			return 0;
		} else if (a.compareTo(b) < 0 /* a < b */) {// 引数の方が大きければ -1
			diff = b.subtract(a);
		} else {// if (0 < a.compareTo(b)/* b < a */)
			diff = a.subtract(b);
		}
		if (createBigInteger(Integer.MAX_VALUE).compareTo(diff) < 0) {
			return Integer.MAX_VALUE;
		} else {
			return Integer.parseInt(a.subtract(b).toString());
		}
	}
}
