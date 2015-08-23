package ch03.exercise03;

import java.util.Objects;
import java.util.function.Supplier;

/*
3．Java1.4は, 予約語assertでもって, Java言語にアサーションを追加しました.
なぜ, アサーションはライブラリの機能として提供されなかったのでしょう.
Java8ではライブラリの機能として実装することができますか.
*/

/*
 *
 * isAssertionがfalseの場合, 本質的にはcondition, messageの評価は不要であるが,
 * Java 1.4リリース当時には, isAssertionの値に応じて, condition, messageの評価をしたり, しなかったりするよい方法がなかったため.
 *
 */

public final class AssertionUtil {

	private AssertionUtil() {
	}

	private static boolean isAsserting = false;

	/**
	 * アサーションの有効/無効を確認する
	 * @return アサーションが有効になっている場合true/ 向こうの場合false
	 */
	public static boolean isAsserting() {
		return isAsserting;
	}

	/**
	 * アサーションを有効にする
	 */
	public static void enableAsserting() {
		isAsserting = true;
	}

	/**
	 * アサーションを無効にする
	 */
	public static void disableAsserting() {
		isAsserting = false;
	}

	/**
	 * isAssertingがtrueかつ, conditionがfalseを返す場合, AssertionErrorをスローする.
	 * @param condition
	 * @throws NullPointerException conditionがnullの場合
	 * @throws AssertionError
	 */
	public static <T> void assertIf(Supplier<Boolean> condition) {
		if (isAsserting) {
			Objects.requireNonNull(condition, "A parameter: condition is null.");
			assertIfInternal(condition, null);
		}
	}

	/**
	 * isAssertingがtrueかつ, conditionがfalseを返す場合, expressionの結果をdetailMessageとした, AssertionErrorをスローする.
	 * @param condition
	 * @param detailMessage - AssertionErrorのdetailMessage
	 * @throws NullPointerException
	 * @throws AssertionError
	 */
	public static <T> void assertIf(Supplier<Boolean> condition, Supplier<T> expression) {
		if (isAsserting) {
			Objects.requireNonNull(condition, "A parameter: condition is null.");
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
