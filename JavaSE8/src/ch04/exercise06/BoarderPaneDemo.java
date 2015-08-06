package ch04.exercise06;

/*6.
図4-7のTopとBottomのボタンを真ん中にそろえなさい.
*/
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class BoarderPaneDemo extends Application {
	@Override
	public void start(Stage stage) {
		BorderPane pane = new BorderPane();
		Button top = new Button("Top");
		pane.setTop(top);
		BorderPane.setAlignment(top, javafx.geometry.Pos.CENTER);

		pane.setLeft(new Button("Left"));
		pane.setCenter(new Button("Center"));
		pane.setRight(new Button("Right"));

		Button bottom = new Button("Bottom");
		pane.setBottom(bottom);
		BorderPane.setAlignment(bottom, javafx.geometry.Pos.CENTER);

		stage.setScene(new Scene(pane));
		stage.show();
	}
}
