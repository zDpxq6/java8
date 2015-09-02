package ch06.exercise01;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

/** 1. 多数のスレッドが更新する最大長の文字列を管理するプログラムを書きなさい. AtomicReferenceと適切な累積関数を使用しなさい. */
public class AtomicReferenceDemo {
	public static void main(String[] args) {
		AtomicReference<String> ar = new AtomicReference<>();
		ExecutorService es = Executors.newCachedThreadPool();
		es.execute(() -> {
			for (int i = 0; i < 100; i++) {
				ar.updateAndGet(updateFunction)
			}
		});
	}
}
