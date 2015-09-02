package ch06.exercise05;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

/**
 * 5. 複数スレッドが複数のファイルからすべての単語を読み込むアプリケーションを書きなさい.
 * 各単語がどのファイルで使用されていたかを管理するためにConcurrentHashMap<String, Set<File>>を使用しなさい.
 * マップを更新するために, mergeメソッドを使用しなさい.
 */
public class Snipet {
	public static void main(String[] args) {
		Map<String, Set<File>> map = new ConcurrentHashMap<>();

		ExecutorService es = Executors.newCachedThreadPool();
		es.submit(() -> {
			File file = new File("alice.txt");
			String contents = null;
			try {
				contents = new String(Files.readAllBytes(Paths.get(file.getPath())), StandardCharsets.UTF_8);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (contents != null) {
				Stream.of(contents.split("[\\P{L}]+")).forEach(key->map.merge(key, file, (t,u)-> t));
			}
		});
	}

}
