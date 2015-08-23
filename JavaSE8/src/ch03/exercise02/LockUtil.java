//(C) 2015 tsuguka hatanaka
package ch03.exercise02;

import java.util.Objects;
import java.util.concurrent.locks.Lock;

public final class LockUtil {
	private LockUtil() {
	}

	/**
	 * ロックをかけて処理を実行する.
	 * @param lock lockオブジェクト.
	 * @param runner 実行する処理.
	 * @throws NullPointerException
	 *             - 引数がnullの場合.
	 */
	public static void withLock(Lock lock, Runnable runner) {
		Objects.requireNonNull(lock, "An parameter: lock is null.");
		Objects.requireNonNull(runner, "An parameter: runner is null.");
		lock.lock();
		try {
			runner.run();
		} finally {
			lock.unlock();
		}
	}

}
