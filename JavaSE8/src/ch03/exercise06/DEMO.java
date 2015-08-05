package ch03.exercise06;

import java.util.Objects;
import java.util.function.BiFunction;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

//3.4節「関数を返す」で見た次のメソッドを完成させなさい.

public class DEMO extends Application {
	public static void main(String[] args) {
		new DEMO().start(new Stage());
	}

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
		return in;
	}

	@Override
	public void start(Stage stage) {
		Image image = new Image("queen-mary.png");

		stage.setScene(new Scene(new HBox(new ImageView(image))));
		stage.show();
	}

}
