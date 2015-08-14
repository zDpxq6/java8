package ch03.exercise20;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class なんか名前考えるUtil {
	public static <T, U> List<U> map(List<T> list, Function<T, U> function) {
		Objects.requireNonNull(list, "The parameter list is null.");
		Objects.requireNonNull(function, "The parameter function is null.");
		List<U> result = new ArrayList<>();
		for (T e : new ArrayList<>(list)) {
			result.add(function.apply(e));
		}
		return result;

	}

	public static void main(String[] args) {

	}
}
