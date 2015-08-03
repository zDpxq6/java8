package ch03.exercise03;

import java.util.Objects;
import java.util.function.Supplier;

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
