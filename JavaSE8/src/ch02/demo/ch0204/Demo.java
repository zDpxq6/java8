package ch02.demo.ch0204;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

// 4. 配列 int[] calues = {1, 4, 9, 16 }があるとします。
// Stream.of(values)は, 何になるでしょうか。
// 代わりに, intのストリームをどうやって取得できるでしょうか。
public class Demo {

	public static void main(String[] args) throws IOException {
		int[] values = { 1, 4, 9, 16 };
		Stream<int[]> stream = Stream.of(values);
		stream.forEach(System.out::println);// 答案 Stream<int>でなく, Stream<int[]>
		
		IntStream intStream = Arrays.stream(values, 0, values.length);
		intStream.forEach(System.out::println);
	}
}