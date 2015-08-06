package ch04.exercise02;

import java.util.HashMap;
import java.util.Map;
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
	private final Map<PropertyNames, Object> rawProperties = new HashMap<>();
	private final Map<PropertyNames, ObjectProperty<Object>> properties = new HashMap<>();
	private final Object LOCK = new Object();

	private enum PropertyNames {
		XXX, YYY, ZZZ,
	}

	public final ObjectProperty<Object> xxxProperty() {
		return property(PropertyNames.XXX);
	}

	public final Object getXXX() {
		return get(PropertyNames.XXX);
	}

	public final void setXXX(Object newValue) {
		Objects.requireNonNull(newValue, "A parameter is null");
		set(PropertyNames.XXX, newValue);
	}

	private final ObjectProperty<Object> property(PropertyNames propertyName) {
		synchronized (this.LOCK) {
			ObjectProperty<Object> result = this.properties.get(propertyName);
			if (result == null) {
				ObjectProperty<Object> tmp = new SimpleObjectProperty<Object>();
				this.properties.put(propertyName, tmp);
			}
			return result;
		}
	}

	private final void set(PropertyNames propertyName, Object newValue) {
		synchronized (this.LOCK) {
			this.rawProperties.put(propertyName, newValue);
			ObjectProperty<Object> property = this.properties.get(propertyName);
			if (property != null) {
				property.set(newValue);
			}
		}
	}

	private final Object get(PropertyNames propertyName) {
		synchronized (this.LOCK) {
			return this.rawProperties.get(propertyName);
		}
	}
}
