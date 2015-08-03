//(C) 2015 tsuguka hatanaka
package ch03.exercise01;
//1.

//条件的なロギングを提供することで, 遅延ロギング技法を強化しなさい.
//典型的な呼び出しは, logIf(Level.FINEST, () -> i == 10, () -> "a[10] = " +
//a[10])となります. ロガーがメッセージをロギングしないのであれば, その条件を評価しないようにしなさい.

import java.util.Objects;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class LoggingUtil {
	private LoggingUtil() {
	}

	public static void logIf(Level level, Supplier<Boolean> condition, Supplier<String> message) {
		Objects.requireNonNull(level, "An parameter: level is null.");
		Objects.requireNonNull(condition, "An parameter: predicate is null.");
		Objects.requireNonNull(message, "An parameter: counsumer is null.");
		if (condition.get()) {
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(level, message);
		}
	}

}
