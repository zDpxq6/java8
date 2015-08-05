package ch04.exercise01;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/*1.
テキストフィールドとラベルを持つプログラムを作成しなさい.
「Hello, JavaFX」プログラムと同じように, そのラベルは,
文字列Hello, FXを100ポイントのフォントで表示するようにしなさい.
テキストフィールドを同じ文字列で初期化しなさい.
ユーザがテキストフィールドを編集したらラベルも更新するようにしなさい.
*/
public class TextFieldDemo extends Application {
	@Override
	public void start(Stage stage) {
		Label label = new Label("Hello, JavaFX!");// ラベルの作成
		label.setFont(new Font(100));// フォントの作成

		TextField textField = new TextField("Hello, JavaFX!");// テキストフィールドの作成
		textField.textProperty().addListener(property -> label.setText(textField.getText()));

		VBox root = new VBox();// 縦に並べる箱の作成
		root.getChildren().addAll(textField, label);// 縦につめる

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Hello");
		stage.show();
	}
}
