package ch04.exercise02;

import java.util.Objects;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/*2.
チャートやテーブルといった多くのJavaFXプロパティを持つクラスを考えなさい.
特定のアプリケーションでは, ほとんどのプロパティには決してリスナが登録されない可能性が高いです.
従って, プロパティごとにプロパティオブジェクトを持つことは無駄です.
プロパティ値を保存するために最初の普通のフィールドを使用して,
初めてxxxProperty()メソッド呼び出された時にだけプロパティオブジェクトを使用するように,
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
