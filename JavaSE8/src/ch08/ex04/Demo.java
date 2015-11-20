package ch08.ex04;

import java.util.stream.LongStream;

public class Demo {

	private static final int A = 11;
	private static final long V = 246154705703781L;
	private static final long N = Double.valueOf(Math.pow(2, 48)).longValue();
	private static final long M = 25214903917l;
	private static final int MAX = 1000000;

	public static void main(String[] args) {
		long min = LongStream.iterate(0, Demo::prev).limit(MAX).reduce((l, m) -> l < m ? l : m).getAsLong();
		System.out.println(min);
	}

	public static long next(long s) {
		return s * M + A % N;
	}

	public static long prev(long s) {
		return (s - A) * V % N;
	}

}
