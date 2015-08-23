//(C) 2015 tsuguka hatanaka
package ch03.exercise02;

import static org.junit.Assert.fail;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

public final class LockUtilTest {

	private static final Lock LOCK = new ReentrantLock();
	private static final long SLEEPING_DURATION = 1000;

	@Test(expected = NullPointerException.class)
	public void nullを入力すると空のストリームが返る0() {
		LockUtil.withLock(null, () -> System.out.println("locking..."));
		fail();
	}

	@Test(expected = NullPointerException.class)
	public void nullを入力すると空のストリームが返る1() {
		LockUtil.withLock(LOCK, null);
		fail();
	}

	@Test
	public void withLockTest() {
		new Thread() {
			@Override
			public void run() {
				LockUtil.withLock(LOCK, () -> {
					try {
						Thread.sleep(SLEEPING_DURATION);
					} catch (Exception e) {
						e.printStackTrace();
					}
					System.out.println("lock1");
				});
			}
		}.start();
		LockUtil.withLock(LOCK, () -> {
			try {
				Thread.sleep(SLEEPING_DURATION);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("lock2");
		});
	}

}
