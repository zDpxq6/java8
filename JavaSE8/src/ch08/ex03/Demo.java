package ch08.ex03;

import java.util.function.BinaryOperator;

public class Demo {

	public static void main(String[] args) {
		// どれも同じ
		System.out.println(calcurateGCD(-2, 4, (m, n) -> m % n));
		System.out.println(calcurateGCD(-2, 4, Math::floorMod));
		System.out.println(calcurateGCD(-2, 4, Integer::remainderUnsigned));
	}

	/**
	 * 最大公約数を求める
	 *
	 * @param m
	 *            整数, 0は許されない
	 * @param n
	 *            整数, 0は許されない
	 * @param op
	 *            余りを求める関数
	 * @return 最大公約数
	 */
	public static final int calcurateGCD(int m, int n, BinaryOperator<Integer> op) {
		if (op == null) {
			throw new NullPointerException("a parameter \"op\" is null");
		}
		if (m == 0 || n == 0) {
			throw new ArithmeticException("neither m nor n can be 0");
		}
		if (m < n) {// mとnを入れ替える
			int tmp = n;
			n = m;
			m = tmp;
		}
		if (n == 0) {
			return m;
		} else {
			return calcurateGCD(Math.abs(n), op.apply(m, n), op);
		}
	}
}