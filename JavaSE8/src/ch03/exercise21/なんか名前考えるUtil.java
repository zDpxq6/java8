package ch03.exercise21;

import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;

public class なんか名前考えるUtil {
	public static <T, U> Future<U> map(Future<T> future, Function<T, U> function) throws InterruptedException, ExecutionException {
		Objects.requireNonNull(future, "The parameter future is null.");
		Objects.requireNonNull(function, "The parameter function is null.");
		return new Future<U>() {

			@Override
			public boolean cancel(boolean mayInterruptIfRunning) {
				return future.cancel(mayInterruptIfRunning);
			}

			@Override
			public boolean isCancelled() {
				return future.isCancelled();
			}

			@Override
			public boolean isDone() {
				return future.isDone();
			}

			@Override
			public U get() throws InterruptedException, ExecutionException {
				return function.apply(future.get());
			}

			@Override
			public U get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
				return function.apply(future.get(timeout, unit));
			}

		};

	}

	public static void main(String[] args) {

	}
}
