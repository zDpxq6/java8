package ch08.ex01;

import static ch08.ex01.UnsignedCalcurator.addUnsigned;
import static ch08.ex01.UnsignedCalcurator.divideUnsigned;
import static ch08.ex01.UnsignedCalcurator.subtructUnsigned;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class CalcDemoTest {

	@Test
	public void 自然数を渡すと単純に加算して返す1() {
		long actual = addUnsigned(0, 1);
		assertThat(actual, is(1l));
	}

	@Test
	public void 自然数を渡すと単純に加算して返す2() {
		long actual = addUnsigned(1, Integer.MAX_VALUE);
		assertThat(actual, is((long) Integer.MAX_VALUE + 1));
	}

	@Test
	public void 負の整数を渡すと符号なし整数に変えてから加算して返す1() {
		long actual = addUnsigned(0, Integer.MIN_VALUE);
		assertThat(actual, is((long) Integer.MAX_VALUE + 1));
	}

	@Test
	public void 負の整数を渡すと符号なし整数に変えてから加算して返す2() {
		long actual = addUnsigned(-1, -1);
		assertThat(actual, is((long) (Math.pow(2, 32) - 1) * 2));
	}

	@Test
	public void 自然数を渡すと単純に減算して返す1() {
		long actual = subtructUnsigned(0, 1);
		assertThat(actual, is(-1l));
	}

	@Test
	public void 自然数を渡すと単純に減算して返す2() {
		long actual = subtructUnsigned(0, Integer.MAX_VALUE);
		assertThat(actual, is(-1l * Integer.MAX_VALUE));
	}

	@Test
	public void 負の整数を渡すと符号なし整数に変えてから減算して返す1() {
		long actual = subtructUnsigned(0, Integer.MIN_VALUE);
		long expected = -1 * ((long) Integer.MAX_VALUE + 1);
		assertThat(actual, is(expected));
	}

	@Test
	public void 負の整数を渡すと符号なし整数に変えてから減算して返す2() {
		long actual = subtructUnsigned(-1, -1);
		assertThat(actual, is(0l));
	}

	@Test
	public void 自然数同士の割り算1() {
		long actual = divideUnsigned(4, 2);
		assertThat(actual, is(2l));
	}

	@Test
	public void 自然数同士の割り算2() {
		long actual = divideUnsigned(5, 2);
		assertThat(actual, is(2l));
	}

	@Test(expected = IllegalArgumentException.class)
	public void 自然数同士の割り算3() {
		long actual = divideUnsigned(5, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void 割られる数が負の数() {
		long actual = divideUnsigned(-1, 1);
		int expected = Integer.divideUnsigned(-1, 1);
		System.out.println(expected);
		assertThat((int) actual, is(expected));
	}
}
