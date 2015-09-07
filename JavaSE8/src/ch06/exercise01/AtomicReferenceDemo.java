package ch06.exercise01;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

/** 1. 多数のスレッドが更新する最大長の文字列を管理するプログラムを書きなさい. AtomicReferenceと適切な累積関数を使用しなさい. */
/*
 * 方針. 1.複数のスレッドが色々な文字列を読み込む. 2. スレッドは各々自分が読み込んだ文字列の中で最大のものを保持する. 3.
 * 累積関数で全スレッドで最大の文字列のものを導出する.
 */
public class AtomicReferenceDemo {
	static final String[] array01 = { "a", "bb", "ccc", "dddd", "eeeee" };
	static final String[] array02 = { "あああああ", "いいいい", "ううう", "ええ", "お" };
	static final String[] array03 = { "xxxxxx" };

	public static void main(String[] args) throws InterruptedException {
		AtomicReference<CharSequence> ar = new AtomicReference<>("");
		ExecutorService es = Executors.newCachedThreadPool();
		es.execute(generateRunnable(array01, ar));
		es.execute(generateRunnable(array02, ar));
		es.execute(generateRunnable(array03, ar));
		es.shutdown();
		es.awaitTermination(1, TimeUnit.HOURS);
		System.out.println(ar.get());
	}

	private static Runnable generateRunnable(CharSequence[] strings, AtomicReference<CharSequence> ar) {
		return () -> Stream.of(strings).forEach(e -> ar.accumulateAndGet(e, (a, b) -> a.length() < b.length() ? b : a));
	}
}
