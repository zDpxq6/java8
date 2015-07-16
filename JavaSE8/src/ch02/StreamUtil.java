package ch02;

import static java.util.Optional.ofNullable;
import static java.util.stream.Stream.empty;
import static java.util.stream.Stream.iterate;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.BiFunction;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

//6. 31ページの2.3節「filter, map, flatMapメソッド」のcharacterStreamメソッドは,
//最初にArrayListを埋めて,それからそのリストをストリームに変換するという具合に, 多少ぎこちないです.
//代わりに, ストリームを使用して1行で書きなさい.
//適切な方法は, 0からs.length()-1までの整数のストリームを作成して, それを
//s::charAtメソッド参照でマップすることです.
public class StreamUtil {

	public static void main(String[] args) {
		Stream<String> stmA = Stream.of("A0", "A1", "A2", "A3", "A4", "A5");
		Stream<String> stmB = Stream.empty();
		Stream<Stream<String>> stmC = Stream.of(stmA, stmB);
		stmC.forEach(System.out::println);
		StreamUtil.zip(stmA, stmB).forEach(System.out::println);
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

	//8. public staic <T> Stream<T> zip(Stream<T> first, Stream<T> second)メソッドを作成しなさい.
	//そのメソッドは, ストリームであるfirstとsecondから要素を交互に取り出し, どちらかのストリームから要素がなくなったら停止します.

	public static <T> Stream<T> zip(Stream<T> s1, Stream<T> s2) {
		if(s1 == null){
			return Stream.empty();
		}
		ContainerIterator<T, T, Container<T>> itr = new ContainerIterator<>(s1.iterator(), s2.iterator(), Container<T>::new);
		Spliterator<Container<T>> spl = Spliterators.spliteratorUnknownSize(itr, Spliterator.IMMUTABLE | Spliterator.NONNULL);
		return StreamSupport.stream(spl, false).flatMap(e -> Stream.of(e.item1, e.item2));
	}

	/** 2つのイテレータから、要素を合成するイテレータ。 */
	private static class ContainerIterator<T, U, R> implements Iterator<R> {
		private final Iterator<T> ite1;
		private final Iterator<U> ite2;
		private final BiFunction<T, U, R> mapper;

		public ContainerIterator(Iterator<T> ite1, Iterator<U> ite2, BiFunction<T, U, R> mapper) {
			this.ite1 = ite1;
			this.ite2 = ite2;
			this.mapper = mapper;
		}

		@Override
		public boolean hasNext() {
			return this.ite1.hasNext() && this.ite2.hasNext();
		}

		@Override
		public R next() {
			return this.mapper.apply(this.ite1.next(), this.ite2.next());
		}
	}

	private static final class Container<T> {
		final public T item1;
		final public T item2;

		public Container(T first, T second) {
			this.item1 = first;
			this.item2 = second;
		}
	}

}
