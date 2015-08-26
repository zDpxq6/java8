package ch03.exercise12;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.UnaryOperator;
import java.util.stream.IntStream;

import ch03.exercise11.ColorTransformer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/* 12. 69ページの3.6節「遅延」のLatentImageを機能拡張して,
 * UnaryOperator<Color>とColorTransformerの両方をサポートするようにしなさい.
 * ヒント: UnaryOperator<Color>をColorTransformerへ適用させなさい.
 */
class LatentImage {
	private final Image image;
	private final List<Optional<ColorTransformer>> pendingOperations = new ArrayList<>();

	private LatentImage(Image image) {
		this.image = image;
	};

	/**
	 * LatentImageのファクトリ
	 *
	 * @param image
	 * @return
	 */
	public static LatentImage from(Image image) {
		Objects.requireNonNull(image, "A parameter image is null.");
		return new LatentImage(image);
	}

	LatentImage transform(UnaryOperator<Color> transformer) {
		this.pendingOperations.add(Optional.of(ColorTransformer.convert(transformer)));
		return this;
	}

	LatentImage transform(ColorTransformer transformer) {
		this.pendingOperations.add(Optional.of(transformer));
		return this;
	}

	public Image toImage() {
		int w = (int) this.image.getWidth();
		int h = (int) this.image.getHeight();
		WritableImage out = new WritableImage(w, h);
		IntStream.range(0, w).forEach(x -> {
			IntStream.range(0, h).forEach(y -> {
				Color c = this.image.getPixelReader().getColor(x, y);
				for (Optional<ColorTransformer> f : this.pendingOperations) {
					if (f.isPresent()) {
						c = f.get().apply(x, y, c);
					}
				}
				out.getPixelWriter().setColor(x, y, c);
			});
		});
		return out;
	}
}

public class Transformer extends Application {
	@Override
	public void start(Stage stage) {
		Image image = new Image("eiffel-tower.jpg");
		Image finalImage = LatentImage.from(image).transform(Color::brighter).transform(Color::grayscale).toImage();
		stage.setScene(new Scene(new HBox(new ImageView(image), new ImageView(finalImage))));
		stage.show();
	}
}
