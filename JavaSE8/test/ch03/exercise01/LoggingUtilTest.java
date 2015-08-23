package ch03.exercise01;

import static org.junit.Assert.fail;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;

public class LoggingUtilTest {

	private static int i = 10;
	private static int[] a = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0 };
	private static final Logger LOGGER = Logger.getLogger("ch03.exercise01.LoggingUtilTest");

	@Test(expected = NullPointerException.class)
	public void nullを入力すると空のストリームが返る0() {
		LoggingUtil.logIf(null, () -> i == 10, () -> "a[10] = " + a[10]);
		fail();
	}

	@Test(expected = NullPointerException.class)
	public void nullを入力すると空のストリームが返る1() {
		LoggingUtil.logIf(Level.FINEST, null, () -> "a[10] = " + a[10]);
		fail();
	}

	@Test(expected = NullPointerException.class)
	public void nullを入力すると空のストリームが返る2() {
		LoggingUtil.logIf(Level.FINEST, () -> i == 10, null);
	}

	@Test
	public void logIfTest() {
		LOGGER.info("log by info");
		LoggingUtil.logIf(Level.INFO, () -> i == 10, () -> "a[9] = " + a[9]);
	}

	@Test
	public void logIfTest2() {
		LOGGER.info("log by info");
		LoggingUtil.logIf(Level.FINEST, () -> i == 10, () -> "a[9] = " + a[9]);
	}

}
