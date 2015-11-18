package ch08.ex01;

import java.util.function.BinaryOperator;

public class UnsignedCalcurator {

	/**
	 * 符号つき整数を符号なし整数に変換して加算する
	 *
	 * @param signedInteger1
	 *            符号つき整数
	 * @param signedInteger2
	 *            符号つき整数
	 * @param f
	 * @return
	 */
	private static long calcurateUnsigned(int signedInteger1, int signedInteger2, BinaryOperator<Long> f) {
		if (Integer.MAX_VALUE < signedInteger1 || Integer.MAX_VALUE < signedInteger2) {
			throw new IllegalArgumentException();
		}
		long aUnsigned = Integer.toUnsignedLong(signedInteger1);
		long bUnsigned = Integer.toUnsignedLong(signedInteger2);
		return f.apply(aUnsigned, bUnsigned);
	}

	public static long addUnsigned(int a, int b) {
		return calcurateUnsigned(a, b, (t, u) -> t + u);
	}

	public static long subtructUnsigned(int a, int b) {
		return calcurateUnsigned(a, b, (t, u) -> t - u);
	}

	public static long divideUnsigned(int a, int b) {
		if (b == 0) {
			throw new ArithmeticException("divide by zero");
		}
		return calcurateUnsigned(a, b, (t, u) -> t / u);
	}

	public static long compareUnsigned(int a, int b) {
		return calcurateUnsigned(a, b, (t, u) -> (long) Long.compare(t, u));
	}

	public static void main(String[] args) {
		long myResult = divideUnsigned(Integer.MAX_VALUE, Integer.MIN_VALUE);
		long result = Long.divideUnsigned(Integer.MAX_VALUE, Integer.MIN_VALUE);
		System.out.println(myResult + ": " + result);
	}
}
