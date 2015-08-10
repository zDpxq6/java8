package ch04.exercise08;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/*8.
FXMLファイルを解析する際には, JavaFX固有の知識は必要としません.
JavaFXで何もせずに, 入れ子になっているオブジェクトを持つオブジェクトをロードし,
FXML構文でプロパティを設定する例を作成しなさい. 注入を使用できたら, さらによいです.
*/

public class FXMLDemo extends Application implements Initializable {
	@FXML
	private TextField username;
	@FXML
	private PasswordField password;
	@FXML
	private Button okButton;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		this.okButton.disableProperty().bind(Bindings.createBooleanBinding(() -> this.username.getText().length() == 0 || this.password.getText().length() == 0, this.username.textProperty(), this.password.textProperty()));
		this.okButton.setOnAction(event -> System.out.println("Verifying " + this.username.getText() + ":" + this.password.getText()));
	}

	@Override
	public void start(Stage stage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("dialog3.fxml"));
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(0);
		}
	}
}
