package ch02;
//1. 28ページの2.1節「イテレーションからストリーム操作へ」のforループの並列バージョンを書きなさい．
//リストのセグメントごとに処理を行う別々のスレッドを多数生成し, 処理が終わるごとに結果を合計するようにしなさい
//(みなさんは, 単一カウンターを更新するためにスレッドを使用したくはないでしょう. なぜですか).

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Demo {
	private static final int MAX_LENGTH = 9;

	public static void main(String[] args) throws IOException {
		String contents = new String(Files.readAllBytes(Paths.get("alice.txt")), StandardCharsets.UTF_8);
		List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));
		List<Callable<Boolean>> tasks = new ArrayList<>();
		for (String w : words) {
			tasks.add(() -> w.length() > MAX_LENGTH );
		}
		ExecutorService es = Executors.newCachedThreadPool();
		List<Future<Boolean>> result = new ArrayList<>();
		try {
			result = es.invokeAll(tasks);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		int counter = 0;
		for (Future<Boolean> f : result) {
			try {
				boolean r = f.get();// 結果が出ていなければここで待たされるが, すべての要素が似たような時間で終わるなら, 全ループで高々1要素処理分くらいしか待たないはず.
				if (r == true) {
					counter++;
				}
			} catch (InterruptedException | ExecutionException e1) {
				e1.printStackTrace();
			}
		}
		System.out.println(counter);

	}
}