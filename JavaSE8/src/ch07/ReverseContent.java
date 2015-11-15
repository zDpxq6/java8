package ch07;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

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

	public static void main(String[] args) {
		try {
			byte[] reversed = reverse(Files.readAllBytes(Paths.get("./ch09ex05src.txt")));
			Files.write(Paths.get("./ch09ex05dest.txt"), reversed, StandardOpenOption.CREATE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
