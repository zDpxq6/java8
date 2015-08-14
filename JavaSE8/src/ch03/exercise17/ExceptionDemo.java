package ch03.exercise17;

import java.util.Objects;
import java.util.function.Consumer;

/*
 * 17. firstとsecondを並列に実行し, どちらかのメソッドが例外をスローしたらhandlerを呼び出すdoInParallelAsync(Runnable first, Runnable second, Consumer<Throwable> handler)を実装しなさい.
 *
 */
public class ExceptionDemo {
	public static void doInOrder(Runnable first, Runnable second, Consumer<Throwable> handler) {
		Thread t = new Thread() {
			@Override
			public void run() {
				try {
					first.run();
					second.run();
				} catch (Throwable t) {
					handler.accept(t);
				}
			}
		};
		t.start();
	}

	public static void doInParallelAsync(Runnable first, Runnable second, Consumer<Throwable> handler) {
		Objects.requireNonNull(first, "The argument first is null.");
		Objects.requireNonNull(second, "The argument second is null.");
		Objects.requireNonNull(handler, "The argument handler is null.");
		new Thread(() -> {
			try {
				first.run();
			} catch (Throwable t) {
				handler.accept(t);
			}
		}).start();
		new Thread(() -> {
			try {
				second.run();
			} catch (Throwable t) {
				handler.accept(t);
			}
		}).start();
	}

	public static void main(String[] args) {
		doInParallelAsync(() -> {
			throw new RuntimeException();
		}, () -> System.out.println("second"), (v) -> System.out.println("error"));
	}
}
