package ch03.exercise01;

import java.util.logging.Level;

import org.junit.Test;

import ch03.exercise01.LoggingUtil;

public class LoggingUtilTest {

	@Test
	public void characterStream_nullを入力すると空のストリームが返る() {
		int i= 10;
		int[] a = {1,2,3,4,5,6,7,8,9,0};
		LoggingUtil.logIf(Level.FINEST, () -> i == 10, () -> "a[10] = " + a[10]);
	}
	//
	// @Test
	// public void characterStream_文字列を入力するとCharacterのStreamが返る() {
	// Stream<Character> actual = StreamUtil.characterStream("TEST");
	// assertThat(actual, is(Stream.of('T', 'E', 'S', 'T')));
	// }
	//
	// @Test
	// public void zip_引数がnullの場合_空のストリームが返る() {
	// Stream<Character> actual = StreamUtil.zip(null,null);
	// assertThat(actual, is(Stream.empty()));
	// }
	//
	// public static void main(String[] args) {
	// Stream<String> stmA = Stream.of("A0", "A1", "A2", "A3", "A4", "A5");
	// Stream<String> stmB = Stream.empty();
	// Stream<Stream<String>> stmC = Stream.of(stmA, stmB);
	// stmC.forEach(System.out::println);
	// StreamUtil.zip(stmA, stmB).forEach(System.out::println);
	// }

}
