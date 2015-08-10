package ch03.exercise03;

import java.util.Objects;
import java.util.function.Supplier;

/*
3．Java1.4は, 予約語assertでもって, Java言語にアサーションを追加しました.
なぜ, アサーションはライブラリの機能として提供されなかったのでしょう.
Java8ではライブラリの機能として実装することができますか.
*/
//「ライブラリの機能として提供する」の意味がわからない
public final class AssertUtil {
	private AssertUtil() {
	}

	public static <T> void asserts(Supplier<Boolean> condition, Supplier<T> expression) {
		Objects.requireNonNull(condition, "A parameter: condition is null.");
		if (condition.get()) {
			if (expression != null) {
				throw new AssertionError(expression.get());
			} else {
				throw new AssertionError();
			}
		}
	}

	public static void main(String[] args) {
		try {
			AssertUtil.asserts(() -> true, () -> "こんなかんじ");
		} catch (AssertionError e) {
			e.printStackTrace();
		}
	}
}
