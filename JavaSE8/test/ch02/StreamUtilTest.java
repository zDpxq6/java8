package ch02;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.stream.Stream;

import org.junit.Test;

public class StreamUtilTest {

	@Test
	public void characterStream_nullを入力すると空のストリームが返る() {
		Stream<Character> actual = StreamUtil.characterStream(null);
		assertThat(actual, is(Stream.empty()));
	}

	@Test
	public void characterStream_文字列を入力するとCharacterのStreamが返る() {
		Stream<Character> actual = StreamUtil.characterStream("TEST");
		assertThat(actual, is(Stream.of('T', 'E', 'S', 'T')));
	}

	@Test
	public void zip_引数がnullの場合_空のストリームが返る() {
		Stream<Character> actual = StreamUtil.zip(null,null);
		assertThat(actual, is(Stream.empty()));
	}

	public static void main(String[] args) {
		Stream<String> stmA = Stream.of("A0", "A1", "A2", "A3", "A4", "A5");
		Stream<String> stmB = Stream.empty();
		Stream<Stream<String>> stmC = Stream.of(stmA, stmB);
		stmC.forEach(System.out::println);
		StreamUtil.zip(stmA, stmB).forEach(System.out::println);
	}

}
