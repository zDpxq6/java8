package ch08.ex11;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;

public class Demo {
	public static InputStream connect(URL url, String username, String password) throws IOException {
		byte[] bytes = new String(username + ":" + password).getBytes();
		URLConnection connection = url.openConnection();
		Base64.Encoder encoder = Base64.getEncoder();
		connection.setRequestProperty("Authorization", "Basic " + encoder.encodeToString(bytes));
		connection.connect();
		return connection.getInputStream();
	}
}
