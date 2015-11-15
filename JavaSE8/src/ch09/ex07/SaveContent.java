package ch09.ex07;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class SaveContent {

	public static void main(String[] args) throws IOException {
		URL url = new URL("https://docs.oracle.com/javase/jp/8/");
		Files.copy(url.openStream(), Paths.get("./ch09ex07dest.txt"), StandardCopyOption.REPLACE_EXISTING);
	}
}
