import org.junit.Test;

public class UncheckedRunnableTest {

	@Test
	public void UncheckedRunnable_例外がスローされない() {
		new Thread(UncheckedRunnable.uncheck(() -> {
			System.out.println("Zzz");
			Thread.sleep(1000);
		})).start();
	}

}
