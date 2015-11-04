package ch08.ex07;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

//reversed()を呼び出すことなく, nullsFirst(naturalOrder()).reversed()を表現しなさい

public class Demo {
	public static void main(String[] args) {
		Comparator<String> com1 = Comparator.<String> nullsFirst(Comparator.naturalOrder()).reversed();
		Comparator<String> com2 = Comparator.<String> nullsLast(Comparator.reverseOrder());
		String[] strings = { null, "A", "B", "C", "D" };
		String[] copied = strings.clone();
		Arrays.sort(strings, com1);
		Arrays.sort(copied, com2);
		for (int i = 0; i < strings.length; i++) {
			System.out.println(Objects.equals(strings[i], copied[i]));
		}
	}

}