//(C) 2015 tsuguka hatanaka
package ch03.exercise02;

/*
2.
ReentrantLockを使用する場合には, 次のイディオムでロックとアンロックをする必要があります.

myLock.lock();
try {
    何らかの処理
} finally {
    myLock.unlock();
}

次のように呼び出すことができるwithLockメソッドを提供しなさい
withLock(myLock, () -> { 何らかの処理 });
*/
import java.util.concurrent.locks.ReentrantLock;

public final class LockUtil {
	private LockUtil() {
	}

	public static void withLock(ReentrantLock lock, Runnable runner) {
		lock.lock();
		try {
			runner.run();
		} finally {
			lock.unlock();
		}
	}

}
