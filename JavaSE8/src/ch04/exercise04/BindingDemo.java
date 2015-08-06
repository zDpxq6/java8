package ch04.exercise04;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*4.
91ページの4.5節「バインディング」のプログラムについて,
円が真ん中に配置され, シーンの4辺の少なくとも2つの辺に常に接するように機能拡張しなさい.
*/
public class BindingDemo extends Application {
	@Override
	public void start(Stage stage) {
		TextArea shipping = new TextArea();
		TextArea billing = new TextArea();
		billing.textProperty().bindBidirectional(shipping.textProperty());
		VBox root = new VBox();
		root.getChildren().addAll(new Label("Shipping"), shipping, new Label("Billing"), billing);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}
