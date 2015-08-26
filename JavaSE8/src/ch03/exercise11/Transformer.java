package ch03.exercise11;

import java.util.stream.IntStream;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/* 11. 2つのColorTransformerオブジェクトを合成できるstaticメソッドを実装しなさい.
 * そして, x座標とy座標を無視するColorTransformerへUnaryOperator<Color>を変えるstaticメソッドを実装しなさい.
 * それから, 変換によって明るくなった画像に灰色の枠を追加するために, 実装したメソッドを使用しなさい(灰色の枠に関しては練習問題5を参照しなさい).
 *
*/
public class Transformer extends Application {

	private static final int MARGIN = 10;

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

	@Override
	public void start(Stage stage) throws Exception {
		Image image = new Image("queen-mary.png");
		int width = (int) image.getWidth();
		int height = (int) image.getHeight();
		Image image2;
		ColorTransformer grayFrame = (x, y, c) -> x < MARGIN || width - MARGIN <= x || y < MARGIN || height - MARGIN <= y ? Color.GRAY : c;
		image2 = transform(image, ColorTransformer.compose(grayFrame, ColorTransformer.convert(Color::brighter)));
		stage.setScene(new Scene(new HBox(new ImageView(image), new ImageView(image2))));
		stage.show();
	}
}