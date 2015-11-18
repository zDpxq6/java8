package ch09.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Scanner;

public class TryWithoutResource {

	public static void convertLinesToLowerCases(String sourcePath, String destinationPath) {
		Objects.requireNonNull(sourcePath, "An argument is null.");
		Objects.requireNonNull(destinationPath, "An argument is null.");
		Scanner in = null;
		PrintWriter out = null;
		try {
			in = new Scanner(Paths.get("./alice.txt"));
			out = new PrintWriter("./out.txt");
			while (in.hasNext()) {
				out.println(in.next().toLowerCase());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		convertLinesToLowerCases("./alice.txt", "./out.txt");
	}
}
