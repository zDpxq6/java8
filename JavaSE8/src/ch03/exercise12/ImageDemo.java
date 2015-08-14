package ch03.exercise12;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/* 12. 69ページの3.6節「遅延」のLatentImageを機能拡張して,
 * UnaryOperator<Color>とColorTransfirmerの両方をサポートするようにしなさい.
 * ヒント: UnaryOperator<Color>をColorTransformerへ適用させなさい.
 */
class LatentImage {
	private Image in;
	private List<UnaryOperator<Color>> pendingOperations;

	public static LatentImage from(Image in) {
		LatentImage result = new LatentImage();
		result.in = in;
		result.pendingOperations = new ArrayList<>();
		return result;
	}

	LatentImage transform(UnaryOperator<Color> f) {
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
				for (UnaryOperator<Color> f : this.pendingOperations) {
					c = f.apply(c);
				}
				out.getPixelWriter().setColor(x, y, c);
			}
		}
		return out;
	}
}

public class ImageDemo extends Application {
	@Override
	public void start(Stage stage) {
		Image image = new Image("eiffel-tower.jpg");
		Image finalImage = LatentImage.from(image).transform(Color::brighter).transform(Color::grayscale).toImage();
		stage.setScene(new Scene(new HBox(new ImageView(image), new ImageView(finalImage))));
		stage.show();
	}
}
