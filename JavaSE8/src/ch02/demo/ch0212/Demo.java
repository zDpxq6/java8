package ch02.demo.ch0212;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;
// 12
// 51ページの2.13「平行ストリーム」で説明したように, AtomicIntegerの配列を更新することで,並列なStream<String>内の短い単語を全て数えなさい.
// 個々のカウントを安全に増やすためにアトミックであるgetAndIncrementメソッドを使用しなさい.

public class Demo {
	private static final int MAX_STRING_LENGTH = 12;
	public static void main(String[] args) throws IOException {
		String contents = new String(Files.readAllBytes(Paths
				.get("alice.txt")), StandardCharsets.UTF_8);
		Stream<String> words = Stream.of(contents.split("[\\P{L}]+"));
		AtomicInteger[] counters = new AtomicInteger[MAX_STRING_LENGTH];
		Arrays.setAll(counters, n -> new AtomicInteger(0));
		words.parallel().forEach(s -> {
			if (s.length() < MAX_STRING_LENGTH) {
				counters[s.length()].getAndIncrement();
			}
		});
		// カウント結果の表示
		System.out.println(Stream.of(counters).mapToInt(AtomicInteger::get).reduce(0, (t, u) -> t + u));
	}
}
