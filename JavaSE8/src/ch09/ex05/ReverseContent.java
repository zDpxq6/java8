package ch09.ex05;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.List;

public class ReverseContent {

	public static void main(String[] args) {
		try {
			List<String> lines = Files.readAllLines(Paths.get("./ch09ex05src.txt"));
			Collections.reverse(lines);
			Files.write(Paths.get("./ch09ex06dest.txt"), lines, StandardOpenOption.CREATE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
