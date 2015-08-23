package ch03.exercise06;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.stream.IntStream;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
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
	public static <T> Image transform(Image image, BiFunction<Color, T, Color> f, T arg) {
		Objects.requireNonNull(image, "A parameter \"in\" is null.");
		Objects.requireNonNull(f, "A parameter \"f\" is null.");
		Objects.requireNonNull(arg, "A parameter \"arg\"is null.");
		int w = (int) image.getWidth();
		int h = (int) image.getHeight();
		PixelReader pReader = image.getPixelReader();
		WritableImage result = new WritableImage(w, h);
		PixelWriter pWriter = result.getPixelWriter();

		IntStream.range(0, w).forEach(x -> {
			IntStream.range(0, h).forEach(y -> {
				pWriter.setColor(x, y, f.apply(pReader.getColor(x, y), arg));
			});
		});

		return result;
	}

	@Override
	public void start(Stage stage) {
		Image image = new Image("queen-mary.png");
		Image converted = transform(image, (c, factor) -> c.deriveColor(0, 1, factor, 1), 1.2);
		stage.setScene(new Scene(new HBox(new ImageView(image), new ImageView(converted))));
		stage.show();
	}

}
