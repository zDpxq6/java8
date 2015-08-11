package ch03.exercise05;

import java.util.function.UnaryOperator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

	private static final int MARGIN = 10;

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

	public static Image transform(Image in, UnaryOperator<Color> f) {
		int width = (int) in.getWidth();
		int height = (int) in.getHeight();
		WritableImage out = new WritableImage(width, height);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				out.getPixelWriter().setColor(x, y, f.apply(in.getPixelReader().getColor(x, y)));
			}
		}
		return out;
	}

	@Override
	public void start(Stage stage) {
		Image image = new Image("queen-mary.png");
		int width = (int) image.getWidth();
		int height = (int) image.getHeight();
		Image image2 = transform(image, (x, y, c) -> x < MARGIN || width - MARGIN <= x || y < MARGIN || height - MARGIN <= y ? Color.GRAY : c);
		stage.setScene(new Scene(new HBox(new ImageView(image), new ImageView(image2))));
		stage.show();
	}
}
