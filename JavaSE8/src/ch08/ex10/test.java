package ch08.ex10;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Function;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class test {
	private static <T> Stream<T> convertToStream(Scanner scanner, Function<Scanner, T> f) {
		Iterator<T> ite = new Iterator<T>() {
			T comingElement = null;

			@Override
			public boolean hasNext() {
				if (this.comingElement != null) {
					return true;
				} else {
					this.comingElement = f.apply(scanner);
					return this.comingElement != null;
				}
			}

			@Override
			public T next() {
				if (this.comingElement != null || hasNext()) {
					T currentElement = this.comingElement;
					this.comingElement = null;
					return currentElement;
				} else {
					throw new NoSuchElementException();
				}
			}
		};
		return StreamSupport.stream(Spliterators.spliteratorUnknownSize(ite, Spliterator.ORDERED | Spliterator.NONNULL), false);
	}

	public static Stream<String> convertToLineStream(Scanner scanner) {
		Objects.requireNonNull(scanner, "a parameter is null");
		return convertToStream(scanner, e -> e.nextLine());
	}

	public static Stream<String> convertToStringStream(Scanner scanner) {
		Objects.requireNonNull(scanner, "a parameter is null");
		return convertToStream(scanner, e -> e.next());
	}

	public static DoubleStream convertToDoubleStream(Scanner scanner) {
		Objects.requireNonNull(scanner, "a parameter is null");
		return convertToStream(scanner, e -> e.nextDouble()).mapToDouble(e -> e.doubleValue());
	}

	public static IntStream convertToIntStream(Scanner scanner) {
		Objects.requireNonNull(scanner, "a parameter is null");
		return convertToStream(scanner, e -> e.nextInt()).mapToInt(e -> e.intValue());
	}
}
