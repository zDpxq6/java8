package ch03.exercise08;

import java.util.Objects;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/* 8. 画像に任意の幅と色の枠を追加するColorTransformerを生成するように,
staticメソッドを書いて, 練習問題5を汎用化しなさい.
*/
@FunctionalInterface
interface ColorTransformer {
	Color apply(int x, int y, Color colorAtXY);
}

public class Transformer extends Application {

	public static Image transform(Image in, ColorTransformer f) {
		int width = (int) in.getWidth();
		int height = (int) in.getHeight();
		WritableImage out = new WritableImage(width, height);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				out.getPixelWriter().setColor(x, y, f.apply(x, y, in.getPixelReader().getColor(x, y)));
			}
		}
		return out;
	}

	private static ColorTransformer createTransformer(Image image, int xMergin, int yMergin, Color merginColor) {
		Objects.requireNonNull(image, "An argument is null.");
		Objects.requireNonNull(merginColor, "An argument is null.");
		requireNonNegative(xMergin, "An argument is negative.");
		requireNonNegative(yMergin, "An argument is negative.");
		int width = (int) image.getWidth();
		int height = (int) image.getHeight();
		requireNonNegative(width - xMergin, "An argument is negative.");
		requireNonNegative(height - yMergin, "An argument is negative.");
		return (x, y, c) -> x < xMergin || width - xMergin <= x || y < yMergin || height - yMergin <= y ? merginColor : c;
	}

	private static int requireNonNegative(int value, String message) throws IllegalStateException {
		if (value < 0) {
			throw new IllegalStateException(message);
		}
		return value;
	}

	@Override
	public void start(Stage stage) {
		Image image = new Image("queen-mary.png");
		Image image2 = transform(image, createTransformer(image, 30, 30, Color.RED));
		stage.setScene(new Scene(new HBox(new ImageView(image), new ImageView(image2))));
		stage.show();
	}
}
