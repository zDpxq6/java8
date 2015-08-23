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

	/**
	 * メッセージが実際に記録されるロギング・レベルである場合にのみ構築される, メッセージのログを記録します.
	 * 指定されたメッセージ・レベルでロガーが現在使用可能な場合, メッセージが指定されたサプライヤ関数を呼び出して構築され,
	 * 登録されているすべての出力ハンドラ・オブジェクトに転送されます.
	 *
	 * @param level
	 *            - メッセージ・レベル識別子の1つ. たとえば, SEVERE
	 * @param condition
	 *            - ログを記録する条件. 満たされなければmsgSupplierは評価されない.
	 * @param msgSupplier
	 *            - 呼び出されると, 目的のログ・メッセージを生成する関数
	 * @throws NullPointerException
	 *             - 引数がnullの場合
	 */
	public static void logIf(Level level, Supplier<Boolean> condition, Supplier<String> msgSupplier) {
		Objects.requireNonNull(level, "An parameter: level is null.");
		Objects.requireNonNull(condition, "An parameter: condition is null.");
		Objects.requireNonNull(msgSupplier, "An parameter: msgSupplier is null.");
		if (condition.get()) {
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(level, msgSupplier);
		}
	}

}
