package ch03.exercise09;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/* 9. 指定された順序で, 指定されたフィールドを比較するコンパレータを作成する
 * lexicographicComparator(String... fieldName)メソッドを書きなさい.
 * 例えば, lexicographicComparator("lastname", "firstname")は,
 * 2つのオブジェクトを受け取り, リフレクションを使用して, lastnameフィールドの値を取得します.
 * 2つのオブジェクトのlastnameフィールドが異なれば, その差を返します. 同じであればfirstnameフィールドに移ります.
 * 全てのフィールドが同じであれば, 0を返します.
*/

public class Transformer {

	/**
	 * 指定された順序で, 指定されたフィールドを比較するコンパレータを作成する. 例えば,
	 * lexicographicComparator("lastname", "firstname")は, 2つのオブジェクトを受け取り,
	 * lastnameフィールドの値を取得する. 2つのオブジェクトのlastnameフィールドが異なれば, その差を返す.
	 * 同じであればfirstnameフィールドに移る. 全てのフィールドが同じであれば, 0を返す.
	 *
	 * @param <T>
	 *
	 * @param fieldName
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	public static <T> Comparator<? super T> lexicographicComparator(String... fieldName) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		Objects.requireNonNull(fieldName, "An argument is null.");
		return (o1, o2) -> {
			for (String n : fieldName) {
				Comparable<?> t1 = null;
				Comparable<?> t2 = null;
				try {
					t1 = getComparableField(o1, n);
					t2 = getComparableField(o2, n);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (t1.equals(t2)) {
					continue;
				} else {
					return ((Comparable) t1).compareTo(t2);
				}
			}
			return 0;
		};
	}

	/**
	 * 指定されたオブジェクトの, 指定されたフィールドを取得する. privateなインスタンスフィールドも表示する.
	 *
	 * @param target
	 *            指定されたオブジェクト.
	 * @param fieldName
	 *            指定されたフィールド名.
	 * @return
	 * @throws IllegalArgumentException
	 *             - if the specified object is not an instance of the class or
	 *             interface declaring the underlying field (or a subclass or
	 *             implementor thereof).
	 * @throws IllegalAccessException
	 *             - if this Field object is enforcing Java language access
	 *             control and the underlying field is inaccessible.
	 * @throws NoSuchFieldException
	 *             - if a field with the specified name is not found.
	 * @throws SecurityException
	 *             - If a security manager, s, is present and the caller's class
	 *             loader is not the same as or an ancestor of the class loader
	 *             for the current class and invocation of
	 *             s.checkPackageAccess() denies access to the package of this
	 *             class.
	 * @throws ExceptionInInitializerError
	 *             - if the initialization provoked by this method fails.
	 */
	public static Object getFieldValue(Object target, String fieldName) throws IllegalArgumentException, NoSuchFieldException, SecurityException {
		Objects.requireNonNull(target, "The argument \"target\" is null.");
		Objects.requireNonNull(fieldName, "The argument \"fieldName\" is null.");

		Class<?> clazz = target.getClass();
		Field f = null;
		while (clazz != null) {
			try {
				f = clazz.getDeclaredField(fieldName);
				break;
			} catch (NoSuchFieldException e) {
				clazz = clazz.getSuperclass();
			}
		}
		if (f != null) {
			f.setAccessible(true);
			try {
				return f.get(target);
			} catch (IllegalAccessException e) {
				throw new AssertionError("Cannot access a field.");
			}
		} else {
			throw new NoSuchFieldException("Could not find a field \"" + fieldName + "\".");
		}
	}

	private static Comparable<?> getComparableField(Object target, String fieldName) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		Object o = getFieldValue(target, fieldName);
		if (o instanceof Comparable) {
			return (Comparable<?>) o;
		} else {
			throw new RuntimeException("Fields are not Comparable");
		}
	}

	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		Person p1 = new Person("tsuguka", "hatanaka", 28);
		Person p2 = new Person("ayumu", "hatanaka", 0);
		List<Person> persons = Arrays.asList(p1, p2);
		Comparator<? super Person> c = lexicographicComparator("firstName", "lastName", "age");
		Collections.sort(persons, c);
		for (Person e : persons) {
			System.out.println(e);
		}
	}
}

class SuperPerson {
	public final String lastName;

	SuperPerson(String lastName) {
		this.lastName = lastName;
	}
}

class Person extends SuperPerson {
	private final String firstName;
	private final int age;

	Person(String lastName, String firstName, int age) {
		super(lastName);
		this.firstName = firstName;
		this.age = age;
	}

	@Override
	public String toString() {
		return this.lastName + " " + this.firstName + " (" + this.age + ")";
	}
}