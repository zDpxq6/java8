package ch04.exercise05;

import java.util.function.BiFunction;
import java.util.function.Function;

/*5.
次のメソッドを書きなさい.
public static <T, R> ObservableValue<R> observe( Function<T, R> f, ObservableValue<T> t)
public static <T, U, R> ObservableValue<R> observe( BiFunction<T, U, R> f, ObservableValue<T> t, ObservableValue<U> u)
このメソッドは, ObservableValueを返し, そのObservableValueのgetValueメソッドはラムダ式の値を返します.
そして, そのObservableValueのInvalidationListenerとChangeListenerが, 入力のどれかが向こうあるいは変更になった時に呼び出されるようにしなさい.
例えば, 次の通りです.
larger.disableProperty().bind(observe(t -> t >= 100, gauge.widthProperty()));
*/
public class Snipet {
	public static <T, R> ObservableValue<R> observe(Function<T, R> f, ObservableValue<T> t) {
		return null;
	};

	public static <T, U, R> ObservableValue<R> observe(BiFunction<T, U, R> f, ObservableValue<T> t, ObservableValue<U> u) {
		return null;
	};

	static class ObservableValue<R>{
		R getValue(){
			return null;
		}
	}
}
