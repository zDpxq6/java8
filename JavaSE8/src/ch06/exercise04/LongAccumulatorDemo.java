package ch06.exercise04;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/** 4. LongAccumulatorを使用して, 要素の最大値あるいは最小値を計算しなさい. */

public class LongAccumulatorDemo {
	private static final int THREAD_NUMBER = 5;
	private static final int GENERATE_NUMBER = 5;

	public static void main(String[] args) throws InterruptedException {
		LongAccumulator la = generateMinCollectAccumulator();

		ExecutorService es = Executors.newCachedThreadPool();
		IntStream.range(0, THREAD_NUMBER).forEach(i -> es.submit(() -> generateLongStream().peek(System.out::println).forEach(r -> la.accumulate(r))));
		es.shutdown();
		es.awaitTermination(1, TimeUnit.SECONDS);
		System.out.println("Result: " + la.get());
	}

	public static LongAccumulator generateMaxCollectAccumulator() {
		return new LongAccumulator((l, r) -> l < r ? r : l, Long.MIN_VALUE);
	}

	public static LongAccumulator generateMinCollectAccumulator() {
		return new LongAccumulator((l, r) -> r < l ? r : l, Long.MAX_VALUE);
	}

	private static LongStream generateLongStream() {
		return new Random().longs(GENERATE_NUMBER);
	}
}
