package ch02.demo.ch0203;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

//3. streamでなく, parallelStreamで長い単語を数えた場合の速度の違いを測りなさい.
// 呼び出しの前後でSystem.nanotimeを呼び出して, 差を表示しなさい。
// 高速なコンピュータを持っているのであれば, (『戦争と平和』(War and Peace)などの)もっと大きなドキュメントで試しなさい。
public class Demo {
	private static final int LENGTH_THRESHORLD = 12;
	private static final int MAX = 1000;

	public static void main(String[] args) throws IOException {
		String contents = new String(Files.readAllBytes(Paths.get("war and peace.txt")), StandardCharsets.UTF_8);
		List<String> words = new ArrayList<String>(Arrays.asList(contents.split("[\\P{L}]+")));
		long result = 0;
		for (int i = 0; i < MAX; i++) {
			result = measure(l -> l.stream().filter(w -> w.length() > LENGTH_THRESHORLD).count(), words);
		}
		System.out.println(result / MAX);

		result = 0;
		for (int i = 0; i < MAX; i++) {
			result = measure(l -> l.removeIf(w -> w.length() <= LENGTH_THRESHORLD), words);
		}
		System.out.println(result / MAX);

	}

	private static long measure(Function<List<String>, ?> consumer, List<String> l) {
		long start = System.currentTimeMillis();
		consumer.apply(l);
		return System.currentTimeMillis() - start;
	}
}