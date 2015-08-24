package ch03.exercise08;

import java.util.Objects;
import java.util.stream.IntStream;

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

	public static Image transform(Image image, ColorTransformer transfer) {
		int w = (int) image.getWidth();
		int h = (int) image.getHeight();
		WritableImage out = new WritableImage(w, h);
		IntStream.range(0, w).forEach(x -> {
			IntStream.range(0, h).forEach(y -> {
				out.getPixelWriter().setColor(x, y, transfer.apply(x, y, image.getPixelReader().getColor(x, y)));
			});
		});
		return out;
	}

	private static ColorTransformer createTransformer(int width, int height, int xMergin, int yMergin, Color merginColor) {
		Objects.requireNonNull(merginColor, "An argument is null.");
		requireNonNegative(xMergin, "An argument is negative.");
		requireNonNegative(yMergin, "An argument is negative.");
		requireNonNegative(width - xMergin, "Either width or xMergine is illegal.");
		requireNonNegative(height - yMergin, "Either hight or yMergine is illegal.");
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
		Image image2 = transform(image, createTransformer((int) image.getWidth(), (int) image.getHeight(), 30, 30, Color.RED));
		stage.setScene(new Scene(new HBox(new ImageView(image), new ImageView(image2))));
		stage.show();
	}
}
