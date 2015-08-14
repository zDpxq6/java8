package ch03.exercise16;

import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

/*
 * 16. 71ページの3.8節「例外の取り扱い」のdoInOrderAsyncを実装し, 2つ目のパラメータはBiConsumer<T, Throwable>としなさい.
 * うまいユースケースを示しなさい. 3つめのパラメータは必要ですか.
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

	public static <T> void doInOrderAsync(Supplier<T> first, BiConsumer<T, Throwable> second) {//いいのか?
		Objects.requireNonNull(first, "The argument first is null.");
		Objects.requireNonNull(second, "The argument second is null.");

		Thread t = new Thread() {
			@Override
			public void run() {
				T result = null;
				try {
					result = first.get();
					second.accept(result, null);
				} catch (Throwable t) {
					second.accept(result, t);
				}
			}
		};
		t.start();
	}

	public static void main(String[] args) {
		doInOrderAsync(
				() -> "result",
				(v, t) -> {
					if(t == null){
						System.out.println(v);
					}else {
						System.out.println("例外がthrowされた");
					}
				}
		);

	}
}
