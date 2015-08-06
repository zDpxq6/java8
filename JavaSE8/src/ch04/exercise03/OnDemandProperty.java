package ch04.exercise03;

import java.util.Objects;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/*3.
ほとんどがデフォルトから変化しない多くのJavaFXプロパティを持つクラスを考えなさい.
デフォルトではない値に設定されたり, xxxProperty()メソッドが最初に呼び出された時に,
要求に応じてプロパティを構築する方法を示しなさい.
*/
public class OnDemandProperty {
	private Object xxx = new Object();
	private ObjectProperty<Object> xxxProperty;

	public final ObjectProperty<Object> xxxProperty() {
		if (this.xxxProperty == null) {
			this.xxxProperty = new SimpleObjectProperty<Object>();
			this.xxxProperty.set(this.xxx);
		}
		return this.xxxProperty;
	}

	public final void setXXX(Object newValue) {
		Objects.requireNonNull(newValue, "A parameter is null");
		this.xxx = newValue;
		if (this.xxxProperty != null) {
			this.xxxProperty.set(this.xxx);
		}
	}

	public final Object getXXX() {
		return this.xxx;
	}
}
