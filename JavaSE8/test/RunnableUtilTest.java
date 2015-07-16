import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Test;

public class RunnableUtilTest {

	@Test(expected = IllegalArgumentException.class)
	public void andThen_第一引数がnull() {
		Runnable r = RunnableUtil.andThen(null, () -> System.out.println("second"));
		new Thread(r).start();
		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void andThen_第引数がnull() {
		Runnable r = RunnableUtil.andThen(() -> System.out.println("first"), null);
		new Thread(r).start();
		fail();
	}

	@Test
	public void andThen_正常系() {
		Runnable expected = () -> System.out.println("second");
		Runnable actual = RunnableUtil.andThen(() -> System.out.println("first"), expected);
		assertThat(actual, is(expected));
	}

}
