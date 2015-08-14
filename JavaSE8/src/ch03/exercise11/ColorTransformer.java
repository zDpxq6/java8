package ch03.exercise11;

import java.util.Objects;
import java.util.function.UnaryOperator;

import javafx.scene.paint.Color;

@FunctionalInterface
public interface ColorTransformer {
	Color apply(int x, int y, Color colorAtXY);

	public static ColorTransformer compose(ColorTransformer c1, ColorTransformer c2) {
		Objects.requireNonNull(c1, "The argument c1 is null.");
		Objects.requireNonNull(c2, "The argument c2 is null.");
		return (x, y, colorAtXY) -> c2.apply(x, y, c1.apply(x, y, colorAtXY));
	}

	public static ColorTransformer convert(UnaryOperator<Color> unaryOperator) {
		Objects.requireNonNull(unaryOperator, "The argument unaryOperator is null.");
		return (x, y, colorAtXY) -> unaryOperator.apply(colorAtXY);
	}
}