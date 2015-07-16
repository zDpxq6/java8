package demo.ch0203;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

//3. streamでなく, parallelStreamで長い単語を数えた場合の速度の違いを測りなさい.
// 呼び出しの前後でSystem.nanotimeを呼び出して, 差を表示しなさい。
// 高速なコンピュータを持っているのであれば, (『戦争と平和』(War and Peace)などの)もっと大きなドキュメントで試しなさい。
public class Demo {
	private static final int LENGTH_THRESHORLD = 11;

	public static void main(String[] args) throws IOException {
		String contents = new String(Files.readAllBytes(Paths
				.get("war and peace.txt")), StandardCharsets.UTF_8);
		List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));
		
		System.out.print("Parallel: ");
		new ParallelMeasuring().measureTime(words, w -> LENGTH_THRESHORLD < w.length());
		System.out.print("Single  : ");
		new Measuring().measureTime(words, w -> LENGTH_THRESHORLD < w.length());
	}
}

interface Measure {
	default void measureTime(List<String> words, Predicate<? super String> paramPredicate) {
		long start = System.nanoTime();
		measure(words, paramPredicate);
		System.out.println(System.nanoTime() - start);
	}

	void measure(List<String> words, Predicate<? super String> paramPredicate);
}

class Measuring implements Measure{
	public void measure(List<String> words, Predicate<? super String> paramPredicate) {
		words.stream().filter(paramPredicate).count();
	}

}

class ParallelMeasuring implements Measure{
	public void measure(List<String> words, Predicate<? super String> paramPredicate) {
		words.parallelStream().filter(paramPredicate).count();
	}
}
