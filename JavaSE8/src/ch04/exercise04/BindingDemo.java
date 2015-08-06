package ch04.exercise04;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/*4.
91ページの4.5節「バインディング」のプログラムについて,
円が真ん中に配置され, シーンの4辺の少なくとも2つの辺に常に接するように機能拡張しなさい.
*/

public class BindingDemo extends Application {
	private static final double X = 100;
	private static final double Y = 100;
	private static final double RADIUS = 100;
	private static final Color CIRCLE_COLOR = Color.RED;
	@Override
	public void start(Stage stage) {
		Circle circle = new Circle(X, Y, RADIUS);
		circle.setFill(CIRCLE_COLOR);
		Pane pane = new Pane();
		pane.getChildren().add(circle);
		Scene scene = new Scene(pane);
		circle.radiusProperty().bind(Bindings.divide(scene.widthProperty(), 2));
		circle.centerXProperty().bind(Bindings.divide(scene.widthProperty(), 2));
		circle.centerYProperty().bind(Bindings.divide(scene.heightProperty(), 2));
		stage.setScene(scene);
		stage.show();
	}
}
