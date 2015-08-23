package ch03.exercise03;

import java.util.Objects;
import java.util.function.Supplier;

/*
3．Java1.4は, 予約語assertでもって, Java言語にアサーションを追加しました.
なぜ, アサーションはライブラリの機能として提供されなかったのでしょう.
Java8ではライブラリの機能として実装することができますか.
*/
//「ライブラリの機能として提供する」の意味がわからない

//遅延実行の機能がなかったため, 常に評価する必要があった.
//assert(boolean isEnableAssertion, boolean condition condition, T expression)
//引数を全て評価した上で, assertを実行しなければならないので, conditionの状況によらず, expressionが評価された.
//これはconditionがnullの場合は不要な評価である.
public final class AssertionUtil {

	private AssertionUtil() {
	}

	private static boolean isAsserting = false;

	public static boolean isAsserting() {
		return isAsserting;
	}

	public static void enableAsserting() {
		isAsserting = true;
	}

	public static void disableAsserting() {
		isAsserting = false;
	}

	public static <T> void assertIf(Supplier<Boolean> condition) {
		Objects.requireNonNull(condition, "A parameter: condition is null.");
		if (isAsserting) {
			assertIfInternal(condition, null);
		}
	}

	public static <T> void assertIf(Supplier<Boolean> condition, Supplier<T> expression) {
		Objects.requireNonNull(condition, "A parameter: condition is null.");
		if (isAsserting) {
			assertIfInternal(condition, expression);
		}
	}

	private static <T> void assertIfInternal(Supplier<Boolean> condition, Supplier<T> expression) {
		if (condition.get()) {
			if (expression != null) {
				throw new AssertionError(expression.get());
			} else {
				throw new AssertionError();
			}
		}
	}

}
