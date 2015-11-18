package ch09.ex06;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Objects;

public class ReverseContent {

	private static final byte[] reverse(byte[] src) {
		byte[] result = src.clone();
		final int len = result.length;
		byte tmp;
		for (int i = 0; i < len / 2; i++) {
			tmp = result[i];
			result[i] = result[len - 1 - i];
			result[len - 1 - i] = tmp;
		}
		return result;
	}

	public static void reverseFileContents(String sourcePath, String destinationPath) throws IOException {
		Objects.requireNonNull(sourcePath, "sourcePath is null");
		Objects.requireNonNull(destinationPath, "destinationPath is null");
		byte[] reversed = reverse(Files.readAllBytes(Paths.get(sourcePath)));
		Files.write(Paths.get(destinationPath), reversed, StandardOpenOption.CREATE);

	}

	public static void main(String[] args) {
		try {
			reverseFileContents("./ch09ex05src.txt", "./ch09ex05dest.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
