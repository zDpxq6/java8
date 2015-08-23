//(C) 2015 tsuguka hatanaka
package ch03.exercise03;

import static org.junit.Assert.fail;

import org.junit.Test;

public final class AssertUtilTest {

	@Test(expected = NullPointerException.class)
	public void throwsNullPointer1() {
		AssertionUtil.assertIf(null, () -> "assertion");
		fail();
	}

	@Test(expected = AssertionError.class)
	public void conditionがtrueならassertionが出る() {
		AssertionUtil.enableAsserting();
		AssertionUtil.assertIf(() -> true, () -> "assertion");
	}

	@Test
	public void conditionがfalseならassertionが出ない() {
		AssertionUtil.enableAsserting();
		AssertionUtil.assertIf(() -> false, () -> "assertion");
	}

	@Test
	public void disableAssertingしているのでassertionが出ない() {
		AssertionUtil.disableAsserting();
		AssertionUtil.assertIf(() -> true, () -> "assertion");
	}

}
