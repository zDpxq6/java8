package ch08.ex14;

import java.util.Objects;
import java.util.function.Function;

public class Processor {

	/**
	 * 引数を処理する
	 *
	 * @param arg
	 *            引数, nullは許されない.
	 * @param f
	 *            処理, nullは許されない.
	 * @return
	 * @throws NullPointerException
	 *             引数がnullの場合
	 */
	public static <T, U> U process(T arg, Function<T, U> f) {
		Objects.requireNonNull(f, "an arguments is null");
		return f.apply(Objects.requireNonNull(arg, "an arguments is null"));
	}

	public static void main(String[] args) {
		try {
			process(null, null);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

		try {
			process("", null);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

		try {
			System.out.println(process("test", String::toUpperCase));
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}
}
