//(C) 2015 tsuguka hatanaka
package ch03.exercise02;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
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
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
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
		assertThat(out.toString(), is("lock2" + System.lineSeparator()));
	}

}
