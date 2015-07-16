package ch02;

import static java.util.Optional.ofNullable;
import static java.util.stream.Stream.empty;
import static java.util.stream.Stream.iterate;

import java.util.stream.Stream;

//31ページの2.3節「filter, map, flatMapメソッド」のcharacterStreamメソッドは,
//最初にArrayListを埋めて,それからそのリストをストリームに変換するという具合に, 多少ぎこちないです.
//代わりに, ストリームを使用して1行で書きなさい.
//適切な方法は, 0からs.length()-1までの整数のストリームを作成して, それを
//s::charAtメソッド参照でマップすることです.
public class StreamUtils {
	public static void main(String[] args) {
		Stream<Character> characterStream = characterStream(null);
		characterStream.forEach(System.out::println);
	}

	/**
	 * StringからCharacterのストリームを生成する.
	 * @param s
	 *            文字列
	 * @throws IllegalArgumentException
	 *             引数がnullのとき
	 * @return
	 */
	public static final Stream<Character> characterStream(String s) {
		return ofNullable(s).map((v) -> iterate(0, n -> ++n).limit(v.length()).map(v::charAt)).orElse(empty());
		// return Stream.iterate(0, n -> n++).limit(s.length()).map(s::charAt);// NG
	}

}
