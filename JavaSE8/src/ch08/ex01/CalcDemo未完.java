package ch08.ex01;

import java.util.function.BinaryOperator;

public class CalcDemo未完 {

	public static void main(String[] args) {

	}

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
		if(b == 0){
			throw new IllegalArgumentException("the parameter \"b\" is 0");
		}
		return calcurateUnsigned(a, b, (t, u) -> t / u);
	}
}
