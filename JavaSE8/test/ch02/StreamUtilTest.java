package ch02;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.stream.Stream;

import org.junit.Test;

public class StreamUtilTest {

	@Test
	public void characterStream_nullを入力すると空のストリームが返る() {
		Stream<Character> actual = StreamUtils.characterStream(null);
		assertThat(actual, is(Stream.empty()));
	}

	@Test
	public void characterStream_文字列を入力するとCharacterのStreamが返る() {
		Stream<Character> actual = StreamUtils.characterStream("TEST");
		assertThat(actual, is(Stream.of('T', 'E', 'S', 'T')));
	}

}
