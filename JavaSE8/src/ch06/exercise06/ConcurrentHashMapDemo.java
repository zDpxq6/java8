package ch06.exercise06;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/*6.
Mapを更新するメソッドとして, mergeの代わりにcomputeIfAbsentを使用して,
練習問題5と同じアプリケーションを作成しなさい. この方法の利点はなんですか.
*/

public class ConcurrentHashMapDemo {

	/**
	 * 指定したファイルパスが示すファイルに含まれる文字を返す.
	 *
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	private static final List<String> fileToStrings(String filePath) throws IOException {
		Objects.requireNonNull(filePath, "A parameter filePaht is null.");
		byte[] bytes = Files.readAllBytes(new File(filePath).toPath());
		String content = new String(bytes, StandardCharsets.UTF_8);

		return Arrays.asList(content.split("[\\P{L}]+"));
	}

	private static Runnable generateRunnableWithContainer(String filePath, Map<String, Set<File>> resultContaner) {
		return () -> {
			try {
				fileToStrings(filePath).forEach(key -> {
					File file = new File(filePath);
					resultContaner.computeIfAbsent(key, t -> {
						Set<File> v = ConcurrentHashMap.newKeySet();
						v.add(file);
						return v;
					});
					resultContaner.computeIfPresent(key, (k, v) -> {
						v.add(file);
						return v;
					});
				});
			} catch (IOException e) {
				e.printStackTrace();
			}
		};
	};

	private static <T> Set<T> generateSet(Supplier<Set<T>> container, T v) {
		Set<T> value = container.get();
		value.add(v);
		return value;
	}

	public static void main(String[] args) {
		Map<String, Set<File>> map = new ConcurrentHashMap<>();

		ExecutorService es = Executors.newCachedThreadPool();
		es.submit(generateRunnableWithContainer("alice.txt", map));
		es.submit(generateRunnableWithContainer("alice2.txt", map));
		es.shutdown();
		try {
			es.awaitTermination(1L, TimeUnit.HOURS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		map.forEach((k, v) -> System.out.println(k + ": " + v.size()));
	}

}
