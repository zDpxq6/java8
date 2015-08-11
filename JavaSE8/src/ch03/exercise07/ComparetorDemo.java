package ch03.exercise07;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

//Comparator<String>を生成するメソッドを書きなさい.
//普通の順序, 逆順, 大文字小文字を区別, 大文字小文字を区別しない,
//空白を含める, 空白を除外する, あるいは, これらの組み合わせとすることができる
//Comparator<String>にしなさい.
//メソッドは, ラムダ式を返すようにしなさい.

public class ComparetorDemo {

	private static final String BLANK = "";
	private static final String SPACE = "\\s";

	public enum Properties {
		REVERSE, IGNORE_CASE, IGNORE_SPACE;
	}

	public static Comparator<String> comparator(Properties... properties) {
		if (properties == null) {
			return (o1, o2) -> o1.compareTo(o2);
		}
		List<Properties> plist = Arrays.asList(properties);
		return (o1, o2) -> {
			if (plist.contains(Properties.IGNORE_CASE)) {
				o1 = o1.toLowerCase();
				o2 = o2.toLowerCase();
			}
			if (plist.contains(Properties.IGNORE_SPACE)) {
				o1 = o1.replaceAll(SPACE, BLANK);
				o2 = o2.replaceAll(SPACE, BLANK);
			}
			if (plist.contains(Properties.REVERSE)) {
				return -1 * o1.compareTo(o2);
			}else{
				return o1.compareTo(o2);
			}
		};
	}

	public static void main(String[] args) {
		String[] strings = { "abc", "ab c", "ABC", "AB C" };
		List<String> stringList = Arrays.asList(strings);
		stringList.sort(comparator());
		for (String e : stringList) {
			System.out.println(e);
		}
		System.out.println("**********");
		stringList.sort(comparator(Properties.REVERSE));
		for (String e : stringList) {
			System.out.println(e);
		}
		System.out.println("**********");
		stringList.sort(comparator(Properties.IGNORE_CASE));
		for (String e : stringList) {
			System.out.println(e);
		}
		System.out.println("**********");
		stringList.sort(comparator(Properties.IGNORE_SPACE));
		for (String e : stringList) {
			System.out.println(e);
		}
	}

}
