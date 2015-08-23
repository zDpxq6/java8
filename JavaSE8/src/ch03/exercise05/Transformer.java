package ch03.exercise05;

import java.util.Objects;
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

/*
 5.
次は, ColorTransformerの具体例です.
次のように, 画像の周りに枠を付加します.
最初に, 62ページの3.3節「関数型インターフェースの選択」のtransformメソッドを,
UnaryOperator<Color>の代わりにColorTramsformerで実装しなさい.
そのtransformメソッドを適切なラムダ式で呼び出しなさい.
*/
@FunctionalInterface
interface ColorTransformer {
	Color apply(int x, int y, Color colorAtXY);
}

public class Transformer extends Application {

	private static final int MARGIN = 500;
	private static final Color MARGINE_COLOR = Color.GRAY;

	public static Image transform(Image image, ColorTransformer colorTransformer) {
		Objects.requireNonNull(image, "A parameter in is null");
		Objects.requireNonNull(colorTransformer, "A parameter f is null");
		int w = (int) image.getWidth();
		int h = (int) image.getHeight();
		PixelReader pReader = image.getPixelReader();
		WritableImage result = new WritableImage(w, h);
		PixelWriter pWriter = result.getPixelWriter();

		IntStream.range(0, w).forEach(x -> {
			IntStream.range(0, h).forEach(y -> {
				pWriter.setColor(x, y, colorTransformer.apply(x, y, pReader.getColor(x, y)));
			});
		});

		return result;
	}

	@Override
	public void start(Stage stage) {
		Image image = new Image("queen-mary.png");
		int width = (int) image.getWidth();
		int height = (int) image.getHeight();
		Image converted = transform(image, (x, y, c) -> x < MARGIN || width - MARGIN <= x || y < MARGIN || height - MARGIN <= y ? MARGINE_COLOR : c);
		stage.setScene(new Scene(new HBox(new ImageView(image), new ImageView(converted))));
		stage.show();
	}
}
