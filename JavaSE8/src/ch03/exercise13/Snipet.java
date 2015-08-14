package ch03.exercise13;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

import ch03.exercise11.ColorTransformer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/* 13. ぼやけ検出, あるいは, エッジ検出といった畳み込みフィルターは, 隣接するピクセルから1つのピクセルを計算します.
 * 画像をぼやかすためには, ピクセルとその隣接する8個のピクセルの平均で, 個々の色値を置き換えます.
 * エッジ検出には個々の色値を4c-n-e-s-wで置き換えます.ここで, 他の色は, 
 * 北(north), 東(east), 南(south), 西(west)のピクセルの色値です.
 * これは69ページの3.6節「遅延」で説明された方法を用いた遅延では実装できないことに注意してください.
 * なぜなら, 計算するために, 前段の画像(あるいは, 少なくとも隣接するピクセル)が必要だからです.
 * これらの操作を扱うために遅延画像処理の機能を強化しなさい. これらの演算の1つが評価される際に, 
 * 前段の計算が強制されるようにしなさい.
 */
class LatentImage {
	private Image in;
	// private List<UnaryOperator<Color>> pendingOperations;
	private List<ColorTransformer> pendingOperations;

	public static LatentImage from(Image in) {
		LatentImage result = new LatentImage();
		result.in = in;
		result.pendingOperations = new ArrayList<>();
		return result;
	}

	LatentImage transform(UnaryOperator<Color> f) {
		this.pendingOperations.add(ColorTransformer.convert(f));
		return this;
	}

	LatentImage transform(ColorTransformer f) {
		this.pendingOperations.add(f);
		return this;
	}

	public Image toImage() {
		int width = (int) this.in.getWidth();
		int height = (int) this.in.getHeight();
		WritableImage out = new WritableImage(width, height);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				Color c = this.in.getPixelReader().getColor(x, y);
				for (ColorTransformer f : this.pendingOperations) {
					c = f.apply(x, y, c);
				}
				out.getPixelWriter().setColor(x, y, c);
			}
		}
		return out;
	}
}

public class Snipet extends Application {
	@Override
	public void start(Stage stage) {
		Image image = new Image("eiffel-tower.jpg");
		Image finalImage = LatentImage.from(image).transform(Color::brighter).transform(Color::grayscale).toImage();
		stage.setScene(new Scene(new HBox(new ImageView(image), new ImageView(finalImage))));
		stage.show();
	}
}
