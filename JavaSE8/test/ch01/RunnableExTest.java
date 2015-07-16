package ch01;
import org.junit.Test;

import ch01.RunnableEx;

public class RunnableExTest {

	@Test
	public void UncheckedRunnable_例外がスローされない() {
		new Thread(RunnableEx.uncheck( () -> { System.out.println("Zzz"); Thread.sleep(1000);} )).start();
	}

}
