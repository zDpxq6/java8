package ch02.demo.ch0209;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

//Stream<ArrayList<T>>内のすべての要素を, 1つのArrayList<T>へまとめなさい．
//具体的には3つの形式のreduceを用いる方法を示しなさい.
public class Demo {

	public static void main(String[] args) {
		System.out.println("********************");
		List<String> result1 = integrateList1(createArrayListStream());
		result1.forEach(System.out::println);
		System.out.println("********************");
		List<String> result2 = integrateList2(createArrayListStream());
		result2.forEach(System.out::println);
		System.out.println("********************");
		List<String> result3 = integrateList3(createArrayListStream());
		result3.forEach(System.out::println);
	}

	private static Stream<ArrayList<String>> createArrayListStream() {
		ArrayList<String> ls1 = new ArrayList<>(Arrays.asList("11", "12", "13"));
		ArrayList<String> ls2 = new ArrayList<>(Arrays.asList("21", "22", "23"));
		ArrayList<String> ls3 = new ArrayList<>(Arrays.asList("31", "32", "33"));
		return Stream.of(ls1, ls2, ls3);
	}

	private static <T> List<T> integrateList1(Stream<ArrayList<T>> arrayListStream) {
		if (arrayListStream == null) {
			return new ArrayList<>();
		}
		Optional<ArrayList<T>> result = arrayListStream.reduce((x, y) -> {
			x.addAll(y);
			return x;
		});
		return result.orElse(new ArrayList<>());
	}

	private static <T> List<T> integrateList2(Stream<ArrayList<T>> arrayListStream) {
		if (arrayListStream == null) {
			return new ArrayList<>();
		}
		return arrayListStream.reduce(new ArrayList<T>(), (x, y) -> {
			x.addAll(y);
			return x;
		});
	}

	private static <T> List<T> integrateList3(Stream<? extends List<T>> arrayListStream) {
		if (arrayListStream == null) {
			return new ArrayList<>();
		}
		return arrayListStream.reduce(new ArrayList<T>(), (x, y) -> {
			x.addAll(y);
			return x;
		} , (p, q) -> {
			p.addAll(q);
			return p;
		});
	}
}
