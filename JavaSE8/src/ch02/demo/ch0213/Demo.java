package ch02.demo.ch0213;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;
// 13
// 連勝問題に対して次の点を変更し, 問題を解きなさい.
// 変更点として, 短い文字列はフィルターで取り出し,
//Collectors.groupingByとCollectors.countingと一緒にcollectメソッドを使用しなさい.

public class Demo {
	private static final int MAX_STRING_LENGTH = 12;
	public static void main(String[] args) throws IOException {
		String contents = new String(Files.readAllBytes(Paths
				.get("alice.txt")), StandardCharsets.UTF_8);

		Stream<String> words = Stream.of(contents.split("[\\P{L}]+"));
		Stream<String> shortWords = words.parallel().filter((s) -> s.length() < MAX_STRING_LENGTH);
		Map<Integer, Long> temp = shortWords.collect(Collectors.groupingBy(String::length, Collectors.counting()));
		long result = 0;
		for(Entry<Integer, Long> e :temp.entrySet()){
			result += e.getValue();
		}
		System.out.println(result);
	}
}
