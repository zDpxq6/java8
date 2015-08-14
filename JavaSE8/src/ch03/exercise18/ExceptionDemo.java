package ch03.exercise18;

import java.util.function.Function;

/*
 * 18. 71ページの3.8節「例外の取り扱い」のuncheckメソッドを次の内容に従って実装しなさい.
 * 具体的には, チェックされる例外をスローするラムダ式からFunction<T,U>を生成するようにしなさい.
 * 任意の例外をスローする抽象メソッドを持つ関数型インターフェースを見つけるか, 作成する必要があることに注意しなさい.
 */
public class ExceptionDemo {
	public static <T, U> Function<T, U> unchecked(ThrowableFunction<T, U> f) {
		return (t) -> {
			try {
				return f.apply(t);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		};
	}
}

interface ThrowableFunction<T, U> {
	public U apply(T t) throws Exception;
}
