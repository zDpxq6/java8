package ch03.exercise07;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import ch03.exercise07.ExtendedComparetor.Properties;

public final class ComparetorTest {

	private static final String[] STRINGS = { "ab c", "abc", "ABC", "AB C" };
	private String[] strings = STRINGS;

	@Before
	public void setUp() {
		this.strings = STRINGS;
	}

	@Test
	public void comparatorIsNull() {
		Arrays.sort(STRINGS, ExtendedComparetor.comparator(null));
		assertThat(STRINGS[0], is("AB C"));
		assertThat(STRINGS[1], is("ABC"));
		assertThat(STRINGS[2], is("ab c"));
		assertThat(STRINGS[3], is("abc"));
	}

	@Test
	public void comparatorIgnoreSpace() {
		Arrays.sort(STRINGS, ExtendedComparetor.comparator(Properties.IGNORE_SPACE));
		assertThat(STRINGS[0], is("ABC"));
		assertThat(STRINGS[1], is("AB C"));
		assertThat(STRINGS[2], is("abc"));
		assertThat(STRINGS[3], is("ab c"));
	}

	public void comparatorReverse() {
		Arrays.sort(STRINGS, ExtendedComparetor.comparator(Properties.REVERSE));
		assertThat(STRINGS[3], is("AB C"));
		assertThat(STRINGS[2], is("ABC"));
		assertThat(STRINGS[1], is("ab c"));
		assertThat(STRINGS[0], is("abc"));
	}

	@Test
	public void comparatorIgnoreCase() {
		Arrays.sort(STRINGS, ExtendedComparetor.comparator(Properties.IGNORE_CASE));
		assertThat(STRINGS[0], is("AB C"));
		assertThat(STRINGS[1], is("ab c"));
		assertThat(STRINGS[2], is("ABC"));
		assertThat(STRINGS[3], is("abc"));
	}

	@Test
	public void comparatorIgnoreCase2() {
		Arrays.sort(STRINGS, ExtendedComparetor.comparator(Properties.REVERSE, Properties.IGNORE_CASE));
		System.out.println(STRINGS[0]);
		System.out.println(STRINGS[1]);
		System.out.println(STRINGS[2]);
		System.out.println(STRINGS[3]);
	}
}