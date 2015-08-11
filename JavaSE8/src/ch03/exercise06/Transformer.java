package ch03.exercise06;

import java.util.Objects;
import java.util.function.BiFunction;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/*3.4節「関数を返す」で見た次のメソッドを完成させなさい.
public static <T> Image transform(Image in, BiFunction<Color, T> f, T arg)
 */

public class Transformer extends Application {

	/**
	 *
	 * @param in
	 * @param f
	 * @param arg
	 * @return
	 * @throws NullPointerException
	 *             引数がnullの場合
	 */
	public static <T> Image transform(Image in, BiFunction<Color, T, Color> f, T arg) {
		Objects.requireNonNull(in, "A parameter \"in\" is null.");
		Objects.requireNonNull(f, "A parameter \"f\" is null.");
		Objects.requireNonNull(arg, "A parameter \"arg\"is null.");
		int width = (int) in.getWidth();
		int height = (int) in.getHeight();
		WritableImage out = new WritableImage(width, height);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				out.getPixelWriter().setColor(x, y, f.apply(in.getPixelReader().getColor(x, y), arg));
			}
		}
		return out;
	}

	@Override
	public void start(Stage stage) {
		Image image = new Image("queen-mary.png");
		Image image2 = transform(image, (c, factor) -> c.deriveColor(0, 1, factor, 1), 1.2);
		stage.setScene(new Scene(new HBox(new ImageView(image), new ImageView(image2))));
		stage.show();
	}

}
