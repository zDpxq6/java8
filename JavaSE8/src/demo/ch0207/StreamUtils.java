package demo.ch0207;

//6. 上司が, メソッドとしてpublic static <T> boolean isFinite(Stream<T> stream)を作成するように求めています.
//それは, よくない考えでしょうか. まずは作成してから考えてみなさい.

//答案: よくない. Streamが無限ストリームかどうかはわからない
import java.io.IOException;
import java.util.stream.Stream;

public class StreamUtils {
	private static final long SEED = 0l;
	private static final long GAP = 1000l;

	public static void main(String[] args) throws IOException {
		System.out.println(isFinite(createInfiniteStream()));
	}

	private static Stream<Long> createInfiniteStream() {
		return Stream.iterate(SEED, (v) -> {
			try {
				Thread.sleep(GAP);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return v + 1;
		});
	}

	public static <T> boolean isFinite(Stream<T> stream) {
		return false; // どう実装すればよいかわからない
	}
}
