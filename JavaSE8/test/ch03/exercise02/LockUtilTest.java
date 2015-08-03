//(C) 2015 tsuguka hatanaka
package ch03.exercise02;

import java.util.Objects;
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

public final class LockUtilTest {
	public static void main(String[] args) {
		ReentrantLock lock = new ReentrantLock();
		new Thread() {
			@Override
			public void run() {
				LockUtil.withLock(lock, () -> {
					try {
						Thread.sleep(5000);
					} catch (Exception e) {
						e.printStackTrace();
					}
					System.out.println("test");
				});
			}
		}.start();
		new Thread() {
			@Override
			public void run() {
				LockUtil.withLock(lock, () -> {
					try {
						Thread.sleep(5000);
					} catch (Exception e) {
						e.printStackTrace();
					}
					System.out.println("test");
				});
			}
		}.start();

	}

	private LockUtilTest() {
	}

	public static void withLock(ReentrantLock lock, Runnable runner) {
		Objects.requireNonNull(lock, "A parameter lock is null.");
		Objects.requireNonNull(runner, "A parameter runner is null.");
		lock.lock();
		try {
			runner.run();
		} finally {
			lock.unlock();
		}
	}

}
